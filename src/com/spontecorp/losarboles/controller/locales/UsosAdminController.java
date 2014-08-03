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
import com.spontecorp.losarboles.jpa.UsoFacade;
import com.spontecorp.losarboles.model.Uso;
import com.spontecorp.losarboles.model.datafx.UsoFx;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.dialog.Dialogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author Casper
 */
public class UsosAdminController extends MainController implements Initializable {

    @FXML
    private Pane newUsoPane;
    @FXML
    private Button nuevoUsoButton;
    @FXML
    private TextField nombreUsoField;
    @FXML
    private TableView<UsoFx> usosTable;
    @FXML
    private TableColumn<UsoFx, String> nombreUsoColumn;

    private final ObservableList<UsoFx> usosData = FXCollections.observableArrayList();
    private final UsoFacade usoFacade = new UsoFacade();
    private boolean isNew = false;
    private Uso usoEdited;

    private static final Logger logger = LoggerFactory.getLogger(UsosAdminController.class);

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newUsoPane.setOpacity(0.0);

        nombreUsoColumn.setCellValueFactory(new PropertyValueFactory<>("nombreProperty"));
        loadUsosTable();
    }

    private void loadUsosTable() {
        List<Uso> usosList = usoFacade.findAll();
        if (!usosList.isEmpty()) {
            try {
                usosData.clear();
                usosList.stream().forEach((uso) -> {
                    usosData.add(new UsoFx(uso.getNombre()));
                });

                usosTable.setItems(usosData);
            } catch (Exception e) {
                logger.error("Lanzo una excepcion", e);
            }
        }
    }

    @FXML
    private void handleNewUsoButton() {
        isNew = true;
        nombreUsoField.setText("");
        showNewUsoPane();
    }

    @FXML
    private void handleEditUsoButton() {
        UsoFx usoSelectedFx = usosTable.getSelectionModel().getSelectedItem();
        if (usoSelectedFx != null) {
            usoEdited = usoFacade.find(usoSelectedFx.getNombreProperty());
            nombreUsoField.setText(usoEdited.getNombre());
        }
        isNew = false;
        showNewUsoPane();
    }

    @FXML
    private void handleCancelButton() {
        hideNewUsoPane();
    }

    @FXML
    private void hadleAceptarButton() {
        if (isNew) {
            if (isInputValid()) {
                Uso usoTemp = new Uso();
                usoTemp.setNombre(nombreUsoField.getText());
                usoFacade.create(usoTemp);
                Dialogs.create()
                        .title("Agregar un Uso de Local")
                        .masthead("Se ha Agregado un Uso de Local al Sistema")
                        .message("Uso de Local Agregado con Exito")
                        .showInformation();
            } else {
                Dialogs.create()
                        .title("Agregar un Uso de Local")
                        .masthead("Error de campo obligatorio")
                        .message("Debe completar el campo con el nombre del Uso de Local")
                        .showError();
            }
        } else {
            if (!nombreUsoField.getText().equals(usoEdited.getNombre())) {
                usoEdited.setNombre(nombreUsoField.getText());
                usoFacade.edit(usoEdited);
                Dialogs.create()
                        .title("Editar un Uso de Local")
                        .masthead("Se ha Editado un Uso de Local al Sistema")
                        .message("Uso de Local Editado con Exito")
                        .showInformation();
            }
        }
        loadUsosTable();
        hideNewUsoPane();
    }

    private void showNewUsoPane() {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), newUsoPane);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        nuevoUsoButton.setDisable(true);
        nombreUsoField.setPromptText("Nombre del Uso de Local");
    }

    private void hideNewUsoPane() {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), newUsoPane);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();
        nuevoUsoButton.setDisable(false);
    }

    @Override
    public boolean isInputValid() {
        if (nombreUsoField.getText() == null || nombreUsoField.getText().equals("")) {
            return false;
        } else {
            return true;
        }
    }

}
