package dao;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import javax.persistence.criteria.CriteriaQuery;
import model.domain.IEntityBase;
import model.domain.Titulo;
import org.hibernate.Cache;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.TimestampType;

public class GenericDao <T extends IEntityBase>
{
    private static Session m_daoManager = HibernateUtil.getSessionFactory().getCurrentSession();
    
    public T findById(Class<T> findClass, Long id)
    {
        T returnType = null;
        
        try
        {
            if(!m_daoManager.isOpen())
                m_daoManager = HibernateUtil.getSessionFactory().openSession();
        
            m_daoManager.getTransaction().begin();
            clearCache();
            
            returnType = (T)m_daoManager.get(findClass, id);
        }
        
        catch(Exception ex) { m_daoManager.getTransaction().rollback(); }
        finally { m_daoManager.getTransaction().commit(); }
        
        return returnType;
    }
    
    public List<T> findQuery(Class<T> entityClass)
    {
        List<T> m_internalList =  new ArrayList<>();
        
        try
        {
            if(!m_daoManager.isOpen())
                m_daoManager = HibernateUtil.getSessionFactory().openSession();

            m_daoManager.getTransaction().begin();
            clearCache();
            
            m_internalList = m_daoManager.createCriteria(entityClass).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            m_daoManager.getTransaction().commit();
        }
        catch(Exception e)
        {
            m_daoManager.getTransaction().rollback();
        }
                
        return m_internalList;
    }
    
    public List<T> findQuery(String queryString, Class<T> entityClass)
    {
        List<T> m_internalList =  new ArrayList<>();
        
        try
        {
            if(!m_daoManager.isOpen())
                m_daoManager = HibernateUtil.getSessionFactory().openSession();
            
            m_daoManager.getTransaction().begin();
            m_internalList = m_daoManager.createQuery(queryString).list();
            
            m_daoManager.getTransaction().commit();
        }
        catch(Exception e)
        {
            m_daoManager.getTransaction().rollback();
        }
                
        return m_internalList;
    }
    
    public void saveOrUpdate(T obj)
    {
        try
        {
            if(!m_daoManager.isOpen())
                m_daoManager = HibernateUtil.getSessionFactory().openSession();
                        
            m_daoManager.getTransaction().begin();
              
            if(obj.getId() == 0) // salvar
                m_daoManager.save(obj);
            else // atualizar
                m_daoManager.merge(obj);

            m_daoManager.getTransaction().commit();
        }
        catch(Exception e)
        {
            m_daoManager.getTransaction().rollback();
        }
    }
    
    public void remove(Class<T> classRemove, Long id)
    {
        T objetoRemocao = findById(classRemove, id);
        
        try
        {
            m_daoManager.getTransaction().begin();
            m_daoManager.delete(objetoRemocao);
            m_daoManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            m_daoManager.getTransaction().rollback();
        }
    }
    
    private void clearCache()
    {
        if (m_daoManager != null)
            m_daoManager.clear();

        Cache cache = m_daoManager.getSessionFactory().getCache();

        if(cache != null)
            cache.evictAllRegions();
    }
    
    public List<T> findPagination( int page, int total, Class<T> entityClass)
    {
        
        List<T> m_internalList =  new ArrayList<>();
        
        try
        {
            if(!m_daoManager.isOpen())
                m_daoManager = HibernateUtil.getSessionFactory().openSession();
            
            m_daoManager.getTransaction().begin();
            m_internalList = m_daoManager.createQuery("from Titulo")
                    .setMaxResults(total)
                    .setFirstResult(page-1)
                    .list();
            
            m_daoManager.getTransaction().commit();
        }
        catch(Exception e)
        {
            m_daoManager.getTransaction().rollback();
        }
                
        return m_internalList;
    }
    
    
}
