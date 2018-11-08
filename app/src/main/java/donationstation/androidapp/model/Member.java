package donationstation.androidapp.model;
import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Member {
    protected String name;
    protected String email;
    protected String password;
    protected String username;
    protected boolean accountState;
    protected String accountType;
    protected String location;
    public static final Member INSTANCE = new Member();
    private ArrayList<Member> memberArray;

    public Member() {memberArray = new ArrayList<>();}
    public void add(Member member) {memberArray.add(member);}
    public List<Member> getMembers() {
        return memberArray;
    }

    public Member(String name, String email, String password, String username, String accountType) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountState = true;
        this.accountType = accountType;
        this.location = location;
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
    public void setLocation(String location) { this.location = location; }
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

    public Member findMemberByEmail(String email) {
        for (Member m : memberArray) {
            if (m.getEmail().equals(email) ) return m;
        }
        return null;
    }
}
