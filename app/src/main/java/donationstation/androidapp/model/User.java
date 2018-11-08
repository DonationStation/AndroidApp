package donationstation.androidapp.model;

public class User extends Member {
    /**
     * constructor for creation of user object
     * @param name the user's name
     * @param email user's email
     * @param password user's password
     * @param username user's username
     * @param accountType user's account type
     */
    public User(String name, String email, String password, String username, String accountType) {
        super(name, email, password, username, accountType);
    }

    /**
     * sets user's account state
     * @param newState boolean value of whether account is locked or not
     */
    public void setAccountState(boolean newState) { super.accountState = newState; }

    /**
     * gets the user's account sate
     * @return boolean value of status
     */
    public boolean getAccountState() { return super.accountState; }
}