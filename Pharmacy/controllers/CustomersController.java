
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
import models.Customers;
import models.CustomersDao;
import views.SystemView;

public class CustomersController implements ActionListener, MouseListener, KeyListener {
    private Customers customer;
    private CustomersDao customerDao;
    private SystemView views;
    DefaultTableModel model = new DefaultTableModel();

    public CustomersController(Customers customer, CustomersDao customerDao, SystemView views) {
        this.customer = customer;
        this.customerDao = customerDao;
        this.views = views;
        
        this.views.btn_register_customer.addActionListener(this);
        this.views.btn_update_customer.addActionListener(this);
        this.views.btn_delete_customer.addActionListener(this);
        this.views.btn_cancel_customer.addActionListener(this);
        
        this.views.jLabelCustomers.addMouseListener(this);
        this.views.customers_table.addMouseListener(this);
        
        this.views.txt_search_customer.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Registrar clientes
        if(e.getSource() == views.btn_register_customer) {
            //Validar que los campos no esten vacios
            if(views.txt_customer_id.getText().equals("")
                    || views.txt_customer_fullname.getText().equals("")
                    || views.txt_customer_address.getText().equals("")
                    || views.txt_customer_telephone.getText().equals("")
                    || views.txt_customer_email.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "All fields are required");
            } else {
                //Realizar inserccion a la bd
                customer.setId(Integer.parseInt(views.txt_customer_id.getText().trim()));
                customer.setFull_name(views.txt_customer_fullname.getText().trim());
                customer.setAddress(views.txt_customer_address.getText().trim());
                customer.setTelephone(views.txt_customer_telephone.getText().trim());
                customer.setEmail(views.txt_customer_email.getText().trim());
                
                if(customerDao.registerCustomerQuery(customer)) {
                    cleanTable();
                    cleanFields();
                    listAllCustomers();
                    
                    JOptionPane.showMessageDialog(null, "Successfully Registered Customer");
                } else {
                    JOptionPane.showMessageDialog(null, "Error registering customer");
                }
            }
        } //Actualizar clientes
          else if(e.getSource() == views.btn_update_customer) {
            if(views.txt_customer_id.equals("")) {
                JOptionPane.showMessageDialog(null, "Select a row to continue");
            } else {
                //Verificar si los campos se encuentran vacios
                if(views.txt_customer_id.getText().equals("")
                        || views.txt_customer_fullname.getText().equals("")
                        || views.txt_customer_address.getText().equals("")
                        || views.txt_customer_telephone.getText().equals("")
                        || views.txt_customer_email.getText().equals("")) {
                    
                    JOptionPane.showMessageDialog(null, "All fields are required");
                } else {
                    //Realizar inserccion a la bd
                    customer.setId(Integer.parseInt(views.txt_customer_id.getText().trim()));
                    customer.setFull_name(views.txt_customer_fullname.getText().trim());
                    customer.setAddress(views.txt_customer_address.getText().trim());
                    customer.setTelephone(views.txt_customer_telephone.getText().trim());
                    customer.setEmail(views.txt_customer_email.getText().trim());
                    
                    if(customerDao.updateCustomerQuery(customer)) {
                        cleanTable();
                        cleanFields();
                        listAllCustomers();
                        views.btn_register_customer.setEnabled(true);
                        
                        JOptionPane.showMessageDialog(null, "Successfully Updated Customer");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error updating customer");
                    }
                }
            }
        } //Eliminar clientes
          else if (e.getSource() == views.btn_delete_customer) {
            int row = views.customers_table.getSelectedRow();
            
            if(row == -1) {
                JOptionPane.showMessageDialog(null, "You must select an customer to delete");
            } else {
                int id = Integer.parseInt(views.customers_table.getValueAt(row, 0).toString());
                int question = JOptionPane.showConfirmDialog(null, "Do you want to delete the selected customer?");
                
                if(question == 0 && customerDao.deleteCustomerQuery(id) != false) {
                    cleanTable();
                    cleanFields();
                    views.btn_register_customer.setEnabled(true);
                    listAllCustomers();
                    
                    JOptionPane.showMessageDialog(null, "Successfully Deleted Customer");
                }
            }
        } //Cancelar accion
          else if (e.getSource() == views.btn_cancel_customer) {
            cleanFields();
            views.btn_register_customer.setEnabled(true);
            views.txt_customer_id.setEnabled(true);
        }
    }
    
    //Listar todos los clientes
    public void listAllCustomers() {
        List<Customers> list = customerDao.listCustomerQuery(views.txt_search_customer.getText());
        model = (DefaultTableModel) views.customers_table.getModel();
        
        Object[] row = new Object[5];
            
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getFull_name();                
            row[2] = list.get(i).getAddress();
            row[3] = list.get(i).getTelephone();
            row[4] = list.get(i).getEmail();
                
            model.addRow(row);
        }
        
        views.customers_table.setModel(model);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == views.customers_table) {
            int row = views.customers_table.rowAtPoint(e.getPoint());
            
            views.txt_customer_id.setText(views.customers_table.getValueAt(row, 0).toString());
            views.txt_customer_fullname.setText(views.customers_table.getValueAt(row, 1).toString());
            views.txt_customer_address.setText(views.customers_table.getValueAt(row, 2).toString());
            views.txt_customer_telephone.setText(views.customers_table.getValueAt(row, 3).toString());
            views.txt_customer_email.setText(views.customers_table.getValueAt(row, 4).toString());
            
            //Inhabilitar campos
            views.txt_customer_id.setEditable(false);
            views.btn_register_customer.setEnabled(false);
        } else if (e.getSource() == views.jLabelCustomers) {
            views.jTabbedPane10.setSelectedIndex(3);
                
            cleanTable();
            cleanFields();
            listAllCustomers();
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
        if(e.getSource() == views.txt_search_customer) {
            cleanTable();
            listAllCustomers();
        }
    }
    
    //Limpiar campos de la vista
    public void cleanFields() {
        views.txt_customer_id.setText("");
        views.txt_customer_id.setEditable(true);
        
        views.txt_customer_fullname.setText("");
        views.txt_customer_address.setText("");
        views.txt_customer_telephone.setText("");
        views.txt_customer_email.setText("");
    }
    
    //Limpiar la tabla de busqueda
    public void cleanTable() {
        for(int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }
}
