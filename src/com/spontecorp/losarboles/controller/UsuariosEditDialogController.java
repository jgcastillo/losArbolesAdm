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

import com.spontecorp.losarboles.model.Perfil;
import com.spontecorp.losarboles.model.Usuario;
import com.spontecorp.losarboles.utilities.Security;
import com.spontecorp.losarboles.utilities.Utilidades;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Casper
 */
public class UsuariosEditDialogController extends UsuariosDialogController {

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
    @FXML
    private CheckBox cambioClaveCheckBox;


    @Override
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

        nombreField.setText(usuario.getNombre());
        apellidoField.setText(usuario.getApellido());
        usuarioField.setText(usuario.getUsr());
        claveField.setText("");
        claveRepeatField.setText("");
        perfilChoiceBox.setValue(usuario.getPerfilId());
        statusChoiceBox.setValue(usuario.getStatus()== Utilidades.ACTIVO? "Activo":"Inactivo");
    }

    /**
     * Llamado cuando el usuario hace click en el botón Aceptar desde editar.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            usuario.setNombre(nombreField.getText());
            usuario.setApellido(apellidoField.getText());
            usuario.setUsr(usuarioField.getText());
            
            if(cambioClaveCheckBox.isSelected()){
                usuario.setPsw(Security.encript(claveField.getText()));
            }
            
            usuario.setPerfilId((Perfil) perfilChoiceBox.getValue());
            usuario.setStatus(((String) statusChoiceBox.getValue()).equals("Activo") ? 1 : 0);

            okClicked = true;
            dialogStage.close();
        }
    }


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

        if (cambioClaveCheckBox.isSelected()) {
            if (claveField.getText() == null || claveField.getText().length() == 0) {
                errorMessage += "No puede dejar el campo Clave en blanco!\n";
            }
            if (claveRepeatField.getText() == null || claveRepeatField.getText().length() == 0) {
                errorMessage += "No puede dejar el campo Repita Clave en blanco!\n";
            }

            if (!claveField.getText().equals(claveRepeatField.getText())) {
                errorMessage += "El campo Clave debe coincidir con el campo Repita Clave!\n";
            }

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
