package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Venda;
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
public class VendaDAO implements Serializable {

    @PersistenceContext(unitName = "Financeiro6M1PU")
    private EntityManager em;
    private List<Venda> listarTodos;
    
    public VendaDAO() {
    }
    
    public void persistir(Venda objeto) throws Exception {
        em.persist(objeto);
    }
    
    public void merge(Venda objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remover(Venda objeto) throws Exception {
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Venda getObjectById(Integer id) throws Exception {
        Venda obj = em.find(Venda.class, id);
        // fazer chamada ao método size para inicilizar as listas
        // isso evitar erro na tela quando as listas forem acessadas
       
        obj.getVendaItens().size();
        return obj ;
    }
    
    public List<Venda> getListarTodos() {
        return em.createQuery("from Venda order by data_venda").getResultList();
    }
    
    public void setListarTodos(List<Venda> listarTodos) {
        this.listarTodos = listarTodos;
    }    
    
    public EntityManager getEm() {
        return em;
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
}
