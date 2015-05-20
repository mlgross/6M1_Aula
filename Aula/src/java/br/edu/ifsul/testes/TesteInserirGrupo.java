package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Grupo;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.PessoaFisica;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jorge Luis Boeira Bavaresco jorge.bavaresco@passofundo.ifsul.edu.br
 */
public class TesteInserirGrupo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("Financeiro6M1PULocal");
        EntityManager em = emf.createEntityManager();
        Grupo g = new Grupo();
        g.setNome("Notebooks");
        em.getTransaction().begin();
        em.persist(g);
        em.getTransaction().commit();
        em.close();
        emf.close();
                
    }

}
