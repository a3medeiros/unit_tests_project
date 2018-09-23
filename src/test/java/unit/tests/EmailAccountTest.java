package unit.tests;

import unit.tests.builder.EmailAccountBuilder;
import unit.tests.general.EmailAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class EmailAccountTest {

    EmailAccount emailAccount = new EmailAccount(null,null,null,null);
    EmailAccountBuilder accountBuilder;
    Instant now;
    String user;
    String domain;

    @BeforeEach
    public void setUp() {
        accountBuilder = new EmailAccountBuilder(emailAccount);
        now = Instant.now();
    }

    @Test
    public void testUserHasSpecialCharacter_True() {
        user = "Ande-rsond_em.edeiros";
        Assertions.assertTrue(accountBuilder.userHasSpecial(user));
    }

  @Test
    public void testDomainHasSpecialCharacter_True () {
        domain = "a3medeiros.com";
        Assertions.assertTrue(accountBuilder.domainHasSpecial(domain));
    }

    @Test
    public void testUserHasSpecialCharacter_False() {
        user = "Ande2%#@Q@!iros";
        Assertions.assertFalse(accountBuilder.userHasSpecial(user));
    }

    @Test
    public void testDomainHasSpecialCharacterDot_False () {
        String domain = ".a3medeiros.com";
        Assertions.assertFalse(accountBuilder.domainHasSpecial(domain));
    }

    @Test
    public void testDomainHasSpecialCharacter_False () {
        String domain = "a!#3medeiros.com";
        Assertions.assertFalse(accountBuilder.domainHasSpecial(domain));
    }

    @Test
    public void testUserAvailability () {
        String user = "anderson1@br.com";
        String[] returnFromValidAdress = user.split("@");
        emailAccount.setUser(returnFromValidAdress[0]);
        emailAccount.setDomain(returnFromValidAdress[1]);
        Assertions.assertTrue(accountBuilder.userAndDomainAvailability());
    }

        @Test
    public void testPasswordExpirationAfter89days_False() {

        Instant instant89DaysAgo = now.minus(89, ChronoUnit.DAYS);
        emailAccount = accountBuilder.addLastPasswordUpdate(instant89DaysAgo).build();
        Assertions.assertFalse(accountBuilder.verifyPasswordExpiration());
    }

    @Test
    public void testPasswordExpirationAfter90days_False() {

        Instant instant90DaysAgo = now.minus(90, ChronoUnit.DAYS);
        emailAccount = accountBuilder.addLastPasswordUpdate(instant90DaysAgo).build();
        Assertions.assertFalse(accountBuilder.verifyPasswordExpiration());
    }

    @Test
    public void testPasswordExpirationAfter91days_True() {

        Instant instant91DaysAgo = now.minus(91, ChronoUnit.DAYS);
        emailAccount = accountBuilder.addLastPasswordUpdate(instant91DaysAgo).build();
        Assertions.assertTrue(accountBuilder.verifyPasswordExpiration());
    }
}
