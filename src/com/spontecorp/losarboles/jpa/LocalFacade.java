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

package com.spontecorp.losarboles.jpa;

import com.spontecorp.losarboles.model.Local;
import com.spontecorp.losarboles.utilities.Utilidades;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Casper
 */
public class LocalFacade extends AbstractFacade<Local>{

    private static final Logger logger = LoggerFactory.getLogger(LocalFacade.class);
    
    public LocalFacade() {
        super(Local.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Utilidades.getEmf().createEntityManager();
    }
    
    public Local findLocal(String nombre, String ubicacion) {
        EntityManager em = getEntityManager();
        Local local = null;
        try {
            Query query = em.createQuery("SELECT l FROM Local l WHERE l.nombre = :local"
                                        + " AND l.ubicacion = :ubicacion");
            query.setParameter("local", nombre);
            query.setParameter("ubicacion", ubicacion);
            local = (Local) query.getSingleResult();
        } catch (Exception e) {
            logger.info("No se encontró el local: " + nombre + " en " + ubicacion);
        } finally {
            em.close();
        }
        return local;
    }
}
