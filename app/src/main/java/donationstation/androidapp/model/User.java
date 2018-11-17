package donationstation.androidapp.model;

/**
 * class to represent a User
 */
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


}