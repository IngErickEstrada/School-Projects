
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

public class EmployeesDao {
    //Instanciar la conexion
    ConnectionMySql cn = new ConnectionMySql();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    //Variables para enviar datos entre interfaces
    public static int id_user = 0;
    public static String full_name_user = "";
    public static String user_name_user = "";
    public static String address_user = "";
    public static String rol_user = "";
    public static String email_user = "";
    public static String telephone_user = "";
    
    //Metodo para loguearse
    public Employees loginQuery(String user, String password) {
        String query = "SELECT * FROM employees WHERE user_name = ? AND password = ?";
        Employees employee = new Employees();
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            //Enviar parametros
            pst.setString(1, user);
            pst.setString(2, password);
            
            //Ejecutar query
            rs = pst.executeQuery();
            
            if (rs.next()) {
                employee.setId(rs.getInt("id"));
                id_user = employee.getId();
                
                employee.setFull_name(rs.getString("full_name"));
                full_name_user = employee.getFull_name();
                
                employee.setUser_name(rs.getString("user_name"));
                user_name_user = employee.getUser_name();
                
                employee.setAddress(rs.getString("address"));
                address_user = employee.getAddress();
                
                employee.setRol(rs.getString("rol"));
                rol_user = employee.getRol();
                
                employee.setEmail(rs.getString("email"));
                email_user = employee.getEmail();
                
                employee.setTelephone(rs.getString("telephone"));
                telephone_user = employee.getTelephone();
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Error getting employee: " + e);
        }
        
        return employee;
    }
    
    //Registrar empleado
    public boolean registerEmployeeQuery(Employees employee) {
        String query = "INSERT INTO employees(id, full_name, user_name, address, telephone, email, password, rol, created, updated)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        Timestamp datetime = new Timestamp(new Date().getTime());
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            //Mandamos los parametros obtenidos
            pst.setInt(1, employee.getId());
            pst.setString(2, employee.getFull_name());
            pst.setString(3, employee.getUser_name());
            pst.setString(4, employee.getAddress());
            pst.setString(5, employee.getTelephone());
            pst.setString(6, employee.getEmail());
            pst.setString(7, employee.getPassword());
            pst.setString(8, employee.getRol());
            pst.setTimestamp(9, datetime);
            pst.setTimestamp(10, datetime);
            
            //Ejecutar query
            pst.execute();
            
            return true;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to register employee: " + e);
            
            return false;
        }
    }
    
    //Metodo para listar empleado
    public List listEmployeesQuery(String value) {
        List<Employees> list_employees = new ArrayList();
        String query = "SELECT * FROM employees ORDER BY rol ASC";
        String query_search_employee = "SELECT * FROM employees WHERE id LIKE '%" + value + "%'";
        
        try {
            conn = cn.getConnection();
            if (value.equalsIgnoreCase("")) {
                pst = conn.prepareStatement(query);
                
                //Ejecutar query
                rs = pst.executeQuery();
            } else {
                pst = conn.prepareStatement(query_search_employee);
                
                //Ejecutar query
                rs = pst.executeQuery();
            }
            
            while (rs.next()) {
                Employees employee = new Employees();
                
                employee.setId(rs.getInt("id"));
                employee.setFull_name(rs.getString("full_name"));
                employee.setUser_name(rs.getString("user_name"));
                employee.setAddress(rs.getString("address"));
                employee.setTelephone(rs.getString("telephone"));
                employee.setEmail(rs.getString("email"));
                employee.setRol(rs.getString("rol"));
                
                list_employees.add(employee);
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
        return list_employees;
    }
    
    //Modificar empleado
    public boolean updateEmployeeQuery(Employees employee) {
        String query = "UPDATE employees SET full_name = ?, user_name = ?, address = ?, telephone = ?, email = ?, rol = ?, updated = ?"
                + "WHERE id = ?";
        
        Timestamp datetime = new Timestamp(new Date().getTime());
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            //Mandamos los parametros obtenidos
            pst.setString(1, employee.getFull_name());
            pst.setString(2, employee.getUser_name());
            pst.setString(3, employee.getAddress());
            pst.setString(4, employee.getTelephone());
            pst.setString(5, employee.getEmail());
            pst.setString(6, employee.getRol());
            pst.setTimestamp(7, datetime);
            pst.setInt(8, employee.getId());
            
            //Ejecutar query
            pst.execute();
            
            return true;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Error when modifying employee data: " + e);
            
            return false;
        }
    }
    
    //Eliminar empleado
    public boolean deleteEmployeeQuery(int id) {
        String query = "DELETE FROM employees WHERE id = " + id;
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            //Ejecutar query
            pst.execute();
            
            return true;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting employee: "
                    + e + " because there is a relationship with another table");
            
            return false;
        }
    }
    
    //Cambiar la contraseña
    public boolean updateEmployeePasswordQuery(Employees employee) {
        String query = "UPDATE employees SET password = ? WHERE user_name = '" + user_name_user + "'";
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            //Mandamos los parametros obtenidos
            pst.setString(1, employee.getPassword());
            
            //Ejecutar query
            pst.executeUpdate();
            
            return true;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Error when trying to change employee password: " + e);
            
            return false;
        }
    }
}

