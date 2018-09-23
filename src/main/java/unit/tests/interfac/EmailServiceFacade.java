package unit.tests.interfac;

import unit.tests.general.Email;
import unit.tests.general.EmailAccount;

import java.util.Collection;

public interface EmailServiceFacade {

    boolean sendEmail(Email email);

    Collection<Email> emailList(EmailAccount account);

}