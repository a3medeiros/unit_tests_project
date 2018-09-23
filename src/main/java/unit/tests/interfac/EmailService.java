package unit.tests.interfac;

import unit.tests.general.Email;
import unit.tests.general.EmailAccount;

import java.util.Collection;

public class EmailService implements EmailServiceFacade {

    @Override
    public boolean sendEmail(Email email) {
        return true;
    }

    @Override
    public Collection<Email> emailList(EmailAccount account) {
        return account.getEmailList();
    }
}
