package model.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.sql.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Type;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cliente implements Serializable, IEntityBase
{
    private Long idCliente;
    private String nome;
    private int numInscricao;
    private Date dataNascimento;
    private Sexo sexo;
    private boolean ativo;
    private Set<Locacao> locacoes;
    
    public Cliente()
    {
    }
    
    public Cliente(String nome, int numInscricao, Date dataNascimento, Sexo sexo, boolean ativo)
    {
        this.nome = nome;
        this.numInscricao = numInscricao;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.ativo = ativo;
    }
    
    public Cliente(Long idCliente, String nome, int numInscricao, Date dataNascimento, Sexo sexo, boolean ativo)
    {
        this.idCliente = idCliente;
        this.nome = nome;
        this.numInscricao = numInscricao;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.ativo = ativo;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    @Override
    public Long getId()
    {
        return idCliente;
    }

    public void setId(Long idCliente)
    {
        this.idCliente = idCliente;
    }
    
    @Column(name="Nome")
    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    @Column(name="Inscricao")
    public int getNumInscricao()
    {
        return numInscricao;
    }

    public void setNumInscricao(int numInscricao)
    {
        this.numInscricao = numInscricao;
    }
    
    @Column(name="Nascimento")
    public Date getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    @Enumerated
    @Column(columnDefinition = "smallint", name="Sexo")
    public Sexo getSexo()
    {
        return sexo;
    }

    public void setSexo(Sexo sexo) 
    {
        this.sexo = sexo;
    }

    @Type(type="true_false")
    @Column(name="Ativo")
    public boolean isAtivo()
    {
        return ativo;
    }

    public void setAtivo(boolean ativo)
    {
        this.ativo = ativo;
    }
    
    @OneToMany(mappedBy = "cliente", targetEntity = Locacao.class, orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Locacao> getLocacoes()
    {
        return locacoes;
    }

    public void setLocacoes(Set<Locacao> locacoes)
    {
        this.locacoes = locacoes;
    }
}
