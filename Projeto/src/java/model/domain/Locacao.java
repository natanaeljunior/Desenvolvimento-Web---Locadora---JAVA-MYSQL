package model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="Locacao")
public class Locacao implements Serializable, IEntityBase
{
    private Long idLocacao;
    private Date dtLocacao;
    private Date dtDevolucaoPrevista;
    private Date dtDevolucaoEfetiva;
    private double valorCobrado;
    private double multaCobrada;
    @JsonIgnore
    private Cliente cliente;
    @JsonIgnore
    private Item item;

    public Locacao()
    {
    }

    public Locacao(Long idLocacao, Date dtLocacao, Date dtDevolucaoPrevista, Date dtDevolucaoEfetiva, double valorCobrado, double multaCobrada)
    {
        this.idLocacao = idLocacao;
        this.dtLocacao = dtLocacao;
        this.dtDevolucaoPrevista = dtDevolucaoPrevista;
        this.dtDevolucaoEfetiva = dtDevolucaoEfetiva;
        this.valorCobrado = valorCobrado;
        this.multaCobrada = multaCobrada;
    }

    public Locacao(Date dtLocacao, Date dtDevolucaoPrevista, Date dtDevolucaoEfetiva, double valorCobrado, double multaCobrada)
    {
        this.dtLocacao = dtLocacao;
        this.dtDevolucaoPrevista = dtDevolucaoPrevista;
        this.dtDevolucaoEfetiva = dtDevolucaoEfetiva;
        this.valorCobrado = valorCobrado;
        this.multaCobrada = multaCobrada;
    }

    public Locacao(Long idLocacao, Date dtLocacao, Date dtDevolucaoPrevista, double valorCobrado, Cliente cliente, Item item)
    {
        this.idLocacao = idLocacao;
        this.dtLocacao = dtLocacao;
        this.dtDevolucaoPrevista = dtDevolucaoPrevista;
        this.valorCobrado = valorCobrado;
        this.cliente = cliente;
        this.item = item;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    @Override
    public Long getId()
    {
        return idLocacao;
    }

    public void setId(Long idLocacao)
    {
        this.idLocacao = idLocacao;
    }

    @Column(name="Locacao")
    public Date getDtLocacao()
    {
        return dtLocacao;
    }

    public void setDtLocacao(Date dtLocacao)
    {
        this.dtLocacao = dtLocacao;
    }

    @Column(name="DevolucaoPrevista")
    public Date getDtDevolucaoPrevista()
    {
        return dtDevolucaoPrevista;
    }

    public void setDtDevolucaoPrevista(Date dtDevolucaoPrevista)
    {
        this.dtDevolucaoPrevista = dtDevolucaoPrevista;
    }

    @Column(name="DevolucaoEfetiva")
    public Date getDtDevolucaoEfetiva()
    {
        return dtDevolucaoEfetiva;
    }

    public void setDtDevolucaoEfetiva(Date dtDevolucaoEfetiva)
    {
        this.dtDevolucaoEfetiva = dtDevolucaoEfetiva;
    }

    @Column(name="Valor")
    public double getValorCobrado()
    {
        return valorCobrado;
    }

    public void setValorCobrado(double valorCobrado)
    {
        this.valorCobrado = valorCobrado;
    }

    @Column(name="Multa")
    public double getMultaCobrada()
    {
        return multaCobrada;
    }

    public void setMultaCobrada(double multaCobrada)
    {
        this.multaCobrada = multaCobrada;
    }
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="ClienteId")
    public Cliente getCliente()
    {
        return cliente;
    }

    @JsonProperty
    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="ItemId")
    public Item getItem()
    {
        return item;
    }

    @JsonProperty
    public void setItem(Item item)
    {
        this.item = item;
    }
}
