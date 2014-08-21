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

package com.spontecorp.losarboles.controller.locales;

import com.spontecorp.losarboles.model.Local;
import com.spontecorp.losarboles.model.Uso;
import com.spontecorp.losarboles.utilities.EmailValidator;
import com.spontecorp.losarboles.utilities.Utilidades;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Casper
 */
public class LocalesNuevoDialogController extends LocalesDialogController{

    @FXML
    private TextField nombreField;
    @FXML
    private TextArea ubicacionField;
    
    @Override
    public boolean isInputValid() {
        String errorMessage = "";

        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "No puede dejar el campo Nombre en blanco!\n";
        }
        if (ubicacionField.getText() == null || ubicacionField.getText().length() == 0) {
            errorMessage += "No puede dejar el campo Ubicación en blanco!\n";
        }
        if(statusChoiceBox.getSelectionModel().isEmpty()){
            errorMessage += "Debe seleccionar un status del Local!\n";
        }
        if (usoChoiceBox.getSelectionModel().isEmpty()) {
            errorMessage += "Debe seleccionar un uso para el Local!\n";
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

    @Override
    public void setLocal(Local local) {
        this.local = local;
        
        nombreField.setText(local.getNombre());
        ubicacionField.setText(local.getUbicacion());
        statusChoiceBox.setValue(local.getStatus());
        usoChoiceBox.setValue(local.getUsoId());
    }
    
    @FXML
    private void handleOk(){
        if(isInputValid()){
            local.setNombre(nombreField.getText());
            local.setUbicacion(ubicacionField.getText());
            local.setUsoId((Uso)usoChoiceBox.getValue());
            local.setStatus(Utilidades.parseStatusLocal(statusChoiceBox.getValue().toString()));
            
            okClicked = true;
            dialogStage.close();
        }
    }
    
}
