package model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable, IEntityBase
{
    private Long id;
    private String login;
    private String senha;

    public Usuario()
    {
    }
        
    public Usuario(Long id, String login, String senha)
    {
        this.id = id;
        this.login = login;
        this.senha = senha;
    }
    
    public Usuario(String login, String senha)
    {
        this.login = login;
        this.senha = senha;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    @Override
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
    
    @Column(name = "Login")
    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }
    
    @Column(name = "Senha")
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }
}
