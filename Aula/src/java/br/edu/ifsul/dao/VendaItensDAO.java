package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.VendaItens;
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
public class VendaItensDAO implements Serializable {

    @PersistenceContext(unitName = "Financeiro6M1PU")
    private EntityManager em;
    private List<VendaItens> listarTodos;

    public VendaItensDAO() {
    }

    public void persistir(VendaItens objeto) throws Exception {
        em.persist(objeto);
    }

    public void merge(VendaItens objeto) throws Exception {
        em.merge(objeto);
    }

    public void remover(VendaItens objeto) throws Exception {
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public VendaItens getObjectById(Integer id) throws Exception {
        return (VendaItens) em.find(VendaItens.class, id);
    }

    public List<VendaItens> getListarTodos() {
        return em.createQuery("from VendaItens order by valortotal").getResultList();
    }

    public void setListarTodos(List<VendaItens> listarTodos) {
        this.listarTodos = listarTodos;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
