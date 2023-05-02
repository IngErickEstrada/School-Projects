
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDao {
    //Instanciar la conexion
    ConnectionMySql cn = new ConnectionMySql();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    //Registrar Categorias
    public boolean registerCategoryQuery(Categories category) {
        String query = "INSERT INTO categories (name, created, updated)"
                + "VALUES (?, ?, ?)";
        
        Timestamp datetime = new Timestamp(new Date().getTime());
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            //Enviar parametros
            pst.setString(1, category.getName());
            pst.setTimestamp(2, datetime);
            pst.setTimestamp(3, datetime);
            
            //Ejecutar query
            pst.execute();
            
            return true;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to register category: " + e);
            
            return false;
        }
    }
    
    //Listar Categorias
    public List listCategoryQuery(String value) {
        List<Categories> list_categories = new ArrayList();
        
        String query = "SELECT * FROM categories";
        String query_search_category = "SELECT * FROM categories WHERE name LIKE '%" + value + "%'";
        
        try {
            conn = cn.getConnection();
            if (value.equalsIgnoreCase("")) {
                pst = conn.prepareStatement(query);
                
                //Ejecutar query
                rs = pst.executeQuery();
            } else {
                pst = conn.prepareStatement(query_search_category);
                
                //Ejecutar query
                rs = pst.executeQuery();
            }
            
            while (rs.next()) {
                Categories category = new Categories();
                
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                
                list_categories.add(category);
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
        return list_categories;
    }
    
    //Modificar categoria
    public boolean updateCategoryQuery(Categories category) {
        String query = "UPDATE categories SET name = ?, updated = ?"
                + "WHERE id = ?";
        
        Timestamp datetime = new Timestamp(new Date().getTime());
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            //Enviar parametros
            pst.setString(1, category.getName());
            pst.setTimestamp(2, datetime);
            pst.setInt(3, category.getId());
            
            //Ejecutar query
            pst.execute();
            
            return true;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Error when modifying category data: " + e);
            
            return false;
        }
    }
    
    //Eliminar categoria
    public boolean deleteCategoryQuery(int id) {
        String query = "DELETE FROM categories WHERE id = " + id;
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            //Ejecutar query
            pst.execute();
            
            return true;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting category: "
                    + e + " because there is a relationship with another table");
            
            return false;
        }
    }
}
