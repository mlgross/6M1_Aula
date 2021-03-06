package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Marcelo
 */
@Entity
public class Cidade implements Serializable {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "seq_cidade",sequenceName = "seq_cidade_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_cidade",strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotEmpty(message = "O nome deve ser informado")
    @Length(max = 50,message = "O nome deve ter até {max} caracteres")
    @Column(name = "nome",length = 50,nullable = false)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "estado",referencedColumnName = "id",nullable = false)
    @NotNull(message = "O Estado deve ser informado")
    private Estado estado;

    public Cidade() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.id);
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
        final Cidade other = (Cidade) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
}
