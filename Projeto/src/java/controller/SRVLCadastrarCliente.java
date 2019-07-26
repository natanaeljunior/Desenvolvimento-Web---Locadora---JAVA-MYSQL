package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.application.APLCadastrarDependente;
import model.application.APLCadastrarSocio;
import model.domain.ReturnType;
import model.domain.Sexo;
import model.domain.Socio;

@WebServlet(name = "SRVLCadastrarCliente", urlPatterns = {"/SRVLCadastrarCliente"})
public class SRVLCadastrarCliente extends HttpServlet
{
    private final SimpleDateFormat m_dataFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException 
    {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter())
        {
            executarAcoes(request.getParameter("operacao"), request, response);
                        
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SRVLCadastrarCliente</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SRVLCadastrarCliente at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private void executarAcoes(String operacao, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException
    {
        switch(operacao)
        {
            case "inserirCliente":
              
                if(request.getParameter("tipoCliente").equals("Socio"))
                {
                    if(inserirSocio(request) == ReturnType.SUCESSO)
                        ativarMensagemReload("Socio inserido com sucesso.", "info", request);
                    else
                        ativarMensagemReload("Falha ao inserir socio.", "danger", request);
                }
                
                else if(request.getParameter("tipoCliente").equals("Dependente"))
                {
                    if(inserirDependente(request) == ReturnType.SUCESSO)
                        ativarMensagemReload("Dependente inserido com sucesso.", "info", request);
                    else
                        ativarMensagemReload("Falha ao inserir dependente.", "danger", request);
                }
            break;
            
            case "removerCliente":
              
                if(request.getParameter("tipoCliente").equals("0"))
                {
                    ReturnType resultConfirm = APLCadastrarSocio.deletarSocio(request.getParameter("id"));

                    if(resultConfirm == ReturnType.SUCESSO)
                        ativarMensagemReload("Socio removido com sucesso.", "warning", request);
                    else if(resultConfirm == ReturnType.CLIENTE_LINKADO)
                        ativarMensagemReload("Falha ao remover. Existem locações linkadas a este cliente.", "danger", request);
                    else
                        ativarMensagemReload("Falha ao remover cliente. Erro inesperado.", "danger", request);
                }
                
                else if(request.getParameter("tipoCliente").equals("1"))
                {
                    ReturnType resultConfirm = APLCadastrarDependente.deletarDependente(request.getParameter("id"));

                    if( resultConfirm == ReturnType.SUCESSO)
                        ativarMensagemReload("Dependente removido com sucesso.", "warning", request);    
                    else
                        ativarMensagemReload("Falha ao remover dependente.", "danger", request);
                }
            break;
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/cliente.jsp");
    }
    
    private ReturnType inserirDependente(HttpServletRequest request) throws ParseException
    {
        String nome = request.getParameter("nome");
        
        if(nome == null || nome.isEmpty())
            return ReturnType.ERRO;
        
        Date dataNascimento = conversaoData(request.getParameter("nascimento"));
        
        if(dataNascimento == null)
            return ReturnType.ERRO;
        
        String sexo = request.getParameter("sexo");
        
        if(sexo == null || sexo.isEmpty())
            return ReturnType.ERRO;
        
        Sexo sexoType = sexo.equals("Masculino") ? Sexo.MASCULINO : Sexo.FEMININO;
        
        String idSocio = request.getParameter("idSocio");
        
        if(idSocio == null || idSocio.isEmpty())
            return ReturnType.ERRO;
        
        String array[] = new String[3];
        array = idSocio.split("-");
        
        Socio socio = APLCadastrarSocio.findSocioId(Long.parseLong(array[0]));
        
        if(socio == null)
            return ReturnType.ERRO;
        
        APLCadastrarDependente.inserirDepedente(nome, dataNascimento, sexoType, socio);
        
        return ReturnType.SUCESSO;
    }
    
    private ReturnType inserirSocio(HttpServletRequest request) throws ParseException
    {
        String nome = request.getParameter("nome");
        
        if(nome == null || nome.isEmpty())
            return ReturnType.ERRO;
        
        Date dataNascimento = conversaoData(request.getParameter("nascimento"));
        
        if(dataNascimento == null)
            return ReturnType.ERRO;
        
        String sexo = request.getParameter("sexo");
        
        if(sexo == null || sexo.isEmpty())
            return ReturnType.ERRO;
        
        Sexo sexoType = sexo.equals("Masculino") ? Sexo.MASCULINO : Sexo.FEMININO;
        
        String endereco = request.getParameter("endereco");
        
        if(endereco == null || endereco.isEmpty())
            return ReturnType.ERRO;
        
        String telefone = request.getParameter("telefone");
        
        if(telefone == null || telefone.isEmpty())
            return ReturnType.ERRO;
        
        String cpf = request.getParameter("cpf");
        
        if(cpf == null || cpf.isEmpty())
            return ReturnType.ERRO;
        
        APLCadastrarSocio.inserirSocio(nome, dataNascimento, sexoType, endereco, telefone, cpf);
        
        return ReturnType.SUCESSO;
    }
    
    private void ativarMensagemReload(String mensagem, String type, HttpServletRequest request)
    {
        request.getSession().setAttribute("isReloadFunction", true);
        request.getSession().setAttribute("messageReload", mensagem);
        request.getSession().setAttribute("typeFunctionReload", type);
    }
    
    private Date conversaoData(String dataClasse) throws ParseException
    {
        if(dataClasse.isEmpty() || dataClasse.equals(""))
            return null;
        
        return new Date(m_dataFormat.parse(dataClasse).getTime()); 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        }
        
        catch (ParseException ex)
        {
            Logger.getLogger(SRVLCadastrarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        } 
        catch (ParseException ex)
        {
            Logger.getLogger(SRVLCadastrarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
