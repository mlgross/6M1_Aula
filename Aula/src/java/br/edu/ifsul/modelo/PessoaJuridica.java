
package br.edu.ifsul.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

/**
 *
 * @author Marcelo
 */
@Entity
@Table(name = "pessoa_juridica")
public class PessoaJuridica extends Pessoa implements Serializable {

    @CNPJ(message = "CNPJ inválido")
    @NotEmpty(message = "CNPJ não pode ser nulo")    
    @Column(name = "cnpj", length = 18, nullable = false)    
    private String cnpj;
    @NotEmpty(message = "Incrição estadual não pode ser nula")   
    @Column(name = "ie", length = 15, nullable = false)    
    private String ie;

    public PessoaJuridica() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }
    
    

}
