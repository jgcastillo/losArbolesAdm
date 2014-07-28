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

import com.spontecorp.losarboles.jpa.PerfilFacade;
import com.spontecorp.losarboles.model.Perfil;
import com.spontecorp.losarboles.model.Usuario;
import com.spontecorp.losarboles.utilities.Security;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Casper
 */
public class UsuariosNuevoDialogController extends UsuariosDialogController {
    
    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField usuarioField;
    @FXML
    private PasswordField claveField;
    @FXML
    private PasswordField claveRepeatField;
//    @FXML
//    private ChoiceBox perfilChoiceBox;
//    @FXML
//    private ChoiceBox statusChoiceBox;
    
//    private Stage dialogStage;
//    private Usuario usuario;
//    private boolean okClicked = false;
    
//    private final ObservableList<Perfil> obsListPerfil = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     * @param usuario
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        loadPerfilChoiceBox();
//        loadStatusChoiceBox();
//    }    
    
//    /**
//     * Configura la Stage de este dialogo.
//     *
//     * @param dialogStage
//     */
//    public void setDialogStage(Stage dialogStage) {
//        this.dialogStage = dialogStage;
//    }
    
    @Override
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
        
        nombreField.setText(usuario.getNombre());
        apellidoField.setText(usuario.getApellido());
        usuarioField.setText(usuario.getUsr());
        claveField.setText("");
        claveRepeatField.setText("");
        perfilChoiceBox.setValue(usuario.getPerfilId());
        statusChoiceBox.setValue(usuario.getStatus());
    }
    
    /**
     * Retorna true si el botón Aceptar fue pulsado, de lo contrario false.
     *
     * @return
     */
//    public boolean isOkClicked() {
//        return okClicked;
//    }
    
//    private void loadPerfilChoiceBox(){
//        PerfilFacade facade = new PerfilFacade();
//        List<Perfil> listPerfil = facade.findAll();
//        listPerfil.stream().forEach((perfil) -> {
//            obsListPerfil.add(perfil);
//        });
//        perfilChoiceBox.setItems(obsListPerfil);
//    }
//    
//    private void loadStatusChoiceBox(){
//        statusChoiceBox.setItems(FXCollections.observableArrayList("Activo", "Inactivo"));
//    }
    
    /**
     * Llamado cuando el usuario hace click en el botón Aceptar desde nuevo.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            usuario.setNombre(nombreField.getText());
            usuario.setApellido(apellidoField.getText());
            usuario.setUsr(usuarioField.getText());
            usuario.setPsw(Security.encript(claveField.getText()));
            usuario.setPerfilId((Perfil)perfilChoiceBox.getValue());
            usuario.setStatus(((String)statusChoiceBox.getValue()).equals("Activo")?1:0);
            
            okClicked = true;
            dialogStage.close();
        }
    }
    
//    /**
//     * Llamado cuando el usuario hace click en el botón Aceptar desde editar.
//     */
//    @FXML
//    private void handleEditOk() {
//        if (isInputValid()) {
//            usuario.setNombre(nombreField.getText());
//            usuario.setApellido(apellidoField.getText());
//            usuario.setUsr(usuarioField.getText());
//            usuario.setPsw(claveField.getText());
//            usuario.setPerfilId((Perfil) perfilChoiceBox.getValue());
//            usuario.setStatus(((String) statusChoiceBox.getValue()).equals("Activo") ? 1 : 0);
//
//            okClicked = true;
//            dialogStage.close();
//        }
//    }

//    /**
//     * Called when the user clicks cancel.
//     */
//    @FXML
//    private void handleCancel() {
//        dialogStage.close();
//    }
    
    /**
     * Valida que el ingreso en los campos sea correcto.
     *
     * @return true si la entrada es válida
     */
    @Override
    protected boolean isInputValid() {
        String errorMessage = "";

        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "No puede dejar el campo Nombre en blanco!\n";
        }
        if (apellidoField.getText() == null || apellidoField.getText().length() == 0) {
            errorMessage += "No puede dejar el campo Apellido en blanco!\n";
        }
        if (usuarioField.getText() == null || usuarioField.getText().length() == 0) {
            errorMessage += "No puede dejar el campo Usuario en blanco!\n";
        }
        if (claveField.getText() == null || claveField.getText().length() == 0) {
            errorMessage += "No puede dejar el campo Clave en blanco!\n";
        }
        if (claveRepeatField.getText() == null || claveRepeatField.getText().length() == 0) {
            errorMessage += "No puede dejar el campo Repita Clave en blanco!\n";
        }
        
        if(!claveField.getText().equals(claveRepeatField.getText())){
            errorMessage += "El campo Clave debe coincidir con el campo Repita Clave!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Dialogs.create()
                    .title("Campos Inválidos")
                    .masthead("Por favor, corrija los campos inválidos")
                    .message(errorMessage)
                    .showError();
            return false;
        }
    }
}
