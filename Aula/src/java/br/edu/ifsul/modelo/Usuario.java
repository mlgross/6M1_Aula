package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Marcelo
 */
@Entity
@Table(name = "usuario")
public class Usuario extends PessoaFisica implements Serializable {
    @Length(max = 20, min = 3,message = "O apelido deve ter entre {min} e {max} caracteres")
    @NotEmpty(message = "O usuário deve possuir um apelido")
    @Column(name = "apelido",length = 20,nullable = false)
    private String apelido;
    
    @Length(max = 255, min = 8,message = "A senha deve ter no mínimo {min} caracteres")
    @NotEmpty(message = "O usuario deve possuir uma senha")
    @Column(name = "senha", length = 255,nullable = false)
    private String senha;
    
    @NotNull(message = "O valor não deve ser nulo")
    @Column(name = "ativo",nullable = false)
    private Boolean ativo;
    
    @NotNull(message = "O administrador não deve ser nulo")
    @Column(name = "administrador",nullable = false)
    private Boolean administrador;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, 
            orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy(value = "data asc")
    private List<AcessoUsuario> acessos = new ArrayList<>();
    
    public List<AcessoUsuario> getAcessos() {
        return acessos;
    }

    public void setAcessos(List<AcessoUsuario> acessos) {
        this.acessos = acessos;
    }
    
    public void adicionarAcesso(AcessoUsuario obj){
        obj.setUsuario(this);
        this.acessos.add(obj);
    }
    
    public void removerAcesso(int index){
        this.acessos.remove(index);
    }
    
    public void removerTodosAcessos(){
        this.acessos.clear();
    }
    
    
    
    //Codigo gerado abaixo    
    public Usuario() {
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.apelido);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.apelido, other.apelido)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "apelido=" + apelido + '}';
    }
    
    
}
