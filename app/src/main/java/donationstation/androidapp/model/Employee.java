package donationstation.androidapp.model;

/**
 * Class to represent Employee
 */
public class Employee extends Member {
    /**
     * constructor for creation of employee object
     * @param name the employee's name
     * @param email employee's email
     * @param password employee's password
     * @param username employee's username
     * @param accountType employee's account type
     */
    public Employee(String name, String email, String password,
                    String username, String accountType) {
        super(name, email, password, username, accountType);
    }

    /**
     * sets employee's account state
     * @param newState boolean value of whether account is locked or not
     */
    public void setAccountState(boolean newState) { super.accountState = newState; }
    /**
     * gets the employee's account sate
     * @return boolean value of status
     */
    public boolean getAccountState() { return super.accountState; }
}

