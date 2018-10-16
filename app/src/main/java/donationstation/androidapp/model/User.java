package donationstation.androidapp.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class User extends Registration {
    public User(String name, String email, String password, String username, String accountType) {
        super(name, email, password, username, accountType);
    }

    public void setAccountState(boolean newState) { super.accountState = newState; }
    public boolean getAccountState() { return super.accountState; }
}