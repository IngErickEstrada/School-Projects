
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Employees;
import models.EmployeesDao;
import static models.EmployeesDao.id_user;
import static models.EmployeesDao.rol_user;
import views.SystemView;

public class EmployeesController implements ActionListener, MouseListener, KeyListener{
    private Employees employee;
    private EmployeesDao employeeDao;
    private SystemView views;
    String rol = rol_user;
    DefaultTableModel model = new DefaultTableModel();

    public EmployeesController(Employees employee, EmployeesDao employeeDao, SystemView views) {
        this.employee = employee;
        this.employeeDao = employeeDao;
        this.views = views;
        
        this.views.btn_register_employee.addActionListener(this);
        this.views.btn_update_employee.addActionListener(this);
        this.views.btn_delete_employee.addActionListener(this);
        this.views.btn_cancel_employee.addActionListener(this);
        this.views.btn_update_profile.addActionListener(this);
        
        this.views.jLabelEmployees.addMouseListener(this);
        this.views.employees_table.addMouseListener(this);
        
        this.views.txt_search_employee.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Registrar empleados
        if(e.getSource() == views.btn_register_employee) {
            //Validar que los campos no esten vacios
            if(views.txt_employee_id.getText().equals("") 
                    || views.txt_employee_fullname.getText().equals("")
                    || views.txt_employee_username.getText().equals("")
                    || views.txt_employee_address.getText().equals("")
                    || views.txt_employee_telephone.getText().equals("")
                    || views.txt_employee_email.getText().equals("")
                    || views.cmb_employee_rol.getSelectedItem().toString().equals("")
                    || String.valueOf(views.txt_employee_password.getPassword()).equals("")) {
                
                JOptionPane.showMessageDialog(null, "All fields are required");
                
            } else {
                //Realizar inserccion a la bd
                employee.setId(Integer.parseInt(views.txt_employee_id.getText().trim()));
                employee.setFull_name(views.txt_employee_fullname.getText().trim());
                employee.setUser_name(views.txt_employee_username.getText().trim());
                employee.setAddress(views.txt_employee_address.getText().trim());
                employee.setTelephone(views.txt_employee_telephone.getText().trim());
                employee.setEmail(views.txt_employee_email.getText().trim());
                employee.setPassword(String.valueOf(views.txt_employee_password.getPassword()));
                employee.setRol(views.cmb_employee_rol.getSelectedItem().toString());
                
                if(employeeDao.registerEmployeeQuery(employee)) {
                    cleanTable();
                    cleanFields();
                    listAllEmployees();
                    
                    JOptionPane.showMessageDialog(null, "Successfully Registered Employee");
                } else {
                    JOptionPane.showMessageDialog(null, "Error registering employee");
                }
            }
        } //Actualizar empleados
          else if(e.getSource() == views.btn_update_employee) {
            if(views.txt_employee_id.equals("")) {
                JOptionPane.showMessageDialog(null, "Select a row to continue");
            } else {
                //Verificar si los campos se encuentran vacios
                if(views.txt_employee_id.getText().equals("")
                        || views.txt_employee_fullname.getText().equals("")
                        || views.txt_employee_username.getText().equals("")
                        || views.txt_employee_address.getText().equals("")
                        || views.txt_employee_telephone.getText().equals("")
                        || views.txt_employee_email.getText().equals("")
                        || views.cmb_employee_rol.getSelectedItem().toString().equals("")) {
                    
                    JOptionPane.showMessageDialog(null, "All fields are required");
                } else {
                    //Realizar inserccion a la bd
                    employee.setId(Integer.parseInt(views.txt_employee_id.getText().trim()));
                    employee.setFull_name(views.txt_employee_fullname.getText().trim());
                    employee.setUser_name(views.txt_employee_username.getText().trim());
                    employee.setAddress(views.txt_employee_address.getText().trim());
                    employee.setTelephone(views.txt_employee_telephone.getText().trim());
                    employee.setEmail(views.txt_employee_email.getText().trim());
                    employee.setPassword(String.valueOf(views.txt_employee_password.getPassword()));
                    employee.setRol(views.cmb_employee_rol.getSelectedItem().toString());
                    
                    if(employeeDao.updateEmployeeQuery(employee)) {
                        cleanTable();
                        cleanFields();
                        listAllEmployees();
                        views.btn_register_employee.setEnabled(true);
                        
                        JOptionPane.showMessageDialog(null, "Successfully Updated Employee");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error updating employee");
                    }
                }
            }
        } //Eliminar empleados
          else if (e.getSource() == views.btn_delete_employee) {
            int row = views.employees_table.getSelectedRow();
            
            if(row == -1) {
                JOptionPane.showMessageDialog(null, "You must select an employee to delete");
            } else if (views.employees_table.getValueAt(row, 0).equals(id_user)) {
                JOptionPane.showMessageDialog(null, "Cannot remove authenticated user");
            } else {
                int id = Integer.parseInt(views.employees_table.getValueAt(row, 0).toString());
                int question = JOptionPane.showConfirmDialog(null, "Do you want to delete the selected employee?");
                
                if(question == 0 && employeeDao.deleteEmployeeQuery(id) != false) {
                    cleanTable();
                    cleanFields();
                    views.btn_register_employee.setEnabled(true);
                    views.txt_employee_password.setEnabled(true);
                    listAllEmployees();
                    
                    JOptionPane.showMessageDialog(null, "Successfully Deleted Employee");
                }
            }
        } //Cancelar accion
          else if (e.getSource() == views.btn_cancel_employee) {
            cleanFields();
            views.btn_register_employee.setEnabled(true);
            views.txt_employee_password.setEnabled(true);
            views.txt_employee_id.setEnabled(true);
        } //Actualizar perfil del empleado
          else if (e.getSource() == views.btn_update_profile) {
            //Recolectar informacion de las cajas de password
            String password = String.valueOf(views.txt_profile_password_modify.getPassword());
            String confirm_password = String.valueOf(views.txt_profile_password_modify_confirm.getPassword());
            
            //Validar si las contraseña no se encuentra vacia
            if(!password.equals("") && !confirm_password.equals("")) {
                //Validar si las contraseñas coinciden entre si
                if(password.equals(confirm_password)) {
                    employee.setPassword(String.valueOf(views.txt_profile_password_modify.getPassword()));
                    
                    if(employeeDao.updateEmployeePasswordQuery(employee) != false) {
                        views.txt_profile_password_modify.setText("");
                        views.txt_profile_password_modify_confirm.setText("");
                        
                        JOptionPane.showMessageDialog(null, "Successfully Modifying Employee Password");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error Modifying Employee Password");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error, both passwords must match");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error, all fields are required");
            }
        }
    }
    
    //Listar todos los empleados
    public void listAllEmployees() {
        if(rol.equals("Administrator")) {
            List<Employees> list = employeeDao.listEmployeesQuery(views.txt_search_employee.getText());
            model = (DefaultTableModel) views.employees_table.getModel();
            
            Object[] row = new Object[7];
            
            for(int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getId();
                row[1] = list.get(i).getFull_name();
                row[2] = list.get(i).getUser_name();
                row[3] = list.get(i).getAddress();
                row[4] = list.get(i).getTelephone();
                row[5] = list.get(i).getEmail();
                row[6] = list.get(i).getRol();
                
                model.addRow(row);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == views.employees_table) {
            int row = views.employees_table.rowAtPoint(e.getPoint());
            
            views.txt_employee_id.setText(views.employees_table.getValueAt(row, 0).toString());
            views.txt_employee_fullname.setText(views.employees_table.getValueAt(row, 1).toString());
            views.txt_employee_username.setText(views.employees_table.getValueAt(row, 2).toString());
            views.txt_employee_address.setText(views.employees_table.getValueAt(row, 3).toString());
            views.txt_employee_telephone.setText(views.employees_table.getValueAt(row, 4).toString());
            views.txt_employee_email.setText(views.employees_table.getValueAt(row, 5).toString());
            views.cmb_employee_rol.setSelectedItem(views.employees_table.getValueAt(row, 6).toString());
            
            //Inhabilitar campos
            views.txt_employee_id.setEditable(false);
            views.txt_employee_password.setEnabled(false);
            views.btn_register_employee.setEnabled(false);
        } else if (e.getSource() == views.jLabelEmployees) {
            if(rol.equals("Administrator")) {
                views.jTabbedPane10.setSelectedIndex(4);
                
                cleanTable();
                cleanFields();
                listAllEmployees();
            } else {
                views.jTabbedPane10.setSelectedIndex(4);
                
                cleanTable();
                cleanFields();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    
    }

    @Override
    public void mouseExited(MouseEvent e) {
    
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
    
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == views.txt_search_employee) {
            cleanTable();
            listAllEmployees();
        }
    }
    
    //Limpiar campos de la vista
    public void cleanFields() {
        views.txt_employee_id.setText("");
        views.txt_employee_id.setEditable(true);
        
        views.txt_employee_fullname.setText("");
        views.txt_employee_username.setText("");
        views.txt_employee_address.setText("");
        views.txt_employee_telephone.setText("");
        views.txt_employee_email.setText("");
        views.txt_employee_password.setText("");
        views.cmb_employee_rol.setSelectedIndex(0);
    }
    
    //Limpiar la tabla de busqueda
    public void cleanTable() {
        for(int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }
}
