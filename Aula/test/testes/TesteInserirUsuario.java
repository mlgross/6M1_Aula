package testes;

import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Usuario;
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
public class TesteInserirUsuario {

    EntityManagerFactory emf;
    EntityManager em;

    public TesteInserirUsuario() {
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
            Usuario obj = new Usuario();
            obj.setNome("Jacobs Helbert Vulsky");
            obj.setBairro("Centro");
            obj.setCidade(em.find(Cidade.class, 1));
            obj.setComplemento("Ap 215");
            obj.setCpf("067.615.752-14");
            obj.setEmail("jhvulsky@gmail.com.br");
            obj.setEndereco("Rua Sete de Setembro");
            obj.setNascimento(Calendar.getInstance());
            obj.setRg("8899334433");
            obj.setCep("99999-999");

            obj.setApelido("Jaco");
            obj.setAtivo(true);
            obj.setAdministrador(true);
            obj.setSenha("6FranceSky288-");
            
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
