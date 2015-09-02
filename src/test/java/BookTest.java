import org.junit.*;
import static org.junit.Assert.*;

public class BookTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Book.all().size());
  }

  @Test
  public void getId_returnsIdAfterSave() {
    Book myBook = new Book( 1, "It", 5);
    myBook.save();
    assertEquals(Book.all().get(0).getId(), myBook.getId());
  }

  @Test
  public void equals_returnsTrueWhenParamsMatch() {
    Book firstInstance = new Book( 1, "It", 5);
    Book secondInstance = new Book( 1, "It", 5);
    assertEquals(true, firstInstance.equals(secondInstance));
  }

  @Test
  public void delete_FromDatabase_true() {
    Book myBook = new Book( 1, "It", 5);
    myBook.save();
    myBook.delete();
    assertEquals(0, Book.all().size());
  }

  @Test
  public void update_Books() {
    Book myBook = new Book( 1, "It", 5);
    myBook.save();
    myBook.updateBooks(4, "FireStarter", 7);
    assertEquals(Book.all().get(0).getTitle(), "FireStarter");
  }
}
