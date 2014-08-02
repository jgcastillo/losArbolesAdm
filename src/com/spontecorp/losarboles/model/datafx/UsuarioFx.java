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

package com.spontecorp.losarboles.model.datafx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Casper
 */
public class UsuarioFx {
    
    private final SimpleStringProperty nombreProperty;
    private final SimpleStringProperty apellidoProperty;
    private final SimpleStringProperty usrProperty;
    private final SimpleStringProperty statusProperty;
    
    public UsuarioFx(String nombreS, String apellidoS, String usrS, String statusS ){
        this.nombreProperty = new SimpleStringProperty(nombreS);
        this.apellidoProperty = new SimpleStringProperty(apellidoS);
        this.usrProperty = new SimpleStringProperty(usrS);
        this.statusProperty = new SimpleStringProperty(statusS);
    }
    
    public String getNombreProperty() {
        return nombreProperty.get();
    }

    public void setNombreProperty(String nombreS) {
        nombreProperty.set(nombreS);
    }

    public String getApellidoProperty() {
        return apellidoProperty.get();
    }

    public void setApellidoProperty(String apellidoS) {
        apellidoProperty.set(apellidoS);
    }

    public String getUsrProperty() {
        return usrProperty.get();
    }

    public void setUsrProperty(String usrS) {
        usrProperty.set(usrS);
    }

    public String getStatusProperty() {
        return statusProperty.get();
    }

    public void setStatusProperty(String statusS) {
        statusProperty.set(statusS);
    }
    
}
