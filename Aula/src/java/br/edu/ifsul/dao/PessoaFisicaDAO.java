package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.PessoaFisica;
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
public class PessoaFisicaDAO implements Serializable {

    @PersistenceContext(unitName = "Financeiro6M1PU")
    private EntityManager em;
    private List<PessoaFisica> listarTodos;
    
    public PessoaFisicaDAO() {
        
    }
    
    public void persistir(PessoaFisica objeto) throws Exception {
        em.persist(objeto);
    }
    
    public void merge(PessoaFisica objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remover(PessoaFisica objeto) throws Exception {
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public PessoaFisica getObjectById(Integer id) throws Exception {
        PessoaFisica obj = em.find(PessoaFisica.class, id);
        // fazer chamada ao método size para inicilizar as listas
        // isso evitar erro na tela quando as listas forem acessadas
       
        obj.getTelefones().size();
        obj.getDesejos().size();                
        return obj ;
    }
    
    public List<PessoaFisica> getListarTodos() {
        return em.createQuery("from PessoaFisica order by nome").getResultList();
    }
    
    public void setListarTodos(List<PessoaFisica> listarTodos) {
        this.listarTodos = listarTodos;
    }    
    
    public EntityManager getEm() {
        return em;
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
}
