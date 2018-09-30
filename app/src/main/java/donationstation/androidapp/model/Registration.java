package donationstation.androidapp.model;

public class Registration {
    private String name;
    private String email;
    private String password;
    private boolean accountState;
    private String accountType;

    public Registration() {
        name = "n/a";
        email = "n/a";
        password = "n/a";
        accountState = true;
        accountType = "n/a";
    }

    public void setAccount(String acc) {
        accountType = acc;
    }

    public String getAccount() {
        return accountType;
    }
}
