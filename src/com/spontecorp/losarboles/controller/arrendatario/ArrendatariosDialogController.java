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
package com.spontecorp.losarboles.controller.arrendatario;

import com.spontecorp.losarboles.model.Arrendatario;
import com.spontecorp.losarboles.utilities.Utilidades;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.EmailValidator;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author Casper
 */
public class ArrendatariosDialogController implements Initializable {

    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField ciField;
    @FXML
    private TextArea direccionTextArea;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField celularField;
    @FXML
    private TextField tlfTrabajoField;
    @FXML
    private TextField emailField;
    @FXML
    private ChoiceBox statusChoiceBox;
    @FXML
    private Label titleLabel;

    private Stage dialogStage;
    private boolean okClicked = false;
    private Arrendatario arrendatario;
    //private final EmailValidator emailValidator = new EmailValidator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadStatusChoiceBox();
    }

    public void setArrendatario(Arrendatario arrendatario, boolean nuevo) {

        if(nuevo){
            titleLabel.setText("Agregar un Arrendatario");
        } else {
            titleLabel.setText("Editar un Arrendatario");
        }
        
        this.arrendatario = arrendatario;

        nombreField.setText(arrendatario.getNombre());
        apellidoField.setText(arrendatario.getApellido());
        ciField.setText(Integer.toString(arrendatario.getCi()));
        direccionTextArea.setText(arrendatario.getDireccion());
        telefonoField.setText(arrendatario.getTelefono());
        celularField.setText(arrendatario.getCelular());
        tlfTrabajoField.setText(arrendatario.getTlfTrabajo());
        emailField.setText(arrendatario.getEmail());
        statusChoiceBox.setValue(Utilidades.parseStatusArrendatario(arrendatario.getStatus()));
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    private void loadStatusChoiceBox() {
        statusChoiceBox.setItems(FXCollections.observableArrayList("Inactivo", "Activo", "Vetado"));
    }

    /**
     * Llamado cuando el usuario hace click en Cancelar.
     */
    @FXML
    protected void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            arrendatario.setNombre(nombreField.getText());
            arrendatario.setApellido(apellidoField.getText());
            arrendatario.setCi(Integer.parseInt(ciField.getText()));
            arrendatario.setDireccion(direccionTextArea.getText());
            arrendatario.setTelefono(telefonoField.getText());
            arrendatario.setCelular(celularField.getText());
            arrendatario.setTlfTrabajo(tlfTrabajoField.getText());
            arrendatario.setEmail(emailField.getText());
            arrendatario.setStatus(Utilidades.parseStatusArrendatario(statusChoiceBox.getValue().toString()));

            okClicked = true;
            dialogStage.close();
        }
    }

    public boolean isInputValid() {
        String errorMessage = "";
        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "No puede dejar el campo Nombre en blanco!\n";
        }
        if (apellidoField.getText() == null || apellidoField.getText().length() == 0) {
            errorMessage += "No puede dejar el campo Apellido en blanco!\n";
        }
        if (ciField.getText() == null || ciField.getText().length() == 0) {
            errorMessage += "No puede dejar el campo C.I. en blanco!\n";
        }
        if (direccionTextArea.getText() == null || direccionTextArea.getText().length() == 0) {
            errorMessage += "No puede dejar el campo Dirección en blanco!\n";
        }
        if (emailField.getText() != null || emailField.getText().length() != 0) {
            if (!EmailValidator.getInstance().isValid(emailField.getText())) {
                errorMessage += "Debe introducir un email válido";
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
