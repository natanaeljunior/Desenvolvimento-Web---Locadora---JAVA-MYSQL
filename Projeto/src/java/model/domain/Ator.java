package model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Ator")    
@JsonIgnoreProperties(value = { "listaTitulos" })
public class Ator implements Serializable, IEntityBase
{
    private long idAtor;
    private String nomeAtor;
    @JsonIgnore
    private Set<Titulo> listaTitulos = new HashSet();; 

    public Ator()
    {
    }
    
    public Ator(long idAtor, String nomeAtor)
    {
        this.idAtor = idAtor;
        this.nomeAtor = nomeAtor;
    }
    
    public Ator(String nomeAtor)
    {
        this.nomeAtor = nomeAtor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    @Override
    public Long getId()
    {
        return idAtor;
    }

    public void setId(long idAtor)
    {
        this.idAtor = idAtor;
    }

    @Column(name="Nome")
    public String getNomeAtor()
    {
        return nomeAtor;
    }

    public void setNomeAtor(String nomeAtor)
    {
        this.nomeAtor = nomeAtor;
    }
    
    @JsonIgnore
    @ManyToMany(mappedBy="atores")  
    public Set<Titulo> getListaTitulos()
    {
        return listaTitulos;
    }
    
    @JsonProperty
    public void setListaTitulos(Set<Titulo> listaTitulos)
    {
        this.listaTitulos = listaTitulos;
    }
}
