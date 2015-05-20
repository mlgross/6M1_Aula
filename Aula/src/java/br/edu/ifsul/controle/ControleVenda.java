package br.edu.ifsul.controle;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.dao.VendaDAO;
import br.edu.ifsul.dao.PessoaFisicaDAO;
import br.edu.ifsul.dao.VendaItensDAO;

import br.edu.ifsul.modelo.Venda;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.modelo.VendaItens;

import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Marcelo
 */
@ManagedBean(name = "controleVenda")
@ViewScoped
public class ControleVenda implements Serializable {

    @EJB
    private VendaDAO dao;
    private Venda objeto;
    @EJB
    private UsuarioDAO daoUsuario;
    private Usuario usuario;
    @EJB
    private PessoaFisicaDAO daopf;
    private PessoaFisica pf;
    @EJB
    private VendaDAO daovenda;
    private VendaItens vendaItens;
    private Boolean novoVendaItens;

    public ControleVenda() {
    }

    public String listar() {
        return "/venda/listar";
    }

    public void novo() {
        objeto = new Venda();
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persistir(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir: " + e.getMessage());
        }
    }

    public void editar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + e.getMessage());
        }
    }

    public void remover(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: "+e.getMessage());
        }
    }
    
    public void novoVendaItens(){
        vendaItens = new VendaItens();
        novoVendaItens = true;
    }
    
    public void alterarVendaItens(int index){
        vendaItens = objeto.getVendaItens().get(index);
        novoVendaItens = false;
    }
    
    public void salvarVendaItens(){
        if (novoVendaItens){
            objeto.adicionarVendaItens(vendaItens);
        }
        Util.mensagemInformacao("VendaItens adicionado com sucesso");
    }
            
    public void removerVendaItens(int index){
        objeto.removerVendaItens(index);
        Util.mensagemInformacao("VendaItens removido com sucesso");
    }

    public VendaDAO getDao() {
        return dao;
    }

    public void setDao(VendaDAO dao) {
        this.dao = dao;
    }

    public Venda getObjeto() {
        return objeto;
    }

    public void setObjeto(Venda objeto) {
        this.objeto = objeto;
    }

    public UsuarioDAO getDaoCidade() {
        return daoUsuario;
    }

    public void setDaoCidade(UsuarioDAO daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

    public VendaItens getVendaItens() {
        return vendaItens;
    }

    public void setVendaItens(VendaItens vendaItens) {
        this.vendaItens = vendaItens;
    }

    public Boolean getNovoVendaItens() {
        return novoVendaItens;
    }

    public void setNovoVendaItens(Boolean novoVendaItens) {
        this.novoVendaItens = novoVendaItens;
    }

    public PessoaFisicaDAO getDaopf() {
        return daopf;
    }

    public void setDaopf(PessoaFisicaDAO daopf) {
        this.daopf = daopf;
    }

    public UsuarioDAO getDaoUsuario() {
        return daoUsuario;
    }

    public void setDaoUsuario(UsuarioDAO daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PessoaFisica getPf() {
        return pf;
    }

    public void setPf(PessoaFisica pf) {
        this.pf = pf;
    }

    public VendaDAO getDaovenda() {
        return daovenda;
    }

    public void setDaovenda(VendaDAO daovenda) {
        this.daovenda = daovenda;
    }

}
