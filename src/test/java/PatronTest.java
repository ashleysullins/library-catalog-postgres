import org.junit.*;
import static org.junit.Assert.*;

public class PatronTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_EmptyAtFirst() {
    assertEquals(0, Patron.all().size());
  }

  @Test
  public void getName_returnName() {
    Patron myPatron = new Patron("Bob Jones", 1, 9, 2, 2015);
    assertEquals("Bob Jones", myPatron.getName());
  }

  @Test
  public void equals_returnsTrueWhenParamsMatch() {
    Patron firstInstance = new Patron("Bob Jones", 1, 9, 2, 2015);
    Patron secondInstance = new Patron("Bob Jones", 1, 9, 2, 2015);
    assertEquals(true, firstInstance.equals(secondInstance));
  }

  @Test
  public void getId_returnsIdAfterSave() {
    Patron myPatron = new Patron("Bob Jones", 1, 9, 2, 2015);
    myPatron.save();
    assertEquals(Patron.all().get(0).getId(), myPatron.getId());
  }

  @Test
  public void delete_FromDatabase_true() {
    Patron myPatron = new Patron("Bob Jones", 1, 9, 2, 2015);
    myPatron.save();
    myPatron.delete();
    assertEquals(0, Patron.all().size());
  }

  @Test
  public void update_PatronName() {
    Patron myPatron = new Patron ("Bob Jones", 1, 9, 2, 2015);
    myPatron.save();
    myPatron.updatePatron("Billy Bob Jones", 1);
    assertEquals(Patron.all().get(0).getName(), "Billy Bob Jones");
  }

}
