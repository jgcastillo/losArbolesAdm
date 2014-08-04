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
    
    // vistas
    public static final String USUARIOS_ADMIN_FILE = "view/UsuariosAdmin.fxml";
    public static final String USUARIOS_EDIT_DIALOG = "view/UsuariosEditDialog.fxml";
    public static final String USUARIOS_NUEVO_DIALOG = "view/UsuariosNuevoDialog.fxml";
    public static final String LOCALES_ADMIN_FILE = "view/locales/LocalesAdmin.fxml";
    public static final String USO_LOCALES_ADMIN_FILE = "view/locales/UsosAdmin.fxml";
    
    public static EntityManagerFactory getEmf() {
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
}
