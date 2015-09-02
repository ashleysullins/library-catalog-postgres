import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/library_catalog_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String books = "DELETE FROM books *;";
      String author = "DELETE FROM authors *;";
      String checks = "DELETE FROM checkouts *;";
      String copy = "DELETE FROM copies *;";
      String pats = "DELETE FROM patrons *;";
      con.createQuery(books).executeUpdate();
      con.createQuery(author).executeUpdate();
      con.createQuery(checks).executeUpdate();
      con.createQuery(copy).executeUpdate();
      con.createQuery(pats).executeUpdate();
    }
  }
}
