package donationstation.androidapp.model;
import java.util.ArrayList;

public abstract class Registration {
    protected String name;
    protected String email;
    protected String password;
    protected String username;
    private boolean accountState;
    protected String accountType;
    private static ArrayList<Registration> registrationArray = new ArrayList<>();

    public Registration(String name, String email, String password, String username) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountState = true;
    }

    public void setAccount(String acc) {
        accountType = acc;
    }
    public String getAccount() {
        return accountType;
    }


    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public static ArrayList<Registration> getRegistrationArray() {
        return registrationArray;
    }

    @Override
    public boolean equals(Object o){
        boolean checkEmail = this.email.equals( ((Registration) o).email);
        boolean checkUsername = this.username.equals(((Registration) o).username);
        return checkEmail && checkUsername;
    }

    @Override
    public String toString() {
        return "A member named " + name + ". And username " + username;
    }
}
