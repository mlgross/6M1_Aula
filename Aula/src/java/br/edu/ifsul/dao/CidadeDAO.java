package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Cidade;
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
public class CidadeDAO implements Serializable {

    @PersistenceContext(unitName = "Financeiro6M1PU")
    private EntityManager em;
    private List<Cidade> listarTodos;
    
    public CidadeDAO() {
        
    }
    
    public void persistir(Cidade objeto) throws Exception {
        em.persist(objeto);
    }
    
    public void merge(Cidade objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remover(Cidade objeto) throws Exception {
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Cidade getObjectById(Integer id) throws Exception {
        return (Cidade) em.find(Cidade.class, id);
    }
    
    public List<Cidade> getListarTodos() {
        return em.createQuery("from Cidade order by nome").getResultList();
    }
    
    public void setListarTodos(List<Cidade> listarTodos) {
        this.listarTodos = listarTodos;
    }    
    
    public EntityManager getEm() {
        return em;
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
}
