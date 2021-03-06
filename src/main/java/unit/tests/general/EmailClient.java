package unit.tests.general;

import unit.tests.builder.EmailAccountBuilder;
import unit.tests.interfac.EmailService;
import unit.tests.interfac.EmailServiceFacade;

import java.time.Instant;
import java.util.Collection;

public class EmailClient {
    private EmailServiceFacade emailService;
    private Collection<EmailAccount> accounts;

    public EmailClient(Collection<EmailAccount> accounts){
        this.emailService = new EmailService();
        this.accounts = accounts;
    }

    public boolean isValidPassword(EmailAccount account){
        EmailAccountBuilder emailAcc = new EmailAccountBuilder(account);
        if(account.getPassword().length() > 6 && !emailAcc.verifyPasswordExpiration()){
            return true;
        }
        return false;
    }

    public boolean isValidAddress(String emailAddress) {
        String[] returnFromValidAdress = emailAddress.split("@");

        try {
            EmailAccount emailAccountToValid = new EmailAccount(returnFromValidAdress[0], returnFromValidAdress[1], null, null);
            EmailAccountBuilder emailAcc = new EmailAccountBuilder(emailAccountToValid);
            if (isValidAcc(emailAcc.userAndDomainAvailability())) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    private boolean isValidCreationDate(Email email) {
        return email.getCreationDate() != null;
    }

    private boolean isValidFrom(Email email) {
        return isValidAddress(email.getFrom());
    }

    private boolean isValidTo(Email email) {

        boolean validTo = false;
        for (String toMailAdress : email.getTo()) {
            validTo = validTo || isValidAddress(toMailAdress);
        }

        return validTo;
    }

    private boolean validationOfCcMail(Email email) {

        boolean validCcs = false;
        for (String ccMailAdress : email.getCc()) {
            validCcs = validCcs || isValidAddress(ccMailAdress);
        }
        return validCcs;

    }

    private boolean validationOfBccMail(Email email) {

        boolean validBccs = false;
        for (String bccMailAdress : email.getBcc()) {
            validBccs = validBccs || isValidAddress(bccMailAdress);
        }
        return validBccs;
    }

    public boolean isValidEmail(Email email) {

        boolean validCreationDate = isValidCreationDate(email);
        boolean validFrom = isValidFrom(email);
        boolean validToMail = isValidTo(email);
        boolean validBccMail = validationOfBccMail(email);
        boolean validCcMail = validationOfCcMail(email);

        return validCreationDate && validFrom && validToMail && validBccMail && validCcMail;
    }

    public Collection<Email> emailList(EmailAccount account){
        try {
            if (isValidPassword(account)) {
                return this.emailService.emailList(account);
            } else {
                throw new UnsupportedOperationException();
            }
        } catch (NullPointerException e) {
            throw new UnsupportedOperationException();
        }
    }

    public void sendEmail(Email email, EmailAccount account) {
        if (this.isValidEmail(email)) {
            try {
                if (this.emailService.sendEmail(email)) {
                    account.getEmailList().add(email);
                    this.accounts.remove(account);
                    this.accounts.add(account);
                } else {
                    throw new UnsupportedOperationException();
                }
            } catch (UnsupportedOperationException e) {
                throw new UnsupportedOperationException();
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public boolean createAccount(EmailAccountBuilder account){
        if (account.build().getUser().isEmpty()) {
            return false;
        }
        if (isValidAcc(this.isValidPassword(account.build()) && account.userAndDomainAvailability())) {
            try {
                account.build().setLastPasswordUpdate(Instant.now());
                return this.accounts.add(account.build());
            } catch (NullPointerException e) {
                throw new UnsupportedOperationException();
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    private boolean isValidAcc(boolean validUser) {
        return validUser;
    }

    public void setAccounts(Collection<EmailAccount> accounts) {
        this.accounts = accounts;
    }

    public EmailServiceFacade getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailServiceFacade emailService) {
        this.emailService = emailService;
    }

    public Collection<EmailAccount> getAccounts() {
        return accounts;
    }
}