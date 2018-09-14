package general;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class EmailAccount {
    private String user;
    private String domain;
    private String password;
    private Instant  lastPasswordUpdate;

    public EmailAccount(String user, String domain, String pswd, Instant lastPasswordUpdate){
        this.user = user;
        this.domain = domain;
        this.password = pswd;
        this.lastPasswordUpdate = lastPasswordUpdate;
    }

    public String getUser() {
        return user;
    }

    public String getDomain() {
        return domain;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setLastPasswordUpdate(Instant lastPasswordUpdate) {
        this.lastPasswordUpdate = lastPasswordUpdate;
    }

    public boolean verifyPasswordExpiration(){
        Instant aux;
        Instant now = Instant.now();
        aux = now.minus(91, ChronoUnit.DAYS);

        if (aux.isAfter(lastPasswordUpdate))
            return true;
        else
            return false;
    }

    public boolean userHasSpecial(String user) {
        System.out.println(user);
       return user.matches("[0-9a-zA-Z(.)|(\\-)|(_)]+");
    }

    public boolean domainHasSpecial(String domain){
        //System.out.println(domain);
        return domain.matches("\\b((?=[a-z0-9-]{1,}\\.)[a-z0-9]+(-[a-z0-9]+)*\\.)+[a-z]{2,}\\b");
    }

}