package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.application.APLLoginUsuario;
import model.domain.ReturnType;

public class SRVLLoginUsuario extends HttpServlet
{
   // public String logado = "FAIL";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter())
        {
            String funcaoSair = request.getParameter("funcaoSair");
            
            if(funcaoSair != null && funcaoSair.equals("1"))
                executarLogout(request, response);
            else
                verificarLogin(request, response);
        }
    }
    
    private void executarLogout(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        request.getSession().setAttribute("logado", "FAIL");
        ativarMensagemReload("Logout efetuado com sucesso.", "info", request);
        response.sendRedirect(request.getContextPath() + "/login/login.jsp");
    }

    private void verificarLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        request.getSession().setAttribute("logado", "FAIL");

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        if(email.equals(""))
            ativarMensagemReload("Falha entrar. Insira um email.", "danger", request);
        
        else if(senha.equals(""))
            ativarMensagemReload("Falha entrar. Insira uma senha.", "danger", request);
        
        else
        {
            ReturnType result = APLLoginUsuario.verificarUsuario(email, senha);
            
            if(result == ReturnType.SUCESSO)
            {
                request.getSession().setAttribute("logado", "OK");
                ativarMensagemReload("Logado com sucesso.", "info", request);
            }
            else
                ativarMensagemReload("Falha ao entrar. Credenciais inválidas.", "danger", request);
        }
        
        if(request.getSession().getAttribute("logado").equals("FAIL"))
            response.sendRedirect(request.getContextPath() + "/login/login.jsp");
        else
            response.sendRedirect("http://localhost:8080/Projeto/admin/index.jsp");
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
