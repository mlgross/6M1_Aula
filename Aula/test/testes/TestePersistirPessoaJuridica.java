package testes;

import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.PessoaJuridica;
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
public class TestePersistirPessoaJuridica {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirPessoaJuridica() {
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
    public void teste() {
        boolean exception = false;
        try {
            PessoaJuridica obj = new PessoaJuridica();
            obj.setNome("New System");
            obj.setBairro("Centro");
            obj.setCep("99999-999");
            obj.setCidade(em.find(Cidade.class, 1));
            obj.setComplemento("AP 400");
            obj.setCnpj("67.697.493/0001-31");
            obj.setEmail("jorge.bavaresco@passofundo.ifsul.edu.br");
            obj.setEndereco("Rua tal");
            obj.setIe("888888438");
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        // verificando se o resultado é igual ao esperado
        Assert.assertEquals(false, exception);
    }

}
