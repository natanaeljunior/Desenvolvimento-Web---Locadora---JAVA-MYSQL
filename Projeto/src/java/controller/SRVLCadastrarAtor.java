package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.application.APLCadastrarAtor;
import model.domain.ReturnType;

public class SRVLCadastrarAtor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter())
        {
            executarAcoes(request.getParameter("operacao"), request, response);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SRVLCadastrarAtor</title>" + request.getParameter("nome"));            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SRVLCadastrarAtor at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private void executarAcoes(String operacao, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        switch(operacao)
        {
            case "inserirator":
                ReturnType resultInsert = APLCadastrarAtor.inserirAtor(request.getParameter("nome"), (long)0);
                
                if(resultInsert == ReturnType.SUCESSO)
                    ativarMensagemReload("Ator inserido com sucesso.", "info", request);
                else
                    ativarMensagemReload("Falha ao inserir ator.", "danger", request);
            break;    
            
            case "removerAtor":                
                ReturnType resultRemove = APLCadastrarAtor.deletarAtor(request.getParameter("id"));

                if(resultRemove == ReturnType.SUCESSO)
                    ativarMensagemReload("Ator removido com sucesso.", "warning", request);
                else if(resultRemove == ReturnType.ATOR_LINKADO)
                    ativarMensagemReload("Falha ao remover. Existem títulos linkados a este ator.", "danger", request);
                else
                    ativarMensagemReload("Falha ao remover ator. Erro inesperado.", "danger", request);
            break;
            
            case "alterarAtor":
               ReturnType resultEdit = APLCadastrarAtor.inserirAtor(request.getParameter("nome"), Long.parseLong(request.getParameter("id")));

               if(resultEdit == ReturnType.SUCESSO)
                   ativarMensagemReload("Ator alterado com sucesso.", "info", request);
               else
                   ativarMensagemReload("Falha ao alterar ator.", "danger", request);
            break;  
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/ator.jsp");
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
