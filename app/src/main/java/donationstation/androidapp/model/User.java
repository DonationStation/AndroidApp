package donationstation.androidapp.model;

public class User extends Member {
    public User(String name, String email, String password, String username, String accountType) {
        super(name, email, password, username, accountType);
    }

    public void setAccountState(boolean newState) { super.accountState = newState; }
    public boolean getAccountState() { return super.accountState; }
}