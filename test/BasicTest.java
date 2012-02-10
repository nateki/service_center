import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;


public class BasicTest extends UnitTest {
    @Before
    public void setup() {
        Fixtures.deleteDatabase();
    
    }
     
     @Test
public void createAndRetrieveCustomer() {
    // Create a new Customer and save it
    new Customer("bob@gmail.com", "secret", "Bob").save();

    // Retrieve the Customer with e-mail address bob@gmail.com
    Customer bob = Customer.find("byEmail", "bob@gmail.com").first();

    // Test
    assertNotNull(bob);
    assertEquals("Bob", bob.fullname);
}
@Test
public void tryConnectAsCustomer() {
    // Create a new Customer and save it
    new Customer("bob@gmail.com", "secret", "Bob").save();

    // Test
    assertNotNull(Customer.connect("bob@gmail.com", "secret"));
    assertNull(Customer.connect("bob@gmail.com", "badpassword"));
    assertNull(Customer.connect("tom@gmail.com", "secret"));
    
}
@Test
    public void createItem() {
    // Create a new Customer and save it
    Customer bob = new Customer("bob@gmail.com", "secret", "Bob").save();

    // Create a new Item
    new Item(bob, "My first Item", "Hello world").save();

    // Test that the Item has been created
    assertEquals(1, Item.count());

    // Retrieve all Items created by Bob
    List<Item> bobItems = Item.find("byOwner", bob).fetch();

    // Tests
    assertEquals(1, bobItems.size());
    Item firstItem = bobItems.get(0);
    assertNotNull(firstItem);
    assertEquals(bob, firstItem.owner);
    assertEquals("My first Item", firstItem.item_name);
    assertEquals("Hello world", firstItem.item_description);
    assertNotNull(firstItem.entry_date);
}
@Test
public void ItemWorkUpdates() {
    // Create a new Customer and save it
    Customer bob = new Customer("bob@gmail.com", "secret", "Bob").save();

    // Create a new Item
    Item bobItem = new Item(bob, "My first Item", "Hello world").save();

    // Item a first WorkUpdate
    new WorkUpdate(bobItem, "Jeff", "Nice Item").save();
    new WorkUpdate(bobItem, "Tom", "I knew that !").save();

    // Retrieve all WorkUpdates
    List<WorkUpdate> bobItemWorkUpdates = WorkUpdate.find("byItem", bobItem).fetch();

    // Tests
    assertEquals(2, bobItemWorkUpdates.size());

    WorkUpdate firstWorkUpdate = bobItemWorkUpdates.get(0);
    assertNotNull(firstWorkUpdate);
    assertEquals("Jeff", firstWorkUpdate.author);
    assertEquals("Nice Item", firstWorkUpdate.content);
    assertNotNull(firstWorkUpdate.UpdatedAt);

    WorkUpdate secondWorkUpdate = bobItemWorkUpdates.get(1);
    assertNotNull(secondWorkUpdate);
    assertEquals("Tom", secondWorkUpdate.author);
    assertEquals("I knew that !", secondWorkUpdate.content);
    assertNotNull(secondWorkUpdate.UpdatedAt);
}
@Test
public void useTheWorkUpdatesRelation() {
    // Create a new Customer and save it
    Customer bob = new Customer("bob@gmail.com", "secret", "Bob").save();

    // Create a new Item
    Item bobItem = new Item(bob, "My first Item", "Hello world").save();

    // Item a first WorkUpdate
    bobItem.addWorkUpdate("Jeff", "Nice Item");
    bobItem.addWorkUpdate("Tom", "I knew that !");

    // Count things
    assertEquals(1, Customer.count());
    assertEquals(1, Item.count());
    assertEquals(2, WorkUpdate.count());

    // Retrieve Bob's Item
    bobItem = Item.find("byOwner", bob).first();
    assertNotNull(bobItem);

    // Navigate to WorkUpdates
    assertEquals(2, bobItem.WorkUpdates.size());
    assertEquals("Jeff", bobItem.WorkUpdates.get(0).author);

    // Delete the Item
    bobItem.delete();

    // Check that all WorkUpdates have been deleted
    assertEquals(1, Customer.count());
    assertEquals(0, Item.count());
    assertEquals(0, WorkUpdate.count());
}
}
