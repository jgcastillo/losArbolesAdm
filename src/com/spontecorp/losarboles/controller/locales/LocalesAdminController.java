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

import com.spontecorp.losarboles.controller.MainController;
import com.spontecorp.losarboles.jpa.LocalFacade;
import com.spontecorp.losarboles.model.Local;
import com.spontecorp.losarboles.model.datafx.LocalFx;
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
public class LocalesAdminController extends MainController implements Initializable {

    @FXML
    private TableView<LocalFx> localesTable;
    @FXML
    private TableColumn<LocalFx, String> nombreColumn;
    @FXML
    private TableColumn<LocalFx, String> ubicacionColumn;
    @FXML
    private TableColumn<LocalFx, String> usoColumn;
    @FXML
    private TableColumn<LocalFx, String> statusColumn;
    
    private final ObservableList<LocalFx> localesData = FXCollections.observableArrayList();
    private final LocalFacade facade = new LocalFacade();
    private final Logger logger = LoggerFactory.getLogger(LocalesAdminController.class);
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombreProperty"));
        ubicacionColumn.setCellValueFactory(new PropertyValueFactory<>("ubicacionProperty"));
        usoColumn.setCellValueFactory(new PropertyValueFactory<>("usoProperty"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusProperty"));
        loadLocalesTable();
    }
    

    @Override
    public boolean isInputValid() {
        // no hace nada aqui, sino en las extensiones
        return true;
    }
    

    public ObservableList<LocalFx> getLocalesData() {
        return localesData;
    }
    
    private void loadLocalesTable(){
        localesData.clear();
        List<Local> listLocales = facade.findAll();
        
        listLocales.stream().forEach((local) ->{
            localesData.add(new LocalFx(local.getNombre(), 
                    local.getUbicacion(),
                    local.getStatus(), 
                    local.getUsoId()));
        });
        localesTable.setItems(localesData);
    }

    @FXML
    private void handleAgregarButton() {
        try {
            Local tempLocal = new Local();
            boolean okClicked = mainApp.showLocalesDialogs(tempLocal, Utilidades.LOCALES_NUEVO_DIALOG);
            if (okClicked) {
                facade.create(tempLocal);
                loadLocalesTable();
                Dialogs.create()
                        .title("Agregar un  Local")
                        .masthead("Se ha Agregado un Local al sistema")
                        .message("Local agregado con éxito.")
                        .showInformation();
            }

        } catch (Exception e) {
            logger.error("Ha ocurrido un error al guardar el usuario", e);
        }
    }

    @FXML
    private void handleEditarButton(){
        try {
            LocalFx selectedLocalFx = localesTable.getSelectionModel().getSelectedItem();
            if(selectedLocalFx != null){
                Local selectedLocal = facade.findLocal(selectedLocalFx.getNombreProperty(),
                                                        selectedLocalFx.getUbicacionProperty());
                boolean okClicked = mainApp.showLocalesDialogs(selectedLocal, Utilidades.LOCALES_EDIT_DIALOG);
                if(okClicked){
                    facade.edit(selectedLocal);
                    loadLocalesTable();
                    Dialogs.create()
                            .title("Edición de Local")
                            .masthead("Se ha Editado un Local del sistema")
                            .message("Local editado con éxito.")
                            .showInformation();
                    
                }
            }else{
                Dialogs.create()
                        .title("No Hay Selección")
                        .masthead("No se ha seleccionado un Local de la tabla")
                        .message("Por favor, seleccione un local de la tabla.")
                        .showWarning();
            }
        } catch (Exception e) {
            logger.error("Ha ocurrido un error al editar el local", e);
        }
    }
    
    @FXML
    private void handleInactivarButton(){
        try {
            LocalFx selectedLocalFx = localesTable.getSelectionModel().getSelectedItem();
            if (selectedLocalFx != null) {
                Local selectedLocal = facade.findLocal(selectedLocalFx.getNombreProperty(), 
                                                        selectedLocalFx.getUbicacionProperty());
                selectedLocal.setStatus(Utilidades.INHABILITADO);

                facade.edit(selectedLocal);
                loadLocalesTable();
                Dialogs.create()
                        .title("Inactivar Local")
                        .masthead("Se ha Inactivado un Local del sistema")
                        .message("Local inactivado con éxito.")
                        .showInformation();
            } else {
                // Nothing selected.
                Dialogs.create()
                        .title("No Hay Selección")
                        .masthead("No se ha selecciona do un Local de la tabla")
                        .message("Por favor, seleccione una persona de la tabla.")
                        .showWarning();
            }
        } catch (Exception e) {
            logger.error("Ha ocurrido un error al editar el local", e);
        }
    }
}
