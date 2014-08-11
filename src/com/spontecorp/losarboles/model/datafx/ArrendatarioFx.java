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

import com.spontecorp.losarboles.utilities.Utilidades;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Casper
 */
public class ArrendatarioFx {
    
    private final SimpleStringProperty nombreProperty;
    private final SimpleStringProperty apellidoProperty;
    private final SimpleIntegerProperty ciProperty;
    private final SimpleStringProperty direccionProperty;
    private final SimpleStringProperty telefonoProperty;
    private final SimpleStringProperty celularProperty;
    private final SimpleStringProperty tlfTrabajoProperty;
    private final SimpleStringProperty emailProperty;
    private final SimpleStringProperty statusProperty;

    public ArrendatarioFx(String nombre, String apellido, int ci, String direccion, 
            String telefono, String celular, String tlfTrabajo, String email, 
            int status) {
        this.nombreProperty = new SimpleStringProperty(nombre);
        this.apellidoProperty = new SimpleStringProperty(apellido);
        this.ciProperty = new SimpleIntegerProperty(ci);
        this.direccionProperty = new SimpleStringProperty(direccion);
        this.telefonoProperty = new SimpleStringProperty(telefono);
        this.celularProperty = new SimpleStringProperty(celular);
        this.tlfTrabajoProperty = new SimpleStringProperty(tlfTrabajo);
        this.emailProperty = new SimpleStringProperty(email);
        this.statusProperty = new SimpleStringProperty(Utilidades.parseStatusArrendatario(status));
    }
    
    public String getNombreProperty(){
        return nombreProperty.get();
    }
    
    public void setNombreProperty(String nombre){
        this.nombreProperty.set(nombre);
    }
    
    public String getApellidoProperty() {
        return apellidoProperty.get();
    }

    public void setApellidoProperty(String apellido) {
        this.apellidoProperty.set(apellido);
    }
    
    public int getCiProperty() {
        return ciProperty.get();
    }

    public void setCiProperty(int ci) {
        this.ciProperty.set(ci);
    }
    
    public String getDireccionProperty() {
        return direccionProperty.get();
    }

    public void setDireccionProperty(String direccion) {
        this.direccionProperty.set(direccion);
    }
    
    public String getTelefonoProperty() {
        return telefonoProperty.get();
    }

    public void setTelefonoProperty(String telefono) {
        this.telefonoProperty.set(telefono);
    }
    
    public String getCelularProperty() {
        return celularProperty.get();
    }

    public void setCelularProperty(String celular) {
        this.celularProperty.set(celular);
    }
    
    public String getTlfTrabajoProperty() {
        return tlfTrabajoProperty.get();
    }

    public void setTlfTrabajoProperty(String tlfTrabajo) {
        this.tlfTrabajoProperty.set(tlfTrabajo);
    }
    
    public String getEmailProperty() {
        return emailProperty.get();
    }

    public void setEmailProperty(String email) {
        this.emailProperty.set(email);
    }
    
    public String getStatusProperty() {
        return statusProperty.get();
    }

    public void setStatusProperty(int status) {
        this.statusProperty.set(Utilidades.parseStatusArrendatario(status));
    }
}
