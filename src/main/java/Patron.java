import org.sql2o.*;
import java.util.List;

public class Patron {
  private int id;
  private String name;
  private int books_id;
  private int due_month;
  private int due_day;
  private int due_year;

  public Patron(String name, int books_id, int due_month, int due_day, int due_year) {
    this.name = name;
    this.books_id = books_id;
    this.due_month = due_month;
    this.due_day = due_day;
    this.due_year = due_year;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getBooksId() {
    return books_id;
  }

  public int getDueMonth() {
    return due_month;
  }

  public int getDueDay() {
    return due_day;
  }

  public int getDueYear(){
    return due_year;
  }

  public String getDueDate() {
    return String.format("%d/%d/%d", due_month, due_day, due_year);
  }

  @Override
  public boolean equals(Object otherPatronInstance) {
    if (!(otherPatronInstance instanceof Patron)) {
      return false;
    } else {
      Patron newPatronInstance = (Patron) otherPatronInstance;
      return this.getName().equals(newPatronInstance.getName()) &&
             this.getBooksId() == newPatronInstance.getBooksId() &&
             this.getDueMonth() == newPatronInstance.getDueMonth() &&
             this.getDueDay() == newPatronInstance.getDueDay() &&
             this.getDueYear() == newPatronInstance.getDueYear();
    }
  }


  public static List<Patron> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patrons";
      return con.createQuery(sql).executeAndFetch(Patron.class);
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patrons (name, books_id, due_month, due_day, due_year) VALUES (:name, :books_id, :due_month, :due_day, :due_year);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", name)
      .addParameter("books_id", books_id)
      .addParameter("due_month", due_month)
      .addParameter("due_day", due_day)
      .addParameter("due_year", due_year)
      .executeUpdate()
      .getKey();
    }
  }

  public void delete() {
    try (Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM patrons WHERE id = :id";
        con.createQuery(deleteQuery)
        .addParameter("id", id)
        .executeUpdate();
      //String joinDeleteQuery = "DELETE FROM authors"
    }
  }

  public void updatePatron(String name, int books_id) {
    this.name = name;
    this.books_id = books_id;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE patrons SET (name, books_id) = (:name, :books_id) WHERE id = :id";
      con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("books_id", books_id)
      .addParameter("id", id)
      .executeUpdate();
    }
  }


}
