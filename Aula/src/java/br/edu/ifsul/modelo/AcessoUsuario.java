package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * @author Marcelo Luis Gross
 */
@Entity
@Table(name = "acessousuario")
public class AcessoUsuario implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_acesso_usuario", sequenceName = "seq_acesso_usuario_id",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_acesso_usuario")
    private Integer id;
    
    @Temporal(TemporalType.DATE)
    @NotNull(message = "A data do acesso do usuário contém um valor nulo, verificar setter")
    @Column(name = "data_acesso", nullable = false)    
    private Calendar data;
    
    @ManyToOne
    @JoinColumn(name = "usuario_nick", referencedColumnName = "apelido", nullable = false)
    private Usuario usuario;

    //Codigo Gerado
    public AcessoUsuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AcessoUsuario other = (AcessoUsuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AcessoUsuario{" + "id=" + id + ", data=" + data + ", usuario=" + usuario + '}';
    }

    

}
