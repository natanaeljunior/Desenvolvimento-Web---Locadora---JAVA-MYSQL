package model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="Dependente")
@PrimaryKeyJoinColumn(name = "Id")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Dependente extends Cliente
{
    private Socio socio;
    
    public Dependente()
    {
    }

    public Dependente(Long id, String nome, int numInscricao, Date dataNascimento, Sexo sexo, Socio socio, boolean ativo)
    {
        super(id, nome, numInscricao, dataNascimento, sexo, ativo);
        this.socio = socio;
    }
    
    @ManyToOne
    @JoinColumn(name="SocioId")
    public Socio getSocio()
    {
        return socio;
    }
    
    public void setSocio(Socio socio)
    {
        this.socio = socio;
    }
}
