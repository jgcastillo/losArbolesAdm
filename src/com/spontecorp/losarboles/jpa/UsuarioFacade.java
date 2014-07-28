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

package com.spontecorp.losarboles.jpa;

import com.spontecorp.losarboles.model.Usuario;
import com.spontecorp.losarboles.utilities.Utilidades;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Casper
 */
public class UsuarioFacade extends AbstractFacade<Usuario>{
    
    private static final Logger logger = LoggerFactory.getLogger(UsuarioFacade.class);

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Utilidades.getEmf().createEntityManager();        
    }
    
    public Usuario findUser(String user) {
        EntityManager em = getEntityManager();
        Usuario usuario = null;
        try {
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.usr = :usuario");
            query.setParameter("usuario", user);
            usuario = (Usuario) query.getSingleResult();
        } catch (Exception e) {
            logger.info("Se trató de ingresar erróneamente con el usuario: " + user);
        } finally {
            em.close();
        }
        return usuario;
    }
}
