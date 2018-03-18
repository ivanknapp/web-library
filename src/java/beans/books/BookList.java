/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.books;

import enums.SearchType;
import beans.genres.Genre;
import beans.genres.GenreList;
import db.DataBase;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author user
 */
public class BookList {
    private ArrayList<Book> bookList = new ArrayList<Book>();

    public ArrayList<Book> getBooks(String sql) {
        Connection connection = DataBase.getInstance().getConnection();
        
        try (
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql)
        ){
            while(rs.next()){
                Book book = new Book();
                
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setPageCount(rs.getInt("page_count"));
                book.setIsbn(rs.getString("isbn"));
                book.setGenre(rs.getString("genre"));
                book.setAuthor(rs.getString("author"));
                book.setPublishDate(rs.getInt("publish_year"));
                book.setPublisher(rs.getString("publisher"));
                book.setImage(rs.getBytes("image"));

                bookList.add(book);
            }
        } catch (Exception e) {
            System.out.println("Ошибка при получении списка книг");
            e.printStackTrace();
        }
        return bookList;
    }
    
    public ArrayList<Book> getAllBooks(){
        return getBooks("SELECT b.id, b.name, b.isbn, b.page_count, b.publish_year, p.name AS publisher, a.fio AS author, g.name AS genre, b.image " +
                        "FROM book b " +
                        "INNER JOIN author a ON b.author_id=a.id " +
                        "INNER JOIN genre g ON b.genre_id=g.id " +
                        "INNER JOIN publisher p ON b.publisher_id=p.id " +
                        "ORDER BY b.name");
    }
    
    public ArrayList<Book> getBooksByGenre(int genre_id){
        if(genre_id == 0){
            return getAllBooks();
        }else{
            return getBooks("SELECT b.id, b.name, b.isbn, b.page_count, b.publish_year, p.name AS publisher, a.fio AS author, g.name AS genre, b.image " +
                            "FROM book b " +
                            "INNER JOIN author a ON b.author_id=a.id " +
                            "INNER JOIN genre g ON b.genre_id=g.id " +
                            "INNER JOIN publisher p ON b.publisher_id=p.id " +
                            "WHERE genre_id = " + genre_id +
                            " ORDER BY b.name");
        }
    }
    
    public ArrayList<Book> getBooksByLetter(String letter){
        if(letter.isEmpty()){
            return getAllBooks();
        }else{
            //mysql function SUBSTRING('name', 1, 1) = n
            //SUBSTRING('example', 1, 2) = ex
            System.err.println(letter);
            return getBooks("SELECT b.id, b.name, b.isbn, b.page_count, b.publish_year, p.name AS publisher, a.fio AS author, g.name AS genre, b.image " +
                            "FROM book b " +
                            "INNER JOIN author a ON b.author_id=a.id " +
                            "INNER JOIN genre g ON b.genre_id=g.id " +
                            "INNER JOIN publisher p ON b.publisher_id=p.id " +
                            "WHERE substr(b.name, 1, 1) = '"+letter+"' ORDER BY b.name");
        }
    }

                
    public ArrayList<Book> getBookBySearch (String searchString, SearchType type){
        StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.isbn, b.page_count, b.publish_year, p.name AS publisher, a.fio AS author, g.name AS genre, b.image " +
                            "FROM book b " +
                            "INNER JOIN author a ON b.author_id=a.id " +
                            "INNER JOIN genre g ON b.genre_id=g.id " +
                            "INNER JOIN publisher p ON b.publisher_id=p.id ");
        if(type == SearchType.AUTHOR){
            //use mysql func LIKE
            sql.append("WHERE LOWER(a.fio) LIKE '%" + searchString.toLowerCase() + "%' ORDER BY b.name");
        }else{
            sql.append("WHERE LOWER(b.name) LIKE '%"+ searchString.toLowerCase() + "%' ORDER BY b.name");
        }
        
        return getBooks(sql.toString());
    }
    
    
    
}
