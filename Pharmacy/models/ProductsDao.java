
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

public class ProductsDao {
    //Instanciar la conexion
    ConnectionMySql cn = new ConnectionMySql();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    //Registrar Producto
    public boolean registerProductQuery(Products product) {
        String query = "INSERT INTO products (code, name, description, unit_price, created, updated, category_id)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        Timestamp datetime = new Timestamp(new Date().getTime());
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            //Enviar parametros
            pst.setInt(1, product.getCode());
            pst.setString(2, product.getName());
            pst.setString(3, product.getDescription());
            pst.setDouble(4, product.getUnit_price());
            pst.setTimestamp(5, datetime);
            pst.setTimestamp(6, datetime);
            pst.setInt(7, product.getCategory_id());
            
            //Ejecutar query
            pst.execute();
            
            return true;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Error to register product: " + e);
            
            return false;
        }
    }
    
    //Listar Productos
    public List listProductQuery(String value) {
        List<Products> list_products = new ArrayList();
        
        String query = "SELECT pro.*, ca.name AS category_name FROM products pro, categories ca WHERE pro.category_id = ca.id";
        String query_search_product = "SELECT pro.*, ca.name AS category_name FROM products pro INNER JOIN categories ca ON pro.category_id = ca.id WHERE pro.name LIKE '%" + value + "%'";
        
        try {
            conn = cn.getConnection();
            if (value.equalsIgnoreCase("")) {
                pst = conn.prepareStatement(query);
                
                //Ejecutar query
                rs = pst.executeQuery();
            } else {
                pst = conn.prepareStatement(query_search_product);
                
                //Ejecutar query
                rs = pst.executeQuery();
            }
            
            while (rs.next()) {
                Products product = new Products();
                
                product.setId(rs.getInt("id"));
                product.setCode(rs.getInt("code"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setUnit_price(rs.getDouble("unit_price"));
                product.setProduct_quantity(rs.getInt("product_quantity"));
                product.setCategory_name(rs.getString("category_name"));
                
                list_products.add(product);
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
        return list_products;
    }
    
    //Modificar producto
    public boolean updateProductQuery(Products product) {
        String query = "UPDATE products SET code = ?, name = ?, description = ?, unit_price = ?, updated = ?, category_id = ? WHERE id = ?";
        
        Timestamp datetime = new Timestamp(new Date().getTime());
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            //Enviar parametros
            pst.setInt(1, product.getCode());
            pst.setString(2, product.getName());
            pst.setString(3, product.getDescription());
            pst.setDouble(4, product.getUnit_price());
            pst.setTimestamp(5, datetime);
            pst.setInt(6, product.getCategory_id());
            pst.setInt(7, product.getId());
            
            //Ejecutar query
            pst.execute();
            
            return true;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Error when modifying product data: " + e);
            
            return false;
        }
    }
    
    //Eliminar producto
    public boolean deleteProductQuery(int id) {
        String query = "DELETE FROM products WHERE id = " + id;
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            //Ejecutar query
            pst.execute();
            
            return true;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting product: "
                    + e + " because there is a relationship with another table");
            
            return false;
        }
    }
    
    //Buscar productos
    public Products searchProduct(int id) {
        String query = "SELECT pro.*, ca.name AS category_name FROM products pro INNER JOIN categories ca ON pro.category_id = ca.id WHERE pro.id = ?";
        
        Products product = new Products();
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            //Enviar parametros
            pst.setInt(1, id);
            
            //Ejecutar query
            rs = pst.executeQuery();
            
            if(rs.next()) {
                product.setId(rs.getInt("id"));
                product.setCode(rs.getInt("code"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setUnit_price(rs.getDouble("unit_price"));
                product.setCategory_id(rs.getInt("category_id"));
                product.setCategory_name(rs.getString("category_name"));
            }
            
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
        return product;
    }
    
    //Buscar producto por codigo
    public Products searchCode(int code) {
        String query = "SELECT pro.id, pro.name FROM products pro WHERE pro.code = ?";
        
        Products product = new Products();
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            //Enviar parametros
            pst.setInt(1, code);
            
            //Ejecutar query
            rs = pst.executeQuery();
            
            if(rs.next()) {
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
            }
            
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
        return product;
    }
    
    //Traer la cantidad de productos
    public Products searchId(int id) {
        String query = "SELECT pro.product_quantity FROM products pro WHERE pro.id = ?";
        
        Products product = new Products();
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            //Enviar parametros
            pst.setInt(1, id);
            
            //Ejecutar query
            rs = pst.executeQuery();
            
            if(rs.next()) {
                product.setProduct_quantity(rs.getInt("product_quantity"));
            }
            
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
        return product;
    }
    
    //Actualizar el almacen
    public boolean updateStockQuery(int amount, int product_id) {
        String query = "UPDATE products SET product_quantity = ? WHERE id = ?";
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            //Enviar parametros
            pst.setInt(1, amount);
            pst.setInt(2, product_id);
            
            //Ejecutar query
            pst.execute();
            
            return true;
            
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            
            return false;
        }
    }
}
