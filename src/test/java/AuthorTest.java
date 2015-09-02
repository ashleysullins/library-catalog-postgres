import org.junit.*;
import static org.junit.Assert.*;

public class AuthorTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_EmptyAtFirst() {
    assertEquals(0, Author.all().size());
  }


}
