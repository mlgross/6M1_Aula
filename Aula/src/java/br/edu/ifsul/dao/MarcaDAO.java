package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Marca;
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
public class MarcaDAO implements Serializable {

    @PersistenceContext(unitName = "Financeiro6M1PU")
    private EntityManager em;
    private List<Marca> listarTodos;

    public MarcaDAO() {

    }

    public void persistir(Marca objeto) throws Exception {
        em.persist(objeto);
    }

    public void merge(Marca objeto) throws Exception {
        em.merge(objeto);
    }

    public void remover(Marca objeto) throws Exception {
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Marca getObjectById(Integer id) throws Exception {
        return (Marca) em.find(Marca.class, id);
    }

    public List<Marca> getListarTodos() {
        return em.createQuery("from Marca order by nome").getResultList();
    }

    public void setListarTodos(List<Marca> listarTodos) {
        this.listarTodos = listarTodos;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
