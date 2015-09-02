import org.sql2o.*;
import java.util.List;

public class Author {
  private int id;
  private String name;
  private int books_id;


  public Author (String name, int books_id) {
  this.name = name;
  this.books_id = books_id;
}
  public String getName() {
    return name;
  }

  public int getBooksId() {
    return books_id;
  }

  public static List<Author> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM authors";
      return con.createQuery(sql).executeAndFetch(Author.class);
    }
  }
}
