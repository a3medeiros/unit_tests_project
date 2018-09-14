package unit.tests;

import builder.EmailAccountBuilder;
import general.EmailAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class EmailAccountTest {

    EmailAccount emailAccount = new EmailAccount(null,null,null,null);
    EmailAccountBuilder accountBuilder;
    Instant now;

    @BeforeEach
    public void setUp() {
        accountBuilder = new EmailAccountBuilder();
        now = Instant.now();
    }

    @Test
    public void testUserHasSpecialCharacter() {
       /* EmailAccount aux = new EmailAccount();
        String user = "Andersond_emedeiros";
        EmailAccount.EmailAccountBuilder account = new EmailAccount.EmailAccountBuilder();
        account.addUser(user).build();
        Assertions.assertTrue(aux.userHasSpecial(user));*/
        String user = "Andersond_emedeiros";
        Assertions.assertTrue(emailAccount.userHasSpecial(user));

     /*   @Test
        public void testDomainHasSpecialCharacter () {
            EmailAccount aux = new EmailAccount();
            String domain = "a3medeiros.com";
            EmailAccount.EmailAccountBuilder account = new EmailAccount.EmailAccountBuilder();
            account.addDomain(domain).build();
            Assertions.assertTrue(aux.domainHasSpecial(domain));
        }*/

    }
}
