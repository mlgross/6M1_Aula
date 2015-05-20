package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Estado;
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
public class EstadoDAO implements Serializable {

    @PersistenceContext(unitName = "Financeiro6M1PU")
    private EntityManager em;
    private List<Estado> listarTodos;
    
    public EstadoDAO() {
        
    }
    
    public void persistir(Estado objeto) throws Exception {
        em.persist(objeto);
    }
    
    public void merge(Estado objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remover(Estado objeto) throws Exception {
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Estado getObjectById(Integer id) throws Exception {
        return (Estado) em.find(Estado.class, id);
    }
    
    public List<Estado> getListarTodos() {
        return em.createQuery("from Estado order by nome").getResultList();
    }
    
    public void setListarTodos(List<Estado> listarTodos) {
        this.listarTodos = listarTodos;
    }    
    
    public EntityManager getEm() {
        return em;
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
}
