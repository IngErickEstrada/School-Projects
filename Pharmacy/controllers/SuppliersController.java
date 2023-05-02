
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
import models.DynamicComboBox;
import static models.EmployeesDao.rol_user;
import models.Suppliers;
import models.SuppliersDao;
import views.SystemView;

public class SuppliersController implements ActionListener, MouseListener, KeyListener {
    private Suppliers supplier;
    private SuppliersDao supplierDao;
    private SystemView views;
    String rol = rol_user;
    DefaultTableModel model = new DefaultTableModel();

    public SuppliersController(Suppliers supplier, SuppliersDao supplierDao, SystemView views) {
        this.supplier = supplier;
        this.supplierDao = supplierDao;
        this.views = views;
        
        this.views.btn_register_supplier.addActionListener(this);
        this.views.btn_update_supplier.addActionListener(this);
        this.views.btn_delete_supplier.addActionListener(this);
        this.views.btn_cancel_supplier.addActionListener(this);
        
        this.views.suppliers_table.addMouseListener(this);
        this.views.jLabelSuppliers.addMouseListener(this);
        
        this.views.txt_search_supplier.addKeyListener(this);
        
        getSupplierName();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Registrar proveedores
        if(e.getSource() == views.btn_register_supplier) {
            //Validar que los campos no esten vacios
            if(views.txt_supplier_name.getText().equals("")
                    || views.txt_supplier_address.getText().equals("")
                    || views.txt_supplier_telephone.getText().equals("")
                    || views.txt_supplier_email.getText().equals("")
                    || views.txt_supplier_description.getText().equals("")
                    || views.cmb_supplier_city.getSelectedItem().toString().equals("")) {
                
                JOptionPane.showMessageDialog(null, "All fields are required");
            } else {
                //Realizar inserccion a la bd
                supplier.setName(views.txt_supplier_name.getText().trim());
                supplier.setDescription(views.txt_supplier_description.getText().trim());
                supplier.setAddress(views.txt_supplier_address.getText().trim());
                supplier.setTelephone(views.txt_supplier_telephone.getText().trim());
                supplier.setEmail(views.txt_supplier_email.getText().trim());
                supplier.setCity(views.cmb_supplier_city.getSelectedItem().toString());
                
                if(supplierDao.registerSupplierQuery(supplier)) {
                    cleanTable();
                    cleanFields();
                    listAllSuppliers();
                    
                    JOptionPane.showMessageDialog(null, "Successfully Registered Supplier");
                } else {
                    JOptionPane.showMessageDialog(null, "Error registering supplier");
                }
            }
        } //Actualizar proveedores
          else if(e.getSource() == views.btn_update_supplier) {
            if(views.txt_supplier_id.equals("")) {
                JOptionPane.showMessageDialog(null, "Select a row to continue");
            } else {
                //Verificar si los campos se encuentran vacios
                if(views.txt_supplier_name.getText().equals("")
                    || views.txt_supplier_address.getText().equals("")
                    || views.txt_supplier_telephone.getText().equals("")
                    || views.txt_supplier_email.getText().equals("")
                    || views.txt_supplier_description.getText().equals("")
                    || views.cmb_supplier_city.getSelectedItem().toString().equals("")) {
                    
                    JOptionPane.showMessageDialog(null, "All fields are required");
                } else {
                    //Realizar inserccion a la bd
                    supplier.setName(views.txt_supplier_name.getText().trim());
                    supplier.setDescription(views.txt_supplier_description.getText().trim());
                    supplier.setAddress(views.txt_supplier_address.getText().trim());
                    supplier.setTelephone(views.txt_supplier_telephone.getText().trim());
                    supplier.setEmail(views.txt_supplier_email.getText().trim());
                    supplier.setCity(views.cmb_supplier_city.getSelectedItem().toString());
                    supplier.setId(Integer.parseInt(views.txt_supplier_id.getText()));
                    
                    if(supplierDao.updateSupplierQuery(supplier)) {
                        cleanTable();
                        cleanFields();
                        listAllSuppliers();
                        views.btn_register_supplier.setEnabled(true);
                        
                        JOptionPane.showMessageDialog(null, "Successfully Updated Supplier");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error updating supplier");
                    }
                }
            }
        } //Eliminar proveedores
          else if (e.getSource() == views.btn_delete_supplier) {
            int row = views.suppliers_table.getSelectedRow();
            
            if(row == -1) {
                JOptionPane.showMessageDialog(null, "You must select an supplier to delete");
            } else {
                int id = Integer.parseInt(views.suppliers_table.getValueAt(row, 0).toString());
                int question = JOptionPane.showConfirmDialog(null, "Do you want to delete the selected supplier?");
                
                if(question == 0 && supplierDao.deleteSupplierQuery(id) != false) {
                    cleanTable();
                    cleanFields();
                    views.btn_register_supplier.setEnabled(true);
                    listAllSuppliers();
                    
                    JOptionPane.showMessageDialog(null, "Successfully Deleted Supplier");
                }
            }
        } //Cancelar accion
          else if (e.getSource() == views.btn_cancel_supplier) {
            cleanFields();
            views.btn_register_supplier.setEnabled(true);
        }
    }
    
