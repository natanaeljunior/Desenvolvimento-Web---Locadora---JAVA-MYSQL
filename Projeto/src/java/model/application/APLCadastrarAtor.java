package model.application;

import dao.GenericDao;
import java.util.List;
import java.util.Objects;
import model.domain.Ator;
import model.domain.ReturnType;
import model.domain.Titulo;

public class APLCadastrarAtor
{
    private static GenericDao<Ator> m_daoInstance;
    
    private static GenericDao<Ator> getDaoInstance()
    {
        if(m_daoInstance == null)
            m_daoInstance = new GenericDao<Ator>();
        
        return m_daoInstance;
    }
    
    public static ReturnType inserirAtor(String nomeAtor, Long idAtor)
    {
        if(nomeAtor.equals(""))
            return ReturnType.ERRO;
        
        Ator atorInsercao = criarAtor(idAtor, nomeAtor);
        
        if(atorInsercao == null)
            return ReturnType.ERRO;
        
        getDaoInstance().saveOrUpdate(atorInsercao);
        
        return ReturnType.SUCESSO;
    }
    
    public static ReturnType deletarAtor(String idAtor)
    {
        if(idAtor.equals(""))
          return ReturnType.ERRO;
        
        Long id = Long.parseLong(idAtor);
        
        if(id == null)
            return ReturnType.ERRO;
        
        ReturnType typeVerificao = verificarExclusao(id);
        
        if(typeVerificao != ReturnType.SUCESSO)
            return typeVerificao;
        
        if(getDaoInstance().findById(Ator.class, id) == null)
            return ReturnType.ERRO; 
        
        getDaoInstance().remove(Ator.class, id);

        return ReturnType.SUCESSO;
    }
    
    private static ReturnType verificarExclusao(Long idAtor)
    {
        List<Titulo> titulos = APLCadastrarTitulo.findTitles();
        
        for(Titulo t : titulos)
        {
            if(t.getAtores().stream().anyMatch(x-> Objects.equals(x.getId(), idAtor)))
                return ReturnType.ATOR_LINKADO;
        }
        
        return ReturnType.SUCESSO;
    }
    
    private static Ator criarAtor(Long idAtor, String nomeAtor)
    {
        return new Ator(idAtor, nomeAtor);
    }
    
    public static List<Ator> findActors()
    {
        return getDaoInstance().findQuery(Ator.class);
    }
    
    public static Ator findAtor(Long idAtor)
    {
        return getDaoInstance().findById(Ator.class, idAtor);
    }
}
