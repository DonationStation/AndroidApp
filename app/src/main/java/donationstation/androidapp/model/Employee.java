package donationstation.androidapp.model;

public class Employee extends  Registration {
    public Employee(String name, String email, String password, String username) {
        super(name, email, password, username);
    }

    public void setAccountState(boolean newState) { super.accountState = newState; }
    public boolean getAccountState() { return super.accountState; }
}

