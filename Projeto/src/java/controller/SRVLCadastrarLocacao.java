package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.application.APLCadastrarCliente;
import model.application.APLCadastrarItem;
import model.application.APLCadastrarLocacao;
import model.domain.Cliente;
import model.domain.Item;
import model.domain.ReturnType;

public class SRVLCadastrarLocacao extends HttpServlet
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
            out.println("<title>Servlet SRVLCadastrarLocacao</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SRVLCadastrarLocacao at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private void executarAcoes(String operacao, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException
    {
        switch(operacao)
        {  
              case "inserirLocacao":
                ReturnType resultInsert = inserirLocacao(request);
                
                if(resultInsert == ReturnType.SUCESSO)
                    ativarMensagemReload("Locação realizada com sucesso.", "info", request);
                else if (resultInsert == ReturnType.CLIENTE_PENDENTE)
                    ativarMensagemReload("Falha ao realizar locação. O cliente ou um de seus dependentes possui pendências.", "danger", request);
                else if (resultInsert == ReturnType.ITEM_INDISPONIVEL)
                    ativarMensagemReload("Falha ao realizar locação. Item indisponível.", "danger", request);
                else
                    ativarMensagemReload("Falha ao realizar locação.", "danger", request);
            break;
            
            case "removerLocacao": 
                ReturnType resultRemove = APLCadastrarLocacao.deletarLocacao(request.getParameter("id"));

                if(resultRemove == ReturnType.SUCESSO)
                    ativarMensagemReload("Locação removida com sucesso.", "warning", request);  
                else if(resultRemove == ReturnType.LOCACAO_PAGA)
                    ativarMensagemReload("Falha ao cancelar. Locação já paga.", "danger", request);
                else
                    ativarMensagemReload("Falha ao cancelar locação. Erro inesperado.", "danger", request);  
            break;
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/locacao.jsp");
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
          try {
              processRequest(request, response);
          } catch (ParseException ex) {
              Logger.getLogger(SRVLCadastrarLocacao.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
          try {
              processRequest(request, response);
          } catch (ParseException ex) {
              Logger.getLogger(SRVLCadastrarLocacao.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    private ReturnType inserirLocacao(HttpServletRequest request) throws ParseException
    {
        String cliente = request.getParameter("cliente-selecao");
        
        if(cliente == null)
            return ReturnType.ERRO;
      
        Cliente obj_cliente = APLCadastrarCliente.findCliente(Long.parseLong(cliente));
   
        if(obj_cliente == null)
            return ReturnType.ERRO;
        
        // cliente possui alguma pendência
        if(APLCadastrarCliente.verificarDependencia(obj_cliente))
            return ReturnType.CLIENTE_PENDENTE;
        
        String item = request.getParameter("item-selecao");
        
        if(item == null)
            return ReturnType.ERRO;
        
        Item obj_item = APLCadastrarItem.findItem(Long.parseLong(item));
       
        if(obj_item == null)
            return ReturnType.ERRO;
       
        Date dtDevolucaoPrevista = somarDiasData(new java.util.Date(), obj_item.getTitulo().getClasse().getDataClasse());
        
        if(dtDevolucaoPrevista == null)
            return ReturnType.ERRO;
        
        APLCadastrarLocacao.inserirLocacao(new java.sql.Date(new java.util.Date().getTime()), dtDevolucaoPrevista, obj_item.getTitulo().getClasse().getValorClasse(), obj_cliente, obj_item);
        
        return ReturnType.SUCESSO;
    }
    
    private Date somarDiasData(java.util.Date date, int day)
    {
        Calendar dataAtualPrev = Calendar.getInstance();
        
        dataAtualPrev.setTime(date);
        dataAtualPrev.add(Calendar.DAY_OF_MONTH, day);
        
        return new java.sql.Date(dataAtualPrev.getTime().getTime());
    }
     
    private Date conversaoData(String dataClasse) throws ParseException
    {
        if(dataClasse.isEmpty() || dataClasse.equals(""))
            return null;
        
        return new Date(m_dataFormat.parse(dataClasse).getTime()); 
    }
}
