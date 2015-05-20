
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * jorge.bavaresco@passofundo.ifsul.edu.br
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa")
public abstract class Pessoa implements Serializable {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa_id",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_pessoa")
    private Integer id;
    @NotEmpty(message = "O nome deve ser informado")
    @Length(max = 50, message = "O  nome não deve ultrapassar {max} caracteres")
    @Column(name = "nome",length = 50, nullable = false)
    private String nome;
    @NotEmpty(message = "O endereço deve ser informado")
    @Length(max = 50, message = "O  endereço não deve ultrapassar {max} caracteres")
    @Column(name = "endereco",length = 50, nullable = false)    
    private String endereco;
    @NotEmpty(message = "O bairro deve ser informado")
    @Length(max = 20, message = "O  bairro não deve ultrapassar {max} caracteres")
    @Column(name = "bairro",length = 20, nullable = false)      
    private String bairro;
    @NotEmpty(message = "O cep deve ser informado")
    @Length(max = 9, message = "O  cep não deve ultrapassar {max} caracteres")
    @Column(name = "cep",length = 9, nullable = false)    
    private String cep;
    @Length(max = 30, message = "O  cep não deve ultrapassar {max} caracteres")
    @Column(name = "complemento",length = 30)        
    private String complemento;
    @Email(message = "Email inválido")
    @NotEmpty(message = "O email deve ser informado")
    @Length(max = 50, message = "O  email não deve ultrapassar {max} caracteres")
    @Column(name = "email",length = 50, nullable = false)        
    private String email;
    @NotNull(message = "A cidade deve ser informada")
    @ManyToOne
    @JoinColumn(name = "cidade",referencedColumnName = "id",nullable = false)
    private Cidade cidade;
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy(value = "descricao asc")
    private List<Telefone> telefones = new ArrayList<>();

    public Pessoa() {
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }
    
    public void adicionarTelefone(Telefone obj){
        obj.setPessoa(this);
        this.telefones.add(obj);
    }
    
    public void removerTelefone(int index){
        this.telefones.remove(index);
    }
    
    public void removerTodosTelefones(){
        this.telefones.clear();
    }
    

}
