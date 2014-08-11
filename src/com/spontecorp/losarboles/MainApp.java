/*
 * Copyrigth (c) 2014, Spontecorp, C.A. Todos los derechos reservados
 *  
 * ESTE SOFTWARE ES SUMINISTRADO POR LOS PROPIETARIOS DE LOS DERECHOS DE AUTOR
 * "TAL COMO ESTA" Y CUALQUIER GARANTIA EXPRESA O IMPLICITA, INCLUYENDO PERO NO LIMITADO A,
 * LAS GARANTIAS DE COMERCIALIZACION Y/O PARA UN PROPOSITO PARTICULAR. EN NINGUN CASO
 * EL PROPIETARIOS DE LOS DERECHOS DE AUTOR (COPYRIGTH) O SUS COLABORADORES SERAN
 * RESPONSABLES  POR NINGUN DAÑO DIRECTO, INDIRECTO, INCIDENTAL, ESPECIAL, EJEMPLARES O
 * DERIVADOS (INCLUYENDO, PERO NO LIMITADO A LA OBTENCION DE BIENES O SERVICIOS SUSTITUTOS, 
 * LA PERDIDA DE USO, DE DATOS O BENEFICIOS O LA INTERRUPCION DEL NEGOCIO) CAUSADOS COMO
 * EN CUALQUIER TEORIA DE RESPONSABILIDAD, YA SEA POR CONTRATO, RESPONSABILIDAD OBJETIVA 
 * O AGRAVIO (INCLUYENDO NEGLIGENCIA) QUE RESULTE DE CUALQUIER FORMA DEL USO DE ESTE SOFTWARE,
 * AUNQUE SE HAYA ADVERTIDO DE LA POSIBILIDAD DE TALES DAÑOS.

 */


package com.spontecorp.losarboles;

import com.spontecorp.losarboles.controller.LoginController;
import com.spontecorp.losarboles.controller.MainController;
import com.spontecorp.losarboles.controller.RootLayoutController;
import com.spontecorp.losarboles.controller.UsuariosDialogController;
import com.spontecorp.losarboles.controller.locales.LocalesDialogController;
import com.spontecorp.losarboles.model.Local;
import com.spontecorp.losarboles.model.Usuario;
import com.spontecorp.losarboles.utilities.Authenticator;
import com.spontecorp.losarboles.utilities.InitDB;
import com.spontecorp.losarboles.utilities.Utilidades;
import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Casper
 */
public class MainApp extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    private Usuario loggedUser;
    
    private static final String BASE_VIEW_FILE = "view/BasePane.fxml";
    
    
    
    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);
    
    @Override
    public void start(Stage primaryStage) {
        InitDB.createDB();
        
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Residencias Los Arboles - Sistema de Administración de Locales");
        this.primaryStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("resources/logoAdm.png")));
        gotoLogin();
        this.primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public Usuario getLoggedUser(){
        return this.loggedUser;
    }
    
    public Stage getPrimaryStage(){
        return primaryStage;
    }
    
    private void gotoLogin(){
        try {
            LoginController login = (LoginController) replaceSceneContent("view/Login.fxml");
            login.setApplication(this);
        } catch (Exception ex) {
            logger.error("Ha ocurrido un error...", ex);
        }
    }
    
    public boolean userLogging(String userId, String password) {
        Usuario usuario = Authenticator.authenticate(userId, password);
        if (usuario != null) {
            loggedUser = usuario;
            initRootLayout();
            showBaseView();
            return true;
        } else {
            return false;
        }
    }

    private void initRootLayout() {
        try {
            // carga el panel inicial del archivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setApplication(this);
            
            primaryStage.show();
        } catch (IOException e) {
            logger.error("Error cargando la ventana principal", e);
        }
    }
    
    public void showBaseView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(BASE_VIEW_FILE));
            AnchorPane base = (AnchorPane) loader.load();

            rootLayout.setCenter(base);
        } catch (IOException e) {
            logger.error("Error cargando la vista base", e);
        }
    }

    public void showNewBase(String fxmlFile, MainController controller){
        InputStream in = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            in = MainApp.class.getResourceAsStream(fxmlFile);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(MainApp.class.getResource(fxmlFile));
            AnchorPane base = (AnchorPane) loader.load(in);
            in.close();
            rootLayout.setCenter(base);
            
            MainController controlador = loader.getController();
            controlador.setMainApp(this);
        } catch (IOException e) {
            logger.error("Error cargando la vista " + fxmlFile, e);
        }
    }
    
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = MainApp.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MainApp.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        return (Initializable) loader.getController();
    }
    
    public boolean showUsuariosDialogs(Usuario usuario, String fxmlFile){
        try {
            // carga el archivo fxml y crea una nueva stage  para eldialogo popup
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(fxmlFile));
            AnchorPane dialog = (AnchorPane)loader.load();
            
            // Crea la Stage del dialogo
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar / Crear Usuario");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("resources/logoAdm.png")));
            Scene scene = new Scene(dialog);
            dialogStage.setScene(scene);
            
            //boolean retorno = false;
            UsuariosDialogController controller;
            if(fxmlFile.equals(Utilidades.USUARIOS_NUEVO_DIALOG)){
                // Coloca el Usuario en el controlador
                controller = loader.getController();
            } else {
                controller = loader.getController();
            }
            controller.setDialogStage(dialogStage);
            controller.setUsuario(usuario);
            
            dialogStage.showAndWait();
            
            return controller.isOkClicked();
            
        } catch (IOException e) {
            logger.error("Error mostrando el dialogo de edicion de usuarios", e);
            return false;
        }
    }
    
    public boolean showLocalesDialogs(Local local, String fxmlFile) {
        try {
            // carga el archivo fxml y crea una nueva stage  para el dialogo popup
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(fxmlFile));
            AnchorPane dialog = (AnchorPane) loader.load();

            // Crea la Stage del dialogo
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar / Crear Local");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("resources/logoAdm.png")));
            Scene scene = new Scene(dialog);
            dialogStage.setScene(scene);

            //boolean retorno = false;
            LocalesDialogController controller;
            controller = loader.getController();
            
            controller.setDialogStage(dialogStage);
            controller.setLocal(local);

            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            logger.error("Error mostrando el dialogo de agregar/editar locales", e);
            return false;
        }
    }
}
