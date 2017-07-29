package quetzalsoft.berserkr.qm;

/**
 * Created by lbathen on 7/6/17.
 */

import java.util.UUID;

public class UserProfile {
    private String email;
    private String username;
    private String password;
    private String salt;
    private UUID id;
    private String bcSecretKey; // in base 64 url safe format
    private String bcPublicKey; // in base 64 url safe format
    private String bcAddress;
    private String rsaSecretKey;
    private String rsaPublicKey;

    public UserProfile() {
        this.id = UUID.randomUUID();
    }

    public  UserProfile(String email, String username, String password)
    {
        this.email = email;
        this.username = username;
        this.password = password;
        this.id = UUID.randomUUID();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getUsername() {
        return username;
    }

    public UUID getId() {
        return id;
    }

    public String getBcAddress() {
        return bcAddress;
    }

    public String getBcPublicKey() {
        return bcPublicKey;
    }

    public String getBcSecretKey() {
        return bcSecretKey;
    }

    public String getRsaPublicKey() {
        return rsaPublicKey;
    }

    public String getRsaSecretKey() {
        return rsaSecretKey;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setBcAddress(String bcAddress) {
        this.bcAddress = bcAddress;
    }

    public void setBcPublicKey(String bcPublicKey) {
        this.bcPublicKey = bcPublicKey;
    }

    public void setBcSecretKey(String bcSecretKey) {
        this.bcSecretKey = bcSecretKey;
    }

    public void setRsaPublicKey(String rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }

    public void setRsaSecretKey(String rsaSecretKey) {
        this.rsaSecretKey = rsaSecretKey;
    }
}
