/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.genres;

import db.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class GenreList {
    
    private ArrayList<Genre> genreList;

    public ArrayList<Genre> getGenreList() {
        genreList = new ArrayList<>();
        Connection connection = DataBase.getInstance().getConnection();
        
        try(
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM genre ORDER BY name")
        ){
            Genre genre = null;
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                
                genre = new Genre(id, name);
                genreList.add(genre);
            }
            
        } catch (SQLException ex) {
            System.out.println("Ошибка при получении списка жанров");
        }
        System.out.println("size = " + genreList.size());
        return genreList;
    }
   
}
