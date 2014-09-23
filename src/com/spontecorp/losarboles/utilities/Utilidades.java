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
package com.spontecorp.losarboles.utilities;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Casper
 */
public class Utilidades {

    // constantes del sistema
    public static final String PERSISTENCE_UNIT_NAME = "LosArbolesAdmPU";

    // status generales
    public static final int ACTIVO = 1;
    public static final int INACTIVO = 0;

    // nombre de perfiles
    public static final String ADMINISTRADOR = "administrador";
    public static final String EDITOR = "editor";
    public static final String REVISOR = "revisor";

    // status de locales
    public static final int LIBRE = 0;
    public static final int OCUPADO = 1;
    public static final int ALQUILADO = 2;
    public static final int INHABILITADO = 3;
    
    // status de arrendatarios
    public static final int ARRENDATARIO_INACTIVO = 0;
    public static final int ARRENDATARIO_ACTIVO = 1;
    public static final int ARRENDATARIO_VETADO = 2;

    // vistas
    public static final String USUARIOS_ADMIN_FILE = "view/UsuariosAdmin.fxml";
    public static final String USUARIOS_EDIT_DIALOG = "view/UsuariosEditDialog.fxml";
    public static final String USUARIOS_NUEVO_DIALOG = "view/UsuariosNuevoDialog.fxml";
    
    public static final String LOCALES_ADMIN_FILE = "view/locales/LocalesAdmin.fxml";
    public static final String LOCALES_EDIT_DIALOG = "view/locales/LocalesEditDialog.fxml";
    public static final String LOCALES_NUEVO_DIALOG = "view/locales/LocalesNuevoDialog.fxml";
    public static final String LOCALES_LISTA = "view/locales/ListaLocales.fxml";
    
    public static final String USO_LOCALES_ADMIN_FILE = "view/locales/UsosAdmin.fxml";

    public static final String ARRENDATARIOS_ADMIN_FILE = "view/arrendatarios/ArrendatariosAdmin.fxml";
    public static final String ARRENDATARIOS_EDIT_DIALOG = "view/arrendatarios/ArrendatariosEditDialog.fxml";
    public static final String ARRENDATARIOS_NUEVO_DIALOG = "view/arrendatarios/ArrendatariosNuevoDialog.fxml";
    
    public static EntityManagerFactory getEmf() {
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static String parseStatusLocal(int status) {
        String estado = "";
        switch (status) {
            case Utilidades.LIBRE:
                estado = "Libre";
                break;
            case Utilidades.OCUPADO:
                estado = "Ocupado";
                break;
            case Utilidades.ALQUILADO:
                estado = "Alquilado";
                break;
            case Utilidades.INHABILITADO:
                estado = "Inhabilitado";
                break;
        }
        return estado;
    }

    public static int parseStatusLocal(String status) {
        int estado = 0;
        switch (status) {
            case "Libre":
                estado = Utilidades.LIBRE;
                break;
            case "Ocupado":
                estado = Utilidades.OCUPADO;
                break;
            case "Alquilado":
                estado = Utilidades.ALQUILADO;
                break;
            case "Inhabilitado":
                estado = Utilidades.INHABILITADO;
                break;
        }
        return estado;
    }
    
    public static String parseStatusArrendatario(int status){
        String estado = "";
        switch (status) {
            case Utilidades.ARRENDATARIO_INACTIVO:
                estado = "Inactivo";
                break;
            case Utilidades.ARRENDATARIO_ACTIVO:
                estado = "Activo";
                break;
            case Utilidades.ARRENDATARIO_VETADO:
                estado = "Vetado";
                break;
        }
        return estado;
    }
    
    public static int parseStatusArrendatario(String status) {
        int estado = 0;
        switch (status) {
            case "Inactivo":
                estado = Utilidades.ARRENDATARIO_INACTIVO;
                break;
            case "Activo":
                estado = Utilidades.ARRENDATARIO_ACTIVO;
                break;
            case "Vetado":
                estado = Utilidades.ARRENDATARIO_VETADO;
                break;
        }
        return estado;
    }
}
