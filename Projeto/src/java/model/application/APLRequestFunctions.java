package model.application;

import dao.GenericDao;
import java.util.ArrayList;
import java.util.List;
import static model.application.APLCadastrarLocacao.efetuarDevolucao;
import model.domain.Cliente;
import model.domain.Item;
import model.domain.Locacao;
import org.hibernate.Hibernate;

public class APLRequestFunctions
{
    public APLRequestFunctions()
    {
    }
        
    private GenericDao<Item> m_daoInstanceItem;
    
    private GenericDao<Item> getDaoInstanceItem()
    {
        if(m_daoInstanceItem == null)
            m_daoInstanceItem = new GenericDao<>();
        
        return m_daoInstanceItem;
    }
    
    private GenericDao<Locacao> m_daoInstanceLocacao;
    
    private GenericDao<Locacao> getDaoInstanceLocacao()
    {
        if(m_daoInstanceLocacao == null)
            m_daoInstanceLocacao = new GenericDao<>();
        
        return m_daoInstanceLocacao;
    }
    
    private GenericDao<Cliente> m_daoInstanceCliente;
    
    private GenericDao<Cliente> getDaoInstanceCliente()
    {
        if(m_daoInstanceCliente == null)
            m_daoInstanceCliente = new GenericDao<>();
        
        return m_daoInstanceCliente;
    }
    
    // retorna a lista de itens
    public List<Item> findItems()
    {
        return getDaoInstanceItem().findQuery(Item.class);
    }
    
    // retorna a lista de clientes
    public List<Cliente> findClientes()
    {
        return getDaoInstanceCliente().findQuery(Cliente.class);
    }
    
    //efetua a devolução de acordo com um item vindo via request
    public int efetuarDevolucao(Long idItem)
    {        
        Item itemLocacao = getDaoInstanceItem().findById(Item.class, idItem);
        
        // item não encontrado
        if(itemLocacao == null)
            return 0;
                
        Hibernate.initialize(itemLocacao.getLocacoes());
        
        // item sem locações
        if(itemLocacao.getLocacoes() == null)
            return 0;
        
        List<Locacao> locacoesItem = new ArrayList(itemLocacao.getLocacoes());
        
        // nenhuma locação necessária para devolução
        if(locacoesItem.stream().allMatch(x-> x.getDtDevolucaoEfetiva() != null))
            return 0;
        
        // item locado
        itemLocacao.setItemLocado(false);
        
        for(Locacao loc : itemLocacao.getLocacoes())
        {
            if(loc.getDtDevolucaoEfetiva() == null)
            {
                loc.setDtDevolucaoEfetiva(new java.sql.Date(new java.util.Date().getTime()));
                getDaoInstanceLocacao().saveOrUpdate(loc);
            }
        }
        
        // merge nas alterações
        getDaoInstanceItem().saveOrUpdate(itemLocacao);

        return 1;
    }
}
