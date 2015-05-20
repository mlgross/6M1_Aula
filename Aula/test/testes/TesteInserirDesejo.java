package testes;

import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.Telefone;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Marcelo
 */
public class TesteInserirDesejo {

    EntityManagerFactory emf;
    EntityManager em;

    public TesteInserirDesejo() {
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
            Produto pro = em.find(Produto.class, 1);
            obj.adicionarDesejos(pro);
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
