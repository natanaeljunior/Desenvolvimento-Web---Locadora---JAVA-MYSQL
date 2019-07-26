package model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Item")
public class Item implements Serializable, IEntityBase
{
    public Long idItem;
    private Integer numSerie;
    private Date dataAquisicao;
    private TipoItem tipoItem;
    private Titulo titulo;
    private Boolean itemLocado;
    private Set<Locacao> locacoes;

    public Item()
    {
    }

    public Item(Long idItem, Integer numSerie,Date dataAquisicao, TipoItem tipoItem, Titulo titulo, Boolean itemLocado)
    {
        this.idItem = idItem;
        this.numSerie = numSerie;
        this.dataAquisicao = dataAquisicao;
        this.tipoItem = tipoItem;
        this.titulo = titulo;
        this.itemLocado = itemLocado;
    }

    public Item(Integer numSerie,Date dataAquisicao, TipoItem tipoItem, Titulo titulo, Boolean itemLocado)
    {
        this.numSerie = numSerie;
        this.dataAquisicao = dataAquisicao;
        this.tipoItem = tipoItem;
        this.titulo = titulo;
        this.itemLocado = itemLocado;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    @Override
    public Long getId()
    {
        return idItem;
    }

    public void setId(Long idItem)
    {
        this.idItem = idItem;
    }

    @Column(name="Serie")
    public Integer getNumSerie()
    {
        return numSerie;
    }
    
    public void setNumSerie(Integer numSerie)
    {
        this.numSerie = numSerie;
    }
    
    @Column(name="Locado")
    public Boolean getItemLocado()
    {
        return itemLocado;
    }

    public void setItemLocado(Boolean itemLocado)
    {
        this.itemLocado = itemLocado;
    }

    @Column(name="Aquisicao")
    public Date getDataAquisicao()
    {
        return dataAquisicao;
    }

    public void setDataAquisicao(Date dataAquisicao)
    {
        this.dataAquisicao = dataAquisicao;
    }

    @Enumerated
    @Column(columnDefinition = "smallint", name="Tipo")
    public TipoItem getTipoItem()
    {
        return tipoItem;
    }

    public void setTipoItem(TipoItem tipoItem)
    {
        this.tipoItem = tipoItem;
    }

    @OneToMany(mappedBy = "item", targetEntity = Locacao.class, orphanRemoval=true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Locacao> getLocacoes()
    {
        return locacoes;
    }

    public void setLocacoes(Set<Locacao> locacoes)
    {
        if(this.locacoes == null)            
            this.locacoes = locacoes;
        
        else if(locacoes != null)
        {
            this.locacoes.clear();
            this.locacoes.addAll(locacoes);
        }
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="TituloId")
    public Titulo getTitulo()
    {
        return titulo;
    }

    public void setTitulo(Titulo titulo)
    {
        this.titulo = titulo;
    }
}
