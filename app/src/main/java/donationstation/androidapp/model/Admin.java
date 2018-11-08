package donationstation.androidapp.model;

public class Admin extends Member {

    /**
     * constructor to create an admin object
     * @param name admin's name
     * @param email admin's email
     * @param password admin's password
     * @param username admin's username
     * @param accountType admin's account type
     */
    public Admin(String name, String email, String password, String username, String accountType) {
        super(name, email, password, username, accountType);
    }

    /**
     * sets admins's account state
     * @param newState boolean value of whether account is locked or not
     */
    public void setAccountState(boolean newState) { super.accountState = newState; }
    /**
     * gets the admin's account sate
     * @return boolean value of status
     */
    public boolean getAccountState() { return super.accountState; }
}
