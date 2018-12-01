package unit.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unit.tests.builder.EmailAccountBuilder;
import unit.tests.general.Email;
import unit.tests.general.EmailAccount;
import unit.tests.general.EmailClient;
import unit.tests.interfac.EmailService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class EmailClientTest {

    private final String subject = "Hello World :D";
    private final String message = "Hello World 2 :D";
    private EmailAccount emailAccount = new EmailAccount(null,null,null,null);
    private EmailAccountBuilder emailAcc = new EmailAccountBuilder(emailAccount);
    private EmailClient client = new EmailClient(new ArrayList<>());
    private Email email;

    @BeforeEach
    public void setUp() {
        Collection<String> emailsList = new ArrayList<String>() {{ add("anderson1@test.com.br"); add("anderson2@test.com.br"); add("anderson3@test.com.br"); }};

        //email acc
        String user = "anderson1@br.com";
        String[] returnFromValidAdress = user.split("@");
        emailAccount.setUser(returnFromValidAdress[0]);
        emailAccount.setDomain(returnFromValidAdress[1]);
        emailAccount.setPassword("0123456");
        emailAccount.setLastPasswordUpdate(Instant.now());

        this.email = new Email();
        this.email.setFrom(user);
        this.email.setCreationDate(Instant.now());
        this.email.setTo(emailsList);
        this.email.setBcc(emailsList);
        this.email.setCc(emailsList);
        this.email.setMessage(message);
        this.email.setSubject(subject);

    }

    @Test
    public void sendEmailTest_True() {
        try{
            this.client.sendEmail(this.email, emailAccount);
        } catch (RuntimeException e) {
           Assertions.fail();
        }
    }

    @Test
    public void sendEmailSubject_True() {
        try{
            Assertions.assertEquals(subject, this.email.getSubject());
        } catch (RuntimeException e) {
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void sendEmailMessage_True() {
        try{
            Assertions.assertEquals(message, this.email.getMessage());
        } catch (RuntimeException e) {
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void createAccPasswordLessThanSixTest_False() {
        try{
            this.emailAccount.setPassword("012345");
            this.client.createAccount(emailAcc);
        } catch (RuntimeException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void createAccTest_True() {
        try{
            this.client.createAccount(emailAcc);
        } catch (NullPointerException e) {
            Assertions.fail();
        }
    }

    @Test
    public void createAccInvalidDomainTest_False() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            this.emailAccount.setDomain("!br.com");
            this.client.createAccount(emailAcc);
        });
    }

    @Test
    public void createAccNullListTest_True() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            this.client.setAccounts(null);
            this.client.createAccount(emailAcc);
        });
    }

    @Test
    public void createAccEmailListTest_True() {
        try{
            this.client.createAccount(emailAcc);
            Assertions.assertTrue(this.client.emailList(emailAccount).isEmpty());
        } catch (UnsupportedOperationException e) {
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void createAccEmailListNullTest_True() {
        try{
            this.client.createAccount(emailAcc);
            Assertions.assertTrue(this.client.emailList(null).isEmpty());
        } catch (UnsupportedOperationException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void createAccEmailListInvalidPassTest_True() {
        try{
            this.emailAccount.setPassword("123");
            Collection<Email> list = this.client.emailList(emailAccount);
            Assertions.assertTrue(false);
        } catch (UnsupportedOperationException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void createAccEmailListTest_False() {
        try{
            for (int x = 0; x < 10; x++ ) {
                this.client.sendEmail(this.email, emailAccount);
            }
            this.client.createAccount(emailAcc);
            Assertions.assertEquals(10, this.client.emailList(emailAccount).size());
        } catch (UnsupportedOperationException e) {
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void isValidPasswordTest_True() {
        Assertions.assertTrue(this.client.isValidPassword(this.emailAccount));
    }

    @Test
    public void isValidPasswordTest_False() {
        this.emailAccount.setPassword("123");
        Assertions.assertFalse(this.client.isValidPassword(this.emailAccount));
    }

    @Test
    public void isValidEmailTest_True() {
        Assertions.assertTrue(this.client.isValidEmail(this.email));
    }

    @Test
    public void isValidEmailTest_False() {
        this.email.setFrom("123");
        Assertions.assertFalse(this.client.isValidEmail(this.email));
    }

}