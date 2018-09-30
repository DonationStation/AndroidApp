package donationstation.androidapp.model;
import java.util.ArrayList;

public abstract class Registration {
    private String name;
    private String email;
    private String password;
    private boolean accountState;
    private String accountType;
    private static ArrayList<Registration> registrationArray;

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

    public static ArrayList<Registration> getRegistrationArray() {
        return registrationArray;
    }

    @Override
    public boolean equals(Object o){
        boolean checkEmail = this.email.equals( ((Registration) o).email);
        boolean checkPassword = this.name.equals(((Registration) o).name);
        return checkEmail && checkPassword;
    }
}
