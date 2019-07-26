package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.application.APLCadastrarLocacao;
import static model.application.APLCadastrarLocacao.inserirLocacao;
import model.domain.Locacao;
import model.domain.ReturnType;

public class SRVLCadastrarDevolucao extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter())
        {
            executarAcoes(request.getParameter("operacao"), request, response);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SRVLCadastrarDevolucao</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SRVLCadastrarDevolucao at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private void executarAcoes(String operacao, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        switch(operacao)
        {  
            case "realizarDevolucao":
                ReturnType resultInsert = inserirDevolucao(request);

                if(resultInsert == ReturnType.SUCESSO)
                    ativarMensagemReload("Devolução efetuada com sucesso.", "info", request);
                else
                    ativarMensagemReload("Falha ao efetuar devolução.", "danger", request);
            break;
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/devolucao.jsp");
    }
    
    private void ativarMensagemReload(String mensagem, String type, HttpServletRequest request)
    {
        request.getSession().setAttribute("isReloadFunction", true);
        request.getSession().setAttribute("messageReload", mensagem);
        request.getSession().setAttribute("typeFunctionReload", type);
    }

    private ReturnType inserirDevolucao(HttpServletRequest request)
    {
        String locacao = request.getParameter("id");
        
        if(locacao == null)
            return ReturnType.ERRO;
      
        Locacao objLocacao =  APLCadastrarLocacao.findLocacao(Long.parseLong(locacao));
        APLCadastrarLocacao.efetuarDevolucao(objLocacao);
        
        return ReturnType.SUCESSO;
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
