package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.AcessoUsuario;
import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marcelo
 */
@Stateless
public class UsuarioDAO implements Serializable {

    @PersistenceContext(unitName = "Financeiro6M1PU")
    private EntityManager em;
    private List<Usuario> listarTodos;
    
    public UsuarioDAO() {
    }
    
    public void persistir(Usuario objeto) throws Exception {
        em.persist(objeto);
    }
    
    public void merge(Usuario objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remover(Usuario objeto) throws Exception {
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Usuario getObjectById(Integer id) throws Exception {
        Usuario obj = em.find(Usuario.class, id);
        // fazer chamada ao método size para inicilizar as listas
        // isso evitar erro na tela quando as listas forem acessadas
       
        obj.getAcessos().size();
        return obj ;
    }
    
    public List<Usuario> getListarTodos() {
        return em.createQuery("from Usuario order by apelido").getResultList();
    }
    
    public void setListarTodos(List<Usuario> listarTodos) {
        this.listarTodos = listarTodos;
    }    
    
    public EntityManager getEm() {
        return em;
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
}
