package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * @author Marcelo Luis Gross
 */
@Entity
@Table(name = "venda")
public class Venda implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_venda", sequenceName = "seq_venda_id",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_venda")
    private Integer id;
    
    @Temporal(TemporalType.DATE)
    @NotNull(message = "A data da venda ao usuário contém um valor nulo, verificar setter")
    @Column(name = "data_venda", nullable = false)    
    private Calendar data;
    
    @NotNull(message = "O valor da venda deve ser informado")
    @Column(name = "valor", nullable = false, columnDefinition = "decimal(12,2)")    
    private Double valor;
    
    @NotNull(message = "A venda deve possuir um status")
    @Column(name = "status",length = 1 , nullable = false)
    private Character status;
    
    @ManyToOne
    @JoinColumn(name = "usuario_nick", referencedColumnName = "apelido", nullable = false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "pessoaFisica", referencedColumnName = "id", nullable = false)            
    private PessoaFisica pessoaFisica;
    
    /**
     * Em OneToMany é necessário mapear pelo atributo, isto é, o nome da variável java
     */
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy(value = "valorTotal asc")
    private List<VendaItens> vendaItens = new ArrayList<>();
        
    public List<VendaItens> getVendaItens() {
        return vendaItens;
    }

    public void setVendaItens(List<VendaItens> vendaItens) {
        this.vendaItens = vendaItens;
    }
    
    public void adicionarVendaItens(VendaItens obj){
        obj.setVenda(this);
        this.vendaItens.add(obj);
    }
    
    public void removerVendaItens(int index){
        this.vendaItens.remove(index);
    }
    
    public void removerTodosVendaItens(){
        this.vendaItens.clear();
    }
    
//código gerado 

    public Venda() {
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
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
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final Venda other = (Venda) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", data=" + data + ", valor=" + valor + ", status=" + status + ", usuario=" + usuario + ", pessoaFisica=" + pessoaFisica + ", vendaItens=" + vendaItens + '}';
    }
    
}
