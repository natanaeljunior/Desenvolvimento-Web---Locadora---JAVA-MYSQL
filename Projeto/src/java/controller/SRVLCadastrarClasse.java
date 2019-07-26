package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.application.APLCadastrarClasse;
import model.domain.ReturnType;

@WebServlet(name = "SRVLCadastrarClasse", urlPatterns = {"/SRVLCadastrarClasse"})
public class SRVLCadastrarClasse extends HttpServlet
{
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter())
        { 
            executarAcoes(request.getParameter("operacao"), request.getParameter("nome"), request.getParameter("data"), request.getParameter("valor"), request, response);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SRVLCadastrarClasse</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SRVLCadastrarClasse at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        
        catch(ParseException | IOException ex)
        {
        }
    }
    
    private void executarAcoes(String operacao, String nomeClasse, String dataClasse, String valorClasse, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException
    {
        switch(operacao)
        {
            case "inserirClasse":
                ReturnType resultInsert = APLCadastrarClasse.inserirClasse(nomeClasse, Integer.parseInt(dataClasse), Double.parseDouble(valorClasse), (long)0);
                
                if(resultInsert == ReturnType.SUCESSO)
                    ativarMensagemReload("Classe inserida com sucesso.", "info", request);
                else
                    ativarMensagemReload("Falha ao inserir classe.", "danger", request);
            break;
            
            case "removerClasse":                
                ReturnType resultRemove = APLCadastrarClasse.deletarClasse(request.getParameter("id"));

                if(resultRemove == ReturnType.SUCESSO)
                    ativarMensagemReload("Classe removida com sucesso.", "warning", request);
                else if(resultRemove == ReturnType.CLASSE_LINKADA)
                    ativarMensagemReload("Falha ao remover. Existem títulos linkados a esta classe.", "danger", request);
                else
                    ativarMensagemReload("Falha ao remover classe. Erro inesperado.", "danger", request);
            break;
            
            case "alterarClasse":
                ReturnType resultEdit = APLCadastrarClasse.inserirClasse(nomeClasse, Integer.parseInt(dataClasse), Double.parseDouble(valorClasse), Long.parseLong(request.getParameter("id")));
                
                if(resultEdit == ReturnType.SUCESSO)
                    ativarMensagemReload("Classe alterada com sucesso.", "info", request);
                else
                    ativarMensagemReload("Falha ao alterar classe.", "danger", request);
            break;
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/classe.jsp");  
    }
    
    private void ativarMensagemReload(String mensagem, String type, HttpServletRequest request)
    {
        request.getSession().setAttribute("isReloadFunction", true);
        request.getSession().setAttribute("messageReload", mensagem);
        request.getSession().setAttribute("typeFunctionReload", type);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
