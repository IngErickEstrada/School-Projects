
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
import models.Products;
import models.ProductsDao;
import views.SystemView;

public class ProductsController implements ActionListener, MouseListener, KeyListener {
    private Products product;
    private ProductsDao productDao;
    private SystemView views;
    String rol = rol_user;
    DefaultTableModel model = new DefaultTableModel();

    public ProductsController(Products product, ProductsDao productDao, SystemView views) {
        this.product = product;
        this.productDao = productDao;
        this.views = views;
        
        this.views.btn_register_product.addActionListener(this);
        this.views.btn_update_product.addActionListener(this);
        this.views.btn_delete_product.addActionListener(this);
        this.views.btn_cancel_product.addActionListener(this);
        
        this.views.products_table.addMouseListener(this);
        this.views.jLabelProducts.addMouseListener(this);
        
        this.views.txt_search_product.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Registrar productos
        if(e.getSource() == views.btn_register_product) {
            if(views.txt_product_code.getText().equals("")
                    || views.txt_product_name.getText().equals("")
                    || views.txt_product_description.getText().equals("")
                    || views.txt_product_unit_price.getText().equals("")
                    || views.cmb_product_categorie.getSelectedItem().toString().equals("")) {
                
                JOptionPane.showMessageDialog(null, "All fields are required");
            } else {
               //Realizar inserccion a la bd
                product.setCode(Integer.parseInt(views.txt_product_code.getText()));
                product.setName(views.txt_product_name.getText().trim());
                product.setDescription(views.txt_product_description.getText().trim());
                product.setUnit_price(Double.parseDouble(views.txt_product_unit_price.getText()));
                
                DynamicComboBox category_id = (DynamicComboBox) views.cmb_product_categorie.getSelectedItem();
                product.setCategory_id(category_id.getId());
                
                if(productDao.registerProductQuery(product)) {
                    cleanTable();
                    cleanFields();
                    listAllProducts();
                    
                    JOptionPane.showMessageDialog(null, "Successfully Registered Product");
                } else {
                    JOptionPane.showMessageDialog(null, "Error registering product");
                }
            }
        } //Actualizar productos
          else if(e.getSource() == views.btn_update_product) {
            if(views.txt_product_id.equals("")) {
                JOptionPane.showMessageDialog(null, "Select a row to continue");
            } else {
                //Verificar si los campos se encuentran vacios
                if(views.txt_product_name.getText().equals("")
                    || views.txt_product_description.getText().equals("")
                    || views.txt_product_unit_price.getText().equals("")
                    || views.cmb_product_categorie.getSelectedItem().toString().equals("")) {
                    
                    JOptionPane.showMessageDialog(null, "All fields are required");
                } else {
                    //Realizar inserccion a la bd
                    product.setCode(Integer.parseInt(views.txt_product_code.getText().trim()));
                    product.setName(views.txt_product_name.getText().trim());
                    product.setDescription(views.txt_product_description.getText().trim());
                    product.setUnit_price(Double.parseDouble(views.txt_product_unit_price.getText()));
                
                    DynamicComboBox category_id = (DynamicComboBox) views.cmb_product_categorie.getSelectedItem();
                    product.setCategory_id(category_id.getId());
                    
                    product.setId(Integer.parseInt(views.txt_product_id.getText()));
                    
                    if(productDao.updateProductQuery(product)) {
                        cleanTable();
                        cleanFields();
                        listAllProducts();
                        views.btn_register_product.setEnabled(true);
                        
                        JOptionPane.showMessageDialog(null, "Successfully Updated Product");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error updating product");
                    }
                }
            }
        } //Eliminar productos
          else if (e.getSource() == views.btn_delete_product) {
            int row = views.products_table.getSelectedRow();
            
            if(row == -1) {
                JOptionPane.showMessageDialog(null, "You must select an product to delete");
            } else {
                int id = Integer.parseInt(views.products_table.getValueAt(row, 0).toString());
                int question = JOptionPane.showConfirmDialog(null, "Do you want to delete the selected product?");
                
                if(question == 0 && productDao.deleteProductQuery(id) != false) {
                    cleanTable();
                    cleanFields();
                    views.btn_register_product.setEnabled(true);
                    listAllProducts();
                    
                    JOptionPane.showMessageDialog(null, "Successfully Deleted Product");
                }
            }
        } //Cancelar accion
          else if (e.getSource() == views.btn_cancel_product) {
            cleanFields();
            views.btn_register_product.setEnabled(true);
        }
    }
    
    //Listar todos los productos
    public void listAllProducts() {
        if(rol.equals("Administrator") || rol.equals("Assistant")) {
            List<Products> list = productDao.listProductQuery(views.txt_search_product.getText());
            model = (DefaultTableModel) views.products_table.getModel();

            Object[] row = new Object[7];

            for(int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getId();
                row[1] = list.get(i).getCode();
                row[2] = list.get(i).getName();
                row[3] = list.get(i).getDescription();
                row[4] = list.get(i).getUnit_price();
                row[5] = list.get(i).getProduct_quantity();
                row[6] = list.get(i).getCategory_name();

                model.addRow(row);
            }
        }
        
        views.products_table.setModel(model);
        
        if(rol.equals("Assistant")) {
            views.btn_register_product.setEnabled(false);
            views.btn_update_product.setEnabled(false);
            views.btn_delete_product.setEnabled(false);
            views.btn_cancel_product.setEnabled(false);
            views.txt_product_code.setEnabled(false);
            views.txt_product_description.setEnabled(false);
            views.txt_product_id.setEditable(false);
            views.txt_product_name.setEditable(false);
            views.txt_product_unit_price.setEditable(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == views.products_table) {
            int row = views.products_table.rowAtPoint(e.getPoint());
            
            views.txt_product_id.setText(views.products_table.getValueAt(row, 0).toString());
            product = productDao.searchProduct(Integer.parseInt(views.txt_product_id.getText()));
            views.txt_product_code.setText("" + product.getCode());
            views.txt_product_name.setText(product.getName());
            views.txt_product_description.setText(product.getDescription());
            views.txt_product_unit_price.setText("" + product.getUnit_price());
            views.cmb_product_categorie.setSelectedItem(new DynamicComboBox(product.getCategory_id(), product.getCategory_name()));
            
            //Inhabilitar campos
            views.txt_product_id.setEditable(false);
            views.btn_register_product.setEnabled(false);
        } else if (e.getSource() == views.jLabelProducts) {
            views.jTabbedPane10.setSelectedIndex(0);
                
            cleanTable();
            cleanFields();
            listAllProducts();
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
        if(e.getSource() == views.txt_search_product) {
            cleanTable();
            listAllProducts();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    
    }
    
    //Limpiar campos de la vista
    public void cleanFields() {
        views.txt_product_id.setText("");
        views.txt_product_id.setEditable(true);
        
        views.txt_product_code.setText("");
        views.txt_product_name.setText("");
        views.txt_product_description.setText("");
        views.txt_product_unit_price.setText("");
        views.cmb_product_categorie.setSelectedIndex(0);
    }
    
    //Limpiar la tabla de busqueda
    public void cleanTable() {
        for(int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    } 
}
