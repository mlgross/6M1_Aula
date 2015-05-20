package br.edu.ifsul.controle;

import br.edu.ifsul.dao.GrupoDAO;
import br.edu.ifsul.modelo.Grupo;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jorge Luis Boeira Bavaresco jorge.bavaresco@passofundo.ifsul.edu.br
 */
@ManagedBean(name = "controleGrupo")
@ViewScoped
public class ControleGrupo implements Serializable {

    @EJB
    private GrupoDAO dao;
    private Grupo objeto;

    public ControleGrupo() {

    }

    public String listar() {
        return "/grupo/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Grupo();
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

    public GrupoDAO getDao() {
        return dao;
    }

    public void setDao(GrupoDAO dao) {
        this.dao = dao;
    }

    public Grupo getObjeto() {
        return objeto;
    }

    public void setObjeto(Grupo objeto) {
        this.objeto = objeto;
    }

}
