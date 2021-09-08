package net.codejava.javaee.bookstore;
 
/**
 * Book.java
 * This is a model class represents a book entity
 * @author www.codejava.net
 *
 */
public class Book {
    protected int id;
    protected String title;
    protected String Director;
    protected String info;
    protected java.sql.Date date;
    protected String genre;
    protected String img;
    protected String mp;
    public Book() {
    }
 
    public Book(int id) {
        this.id = id;
    }
 
    public Book(int id, String title, String author, String info, java.sql.Date date, String genre, String img) {
        this(title, director, info, date, genre,img,mp);
        this.id = id;
    }
     
    public Book(String title, String author, String info,  java.sql.Date date, String genre, String img,String mp) {
        this.title = title;
        this.director = director;
        this.info = info;
        this.date = date;
        this.genre = genre;
        this.img = img;
        this.mp = mp;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getDirector() {
        return director;
    }
 
    public void setAuthor(String director) {
        this.director = director;
    }
 
    
    
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    
    public java.sql.Date getDate() {
        return date;
    }
    public void setDate(java.sql.Date date) {
        this.date = date;
    }
    
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getImg() {
        return img;
    }
 
    public void setMp(String mp) {
        this.mp = mp;
    }
    public String getMp() {
        return mp;
    }
 
    public void setMp(String mp) {
        this.mp = mp;
    }
}