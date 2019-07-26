package controller.filtro;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FLTLoginUsuario implements Filter {
    
    private static final boolean debug = true;
    private FilterConfig filterConfig = null;
    
    public FLTLoginUsuario()
    {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException
    {
        if (debug)
            log("FLTLoginUsuario:DoBeforeProcessing");
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException
    {
        if (debug)
            log("FLTLoginUsuario:DoAfterProcessing");
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        try
        {
            String contextPath = ((HttpServletRequest)request).getContextPath();
            HttpSession sessao = ((HttpServletRequest)request).getSession();

            if (sessao == null)
                ((HttpServletResponse)response).sendRedirect(contextPath + "/login/login.jsp");
            else
            {
                String status = (String)sessao.getAttribute("logado");
                
                if (status == null || !status.equals("OK"))
                    ((HttpServletResponse)response).sendRedirect(contextPath + "/login/login.jsp");
                else
                    chain.doFilter(request, response); 
            }
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    }

    public FilterConfig getFilterConfig()
    {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
    }

    public void destroy()
    {        
    }

    public void init(FilterConfig filterConfig)
    {        
        this.filterConfig = filterConfig;
        
        if (filterConfig != null)
        {
            if (debug)              
                log("FLTLoginUsuario:Initializing filter");
        }
    }

    @Override
    public String toString()
    {
        if (filterConfig == null)
            return ("FLTLoginUsuario()");
        
        StringBuffer sb = new StringBuffer("FLTLoginUsuario(");
        sb.append(filterConfig);
        sb.append(")");
        
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response)
    {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals(""))
        {
            try
            {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            }
            catch (Exception ex)
            {
            }
        }
        else
        {
            try
            {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            }
            catch (Exception ex)
            {
            }
        }
    }
    
    public static String getStackTrace(Throwable t)
    {
        String stackTrace = null;
        
        try
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        }
        catch (Exception ex)
        {
        }
        
        return stackTrace;
    }
    
    public void log(String msg)
    {
        filterConfig.getServletContext().log(msg);        
    }
}
