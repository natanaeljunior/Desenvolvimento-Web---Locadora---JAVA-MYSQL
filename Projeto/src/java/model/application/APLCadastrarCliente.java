package model.application;

import dao.GenericDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import model.domain.Cliente;
import model.domain.Dependente;
import model.domain.Locacao;
import model.domain.ReturnType;
import model.domain.Socio;
import model.domain.Titulo;
import org.hibernate.Hibernate;

public class APLCadastrarCliente
{
    private static GenericDao<Cliente> m_daoInstance;
    
    private static GenericDao<Cliente> getDaoInstance()
    {
        if(m_daoInstance == null)
            m_daoInstance = new GenericDao<Cliente>();
        
        return m_daoInstance;
    }
    
    public static ReturnType deletarAtor(String idCliente)
    {
        if(idCliente.equals(""))
          return ReturnType.ERRO;
        
        Long id = Long.parseLong(idCliente);
        
        if(id == null)
            return ReturnType.ERRO;
        
        if(getDaoInstance().findById(Cliente.class, id) == null)
            return ReturnType.ERRO;
        
        getDaoInstance().remove(Cliente.class, id);

        return ReturnType.SUCESSO;
    }
    
    public static List<Cliente> findClients()
    {
        return getDaoInstance().findQuery(Cliente.class);
    }
    
    public static Cliente findCliente(Long idCliente)
    {
        return getDaoInstance().findById(Cliente.class, idCliente);
    }
    
    public static boolean verificarDependencia(Cliente cliente)
    {
        List<Locacao> locacoes = APLCadastrarLocacao.findLocations();
        
        if (locacoes.stream().anyMatch((l) -> (Objects.equals(l.getCliente().getId(), cliente.getId()) && l.getDtDevolucaoEfetiva() == null)))
            return true;
        
        if(cliente.getClass().getSimpleName().equals("Dependente"))
        {
            List<Dependente> listaDependentes = new ArrayList(((Dependente)cliente).getSocio().getDependentes()); 
         
            if (listaDependentes.stream().anyMatch((d) -> (locacoes.stream().anyMatch((l) -> (Objects.equals(l.getCliente().getId(), d.getId()) && l.getDtDevolucaoEfetiva() == null)))))
                return true;
            
            if (locacoes.stream().anyMatch((l) -> (Objects.equals(l.getCliente().getId(), ((Dependente)cliente).getSocio().getId()))))
                return true;
        }
        
        // Caso o cliente for um sócio, verifica-se as locações dele e de seus dependentes
        if(cliente.getClass().getSimpleName().equals("Socio"))
        {
            Hibernate.initialize(((Socio)cliente).getDependentes());
            
            if(((Socio)cliente).getDependentes() != null)
            {
                List<Dependente> listaDependentes = new ArrayList(((Socio)cliente).getDependentes()); //APLCadastrarDependente.getDependentesSocio(idCliente);
            
                if (listaDependentes.stream().anyMatch((d) -> (locacoes.stream().anyMatch((l) -> (Objects.equals(l.getCliente().getId(), d.getId()) && l.getDtDevolucaoEfetiva() == null)))))
                    return true;
                else
                    return false;
            }
            else
                return false;
        }
        
        return false;
    }
    
    public static String findTCliente(Long idCliente)
    {
        Socio s = APLCadastrarSocio.findSocioId(idCliente);
        
        if(s != null)
            return "Sócio";
        
        Dependente d  = APLCadastrarDependente.findDependenteId(idCliente);
        
        if(d != null)
            return "Dependente";
        
        return  "Alien";
    }
    
    public static int findTClienteInt(Long idCliente)
    {
        Socio sit = APLCadastrarSocio.findSocioId(idCliente);
        
        if(sit != null)
            return 0;
        
        Dependente dit  = APLCadastrarDependente.findDependenteId(idCliente);
        
        if(dit != null)
            return 1;
        
        return -1;
    }   
}
