import org.sql2o.*;
import java.util.List;

public class Book {
  private int id;
  private int author_id;
  private String title;
  private int copies_id;

  public Book(int author_id, String title, int copies_id) {
    this.author_id = author_id;
    this.title = title;
    this.copies_id = copies_id;
  }

  public int getId() {
    return id;
  }

  public int getAuthorId() {
    return author_id;
  }

  public String getTitle() {
    return title;
  }

  public int getCopiesId() {
    return copies_id;
  }

  public static List<Book> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM books";
      return con.createQuery(sql).executeAndFetch(Book.class);
    }
  }

  @Override
  public boolean equals(Object otherBookInstance) {
    if (!(otherBookInstance instanceof Book)) {
      return false;
    } else {
      Book newBookInstance = (Book) otherBookInstance;
      return this.getAuthorId() == newBookInstance.getAuthorId() &&
             this.getTitle().equals(newBookInstance.getTitle()) &&
             this.getCopiesId() == newBookInstance.getCopiesId();
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO books (author_id, title, copies_id) VALUES (:author_id, :title, :copies_id);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("author_id", author_id)
      .addParameter("title", title)
      .addParameter("copies_id", copies_id)
      .executeUpdate()
      .getKey();
    }
  }

  public void delete() {
    try (Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM books WHERE id = :id";
        con.createQuery(deleteQuery)
        .addParameter("id", id)
        .executeUpdate();
      //String joinDeleteQuery = "DELETE FROM authors"
    }
  }

  public void updateBooks(int author_id, String title, int copies_id) {
    this.author_id = author_id;
    this.title = title;
    this.copies_id = copies_id;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE books SET (author_id, title, copies_id) = (:author_id, :title, :copies_id) WHERE id = :id";
      con.createQuery(sql)
      .addParameter("author_id", author_id)
      .addParameter("title", title)
      .addParameter("copies_id", copies_id)
      .addParameter("id", id)
      .executeUpdate();
    }
  }


}
