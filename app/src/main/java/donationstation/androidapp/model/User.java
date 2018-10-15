package donationstation.androidapp.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class User extends Registration {
    public User(String name, String email, String password, String username) {
        super(name, email, password, username);
    }

    public void setAccountState(boolean newState) { super.accountState = newState; }
    public boolean getAccountState() { return super.accountState; }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("email", email);
        result.put("password", password);
        result.put("username", username);

        return result;
    }

}