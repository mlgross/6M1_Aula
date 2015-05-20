package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Grupo;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jorge Luis Boeira Bavaresco jorge.bavaresco@passofundo.ifsul.edu.br
 */
@Stateless
public class GrupoDAO implements Serializable {

    @PersistenceContext(unitName = "Financeiro6M1PU")
    private EntityManager em;
    private List<Grupo> listarTodos;

    public GrupoDAO() {

    }

    public void persistir(Grupo objeto) throws Exception {
        em.persist(objeto);
    }

    public void merge(Grupo objeto) throws Exception {
        em.merge(objeto);
    }

    public void remover(Grupo objeto) throws Exception {
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Grupo getObjectById(Integer id) throws Exception {
        return (Grupo) em.find(Grupo.class, id);
    }

    public List<Grupo> getListarTodos() {
        return em.createQuery("from Grupo order by nome").getResultList();
    }

    public void setListarTodos(List<Grupo> listarTodos) {
        this.listarTodos = listarTodos;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
