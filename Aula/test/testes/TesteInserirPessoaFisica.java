package testes;


import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.PessoaFisica;
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
 * @author jorge
 */
public class TesteInserirPessoaFisica {

    EntityManagerFactory emf;
    EntityManager em;

    public TesteInserirPessoaFisica() {
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
            PessoaFisica obj = new PessoaFisica();            
            obj.setNome("João");
            obj.setBairro("Centro");
            obj.setCidade(em.find(Cidade.class, 1));
            obj.setComplemento("Ap 400");
            obj.setCpf("067.615.752-14");
            obj.setEmail("jorge.bavaresco@passofundo.ifsul.edu.br");
            obj.setEndereco("Rua Sete de Setembro");
            obj.setNascimento(Calendar.getInstance());
            obj.setRg("8899334433");
            obj.setCep("99999-999");
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
