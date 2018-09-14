package builder;

import general.EmailAccount;

import java.time.Instant;

public class EmailAccountBuilder {

    private EmailAccount emailAccount;
    private String user = "anderson";
    private String domain = "demedeiros";
    private String password = "unittest1234";
    private Instant lastPasswordUpdate = Instant.now();


    public EmailAccountBuilder addUser(String user) {
        this.user = user;
        return this;
    }

    public EmailAccountBuilder addDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public EmailAccountBuilder addPassword(String pswd) {
        this.password = pswd;
        return this;
    }

    public EmailAccountBuilder addLastPasswordUpdate(Instant lastPasswordUpdate) {
        this.lastPasswordUpdate = lastPasswordUpdate;
        return this;
    }

    public EmailAccount build(){
        emailAccount = new EmailAccount("x","y","z",null);
        emailAccount.setUser(this.user);
        emailAccount.setDomain(this.domain);
        emailAccount.setPassword(this.password);
        emailAccount.setLastPasswordUpdate(this.lastPasswordUpdate);
        return emailAccount;
    }

}

