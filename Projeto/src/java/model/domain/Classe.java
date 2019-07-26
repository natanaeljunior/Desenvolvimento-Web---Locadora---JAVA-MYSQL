package model.domain;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="Classe")
@XmlRootElement
public class Classe implements Serializable, IEntityBase
{
    private Long idClasse;
    private String nomeClasse;
    private Double valorClasse;
    private Integer dataClasse;

    public Classe()
    {
    }

    public Classe(Long idClasse, String nomeClasse, Integer dataClasse, Double valorClasse)
    {
        this.idClasse = idClasse;
        this.nomeClasse = nomeClasse;
        this.valorClasse = valorClasse;
        this.dataClasse = dataClasse;
    }
        
    public Classe(String nomeClasse, Integer dataClasse, Double valorClasse)
    {
        this.nomeClasse = nomeClasse;
        this.valorClasse = valorClasse;
        this.dataClasse = dataClasse;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    @Override
    public Long getId()
    {
        return idClasse;
    }
    
    public void setId(Long idClasse)
    {
        this.idClasse = idClasse;
    }
    
    @Column(name="Nome")
    public String getNomeClasse()
    {
        return nomeClasse;
    }

    public void setNomeClasse(String nomeClasse)
    {
        this.nomeClasse = nomeClasse;
    }

    @Column(name="Valor")
    public Double getValorClasse()
    {
        return valorClasse;
    }

    public void setValorClasse(Double valorClasse)
    {
        this.valorClasse = valorClasse;
    }

    @Column(name="Devolucao")
    public Integer getDataClasse()
    {
        return dataClasse;
    }

    public void setDataClasse(Integer dataClasse)
    {
        this.dataClasse = dataClasse;
    }
}
