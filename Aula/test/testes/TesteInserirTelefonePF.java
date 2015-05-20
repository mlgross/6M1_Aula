package testes;

import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Telefone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jorge
 */
public class TesteInserirTelefonePF {

    EntityManagerFactory emf;
    EntityManager em;

    public TesteInserirTelefonePF() {
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("Financeiro6M1PULocal");
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    public void testar() {
        boolean exception = false;
        try {
            PessoaFisica obj = em.find(PessoaFisica.class, 1);
            Telefone t1 = new Telefone();
            t1.setNumero("(54)9999-9999");
            t1.setDescricao("Casa");
            obj.adicionarTelefone(t1);
            Telefone t2 = new Telefone();
            t2.setNumero("(54)8877-9899");
            t2.setDescricao("Celular");
            obj.adicionarTelefone(t2);
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);
    }

}
