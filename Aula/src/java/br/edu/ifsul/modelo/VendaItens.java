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
@Table(name = "vendaitens")
public class VendaItens implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_venda_itens", sequenceName = "seq_venda_itens_id",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_venda_itens")
    private Integer id;

    @NotNull(message = "A quantidade deve ser informada")
    @Column(name = "quantidade", nullable = false, columnDefinition = "decimal(12,2)")
    private Double quantidade;

    @NotNull(message = "O valor do produto deve ser informada")
    @Column(name = "valorUnitario", nullable = false, columnDefinition = "decimal(12,2)")
    private Double valorUnitario;

    @NotNull(message = "O valor total da venda deve ser informada")
    @Column(name = "valorTotal", nullable = false, columnDefinition = "decimal(12,2)")
    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name = "produto", referencedColumnName = "id", nullable = false)
    private Produto produto;
    
    @ManyToOne
    @JoinColumn(name = "venda_id", referencedColumnName = "id")
    private Venda venda;

//codigo gerado    
    public VendaItens() {
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final VendaItens other = (VendaItens) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VendaItens{" + "id=" + id + ", quantidade=" + quantidade + ", valorUnitario=" + valorUnitario + ", valorTotal=" + valorTotal + ", produto=" + produto + '}';
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

}
