package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.application.APLCadastrarDiretor;
import model.domain.ReturnType;

@WebServlet(name = "SRVLCadastrarDiretor", urlPatterns = {"/SRVLCadastrarDiretor"})
public class SRVLCadastrarDiretor extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter())
        {
            executarAcoes(request.getParameter("operacao"), request.getParameter("nome"), request, response);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SRVLCadastrarDiretor</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SRVLCadastrarDiretor at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private void executarAcoes(String operacao, String nomeDiretor, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        switch(operacao)
        {
            case "inserirDiretor":
                ReturnType resultInsert = APLCadastrarDiretor.inserirDiretor(nomeDiretor, (long)0);
                
                if(resultInsert == ReturnType.SUCESSO)
                    ativarMensagemReload("Diretor inserido com sucesso.", "info", request);
                else
                    ativarMensagemReload("Falha ao inserir diretor.", "danger", request);
            break;
            
            case "removerDiretor": 
                ReturnType resultRemove = APLCadastrarDiretor.deletarDiretor(request.getParameter("id"));

                if(resultRemove == ReturnType.SUCESSO)
                    ativarMensagemReload("Diretor removido com sucesso.", "warning", request);
                else if(resultRemove == ReturnType.DIRETOR_LINKADO)
                    ativarMensagemReload("Falha ao remover. Existem títulos linkados a este diretor.", "danger", request);
                else
                    ativarMensagemReload("Falha ao remover diretor. Erro inesperado.", "danger", request);
            break;
            
            case "alterarDiretor":
                ReturnType resultEdit = APLCadastrarDiretor.inserirDiretor(nomeDiretor, Long.parseLong(request.getParameter("id")));
                
                if(resultEdit == ReturnType.SUCESSO)
                    ativarMensagemReload("Diretor Alterado com sucesso.", "info", request);
                else
                    ativarMensagemReload("Falha ao Alterar diretor.", "danger", request);
            break;
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/diretor.jsp");  
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
