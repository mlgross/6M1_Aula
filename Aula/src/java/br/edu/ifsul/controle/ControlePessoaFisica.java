package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.PessoaFisicaDAO;
import br.edu.ifsul.dao.EstadoDAO;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Telefone;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jorge Luis Boeira Bavaresco jorge.bavaresco@passofundo.ifsul.edu.br
 */
@ManagedBean(name = "controlePessoaFisica")
@ViewScoped
public class ControlePessoaFisica implements Serializable {

    @EJB
    private PessoaFisicaDAO dao;
    private PessoaFisica objeto;
    @EJB
    private CidadeDAO daoCidade;
    private Telefone telefone;
    private Boolean novoTelefone;

    public ControlePessoaFisica() {

    }

    public String listar() {
        return "/privado/pessoafisica/listar";
    }

    public void novo() {
        objeto = new PessoaFisica();
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
    
    public void novoTelefone(){
        telefone = new Telefone();
        novoTelefone = true;
    }
    
    public void alterarTelefone(int index){
        telefone = objeto.getTelefones().get(index);
        novoTelefone = false;
    }
    
    public void salvarTelefone(){
        if (novoTelefone){
            objeto.adicionarTelefone(telefone);
        }
        Util.mensagemInformacao("Telefone adicionado com sucesso");
    }
            
    public void removerTelefone(int index){
        objeto.removerTelefone(index);
        Util.mensagemInformacao("Telefone removido com sucesso");
    }
    

    public PessoaFisicaDAO getDao() {
        return dao;
    }

    public void setDao(PessoaFisicaDAO dao) {
        this.dao = dao;
    }

    public PessoaFisica getObjeto() {
        return objeto;
    }

    public void setObjeto(PessoaFisica objeto) {
        this.objeto = objeto;
    }

    public CidadeDAO getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDAO daoCidade) {
        this.daoCidade = daoCidade;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Boolean getNovoTelefone() {
        return novoTelefone;
    }

    public void setNovoTelefone(Boolean novoTelefone) {
        this.novoTelefone = novoTelefone;
    }

}
