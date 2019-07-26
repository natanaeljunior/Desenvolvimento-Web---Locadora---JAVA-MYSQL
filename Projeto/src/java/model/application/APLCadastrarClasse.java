package model.application;

import dao.GenericDao;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import model.domain.Classe;
import model.domain.ReturnType;
import model.domain.Titulo;

public class APLCadastrarClasse
{
    private static GenericDao<Classe> m_daoInstance;
    
    private static GenericDao<Classe> getDaoInstance()
    {
        if(m_daoInstance == null)
            m_daoInstance = new GenericDao<Classe>();
        
        return m_daoInstance;
    }
    
    public static ReturnType inserirClasse(String nomeClasse, int dataVencimento, double valorClasse, Long idClasse)
    {
        if(nomeClasse.equals(""))
            return ReturnType.ERRO;
        
        if(dataVencimento  == 0)
            return ReturnType.ERRO;
        
        if(valorClasse == 0)
            return ReturnType.ERRO;
        
        Classe classeInsercao = criarClasse(nomeClasse, dataVencimento, valorClasse, idClasse);
        
        if(classeInsercao == null)
            return ReturnType.ERRO;
        
        getDaoInstance().saveOrUpdate(classeInsercao);
        
        return ReturnType.SUCESSO;
    }
    
    public static ReturnType deletarClasse(String idClasse)
    {
        if(idClasse.equals(""))
          return ReturnType.ERRO;
        
        Long id = Long.parseLong(idClasse);
        
        if(id == null)
            return ReturnType.ERRO;
        
        ReturnType typeVerificao = verificarExclusao(id);
        
        if(typeVerificao != ReturnType.SUCESSO)
            return typeVerificao;
        
        if(getDaoInstance().findById(Classe.class, id) == null)
            return ReturnType.ERRO; 
        
        getDaoInstance().remove(Classe.class, id);

        return ReturnType.SUCESSO;
    }
    
    private static ReturnType verificarExclusao(Long idClasse)
    {
        List<Titulo> titulos = APLCadastrarTitulo.findTitles();
        
        for(Titulo t : titulos)
        {
            if(Objects.equals(t.getClasse().getId(), idClasse))
                return ReturnType.CLASSE_LINKADA;
        }
        
        return ReturnType.SUCESSO;
    }
    
    private static Classe criarClasse(String nomeClasse, int dataVencimento, double valorClasse, Long idClasse)
    {
        return new Classe(idClasse, nomeClasse, dataVencimento, valorClasse);
    }
    
    public static List<Classe> findClasses()
    {
        return getDaoInstance().findQuery(Classe.class);
    }
    
    public static Classe findClasse(Long idClasse)
    {
        return getDaoInstance().findById(Classe.class, idClasse);
    }
}
