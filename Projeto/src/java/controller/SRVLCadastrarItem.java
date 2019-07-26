package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.application.APLCadastrarItem;
import model.application.APLCadastrarTitulo;
import model.domain.ReturnType;
import model.domain.TipoItem;
import model.domain.Titulo;

public class SRVLCadastrarItem extends HttpServlet
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
            out.println("<title>Servlet SRVLCadastrarItem</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SRVLCadastrarItem at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private void executarAcoes(String operacao, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException
    {
        switch(operacao)
        {
            case "inserirItem":
                if(inserirItem(request, (long)0) == ReturnType.SUCESSO)
                    ativarMensagemReload("Item inserido com sucesso.", "info", request);
                else
                    ativarMensagemReload("Falha ao inserir socio.", "danger", request);
            break;
        
            case "removerItem": 
                ReturnType resultConfirm = APLCadastrarItem.deletarItem(request.getParameter("id"));

                if(resultConfirm == ReturnType.SUCESSO)
                    ativarMensagemReload("Item removido com sucesso.", "warning", request);
                else if(resultConfirm == ReturnType.ITEM_LINKADO)
                    ativarMensagemReload("Falha ao remover. Existem locações linkadas a este item.", "danger", request);
                else
                    ativarMensagemReload("Falha ao remover item. Erro inesperado.", "danger", request);
            break;
            
             case "alterarItem":
                 ReturnType resultEdit = inserirItem(request, Long.parseLong(request.getParameter("id")));
                                
                if(resultEdit == ReturnType.SUCESSO)
                    ativarMensagemReload("Item alterado com sucesso.", "info", request);
                else
                    ativarMensagemReload("Falha ao alterar Item.", "danger", request);
            break;

        }
        
        response.sendRedirect(request.getContextPath() + "/admin/item.jsp");
    }
    
    private void ativarMensagemReload(String mensagem, String type, HttpServletRequest request)
    {
        request.getSession().setAttribute("isReloadFunction", true);
        request.getSession().setAttribute("messageReload", mensagem);
        request.getSession().setAttribute("typeFunctionReload", type);
    }
    
    private ReturnType inserirItem(HttpServletRequest request, Long idItem) throws ParseException
    {
        String numSerie = request.getParameter("nSerie");
        
        if (numSerie == null || numSerie.isEmpty())
            return ReturnType.ERRO;
        
        Date dataAquisicao = conversaoData(request.getParameter("dtAquisicao"));
        
        if (dataAquisicao == null)
            return ReturnType.ERRO;

        String tipoItem = request.getParameter("tipoMidia");
        
        if (tipoItem == null)
            return ReturnType.ERRO;
        
        TipoItem tipo = TipoItem.values()[Integer.parseInt(tipoItem)];
        
        String titulo = request.getParameter("idTitulo");
        
        if (titulo == null)
            return ReturnType.ERRO;
        
        Long idTitulo = Long.parseLong(titulo);
        
        Titulo tituloItem = APLCadastrarTitulo.findTitle(idTitulo);
        
        
        APLCadastrarItem.inserirItem(numSerie,dataAquisicao, tipo, tituloItem, idItem);
        
        return ReturnType.SUCESSO;
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
         try {
             processRequest(request, response);
         } catch (ParseException ex) {
             Logger.getLogger(SRVLCadastrarItem.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
         try {
             processRequest(request, response);
         } catch (ParseException ex) {
             Logger.getLogger(SRVLCadastrarItem.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
