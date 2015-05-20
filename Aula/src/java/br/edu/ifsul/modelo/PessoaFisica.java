
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Marcelo
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa_fisica")
public class PessoaFisica extends Pessoa implements Serializable {
    @Length(max = 10, min = 10,message = "O RG deve ter {max} caracteres")
    @NotEmpty(message = "O RG deve ser informado")
    @Column(name = "rg",length = 10,nullable = false)
    private String rg;
    @CPF(message = "CPF inválido")
    @Length(min = 14,max = 14,message = "CPF inválido")
    @NotEmpty(message = "O CPF deve ser informado")
    @Column(name = "cpf", length = 14,nullable = false)
    private String cpf;
    @Temporal(TemporalType.DATE)
    @NotNull(message = "O nascimento deve ser informado")
    @Column(name = "nascimento",nullable = false)
    private Calendar nascimento;
    @ManyToMany
    @JoinTable(name = "desejos",
            // joinColumn se refere a coluna que armazena o id desta entidade
            joinColumns = 
            @JoinColumn(name = "pessoa_fisica", referencedColumnName = "id"),
            // inverseJoinColumns se refere a coluna que armazena o id 
            //da entidade do outro lado da relação
            inverseJoinColumns = 
            @JoinColumn(name = "produto",referencedColumnName = "id"))
    private List<Produto> desejos = new ArrayList<>();

    public PessoaFisica() {
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Calendar getNascimento() {
        return nascimento;
    }

    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
    }

    public List<Produto> getDesejos() {
        return desejos;
    }

    public void setDesejos(List<Produto> desejos) {
        this.desejos = desejos;
    }
    
    public void adicionarDesejos(Produto obj){
        if (!this.getDesejos().contains(obj)){
            this.getDesejos().add(obj);
        }
    }
    
    public void removerDesejos(Produto obj){
        this.getDesejos().remove(obj);
    }
}
