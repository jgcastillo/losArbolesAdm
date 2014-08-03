/*
 * Copyrigth (c) 2014, Spontecorp, C.A. Todos los derechos reservados
 *  
 * ESTE SOFTWARE ES SUMINISTRADO POR LOS PROPIETARIOS DE LOS DERECHOS DE AUTOR
 * "TAL COMO ESTA" Y CUALQUIER GARANTIA EXPRESA O IMPLICITA, INCLUYENDO PERO NO LIMITADO A,
 * LAS GARANTIAS DE COMERCIALIZACION Y/O PARA UN PROPOSITO PARTICULAR. EN NINGUN CASO
 * EL PROPIETARIOS DE LOS DERECHOS DE AUTOR (COPYRIGTH) O SUS COLABORADORES SERAN
 * RESPONSABLES  POR NINGUN DAÑO DIRECTO, INDIRECTO, INCIDENTAL, ESPECIAL, EJEMPLARES O
 * DERIVADOS (INCLUYENDO, PERO NO LIMITADO A LA OBTENCION DE BIENES O SERVICIOS SUSTITUTOS, 
 * LA PERDIDA DE USO, DE DATOS O BENEFICIOS O LA INTERRUPCION DEL NEGOCIO) CAUSADOS COMO
 * EN CUALQUIER TEORIA DE RESPONSABILIDAD, YA SEA POR CONTRATO, RESPONSABILIDAD OBJETIVA 
 * O AGRAVIO (INCLUYENDO NEGLIGENCIA) QUE RESULTE DE CUALQUIER FORMA DEL USO DE ESTE SOFTWARE,
 * AUNQUE SE HAYA ADVERTIDO DE LA POSIBILIDAD DE TALES DAÑOS.

 */

package com.spontecorp.losarboles.controller;

import com.spontecorp.losarboles.MainApp;
import com.spontecorp.losarboles.controller.locales.LocalesAdminController;
import com.spontecorp.losarboles.controller.locales.UsosAdminController;
import com.spontecorp.losarboles.model.Usuario;
import com.spontecorp.losarboles.utilities.Utilidades;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Casper
 */
public class RootLayoutController implements Initializable {

    private MainApp application;
    private Usuario loggedUser;
    
    @FXML
    private Menu menuConfiguracion;
    @FXML
    private MenuBar menuBar;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuBar.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FadeTransition fadeTransition
                        = new FadeTransition(Duration.millis(500), menuBar);
                fadeTransition.setFromValue(0.0);
                fadeTransition.setToValue(1.0);
                fadeTransition.play();

            }
        });
        menuBar.setOnMouseExited((MouseEvent mouseEvent) -> {
            FadeTransition fadeTransition
                    = new FadeTransition(Duration.millis(500), menuBar);
            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.0);
            fadeTransition.play();
        });
    }    
    
    public void setApplication(MainApp application) {
        this.application = application;
        loggedUser = application.getLoggedUser();
        if(!loggedUser.getPerfilId().getNombre().equals(Utilidades.ADMINISTRADOR)){
            menuConfiguracion.setVisible(false);
        } else {
            menuConfiguracion.setVisible(true);
        }
    }
    
    public void setLoggedUser(Usuario usuario){
        this.loggedUser = usuario;
    }
    
    @FXML
    private void handleMenuInicial(ActionEvent event){
        application.showBaseView();
    }
    
    @FXML
    private void handleSalir(ActionEvent event){
        Platform.exit();
    }
    
    @FXML
    private void handleMenuUsuarios(ActionEvent event){
        application.showNewBase(Utilidades.USUARIOS_ADMIN_FILE, new UsuariosAdminController());
    }
    
    @FXML
    private void handleMenuLocales(ActionEvent event){
        application.showNewBase(Utilidades.LOCALES_ADMIN_FILE, new LocalesAdminController());
    }
    
    @FXML
    private void handleMenuUsoLocales(ActionEvent event){
        application.showNewBase(Utilidades.USO_LOCALES_ADMIN_FILE, new UsosAdminController());
    }
}
