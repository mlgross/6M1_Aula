package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ProdutoDAO;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jorge Luis Boeira Bavaresco jorge.bavaresco@passofundo.ifsul.edu.br
 */
@ManagedBean(name = "controleProduto")
@ViewScoped
public class ControleProduto implements Serializable {

    @EJB
    private ProdutoDAO dao;
    private Produto objeto;

    public ControleProduto() {

    }

    public String listar() {
        return "/produto/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Produto();
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persistir(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + e.getMessage());
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
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: " + e.getMessage());
        }
    }

    public ProdutoDAO getDao() {
        return dao;
    }

    public void setDao(ProdutoDAO dao) {
        this.dao = dao;
    }

    public Produto getObjeto() {
        return objeto;
    }

    public void setObjeto(Produto objeto) {
        this.objeto = objeto;
    }

}
