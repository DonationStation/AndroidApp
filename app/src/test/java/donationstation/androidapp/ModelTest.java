package donationstation.androidapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;

import donationstation.androidapp.model.Donation;
import donationstation.androidapp.model.DonationItem;
import donationstation.androidapp.model.Location;
import donationstation.androidapp.model.LocationModel;
import donationstation.androidapp.model.Member;
import donationstation.androidapp.model.Employee;


import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;




public class ModelTest {
    Location locTest = new Location(123, "name", "latitude", "longitude",
            "address", "city", "state", "zip", "type", "phone",
            "website");

    LocationModel locations = LocationModel.INSTANCE;

    DonationItem donTest = new DonationItem("11/7/2018", "18:00", "AFD STATION 4", "CAT A", 32.3, "short",
            "long");

    Donation donations = Donation.INSTANCE;

    Member members = Member.INSTANCE;
    Member m = new Member("test", "testing@example.com", "password", "test", "user");

    @Test
    public void testLocationModel() {
        locations.addItem(locTest);
        assertEquals(locTest, locations.findItemByKey(123));
        assertTrue(locTest.getName().equals(locations.findItemByKey(123).getName()));
        assertEquals(null, locations.findItemByKey(456));
        assertNotSame(locTest,(locations.findItemByKey(456)));
    }

    @Test
    public void testDonationModel() {
        donations.addItem(donTest);
        assertEquals(donTest, donations.findItemByCat("CAT A"));
        assertTrue(donTest.getCategory().equals(donations.findItemByCat("CAT A").getCategory()));
        assertEquals(null, donations.findItemByCat("CAT B"));
        assertNotSame(donTest, donations.findItemByCat("CAT B"));
    }

    @Test
    public void testMemberModel() {
        members.add(m);
        assertTrue(m.getAccount().equals("user"));
        assertEquals(m, members.findMemberByEmail("testing@example.com"));
        assertNotSame(m, members.findMemberByEmail("test2@example.com"));
    }
    
    @Test
    public void testEmplyeeModel() {
        members.add(e);
        assertTrue(e.getAccount().equals("employee"));
        assertEquals(e,members.findMemberByEmail("EmpoyeeTestJunitEmail@example.com"));
        assertNotSame(e, members.findMemberByEmail("testing@example.com"));
    }
}
