package model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="Socio")
@PrimaryKeyJoinColumn(name = "Id")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Socio extends Cliente
{
    private String cpf;
    private String endereco;
    private String tel;
    @JsonIgnore
    private Set<Dependente> dependentes;

    public Socio()
    {
    }

    public Socio(String cpf, String endereco, String tel, String nome, int numInscricao, Date dataNascimento, Sexo sexo, boolean ativo)
    {
        super(nome, numInscricao, dataNascimento, sexo, ativo);
        
        this.cpf = cpf;
        this.endereco = endereco;
        this.tel = tel;
    }

    public Socio(String cpf, String endereco, String tel, Long idCliente, String nome, int numInscricao, Date dataNascimento, Sexo sexo, boolean ativo)
    {
        super(idCliente, nome, numInscricao, dataNascimento, sexo, ativo);
        
        this.cpf = cpf;
        this.endereco = endereco;
        this.tel = tel;
    }
    
    @Column(name="CPF")
    public String getCpf()
    {
        return cpf;
    }
    
    @JsonIgnore
    @OneToMany(mappedBy = "socio", targetEntity = Dependente.class, orphanRemoval=true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Set<Dependente> getDependentes()
    {
        return dependentes;
    }

    @JsonProperty
    public void setDependentes(Set<Dependente> dependentes)
    {
        this.dependentes = dependentes;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    @Column(name="Endereco")
    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }

    @Column(name="Telefone")
    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel) 
    {      
        this.tel = tel;
    }
}
