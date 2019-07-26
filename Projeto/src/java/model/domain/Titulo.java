package model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="Titulo")
@XmlRootElement
public class Titulo implements Serializable, IEntityBase
{
    private Long idTitulo;
    private String nome;
    private String imagem;
    private int ano;
    private String sinopse;
    private Categoria categoria;
    private Classe classe;
    private Diretor diretor;
    private Set<Ator> atores = new HashSet();
    @JsonIgnore
    private Set<Item> items = new HashSet();

    public Titulo()
    {
    }
    
    public Titulo(Long idTitulo, String nome, String imagem, int ano, String sinopse, Categoria categoria, Classe classe, Diretor diretor, Set<Ator> atores, Set<Item> items)
    {
        this.idTitulo = idTitulo;
        this.nome = nome;
        this.imagem = imagem;
        this.ano = ano;
        this.sinopse = sinopse;
        this.categoria = categoria;
        this.classe = classe;
        this.diretor = diretor;
        this.atores = atores;
        this.items = items;
    }

    public Titulo(Long idTitulo, String nome,String imagem, int ano, String sinopse, Categoria categoria, Classe classe, Diretor diretor, Set<Ator> atores)
    {
        this.idTitulo = idTitulo;
        this.nome = nome;
        this.imagem = imagem;
        this.ano = ano;
        this.sinopse = sinopse;
        this.categoria = categoria;
        this.classe = classe;
        this.diretor = diretor;
        this.atores = atores;
    }
    
    public Titulo(Long idTitulo, String nome, String imagem, int ano, String sinopse, Categoria categoria, Classe classe, Diretor diretor, Set<Ator> atores, HashSet items)
    {
        this.idTitulo = idTitulo;
        this.nome = nome;
        this.imagem = imagem;
        this.ano = ano;
        this.sinopse = sinopse;
        this.categoria = categoria;
        this.classe = classe;
        this.diretor = diretor;
        this.atores = atores;
        this.items = items;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    @Override
    public Long getId()
    {
        return idTitulo;
    }

    public void setId(Long idTitulo)
    {
        this.idTitulo = idTitulo;
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
    
    @Column(name="Imagem")
    public String getImagem()
    {
        return imagem;
    }

    public void setImagem(String imagem)
    {
        this.imagem = imagem;
    }
    
    @Column(name="Ano")
    public int getAno()
    {
        return ano;
    }

    public void setAno(int ano)
    {
        this.ano = ano;
    }

    @Column(name="Sinopse")
    public String getSinopse()
    {
        return sinopse;
    }

    public void setSinopse(String sinopse)
    {
        this.sinopse = sinopse;
    }

    @Enumerated
    @Column(columnDefinition = "smallint", name="Categoria")
    public Categoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(Categoria categoria)
    {
        this.categoria = categoria;
    }

    @ManyToOne
    @JoinColumn(name="ClasseId")
    public Classe getClasse()
    {
        return classe;
    }

    public void setClasse(Classe classe)
    {
        this.classe = classe;
    }

    @ManyToOne
    @JoinColumn(name="DiretorId")
    public Diretor getDiretor()
    {
        return diretor;
    }

    public void setDiretor(Diretor diretor)
    {
        this.diretor = diretor;
    }
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ator_titulo",
        joinColumns = @JoinColumn(name = "TituloId", referencedColumnName = "Id"),
        inverseJoinColumns = @JoinColumn(name = "AtorId", referencedColumnName = "Id"))
    public Set<Ator> getAtores()
    {
        return atores;
    }

    public void setAtores(Set<Ator> atores)
    {
        this.atores = atores;
    }
    
    @JsonIgnore
    @OneToMany(mappedBy = "titulo", targetEntity = Item.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Item> getItems()
    {
        return items;
    }

    @JsonProperty
    public void setItems(Set<Item> items)
    {
        this.items = items;
    }
    
    @JsonIgnore
    @Transient
    public String getNomeAtores()
    {
        if(getAtores().isEmpty())
            return "";
        
        else
            return getAtores().stream()
                              .map(ator -> String.valueOf(ator.getNomeAtor()))
                              .collect(Collectors.joining(", "));
    }
}
