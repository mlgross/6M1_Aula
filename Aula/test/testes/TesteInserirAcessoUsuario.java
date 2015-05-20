package testes;

import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.modelo.AcessoUsuario;
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
 * @author Marcelo Luis Gross
 */
public class TesteInserirAcessoUsuario {

    EntityManagerFactory emf;
    EntityManager em;

    public TesteInserirAcessoUsuario() {
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
            Usuario obj = em.find(Usuario.class, 2);
            
            AcessoUsuario a1 = new AcessoUsuario();
            a1.setData(Calendar.getInstance());
            obj.adicionarAcesso(a1);
           
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
