package model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="Diretor")
@XmlRootElement
public class Diretor implements Serializable, IEntityBase
{
    private long idDiretor;
    private String nomeDiretor;

    public Diretor()
    {
    }
    
    public Diretor(long idDiretor, String nomeDiretor)
    {
        this.idDiretor = idDiretor;
        this.nomeDiretor = nomeDiretor;
    }
    
    public Diretor(String nomeDiretor)
    {
        this.nomeDiretor = nomeDiretor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    @Override
    public Long getId()
    {
        return idDiretor;
    }

    public void setId(long idDiretor)
    {
        this.idDiretor = idDiretor;
    }

    @Column(name="Nome")
    public String getnomeDiretor()
    {
        return nomeDiretor;
    }

    public void setnomeDiretor(String nomeDiretor)
    {
        this.nomeDiretor = nomeDiretor;
    }
}
