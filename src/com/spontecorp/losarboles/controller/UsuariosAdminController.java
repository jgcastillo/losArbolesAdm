/*
 * Copyrigth (c) 2014, Spontecorp, C.A. Todos los derechos reservados
 *  
 * ESTE SOFTWARE ES SUMINISTRADO POR LOS PROPIETARIOS DE LOS DERECHOS DE AUTOR
 * "TAL COMO ESTA" Y CUALQUIER GARANTIA EXPRESA O IMPLICITA, INCLUYENDO PERO NO LIMITADO A,
 * LAS GARANTIAS DE COMERCIALIZACION Y/O PARA UN PROPOSITO PARTICULAR. EN NINGUN CASO
 * EL PROPIETARIOS DE LOS DERECHOS DE AUTOR (COPYRIGTH) O SUS COLABORADORES SERAN
 * RESPONSABLES  POR NINGUN DA�O DIRECTO, INDIRECTO, INCIDENTAL, ESPECIAL, EJEMPLARES O
 * DERIVADOS (INCLUYENDO, PERO NO LIMITADO A LA OBTENCION DE BIENES O SERVICIOS SUSTITUTOS, 
 * LA PERDIDA DE USO, DE DATOS O BENEFICIOS O LA INTERRUPCION DEL NEGOCIO) CAUSADOS COMO
 * EN CUALQUIER TEORIA DE RESPONSABILIDAD, YA SEA POR CONTRATO, RESPONSABILIDAD OBJETIVA 
 * O AGRAVIO (INCLUYENDO NEGLIGENCIA) QUE RESULTE DE CUALQUIER FORMA DEL USO DE ESTE SOFTWARE,
 * AUNQUE SE HAYA ADVERTIDO DE LA POSIBILIDAD DE TALES DA�OS.

 */

package com.spontecorp.losarboles.controller;

import com.spontecorp.losarboles.MainApp;
import com.spontecorp.losarboles.jpa.UsuarioFacade;
import com.spontecorp.losarboles.model.Usuario;
import com.spontecorp.losarboles.model.datafx.UsuarioFx;
import com.spontecorp.losarboles.utilities.Utilidades;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.dialog.Dialogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author Casper
 */
public class UsuariosAdminController extends MainController implements Initializable{

    @FXML
    private TableView<UsuarioFx> usuariosTable;
    @FXML
    private TableColumn<UsuarioFx, String> apellidoColumn;
    @FXML
    private TableColumn<UsuarioFx, String> nombreColumn;
    @FXML
    private TableColumn<UsuarioFx, String> usuarioColumn;
    @FXML
    private TableColumn<UsuarioFx, String> statusColumn;
    
    private final ObservableList<UsuarioFx> usuariosData = FXCollections.observableArrayList();
    private static final Logger logger = LoggerFactory.getLogger(UsuariosAdminController.class);
    // Reference to the main application.
    private MainApp mainApp;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellidoProperty"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombreProperty"));
        usuarioColumn.setCellValueFactory(new PropertyValueFactory<>("usrProperty"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusProperty"));
        loadUsuariosTable();
    }
    
    /**
     * Es llamada por la aplicacion principal para dar referenciarse a si misma
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public ObservableList<UsuarioFx> getUsuariosData(){
        return usuariosData;
    }
    
    private void loadUsuariosTable(){
        usuariosData.clear();
        UsuarioFacade facade = new UsuarioFacade();
        List<Usuario> listUsuario = facade.findAll();
        
        listUsuario.stream().forEach((usuario) -> {
            usuariosData.add(new UsuarioFx(usuario.getNombre(),
                    usuario.getApellido(),
                    usuario.getUsr(),
                    usuario.getStatus()== Utilidades.ACTIVO?"Activo":"Inactivo"));
        });
        
        usuariosTable.setItems(usuariosData);
    }
    
    @FXML
    private void handleAgregarButton(){
        try {
            UsuarioFacade facade = new UsuarioFacade();
            Usuario tempUsuario = new Usuario();
            boolean okClicked = mainApp.showUsuariosDialogs(tempUsuario, Utilidades.USUARIOS_NUEVO_DIALOG);
            if (okClicked) {
                facade.create(tempUsuario);
                loadUsuariosTable();
                Dialogs.create()
                    .title("Agregar un  Usuario")
                    .masthead("Se ha Agregado un Usuario al sistema")
                    .message("Usuario agregado con éxito.")
                    .showInformation();
            }
            
        } catch (Exception e) {
            logger.error("Ha ocurrido un error al guardar el usuario", e);
        }
    }
    
    @FXML
    private void handleEditarButton(){
        try {
            UsuarioFacade facade = new UsuarioFacade();
            UsuarioFx selectedUsuarioFx = usuariosTable.getSelectionModel().getSelectedItem();
            if(selectedUsuarioFx != null){
                Usuario selectedUsuario = facade.findUser(selectedUsuarioFx.getUsrProperty());
                boolean okClicked = mainApp.showUsuariosDialogs(selectedUsuario, Utilidades.USUARIOS_EDIT_DIALOG);
                if(okClicked){
                    facade.edit(selectedUsuario);
                    loadUsuariosTable();
                    Dialogs.create()
                            .title("Edición de Usuario")
                            .masthead("Se ha Editado un Usuario del sistema")
                            .message("Usuario editado con éxito.")
                            .showInformation();
                }
            } else {
                Dialogs.create()
                        .title("No Hay Selección")
                        .masthead("No se ha selecciona do un Usuario de la tabla")
                        .message("Por favor, seleccione una persona de la tabla.")
                        .showWarning();
            }
        } catch (Exception e) {
            logger.error("Ha ocurrido un error al editar el usuario", e);
        }
        
    }
    
    /**
     * Llamado cuando el usuario hace click en botón Eliminar
     */
    @FXML
    private void handleInactivarButton() {
        try {
            UsuarioFacade facade = new UsuarioFacade();
            UsuarioFx selectedUsuarioFx = usuariosTable.getSelectionModel().getSelectedItem();
            if(selectedUsuarioFx != null){
                Usuario selectedUsuario = facade.findUser(selectedUsuarioFx.getUsrProperty());
                selectedUsuario.setStatus(Utilidades.INACTIVO);
                
                facade.edit(selectedUsuario);
                loadUsuariosTable();
                Dialogs.create()
                        .title("Inactivar Usuario")
                        .masthead("Se ha Inactivado un Usuario del sistema")
                        .message("Usuario inactivado con éxito.")
                        .showInformation();
            } else {
                // Nothing selected.
                Dialogs.create()
                        .title("No Hay Selección")
                        .masthead("No se ha selecciona do un Usuario de la tabla")
                        .message("Por favor, seleccione una persona de la tabla.")
                        .showWarning();
            }
        } catch (Exception e) {
            logger.error("Ha ocurrido un error al editar el usuario", e);
        }
    }

    @Override
    public boolean isInputValid() {
        // no hace nada aqui, sino enlas extensiones
        return true;
    }
    
}
