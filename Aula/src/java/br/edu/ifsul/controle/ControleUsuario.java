package br.edu.ifsul.controle;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.modelo.AcessoUsuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Marcelo
 */
@ManagedBean(name = "controleUsuario")
@ViewScoped
public class ControleUsuario implements Serializable {

    @EJB
    private UsuarioDAO dao;
    private Usuario objeto;
    private AcessoUsuario acesso;
    private Boolean novoAcesso;

    public ControleUsuario() {

    }

    public String listar() {
        return "/usuario/listar";
    }

    public void novo() {
        objeto = new Usuario();
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
    
    public void novoAcesso(){
        acesso = new AcessoUsuario();
        novoAcesso = true;
    }
    
    public void alterarAcesso(int index){
        acesso = objeto.getAcessos().get(index);
        novoAcesso = false;
    }
    
    public void salvarAcesso(){
        if (novoAcesso){
            objeto.adicionarAcesso(acesso);
        }
        Util.mensagemInformacao("Acesso adicionado com sucesso");
    }
            
    public void removerAcesso(int index){
        objeto.removerAcesso(index);
        Util.mensagemInformacao("Acesso removido com sucesso");
    }
    

    public UsuarioDAO getDao() {
        return dao;
    }

    public void setDao(UsuarioDAO dao) {
        this.dao = dao;
    }

    public Usuario getObjeto() {
        return objeto;
    }

    public void setObjeto(Usuario objeto) {
        this.objeto = objeto;
    }

    public AcessoUsuario getAcesso() {
        return acesso;
    }

    public void setAcesso(AcessoUsuario acesso) {
        this.acesso = acesso;
    }

    public Boolean getNovoAcesso() {
        return novoAcesso;
    }

    public void setNovoAcesso(Boolean novoAcesso) {
        this.novoAcesso = novoAcesso;
    }

}
