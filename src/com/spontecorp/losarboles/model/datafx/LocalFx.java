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

import com.spontecorp.losarboles.model.Uso;
import com.spontecorp.losarboles.utilities.Utilidades;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Casper
 */
public class LocalFx {
    
    private final SimpleStringProperty nombreProperty;
    private final SimpleStringProperty ubicacionProperty;
    private final SimpleStringProperty statusProperty;
    private final SimpleStringProperty usoProperty;
    
    public LocalFx (String nombre, String ubicacion, int status, Uso uso){
        this.nombreProperty = new SimpleStringProperty(nombre);
        this.ubicacionProperty = new SimpleStringProperty(ubicacion);
        this.statusProperty = new SimpleStringProperty(Utilidades.parseStatusLocal(status));
        this.usoProperty = new SimpleStringProperty(uso.getNombre());
    }
    
    public String getNombreProperty(){
        return nombreProperty.get();
    }
    
    public void setNombreProperty(String nombre){
        nombreProperty.set(nombre);
    }
    
    public String getUbicacionProperty() {
        return ubicacionProperty.get();
    }

    public void setUbicacionProperty(String ubicacion) {
        ubicacionProperty.set(ubicacion);
    }
    
    public String getStatusProperty() {
        return statusProperty.get();
    }

    public void setStatusProperty(int status) {
        statusProperty.set(Utilidades.parseStatusLocal(status));
    }
    
    public String getUsoProperty() {
        return usoProperty.get();
    }

    public void setUsoProperty(Uso uso) {
        usoProperty.set(uso.getNombre());
    }
    
}
