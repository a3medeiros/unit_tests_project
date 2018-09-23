package unit.tests.general;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailAccount {
    private String user;
    private String domain;
    private String password;
    private Instant lastPasswordUpdate;
    private Collection<Email> emailList;

    public EmailAccount(String user, String domain, String pswd, Instant lastPasswordUpdate){
        this.user = user;
        this.domain = domain;
        this.password = pswd;
        this.lastPasswordUpdate = lastPasswordUpdate;
        this.emailList = new ArrayList<>();
    }

    public String getPassword() {       return password;    }

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

    public Collection<Email> getEmailList() {
        return emailList;
    }

    public Instant getLastPasswordUpdate() {
        return lastPasswordUpdate;
    }
}