package model.application;

import dao.GenericDao;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import model.domain.Item;
import model.domain.Locacao;
import model.domain.ReturnType;
import model.domain.TipoItem;
import model.domain.Titulo;

public class APLCadastrarItem
{
    private static GenericDao<Item> m_daoInstance;
    
    private static GenericDao<Item> getDaoInstance()
    {
        if(m_daoInstance == null)
            m_daoInstance = new GenericDao<Item>();
        
        return m_daoInstance;
    }
        
    public static ReturnType inserirItem(String numSerie, Date dataAquisicao, TipoItem tipoItem, Titulo titulo, Long idItem) throws ParseException
    {   
        Item itemInsercao = criarItem(numSerie,dataAquisicao, tipoItem, titulo, idItem);
        if(itemInsercao == null)
            return ReturnType.ERRO;
        
        getDaoInstance().saveOrUpdate(itemInsercao);
        
        return ReturnType.SUCESSO;
    }
   
    public static ReturnType deletarItem(String idItem)
    {
        if(idItem.equals(""))
          return ReturnType.ERRO;
        
        Long id = Long.parseLong(idItem);
        
        if(id == null)
            return ReturnType.ERRO;
        
        ReturnType typeVerificao = verificarExclusao(id);
        
        if(typeVerificao != ReturnType.SUCESSO)
            return typeVerificao;
        
        if(getDaoInstance().findById(Item.class, id) == null)
            return ReturnType.ERRO; 
        
        getDaoInstance().remove(Item.class, id);

        return ReturnType.SUCESSO;
    }
    
    private static ReturnType verificarExclusao(Long idItem)
    {
        List<Locacao> locacoes = APLCadastrarLocacao.findLocations();
        
        for(Locacao l : locacoes)
        {
            if(Objects.equals(l.getItem().getId(), idItem))
                return ReturnType.ITEM_LINKADO;
        }
        
        return ReturnType.SUCESSO;
    }
    
    private static Item criarItem(String numSerie, Date dataAquisicao, TipoItem tipoItem, Titulo titulo, Long idItem)
    {
        return new Item(idItem, Integer.parseInt(numSerie), dataAquisicao,tipoItem, titulo, false);
    }

    public static void alterarEstadoLocacao(Item item, boolean estado)
    {
        item.setItemLocado(estado);
        getDaoInstance().saveOrUpdate(item);
    }
    
    public static List<Item> findItems()
    {
        return getDaoInstance().findQuery(Item.class);
    }
    
    public static Item findItem(Long idItem)
    {
        return getDaoInstance().findById(Item.class, idItem);
    }
}
