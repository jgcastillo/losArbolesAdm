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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author Casper
 */
public class ListaLocalesController extends MainController implements Initializable {

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
    
    private void loadLocalesTable() {
        localesData.clear();
        List<Local> listLocales = facade.findAll();

        listLocales.stream().forEach((local) -> {
            localesData.add(new LocalFx(local.getNombre(),
                    local.getUbicacion(),
                    local.getStatus(),
                    local.getUsoId()));
        });
        localesTable.setItems(localesData);
    }

    @Override
    public boolean isInputValid() {
        // no hace nada
        return true;
    }
}
