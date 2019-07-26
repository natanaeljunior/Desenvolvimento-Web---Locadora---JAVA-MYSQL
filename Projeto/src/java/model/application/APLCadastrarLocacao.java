package model.application;

import dao.GenericDao;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.domain.Cliente;
import model.domain.Item;
import model.domain.Locacao;
import model.domain.ReturnType;
import org.hibernate.Hibernate;

public class APLCadastrarLocacao
{
    private static GenericDao<Locacao> m_daoInstance;
    
    private static GenericDao<Locacao> getDaoInstance()
    {
        if(m_daoInstance == null)
            m_daoInstance = new GenericDao<Locacao>();
        
        return m_daoInstance;
    }
      
    public static ReturnType inserirLocacao(Date dtLocacao, Date dtDevolucaoPrevista, double vlrLocacao, Cliente obj_cliente, Item obj_item)
    {
        Locacao LocacaoInsercao = criarLocacao(dtLocacao, dtDevolucaoPrevista, vlrLocacao, obj_cliente, obj_item);
        
        if(LocacaoInsercao == null)
            return ReturnType.ERRO;
        
        obj_item.setItemLocado(true);
        
        getDaoInstance().saveOrUpdate(LocacaoInsercao);
        
        return ReturnType.SUCESSO;
    }
    
    public static ReturnType deletarLocacao(String idLocacao)
    {
        if(idLocacao.equals(""))
          return ReturnType.ERRO;
        
        Long id = Long.parseLong(idLocacao);
        
        if(id == null)
            return ReturnType.ERRO;
        
        if(getDaoInstance().findById(Locacao.class, id) == null)
            return ReturnType.ERRO;
        
        ReturnType typeVerificao = verificarExclusao(id);
        
        if(typeVerificao != ReturnType.SUCESSO)
            return typeVerificao;
        
        removerLocacaoItem(id);
        getDaoInstance().remove(Locacao.class, id);

        return ReturnType.SUCESSO;
    }
    
    private static ReturnType verificarExclusao(Long idLocacao)
    {
        Locacao locacao = APLCadastrarLocacao.findLocacao(idLocacao);
        
        if(locacao.getDtDevolucaoEfetiva() != null)
            return ReturnType.LOCACAO_PAGA;
        
        return ReturnType.SUCESSO;
    }
    
    private static void removerLocacaoItem(Long idLocacao)
    {
        APLCadastrarItem.alterarEstadoLocacao(findLocacao(idLocacao).getItem(), false);
    }
    
    private static void removerLocacaoItem(Item itemLocacao)
    {
        APLCadastrarItem.alterarEstadoLocacao(itemLocacao, false);
    }
    
    private static Locacao criarLocacao(Date dtLocacao, Date dtDevolucaoPrevista, double vlrLocacao, Cliente obj_cliente, Item item)
    {
        return new Locacao(Long.valueOf(0), dtLocacao, dtDevolucaoPrevista,vlrLocacao, obj_cliente, item);
    }
    
    //inserir data de devolução efetiva
    //remover estado locado do item
    public static void efetuarDevolucao(Locacao loc)
    {
        loc.setDtDevolucaoEfetiva(new java.sql.Date(new java.util.Date().getTime()));
        removerLocacaoItem(loc.getItem());
    }
    
    public static Locacao buscarLocacaoDevolucao(Long idItem)
    {
        return buscarLocacoesDevolucao().stream().filter(x -> x.getItem().getId() == idItem).findFirst().get();
    }
    
    //buscar apenas as locações onde ainda não foi realizada devolução
    //verificar quais destas locações estão fora do prazo de pagamento e acrescentar à multa (10 * total de dias)
    public static List<Locacao> buscarLocacoesDevolucao()
    {
        List<Locacao> locacoesRetorno = new ArrayList<>();
        
        for(Locacao loc : findLocations())
        {
            // locação pendente
            if(loc.getDtDevolucaoEfetiva() == null)
            {
                int diasMulta = calcularDiferencaDias(loc.getDtDevolucaoPrevista());
                
                // locação com multa
                if(diasMulta > 0)
                    atribuirMulta(loc, diasMulta);
                
                locacoesRetorno.add(loc);
            }
        }
        
        return locacoesRetorno;
    }
    
    private static void atribuirMulta(Locacao locacao, Integer diasMulta)
    {
        if(locacao.getMultaCobrada() > 0)
            return;
        
        locacao.setMultaCobrada(diasMulta * 10);
        getDaoInstance().saveOrUpdate(locacao);
    }
    
    private static int calcularDiferencaDias(Date dataInicial)
    {
        Calendar calendarioDataUm = Calendar.getInstance();
        Calendar calendarioDataDois = Calendar.getInstance();
        
        calendarioDataUm.setTime(dataInicial);
        calendarioDataDois.setTime(new java.util.Date()); 
        
        return calendarioDataDois.get(Calendar.DAY_OF_YEAR) - calendarioDataUm.get(Calendar.DAY_OF_YEAR);
    }
    
    public static List<Locacao> findLocations()
    {
        return getDaoInstance().findQuery(Locacao.class);
    }
    
    public static Locacao findLocacao(Long idLocacao)
    {
        return getDaoInstance().findById(Locacao.class, idLocacao);
    }
}
