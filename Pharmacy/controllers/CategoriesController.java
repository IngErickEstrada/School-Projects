
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
import models.Categories;
import models.CategoriesDao;
import models.DynamicComboBox;
import static models.EmployeesDao.rol_user;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import views.SystemView;

public class CategoriesController implements ActionListener, MouseListener, KeyListener{
    private Categories category;
    private CategoriesDao categoriesDao;
    private SystemView views;
    String rol = rol_user;
    DefaultTableModel model = new DefaultTableModel();


    public CategoriesController(Categories category, CategoriesDao categoryDao, SystemView views) {
        this.category = category;
        this.categoriesDao = categoryDao;
        this.views = views;
        
        this.views.btn_register_category.addActionListener(this);
        this.views.btn_update_category.addActionListener(this);
        this.views.btn_delete_category.addActionListener(this);
        this.views.btn_cancel_category.addActionListener(this);
        
        this.views.categories_table.addMouseListener(this);
        this.views.jLabelCategories.addMouseListener(this);
        
        this.views.txt_search_category.addKeyListener(this);
        
        getCategoryName();
        AutoCompleteDecorator.decorate(views.cmb_product_categorie);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Registrar Categorias
        if(e.getSource() == views.btn_register_category) {
            //Validar que los campos no esten vacios
            if(views.txt_category_name.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "All fields are required");
            } else {
                //Realizar inserccion a la bd
                category.setName(views.txt_category_name.getText().trim());
                
                if(categoriesDao.registerCategoryQuery(category)) {
                    cleanTable();
                    cleanFields();
                    listAllCategories();
                    
                    JOptionPane.showMessageDialog(null, "Successfully Registered Category");
                } else {
                    JOptionPane.showMessageDialog(null, "Error registering category");
                }
            }
        } //Actualizar categorias
        else if(e.getSource() == views.btn_update_category) {
            if(views.txt_categoy_id.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Select a row to continue");
            } else {
                //Verificar si los campos se encuentran vacios
                if(views.txt_categoy_id.getText().equals("") ||
                        views.txt_category_name.getText().equals("")) {
                    
                    JOptionPane.showMessageDialog(null, "All fields are required");
                } else {
                    //Realizar inserccion a la bd
                    category.setId(Integer.parseInt(views.txt_categoy_id.getText().trim()));
                    category.setName(views.txt_category_name.getText().trim());
                    
                    if(categoriesDao.updateCategoryQuery(category)) {
                        cleanTable();
                        cleanFields();
                        listAllCategories();
                        views.btn_register_category.setEnabled(true);
                        
                        JOptionPane.showMessageDialog(null, "Successfully Updated Category");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error updating category");
                    }
                }
            }
        } //Eliminar categorias
          else if (e.getSource() == views.btn_delete_category) {
            int row = views.categories_table.getSelectedRow();
            
            if(row == -1) {
                JOptionPane.showMessageDialog(null, "You must select an category to delete");
            } else {
                int id = Integer.parseInt(views.categories_table.getValueAt(row, 0).toString());
                int question = JOptionPane.showConfirmDialog(null, "Do you want to delete the selected category?");
                
                if(question == 0 && categoriesDao.deleteCategoryQuery(id) != false) {
                    cleanTable();
                    cleanFields();
                    views.btn_register_category.setEnabled(true);
                    listAllCategories();
                    
                    JOptionPane.showMessageDialog(null, "Successfully Deleted Category");
                }
            }
        } //Cancelar accion
          else if (e.getSource() == views.btn_cancel_category) {
            cleanFields();
            views.btn_register_category.setEnabled(true);
        }
    }
    
    //Listar todos las categorias
    public void listAllCategories() {
        if(rol.equals("Administrator")) {
            List<Categories> list = categoriesDao.listCategoryQuery(views.txt_search_category.getText());
            model = (DefaultTableModel) views.categories_table.getModel();

            Object[] row = new Object[2];

            for(int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getId();
                row[1] = list.get(i).getName();

                model.addRow(row);
            }
        }
        
        views.categories_table.setModel(model);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       if(e.getSource() == views.categories_table) {
            int row = views.categories_table.rowAtPoint(e.getPoint());
            
            views.txt_categoy_id.setText(views.categories_table.getValueAt(row, 0).toString());
            views.txt_category_name.setText(views.categories_table.getValueAt(row, 1).toString());
            
            //Inhabilitar campos
            views.txt_categoy_id.setEditable(false);
            views.btn_register_category.setEnabled(false);
        } else if (e.getSource() == views.jLabelCategories) {
            if(rol.equals("Administrator")) {
                views.jTabbedPane10.setSelectedIndex(6);

                cleanTable();
                cleanFields();
                listAllCategories();
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
       if(e.getSource() == views.txt_search_category) {
            cleanTable();
            listAllCategories();
        }
    }
    
    //Limpiar campos de la vista
    public void cleanFields() {
        views.txt_categoy_id.setText("");
        views.txt_categoy_id.setEditable(false);
        
        views.txt_category_name.setText("");
    }
    
    //Limpiar la tabla de busqueda
    public void cleanTable() {
        for(int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    } 
    
    //Metodo para mostrar el nombre de las categorias
    public void getCategoryName() {
        List<Categories> list = categoriesDao.listCategoryQuery(views.txt_search_category.getText());
        
        for(int i = 0; i < list.size(); i++) {
            int id = list.get(i).getId();
            String name = list.get(i).getName();
            
            views.cmb_product_categorie.addItem(new DynamicComboBox(id, name));
        }
    }
}
