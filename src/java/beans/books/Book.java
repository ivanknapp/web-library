/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.books;

import db.DataBase;
import java.awt.Image;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Book implements Serializable{
    private long id;
    private String name;
    private byte[] content;
    private int pageCount;
    private String isbn;
    private String genre;
    private String author;
    private int publishDate;
    private String publisher;
    private byte[] image;

    public Book() {
    }

    public Book(long id, String name, byte[] content, int pageCount, String isbn, String genre, String author, int publishDate, String publisher, byte[] image) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.genre = genre;
        this.author = author;
        this.publishDate = publishDate;
        this.publisher = publisher;
        this.image = image;
    }

    

     
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {       
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }  

    public int getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(int publishDate) {
        this.publishDate = publishDate;
    }
    
    public void fillPdfContent(){
        String sql = "SELECT content FROM book "
                    + "WHERE id=" + getId();
        Connection connection = DataBase.getInstance().getConnection();
        try(
            
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
        ){
            while (rs.next()){
                this.setContent(rs.getBytes("content"));
            }
            System.out.println("Content length = " + this.content.length);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ошибка при получении контента");
        }
        
    }
    
}
