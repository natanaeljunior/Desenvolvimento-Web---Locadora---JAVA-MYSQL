package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.application.APLCadastrarAtor;
import model.application.APLCadastrarClasse;
import model.application.APLCadastrarDiretor;
import model.application.APLCadastrarTitulo;
import model.domain.Ator;
import model.domain.Categoria;
import model.domain.Classe;
import model.domain.Diretor;
import model.domain.ReturnType;
import org.apache.commons.fileupload.FileItem;

@MultipartConfig(fileSizeThreshold = 1024*1024*2,
            maxFileSize = 1024*1024*10,
            maxRequestSize = 1024*1024*50,
         location="/"
)
public class SRVLCadastrarTitulo extends HttpServlet
{
    private final SimpleDateFormat m_dataFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static  final String SAVE_DIR = "titles";
    private String fileName = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, Exception
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        executarAcoes(request.getParameter("operacao"), request, response);

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet SRVLCadastrarTitulo</title>");            
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet SRVLCadastrarTitulo at " + request.getContextPath() + "</h1>");
        out.println("</body>");
        out.println("</html>");
    }
    
    private void executarAcoes(String operacao, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, ServletException, Exception
    {
        switch(operacao)
        {  
            case "inserirTitulo":
                ReturnType resultInsert = inserirTitulo(request, (long)0 );
                                
                if(resultInsert == ReturnType.SUCESSO)
                    ativarMensagemReload("Título inserido com sucesso.", "info", request);
                else
                    ativarMensagemReload("Falha ao inserir título.", "danger", request);
            break;
            
            case "removerTitulo": 
                ReturnType resultRemove = APLCadastrarTitulo.deletarTitulo(request.getParameter("id"));

                if(resultRemove == ReturnType.SUCESSO)
                    ativarMensagemReload("Título removido com sucesso.", "warning", request);  
                else if(resultRemove == ReturnType.TITULO_LINKADO)
                    ativarMensagemReload("Falha ao remover. Existem itens linkados a este título.", "danger", request);
                else
                    ativarMensagemReload("Falha ao remover título. Erro inesperado.", "danger", request);  
            break;
            
            case "alterarTitulo":
                ReturnType resultEdit = inserirTitulo(request, Long.parseLong(request.getParameter("id")));
                                
                if(resultEdit == ReturnType.SUCESSO)
                    ativarMensagemReload("Título alterado com sucesso.", "info", request);
                else
                    ativarMensagemReload("Falha ao alterar título.", "danger", request);
            break;
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/titulo.jsp");
    }
    
    private void ativarMensagemReload(String mensagem, String type, HttpServletRequest request)
    {
        request.getSession().setAttribute("isReloadFunction", true);
        request.getSession().setAttribute("messageReload", mensagem);
        request.getSession().setAttribute("typeFunctionReload", type);
    }
        
    private ReturnType inserirTitulo(HttpServletRequest request, Long idTitulo) throws ParseException, IOException, ServletException, Exception
    {
        String nome = request.getParameter("nome-titulo");
        
        if(nome == null || nome.isEmpty())
            return ReturnType.ERRO;
        
        Part part = request.getPart("imagem");
        fileName = extractFileName(part);
        
        String uploadFolder = getServletContext().getRealPath("").replace("build", "") 
                             + File.separator + "images" + File.separator + SAVE_DIR;

        File fileDir = new File(uploadFolder);
        File file = new File(fileDir, fileName);

        try (InputStream input = part.getInputStream())
        {
            Files.copy(input, file.toPath());
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        
        String pathImage = "images" + File.separator + SAVE_DIR + File.separator + fileName;
        
        String categoriaFilme = request.getParameter("categoria-selecao");
        
        if(categoriaFilme == null || categoriaFilme.isEmpty())
            return ReturnType.ERRO;
        
        Categoria categoriaTitulo = Categoria.values()[Integer.parseInt(categoriaFilme)];
        
        if(categoriaTitulo == null)
            return ReturnType.ERRO;
        
        String idDiretor = request.getParameter("diretor-selecao");
        
        if(idDiretor == null || idDiretor.isEmpty())
            return ReturnType.ERRO;
        
        Diretor m_diretorTitulo = APLCadastrarDiretor.findDirector(Long.parseLong(idDiretor));
        
        if(m_diretorTitulo == null)
            return ReturnType.ERRO;
        
        String[] idAtor = request.getParameterValues("ator-selecao");
        
        if(idAtor == null)
            return ReturnType.ERRO;
        
        ArrayList<Ator> m_listaAtores = new ArrayList<>();
        
        for(int i = 0; i < idAtor.length; i++)
            m_listaAtores.add(APLCadastrarAtor.findAtor(Long.parseLong(idAtor[i])));
        
        if(m_listaAtores == null)
            return ReturnType.ERRO;
        
        String idClasse = request.getParameter("classe-selecao");
        
        if(idClasse == null || idClasse.isEmpty())
            return ReturnType.ERRO;
        
        Classe m_classeTitulo = APLCadastrarClasse.findClasse(Long.parseLong(idClasse));
        
        if(m_classeTitulo == null)
            return ReturnType.ERRO;
        
        Calendar cal = Calendar.getInstance();
        
        if (idTitulo == 0)
        {
            Date dataFilme = conversaoData(request.getParameter("ano-titulo"));
            
            if (dataFilme == null) 
                return ReturnType.ERRO;
            
            cal.setTime(dataFilme);
        }
        else
        {
            String dataFilme = request.getParameter("ano-titulo");
            
            if (dataFilme == null) 
                return ReturnType.ERRO;
        }
            
        String sinopseFilme = request.getParameter("sinopse-titulo");
        
        if(sinopseFilme == null)
            return ReturnType.ERRO;
        
        APLCadastrarTitulo.inserirTitulo(idTitulo, nome, pathImage, cal.get(Calendar.YEAR), m_listaAtores, m_diretorTitulo, sinopseFilme, categoriaTitulo, m_classeTitulo);
        
        return ReturnType.SUCESSO;
    }
    
    private Date conversaoData(String dataClasse) throws ParseException
    {
        if(dataClasse.isEmpty() || dataClasse.equals(""))
            return null;
        
        return new Date(m_dataFormat.parse(dataClasse).getTime()); 
    }
    
    private String extractFileName(Part part)
    {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        
        for (String s: items)
        {
            if(s.trim().startsWith("filename"))
                return s.substring(s.indexOf("=")+2, s.length()-1);
        }
        
        return "";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try { processRequest(request, response); }
        catch (ParseException ex) { Logger.getLogger(SRVLCadastrarTitulo.class.getName()).log(Level.SEVERE, null, ex); } catch (Exception ex) {
            Logger.getLogger(SRVLCadastrarTitulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try { processRequest(request, response); } catch (Exception ex) {
            Logger.getLogger(SRVLCadastrarTitulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}