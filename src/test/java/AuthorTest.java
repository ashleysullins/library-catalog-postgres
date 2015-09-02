import org.junit.*;
import static org.junit.Assert.*;

public class AuthorTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_EmptyAtFirst() {
    assertEquals(0, Author.all().size());
  }

  @Test
  public void getName_returnName() {
    Author myAuthor = new Author("Steven King");
    assertEquals("Steven King", myAuthor.getName());
  }

  @Test
  public void equals_returnsTrueWhenParamsMatch() {
    Author firstInstance = new Author("Steven King");
    Author secondInstance = new Author("Steven King");
    assertEquals(true, firstInstance.equals(secondInstance));
  }

  @Test
  public void getId_returnsIdAfterSave() {
    Author myAuthor = new Author("Steven King");
    myAuthor.save();
    assertEquals(Author.all().get(0).getId(), myAuthor.getId());
  }

  @Test
  public void delete_FromDatabase_true() {
    Author myAuthor = new Author("Steven King");
    myAuthor.save();
    myAuthor.delete();
    assertEquals(0, Author.all().size());
  }

  @Test
  public void update_AuthorName() {
    Author myAuthor = new Author ("Steven King");
    myAuthor.save();
    myAuthor.updateName("J.K. Rowling");
    assertEquals(Author.all().get(0).getName(), "J.K. Rowling");
  }
}
