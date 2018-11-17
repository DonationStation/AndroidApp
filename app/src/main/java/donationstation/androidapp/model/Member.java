package donationstation.androidapp.model;
import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to represent a Member
 */
public class Member {
    protected String name;
    protected String email;
    protected String password;
    protected String username;
    protected boolean accountState;
    protected int attempts;
    protected String accountType;
    protected String location;
    public static final Member INSTANCE = new Member();
    private ArrayList<Member> memberArray;

    /**
     * Empty constructor to create a member object array
     */
    public Member() {
        memberArray = new ArrayList<>();
    }

    /**
     * add member to member array
     * @param member the object to be added
     */
    public void add(Member member) {
        memberArray.add(member);
    }

    /**
     * list of members
     * @return the list of member objects
     */
    public List<Member> getMembers() {
        return memberArray;
    }

    /**
     * Constructor to create a member object
     * @param name member's name
     * @param email member's email
     * @param password member's password
     * @param username member's username
     * @param accountType member's account type
     */
    public Member(String name, String email, String password, String username, String accountType) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountState = true;
        this.accountType = accountType;
        this.location = location;
        this.attempts = 0;
    }

    /**
     * sets the member's account type
     * @param acc the type of account
     */
    public void setAccount(String acc) {
        accountType = acc;
    }

    /**
     * returns the member's account type
     * @return account type
     */
    public String getAccount() {
        return accountType;
    }

    /**
     * returns the member's username
     * @return username
     */
    public String getUsername() { return username; }

    /**
     * returns the member's email
     * @return email
     */
    public String getEmail() { return email; }

    /**
     * returns the member's password
     * @return password
     */
    public String getPassword() { return password; }

    /**
     * set members location
     * @param location location to set for member
     */
    public void setLocation(String location) { this.location = location; }

    /**
     * return members location
     * @return location
     */
    public String getLocation() {return location; }



    @Override
    public boolean equals(Object o){
        boolean checkEmail = this.email.equals( ((Member) o).email);
        boolean checkUsername = this.username.equals(((Member) o).username);
        return checkEmail && checkUsername;
    }

    @Override
    public String toString() {
        return "A member named " + name + ". And username " + username;
    }

    /**
     *  no clue what this does
     * @return A MAP
     */
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("email", email);
        result.put("password", password);
        result.put("username", username);
        result.put("accountType", accountType);
        result.put("location", location);

        return result;
    }

    /**
     * Finds a member in the array by email
     * @param email the email to look for in the array
     * @return the member object if found, null if not
     */
    public Member findMemberByEmail(String email) {
        for (Member m : memberArray) {
            if (m.getEmail().equals(email) ) return m;
        }
        return null;
    }
}
