package donationstation.androidapp.model;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Registration {
    protected String name;
    protected String email;
    protected String password;
    protected String username;
    protected boolean accountState;
    protected String accountType;
    private static ArrayList<Registration> registrationArray = new ArrayList<>();


    public Registration(String name, String email, String password, String username, String accountType) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountState = true;
        this.accountType = accountType;
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
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("email", email);
        result.put("password", password);
        result.put("username", username);
        result.put("accountType", accountType);

        return result;
    }

}
