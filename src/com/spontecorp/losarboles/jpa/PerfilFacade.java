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


import com.spontecorp.losarboles.model.Perfil;
import com.spontecorp.losarboles.utilities.Utilidades;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Casper
 */
public class PerfilFacade extends AbstractFacade<Perfil>{

    public PerfilFacade() {
        super(Perfil.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Utilidades.getEmf().createEntityManager();
    }
    
    public Perfil findPerfil(String nombre) {
        EntityManager em = getEntityManager();
        Perfil perfil = null;
        try {
            Query q = em.createQuery("SELECT p FROM Perfil p WHERE p.nombre = :nombre");
            q.setParameter("nombre", nombre);
            perfil = (Perfil) q.getSingleResult();
        } catch (NoResultException e) {
        } finally {
            em.close();
        }
        return perfil;
    }
        
}
