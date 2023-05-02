
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

public class SuppliersDao {
    //Instanciar la conexion
    ConnectionMySql cn = new ConnectionMySql();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    //Registrar Proveedor
    public boolean registerSupplierQuery(Suppliers supplier) {
        String query = "INSERT INTO suppliers (name, description, address, telephone, email, city, created, updated)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Timestamp datetime = new Timestamp(new Date().getTime());
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            //Enviar parametros
            pst.setString(1, supplier.getName());
            pst.setString(2, supplier.getDescription());
            pst.setString(3, supplier.getAddress());
            pst.setString(4, supplier.getTelephone());
            pst.setString(5, supplier.getEmail());
            pst.setString(6, supplier.getCity());
            pst.setTimestamp(7, datetime);
            pst.setTimestamp(8, datetime);
            
            //Ejecutar query
            pst.execute();
            
            return true;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to register supplier: " + e);
            
            return false;
        }
    }
    
    //Listar Proveedores
    public List listSupplierQuery(String value) {
        List<Suppliers> list_suppliers = new ArrayList();
        
        String query = "SELECT * FROM suppliers";
        String query_search_supplier = "SELECT * FROM suppliers WHERE name LIKE '%" + value + "%'";
        
        try {
            conn = cn.getConnection();
            if (value.equalsIgnoreCase("")) {
                pst = conn.prepareStatement(query);
                
                //Ejecutar query
                rs = pst.executeQuery();
            } else {
                pst = conn.prepareStatement(query_search_supplier);
                
                //Ejecutar query
                rs = pst.executeQuery();
            }
            
            while (rs.next()) {
                Suppliers supplier = new Suppliers();
                
                supplier.setId(rs.getInt("id"));
                supplier.setName(rs.getString("name"));
                supplier.setDescription(rs.getString("description"));
                supplier.setAddress(rs.getString("address"));
                supplier.setTelephone(rs.getString("telephone"));
                supplier.setEmail(rs.getString("email"));
                supplier.setCity(rs.getString("city"));
                
                list_suppliers.add(supplier);
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
        return list_suppliers;
    }
    
    //Modificar proveedor
    public boolean updateSupplierQuery(Suppliers supplier) {
        String query = "UPDATE suppliers SET name = ?, description = ?, address = ?, telephone = ?, email = ?, city = ?, updated = ?"
                + "WHERE id = ?";
        
        Timestamp datetime = new Timestamp(new Date().getTime());
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            //Enviar parametros
            pst.setString(1, supplier.getName());
            pst.setString(2, supplier.getDescription());
            pst.setString(3, supplier.getAddress());
            pst.setString(4, supplier.getTelephone());
            pst.setString(5, supplier.getEmail());
            pst.setString(6, supplier.getCity());
            pst.setTimestamp(7, datetime);
            pst.setInt(8, supplier.getId());
            
            //Ejecutar query
            pst.execute();
            
            return true;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Error when modifying supplier data: " + e);
            
            return false;
        }
    }
    
    //Eliminar proveedor
    public boolean deleteSupplierQuery(int id) {
        String query = "DELETE FROM suppliers WHERE id = " + id;
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            //Ejecutar query
            pst.execute();
            
            return true;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting supplier: "
                    + e + " because there is a relationship with another table");
            
            return false;
        }
    }
}
