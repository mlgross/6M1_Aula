package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Produto;
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
public class ProdutoDAO implements Serializable {

    @PersistenceContext(unitName = "Financeiro6M1PU")
    private EntityManager em;
    private List<Produto> listarTodos;

    public ProdutoDAO() {

    }

    public void persistir(Produto objeto) throws Exception {
        em.persist(objeto);
    }

    public void merge(Produto objeto) throws Exception {
        em.merge(objeto);
    }

    public void remover(Produto objeto) throws Exception {
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Produto getObjectById(Integer id) throws Exception {
        return (Produto) em.find(Produto.class, id);
    }

    public List<Produto> getListarTodos() {
        return em.createQuery("from Produto order by nome").getResultList();
    }

    public void setListarTodos(List<Produto> listarTodos) {
        this.listarTodos = listarTodos;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