    //Listar todos los proveedores
    public void listAllSuppliers() {
        if(rol.equals("Administrator")) {
            List<Suppliers> list = supplierDao.listSupplierQuery(views.txt_search_supplier.getText());
            model = (DefaultTableModel) views.suppliers_table.getModel();

            Object[] row = new Object[7];

            for(int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getId();
                row[1] = list.get(i).getName();
                row[2] = list.get(i).getDescription();
                row[3] = list.get(i).getAddress();
                row[4] = list.get(i).getTelephone();
                row[5] = list.get(i).getEmail();
                row[6] = list.get(i).getCity();

                model.addRow(row);
            }
        }
        
        views.suppliers_table.setModel(model);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == views.suppliers_table) {
            int row = views.suppliers_table.rowAtPoint(e.getPoint());
            
            views.txt_supplier_id.setText(views.suppliers_table.getValueAt(row, 0).toString());
            views.txt_supplier_name.setText(views.suppliers_table.getValueAt(row, 1).toString());
            views.txt_supplier_description.setText(views.suppliers_table.getValueAt(row, 2).toString());
            views.txt_supplier_address.setText(views.suppliers_table.getValueAt(row, 3).toString());
            views.txt_supplier_telephone.setText(views.suppliers_table.getValueAt(row, 4).toString());
            views.txt_supplier_email.setText(views.suppliers_table.getValueAt(row, 5).toString());
            views.cmb_supplier_city.setSelectedItem(views.suppliers_table.getValueAt(row, 6).toString());
            
            //Inhabilitar campos
            views.txt_supplier_id.setEditable(false);
            views.btn_register_supplier.setEnabled(false);
        } else if (e.getSource() == views.jLabelSuppliers) {
            views.jTabbedPane10.setSelectedIndex(5);
                
            cleanTable();
            cleanFields();
            listAllSuppliers();
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
        if(e.getSource() == views.txt_search_supplier) {
            cleanTable();
            listAllSuppliers();
        }
    }
    
    //Limpiar campos de la vista
    public void cleanFields() {
        views.txt_supplier_id.setText("");
        views.txt_supplier_id.setEditable(true);
        
        views.txt_supplier_name.setText("");
        views.txt_supplier_address.setText("");
        views.txt_supplier_telephone.setText("");
        views.txt_supplier_email.setText("");
        views.txt_supplier_description.setText("");
        views.cmb_supplier_city.setSelectedIndex(0);
    }
    
    //Limpiar la tabla de busqueda
    public void cleanTable() {
        for(int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }
    
    //Metodo para mostrar el nombre de las categorias
    public void getSupplierName() {
        List<Suppliers> list = supplierDao.listSupplierQuery(views.txt_search_supplier.getText());
        
        for(int i = 0; i < list.size(); i++) {
            int id = list.get(i).getId();
            String name = list.get(i).getName();
            
            views.cmb_purchase_supplier.addItem(new DynamicComboBox(id, name));
        }
    }
}
