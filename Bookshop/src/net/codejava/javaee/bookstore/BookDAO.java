package net.codejava.javaee.bookstore;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

 
/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table book
 * in the database.
 * @author www.codejava.net
 *
 */
public class BookDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public BookDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     
    public boolean insertBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (date, title, author, genre, information, img) VALUES (?, ?, ?, ?, ?, ?)";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setDate(1, book.getDate());
        statement.setString(2, book.getTitle());
        statement.setString(3, book.getAuthor());
        statement.setString(4, book.getGenre());
        statement.setString(5, book.getInfo());
        statement.setString(6, book.getImg());
      
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Book> listAllBooks(String SelectedGenre, String SelectedDate) throws SQLException {
        List<Book> listBook = new ArrayList<>();
        String sql = "SELECT * FROM books";
        connect();
        if(SelectedDate==null) {
        	SelectedDate="";
        }
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	if(SelectedGenre.equalsIgnoreCase("all")||resultSet.getString("genre").equalsIgnoreCase(SelectedGenre)) {
                if(SelectedDate.equals("")||resultSet.getDate("date").toString().equalsIgnoreCase(SelectedDate)) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    String info = resultSet.getString("information");		
                    java.sql.Date date = resultSet.getDate("date");
                    String genre = resultSet.getString("genre");
                    String img=resultSet.getString("img");
                    Book book = new Book(id, title, author, info, date, genre, img);
                    listBook.add(book);
                }

        	}		
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listBook;
    }
     
    public boolean deleteBook(Book book) throws SQLException {
        String sql = "DELETE FROM books where id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, book.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateBook(Book book) throws SQLException {
        String sql = "UPDATE books SET  date = ?, title = ?, author = ?, genre = ?, information = ?, img = ?";
        sql += " WHERE id = ?";
        connect();
         
        
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setDate(1, book.getDate());
        statement.setString(2, book.getTitle());
        statement.setString(3, book.getAuthor());
        statement.setString(4, book.getGenre());
        statement.setString(5, book.getInfo());
        statement.setString(6, book.getImg());
        statement.setInt(7, book.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Book getBook(int id) throws SQLException {
        Book book = null;
        String sql = "SELECT * FROM books WHERE id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String info = resultSet.getString("information");		
            java.sql.Date date = resultSet.getDate("date");
            String genre = resultSet.getString("genre");
            String img=resultSet.getString("img");
             
            book = new Book(id, title, author, info, date, genre, img);
        }
         
        resultSet.close();
        statement.close();
         
        return book;
    }
    
    
}