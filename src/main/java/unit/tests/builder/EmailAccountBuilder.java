package unit.tests.builder;

import unit.tests.general.EmailAccount;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailAccountBuilder {

    private EmailAccount emailAccount;

    public EmailAccountBuilder(EmailAccount emailAccountToValid) {
        this.emailAccount = emailAccountToValid;
    }

    public EmailAccount build(){
        return this.emailAccount;
    }

    public boolean verifyPasswordExpiration(){
        Instant aux;
        //TODO [mmcj]: Use dependency injection instead of instantiate Instant
        Instant now = Instant.now();
        aux = now.minus(91, ChronoUnit.DAYS);

        if (aux.isAfter(this.emailAccount.getLastPasswordUpdate())||aux.equals(this.emailAccount.getLastPasswordUpdate()))
            return true;
        else
            return false;
    }

    public boolean userHasSpecial(String user) {
        //System.out.println(user);
        return user.matches("[0-9a-zA-Z(.)|(\\-)|(_)]+");
    }

    public boolean domainHasSpecial(String domain){
        //System.out.println(domain);
        return domain.matches("\\b((?=[a-z0-9-]{1,}\\.)[a-z0-9]+(-[a-z0-9]+)*\\.)+[a-z]{2,}\\b");
    }

    public boolean userAndDomainAvailability() {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(this.emailAccount.getUser()+"@"+this.emailAccount.getDomain());
        return matcher.find();

    }

    public EmailAccountBuilder addLastPasswordUpdate(Instant lastPasswordUpdate) {
        this.emailAccount.setLastPasswordUpdate(lastPasswordUpdate);
        return this;
    }
}

