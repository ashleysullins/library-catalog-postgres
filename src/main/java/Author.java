import org.sql2o.*;
import java.util.List;

public class Author {
  private int id;
  private String name;

  public Author (String name) {
  this.name = name;
}
  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public static List<Author> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM authors";
      return con.createQuery(sql).executeAndFetch(Author.class);
    }
  }

  @Override
  public boolean equals(Object otherAuthorInstance) {
    if (!(otherAuthorInstance instanceof Author)) {
      return false;
    } else {
      Author newAuthorInstance = (Author) otherAuthorInstance;
      return this.getName().equals(newAuthorInstance.getName());
    }
  }


  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO authors (name) VALUES (:name);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", name)
      .executeUpdate()
      .getKey();
    }
  }

  public void delete() {
    try (Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM authors WHERE id = :id";
        con.createQuery(deleteQuery)
        .addParameter("id", id)
        .executeUpdate();
      //String joinDeleteQuery = "DELETE FROM authors"
    }
  }

  public void updateName(String name) {
    this.name = name;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE authors SET name = :name WHERE id = :id";
      con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

}
