package webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.application.APLCadastrarLocacao;
import model.domain.Item;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import model.application.APLCadastrarCliente;
import model.application.APLCadastrarItem;
import model.application.APLRequestFunctions;
import model.domain.Cliente;
import model.domain.Locacao;

@Path("/request")
public class Requisicao {
    
    private ObjectMapper m_mapper;
    private String m_resultString;
    private APLRequestFunctions m_aplRequest;
    
    @PostConstruct
    private void init()
    {
        m_resultString = "";
        m_mapper = new ObjectMapper();
        m_mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        m_aplRequest = new APLRequestFunctions();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("titulos")
    public String listarTitulos()
    {
        m_resultString = "";
        List<Item> result = m_aplRequest.findItems();
        
        try 
        {
            m_resultString = m_mapper.writeValueAsString(result);
        }
        catch (JsonProcessingException ex)
        {
            System.out.println(ex.getMessage());
        }

        return m_resultString;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("informacoesDevolucao")
    public String obterDevolucao(String jsonDevolucao)
    {
        JsonObject obj = new JsonParser().parse(jsonDevolucao).getAsJsonObject();
        Long idItem = obj.get("idItem").getAsLong();
        
        m_resultString = "";
        Locacao result = APLCadastrarLocacao.buscarLocacaoDevolucao(idItem);
        
        try { m_resultString = m_mapper.writeValueAsString(result); }
        catch (JsonProcessingException ex) { }
        
        return m_resultString;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("clientes")
    public String listarClientes()
    {
        m_resultString = "";
        
        // apenas clientes que não possuem nenhuma pendência
        List<Cliente> result = m_aplRequest.findClientes()
                                .stream()
                                .filter(x-> APLCadastrarCliente.verificarDependencia(x) == false)
                                .collect(Collectors.toList());
        
        try { m_resultString = m_mapper.writeValueAsString(result); }
        catch (JsonProcessingException ex) { }

        return m_resultString;
    }
    
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("devolucao")
    public String inserirDevolucao(String jsonDevolucao)
    {
       JsonObject obj = new JsonParser().parse(jsonDevolucao).getAsJsonObject();
       Long idItem = obj.get("idItem").getAsLong();
       
       switch(m_aplRequest.efetuarDevolucao(idItem))
       {
           case 0:
               return "Nenhum item afetado.";
           case -1:
               return "Falha ao efetuar devolução.";
           default:
               return "Devolução efetuada com sucesso.";
       }
    }
    
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("locacao")
    public String inserirLocacao(String jsonLocacao)
    {
        JsonObject obj = new JsonParser().parse(jsonLocacao).getAsJsonObject();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");  

        try
        {
            // locação
            java.sql.Date dataLocacao = new java.sql.Date(formater.parse(obj.get("dataLocacao").getAsString()).getTime());
            
            // devolução
            java.sql.Date dataDevolucao = new java.sql.Date(formater.parse(obj.get("dataDevolucao").getAsString()).getTime());
            
            // valor
            Double valorLocacao = obj.get("valor").getAsDouble();
            
            // cliente
            Cliente clienteLocacao = APLCadastrarCliente.findCliente(obj.get("idCliente").getAsLong());
            
            // item
            Item itemLocacao = APLCadastrarItem.findItem(obj.get("idItem").getAsLong());
            
            APLCadastrarLocacao.inserirLocacao(dataLocacao, dataDevolucao, valorLocacao, clienteLocacao, itemLocacao);
            return "Locação inserida com sucesso.";
        }
        
        catch(Exception ex)
        {
            return "Falha na requisição.";
        }
    }
}
