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

import com.spontecorp.losarboles.jpa.UsoFacade;
import com.spontecorp.losarboles.model.Local;
import com.spontecorp.losarboles.model.Uso;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 *
 * @author Casper
 */
public abstract class LocalesDialogController implements Initializable{
    @FXML
    protected ChoiceBox statusChoiceBox;
    @FXML
    protected ChoiceBox usoChoiceBox;
    protected Stage dialogStage;
    protected boolean okClicked = false;
    protected Local local;
    
    protected final ObservableList obsListUso = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUsoChoiceBox();
        loadStatusChoiceBox();
    }

    public abstract boolean isInputValid();
    
    public abstract void setLocal(Local local);
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public boolean isOkClicked() {
        return okClicked;
    }
    
    /**
     * Llamado cuando el usuario hace click en Cancelar.
     */
    @FXML
    protected void handleCancel() {
        dialogStage.close();
    }
    
    private void loadStatusChoiceBox(){
        statusChoiceBox.setItems(FXCollections.observableArrayList("Libre", "Ocupado", "Alquilado", "Inhabilitado"));
    }
    
    private void loadUsoChoiceBox() {
        UsoFacade facade = new UsoFacade();
        List<Uso> usoList = facade.findAll();
        usoList.stream().forEach((uso)->{
            obsListUso.add(uso);
        });
        usoChoiceBox.setItems(obsListUso);
    }

}
