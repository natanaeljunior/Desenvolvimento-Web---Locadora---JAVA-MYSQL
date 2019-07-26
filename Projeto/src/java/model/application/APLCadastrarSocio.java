package model.application;

import dao.GenericDao;
import java.util.List;
import model.domain.ReturnType;
import java.sql.Date;
import java.util.Objects;
import model.domain.Locacao;
import model.domain.Sexo;
import model.domain.Socio;

public class APLCadastrarSocio
{
    private static GenericDao<Socio> m_daoInstance;
    
    private static GenericDao<Socio> getDaoInstance()
    {
        if(m_daoInstance == null)
            m_daoInstance = new GenericDao<Socio>();
        
        return m_daoInstance;
    }
    
    public static ReturnType inserirSocio(String nome, Date dataNascimento, Sexo sexoType, String endereco, String telefone, String cpf)
    {
        Socio socioInsercao = criarSocio(nome, dataNascimento, sexoType, endereco, telefone, cpf);
        
        if(socioInsercao == null)
            return ReturnType.ERRO;
        
        getDaoInstance().saveOrUpdate(socioInsercao);
        
        return ReturnType.SUCESSO;
    }
    
    public static ReturnType deletarSocio(String idSocio)
    {
        if(idSocio.equals(""))
          return ReturnType.ERRO;
        
        Long id = Long.parseLong(idSocio);
        
        if(id == null)
            return ReturnType.ERRO;
        
        if(getDaoInstance().findById(Socio.class, id) == null)
            return ReturnType.ERRO;
        
        ReturnType typeVerificao = verificarExclusao(id);
        
        if(typeVerificao != ReturnType.SUCESSO)
            return typeVerificao;
        
        
        getDaoInstance().remove(Socio.class, id);

        return ReturnType.SUCESSO;
    }
    
    private static ReturnType verificarExclusao(Long idCliente)
    {
        List<Locacao> locacoes = APLCadastrarLocacao.findLocations();
        
        for(Locacao l : locacoes)
        {
            if(Objects.equals(l.getCliente().getId(), idCliente))
                return ReturnType.CLIENTE_LINKADO;
        }
        
        return ReturnType.SUCESSO;
    }
    
    private static Socio criarSocio(String nome, Date dataNascimento, Sexo sexoType, String endereco, String telefone, String cpf)
    {
        return new Socio(cpf, endereco, telefone, (long)0, nome, gerarInscricao(), dataNascimento, sexoType, true);
    }
    
    private static int gerarInscricao()
    {
       java.util.Date dataAtual = new java.util.Date();
       return (int)Math.pow((dataAtual.getDay() + dataAtual.getMonth() + dataAtual.getYear() + dataAtual.getHours() + dataAtual.getSeconds() + dataAtual.getMinutes()), 2);
    }
    
    public static List<Socio> findSocios()
    {
        return getDaoInstance().findQuery(Socio.class);
    }
    
    public static Socio findSocioId(Long idSocio)
    {
        return getDaoInstance().findById(Socio.class, idSocio);
    }
}
