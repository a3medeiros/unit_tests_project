package general;

import interfac.EmailService;

import java.util.Collection;

public class EmailClient {

    private EmailService emailService;

    public void setEmailService(EmailService emailService){
        this.emailService = emailService;
    }

    public boolean isValidAddress(String emailAddress){
        throw new UnsupportedOperationException();
    }

    public boolean isValidEmail(Email email){
        throw new UnsupportedOperationException();
    }

    public Collection<Email> emailList(EmailAccount account){
        throw new UnsupportedOperationException();
    }

    public void sendEmail(Email email){
        throw new UnsupportedOperationException();
    }

    public boolean createAccount(EmailAccount account){
        throw new UnsupportedOperationException();
    }
}