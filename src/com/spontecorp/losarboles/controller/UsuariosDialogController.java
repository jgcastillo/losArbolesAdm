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
 * Esta clase es la raiz de los controladores de Creación y Edición de Usuarios.
 * Los métodos <code>setUsuario()</code> y <code>isInputValid()</code> son abstractos
 * para poder aprovechar la funcionalidad de acuerdo a la funcionalidad deseada.
 * 
 * @author Casper
 */
public abstract class UsuariosDialogController implements Initializable{

    @FXML
    protected ChoiceBox perfilChoiceBox;
    @FXML
    protected ChoiceBox statusChoiceBox;
    
    protected Stage dialogStage;
    protected Usuario usuario;
    protected boolean okClicked = false;
    protected final ObservableList<Perfil> obsListPerfil = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadPerfilChoiceBox();
        loadStatusChoiceBox();
    }
    
    /**
     * Configura la Stage de este dialogo.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    private void loadPerfilChoiceBox() {
        PerfilFacade facade = new PerfilFacade();
        List<Perfil> listPerfil = facade.findAll();
        listPerfil.stream().forEach((perfil) -> {
            obsListPerfil.add(perfil);
        });
        perfilChoiceBox.setItems(obsListPerfil);
    }
    
    private void loadStatusChoiceBox() {
        statusChoiceBox.setItems(FXCollections.observableArrayList("Activo", "Inactivo"));
    }
    
    public abstract void setUsuario(Usuario usuario);
    
    protected abstract boolean isInputValid();
    
    /**
     * Retorna true si el botón Aceptar fue pulsado, de lo contrario false.
     *
     * @return
     */
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
    
    
}
