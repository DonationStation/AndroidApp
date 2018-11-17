package donationstation.androidapp;

import org.junit.Test;

import donationstation.androidapp.model.Donation;
import donationstation.androidapp.model.DonationItem;
import donationstation.androidapp.model.Employee;
import donationstation.androidapp.model.Location;
import donationstation.androidapp.model.LocationModel;
import donationstation.androidapp.model.Member;
import donationstation.androidapp.model.User;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;


/**
 * Model to Test
 */
public class ModelTest {
    Location locTest = new Location(123, "name", "latitude",
            "longitude",
            "address", "city", "state", "zip", "type", "phone",
            "website");

    LocationModel locations = LocationModel.INSTANCE;

    DonationItem donTest = new DonationItem("11/7/2018", "18:00",
            "AFD STATION 4", "CAT A", 32.3, "short",
            "long");

    Donation donations = Donation.INSTANCE;

    Member members = Member.INSTANCE;
    Member m = new Member("test", "testing@example.com",
            "password", "test", "user");
    Employee e = new Employee("EmplyeeTest", "EmpoyeeTestJunitEmail@example.com",
            "EmployeePassword", "Username", "employee");
    User u = new User("UserTest", "UserTestJunitEmail@example.com",
            "UserPassword", "UserTestUserName", "user");

    /**
     * method to conduct testings
     * Kaushi's method
     */
    @Test
    public void testLocationModel() {
        locations.addItem(locTest);
        assertEquals(locTest, locations.findItemByKey(123));
        assertTrue(locTest.getName().equals(locations.findItemByKey(123).getName()));
        assertEquals(null, locations.findItemByKey(456));
        assertNotSame(locTest,(locations.findItemByKey(456)));
    }
    /**
     * method to conduct testings
     * Garrett's method
     */
    @Test
    public void testDonationModel() {
        donations.addItem(donTest);
        assertEquals(donTest, donations.findItemByCat("CAT A"));
        assertTrue(donTest.getCategory().equals(donations.findItemByCat("CAT A").getCategory()));
        assertEquals(null, donations.findItemByCat("CAT B"));
        assertNotSame(donTest, donations.findItemByCat("CAT B"));
    }
    /**
     * method to conduct testings
     * Ricky's method
     */
    @Test
    public void testMemberModel() {
        members.add(m);
        assertTrue(m.getAccount().equals("user"));
        assertEquals(m, members.findMemberByEmail("testing@example.com"));
        assertNotSame(m, members.findMemberByEmail("test2@example.com"));
    }
    /**
     * method to conduct testings
     * Carl's method
     */
    @Test
    public void testEmployeeModel() {
        members.add(e);
        assertTrue(e.getAccount().equals("employee"));
        assertEquals(e,members.findMemberByEmail("EmpoyeeTestJunitEmail@example.com"));
        assertNotSame(e, members.findMemberByEmail("testing@example.com"));
    }
    /**
     * method to conduct testings
     * Joon's method
     */
    @Test
    public void testUserModel() {
        members.add(u);
        assertTrue(u.getAccount().equals("user"));
        assertEquals(u, members.findMemberByEmail("UserTestJunitEmail@example.com"));
        assertNotSame(u, members.findMemberByEmail("testing@example.com"));
    }
}
