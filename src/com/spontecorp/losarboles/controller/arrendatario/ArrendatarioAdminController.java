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

import com.spontecorp.losarboles.controller.MainController;
import com.spontecorp.losarboles.jpa.ArrendatarioFacade;
import com.spontecorp.losarboles.model.Arrendatario;
import com.spontecorp.losarboles.model.datafx.ArrendatarioFx;
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
public class ArrendatarioAdminController extends MainController implements Initializable {

    @FXML
    private TableView<ArrendatarioFx> arrendatariosTable;
    @FXML
    private TableColumn<ArrendatarioFx, String> apellidoColumn;
    @FXML
    private TableColumn<ArrendatarioFx, String> nombreColumn;
    @FXML
    private TableColumn<ArrendatarioFx, String> telefonoColumn;
    @FXML
    private TableColumn<ArrendatarioFx, String> celularColumn;
    @FXML
    private TableColumn<ArrendatarioFx, String> statusColumn;
    
    
    private final ObservableList<ArrendatarioFx> arrendatariosData = FXCollections.observableArrayList();
    private final Logger logger = LoggerFactory.getLogger(ArrendatarioAdminController.class);
    
    private ArrendatarioFacade facade = new ArrendatarioFacade();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellidoProperty"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombreProperty"));
        telefonoColumn.setCellValueFactory(new PropertyValueFactory<>("telefonoProperty"));
        celularColumn.setCellValueFactory(new PropertyValueFactory<>("celularProperty"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusProperty"));
        loadArrendatariosTable();
    }    

    @Override
    public boolean isInputValid() {
        return true;
    }
    
    public ObservableList<ArrendatarioFx> getArrendatariosData() {
        return arrendatariosData;
    }
    
    private void loadArrendatariosTable() {
        arrendatariosData.clear();
        List<Arrendatario> listArrendatarios = facade.findAll();

        listArrendatarios.stream().forEach((arrendatario) -> {
            arrendatariosData.add(new ArrendatarioFx(arrendatario.getNombre(),
                    arrendatario.getApellido(),
                    arrendatario.getCi(),
                    arrendatario.getDireccion(),
                    arrendatario.getTelefono(),
                    arrendatario.getCelular(),
                    arrendatario.getTlfTrabajo(),
                    arrendatario.getEmail(),
                    arrendatario.getStatus()));
        });
        arrendatariosTable.setItems(arrendatariosData);
    }
    
    @FXML
    private void handleAgregarButton() {
        try {
            Arrendatario tempArrendatario = new Arrendatario();
            boolean okClicked = mainApp.showArrendatariosDialogs(tempArrendatario, 
                                Utilidades.ARRENDATARIOS_NUEVO_DIALOG,
                                true);
            if (okClicked) {
                facade.create(tempArrendatario);
                loadArrendatariosTable();
                Dialogs.create()
                        .title("Agregar un  Arrendatario")
                        .masthead("Se ha Agregado un Arrendatario al sistema")
                        .message("Arrendatario agregado con éxito.")
                        .showInformation();
            }

        } catch (Exception e) {
            logger.error("Ha ocurrido un error al guardar el usuario", e);
        }
    }

    @FXML
    private void handleEditarButton() {
        try {
            ArrendatarioFx selectedRenterFx = arrendatariosTable.getSelectionModel().getSelectedItem();
            if(selectedRenterFx != null){
                Arrendatario selectedRenter = facade.findArrendatario(selectedRenterFx.getCiProperty());
                boolean okClicked = mainApp.showArrendatariosDialogs(selectedRenter, 
                                    Utilidades.ARRENDATARIOS_NUEVO_DIALOG,
                                    false);
                if(okClicked){
                    facade.edit(selectedRenter);
                    loadArrendatariosTable();
                    Dialogs.create()
                            .title("Edición de Arrendatario")
                            .masthead("Se ha Editado un Arrendatario en el sistema")
                            .message("Arrendatario editado con éxito.")
                            .showInformation();
                }
            } else {
                Dialogs.create()
                        .title("No hay Selección")
                        .masthead("No se ha seleccionado un Arrendatario de la tabla")
                        .message("Por favor, seleccione un Arrendatario de la tabla.")
                        .showInformation();
            }
        } catch (Exception e) {
            logger.error("Ha ocurrido un error al editar el Arrendatario", e);
        }

    }

    @FXML
    private void handleInactivarButton() {
        try {
            ArrendatarioFx selectedArrendatarioFx = arrendatariosTable.getSelectionModel().getSelectedItem();
            if (selectedArrendatarioFx != null) {
                Arrendatario selectedArrendatario = facade.findArrendatario(selectedArrendatarioFx.getCiProperty());
                selectedArrendatario.setStatus(Utilidades.INACTIVO);

                facade.edit(selectedArrendatario);
                loadArrendatariosTable();
                Dialogs.create()
                        .title("Inactivar Arrendatario")
                        .masthead("Se ha Inactivado un Arrendatario del sistema")
                        .message("Arrendatario inactivado con éxito.")
                        .showInformation();
            } else {
                // Nothing selected.
                Dialogs.create()
                        .title("No Hay Selección")
                        .masthead("No se ha selecciona do un Arrendatario de la tabla")
                        .message("Por favor, seleccione una persona de la tabla.")
                        .showWarning();
            }
        } catch (Exception e) {
            logger.error("Ha ocurrido un error al editar el local", e);
        }
    }
}
