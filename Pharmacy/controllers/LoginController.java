
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.Employees;
import models.EmployeesDao;
import views.LoginView;
import views.SystemView;

public class LoginController implements ActionListener{
    private Employees employee;
    private EmployeesDao employee_dao;
    private LoginView login_view;

    public LoginController(Employees employee, EmployeesDao employee_dao, LoginView login_view) {
        this.employee = employee;
        this.employee_dao = employee_dao;
        this.login_view = login_view;
        this.login_view.btn_enter.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Obtener los datos de la vista
        String user = login_view.txt_username.getText().trim();
        String pass = String.valueOf(login_view.txt_password.getPassword());
        
        if(e.getSource() == login_view.btn_enter) {
            //Validar que los campos no esten vacios
            if(!user.equals("") || !pass.equals("")) {
                //Pasar los parametros al metodo login
                employee = employee_dao.loginQuery(user, pass);
                
                //Verificar la existencia del usuario
                if(employee.getUser_name() != null) {
                    if(employee.getRol().equals("Administrador")) {
                        SystemView admin = new SystemView();
                        admin.setVisible(true);
                    } else {
                        SystemView aux = new SystemView();
                        aux.setVisible(true);
                    }
                    this.login_view.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect user or password");
                }
            } else {
                JOptionPane.showMessageDialog(null, "The fields are empty");
            }
        }
    }
    
    
}
