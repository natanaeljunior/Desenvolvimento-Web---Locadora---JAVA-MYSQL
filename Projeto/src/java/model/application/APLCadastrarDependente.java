package model.application;

import dao.GenericDao;
import java.sql.Date;
import java.util.List;
import static java.util.stream.Collectors.toList;
import model.domain.Dependente;
import model.domain.ReturnType;
import model.domain.Sexo;
import model.domain.Socio;

public class APLCadastrarDependente
{
    private static GenericDao<Dependente> m_daoInstance;
    
    private static GenericDao<Dependente> getDaoInstance()
    {
        if(m_daoInstance == null)
            m_daoInstance = new GenericDao<Dependente>();
        
        return m_daoInstance;
    }
     
    public static ReturnType inserirDepedente(String nome, Date dataNascimento, Sexo sexoType, Socio socio)
    {
        Dependente dependenteInsercao = criarDependente(nome, dataNascimento, sexoType, socio, true);
        
        if(dependenteInsercao == null)
            return ReturnType.ERRO;
        
        getDaoInstance().saveOrUpdate(dependenteInsercao);
        
        return ReturnType.SUCESSO;
    }
    
    public static ReturnType deletarDependente(String idDependente)
    {
        if(idDependente.equals(""))
          return ReturnType.ERRO;
        
        Long id = Long.parseLong(idDependente);
        
        if(id == null)
            return ReturnType.ERRO;
        
        if(getDaoInstance().findById(Dependente.class, id) == null)
            return ReturnType.ERRO;
        
        getDaoInstance().remove(Dependente.class, id);

        return ReturnType.SUCESSO;
    }
    
    public static Dependente findDependenteId(Long idDependente)
    {
        return getDaoInstance().findById(Dependente.class, idDependente);
    }

    private static Dependente criarDependente(String nome, Date dataNascimento, Sexo sexoType, Socio socio, boolean isAtivo)
    {
        return new Dependente((long)0, nome, gerarInscricao(), dataNascimento, sexoType, socio, isAtivo);
    }
    
    private static int gerarInscricao()
    {
       java.util.Date dataAtual = new java.util.Date();
       return (int)Math.pow((dataAtual.getDay() + dataAtual.getMonth() + dataAtual.getYear() + dataAtual.getHours() + dataAtual.getSeconds() + dataAtual.getMinutes()), 2);
    }
}
