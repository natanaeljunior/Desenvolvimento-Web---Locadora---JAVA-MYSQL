package model.application;

import dao.GenericDao;
import java.util.List;
import java.util.Objects;
import model.domain.Diretor;
import model.domain.ReturnType;
import model.domain.Titulo;

public class APLCadastrarDiretor
{
    private static GenericDao<Diretor> m_daoInstance;
    
    private static GenericDao<Diretor> getDaoInstance()
    {
        if(m_daoInstance == null)
            m_daoInstance = new GenericDao<Diretor>();
        
        return m_daoInstance;
    }
    
    public static ReturnType inserirDiretor(String nomeDiretor, Long idDiretor)
    {
        if(nomeDiretor.equals(""))
            return ReturnType.ERRO;
        
        Diretor DiretorInsercao = criarDiretor(nomeDiretor, idDiretor);
        
        if(DiretorInsercao == null)
            return ReturnType.ERRO;
        
        getDaoInstance().saveOrUpdate(DiretorInsercao);
        
        return ReturnType.SUCESSO;
    }
    
    public static ReturnType deletarDiretor(String idDiretor)
    {
        if(idDiretor.equals(""))
          return ReturnType.ERRO;
        
        Long id = Long.parseLong(idDiretor);
        
        if(id == null)
            return ReturnType.ERRO;
        
        ReturnType typeVerificao = verificarExclusao(id);
        
        if(typeVerificao != ReturnType.SUCESSO)
            return typeVerificao;
        
        if(getDaoInstance().findById(Diretor.class, id) == null)
            return ReturnType.ERRO; 
        
        getDaoInstance().remove(Diretor.class, id);

        return ReturnType.SUCESSO;
    }
    
    private static ReturnType verificarExclusao(Long idDiretor)
    {
        List<Titulo> titulos = APLCadastrarTitulo.findTitles();
        
        for(Titulo t : titulos)
        {
            if(Objects.equals(t.getDiretor().getId(), idDiretor))
                return ReturnType.DIRETOR_LINKADO;
        }
        
        return ReturnType.SUCESSO;
    }
    
    private static Diretor criarDiretor(String nomeDiretor, Long idDiretor)
    {
        return new Diretor(idDiretor, nomeDiretor);
    }
    
    public static List<Diretor> findDirectors()
    {
        return getDaoInstance().findQuery(Diretor.class);
    }
    
    public static Diretor findDirector(Long idDiretor)
    {
        return getDaoInstance().findById(Diretor.class, idDiretor);
    }
}
