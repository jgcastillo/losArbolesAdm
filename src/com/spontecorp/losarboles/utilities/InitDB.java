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

package com.spontecorp.losarboles.utilities;

import com.spontecorp.losarboles.jpa.PerfilFacade;
import com.spontecorp.losarboles.jpa.UsuarioFacade;
import com.spontecorp.losarboles.model.Perfil;
import com.spontecorp.losarboles.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Casper
 */
public class InitDB {
    private static final Logger logger = LoggerFactory.getLogger(InitDB.class);

    /**
     * Crea la base de datos Derby si esta no existe
     */
    public static void createDB() {
        PerfilFacade perfilFacade = new PerfilFacade();
        UsuarioFacade usuarioFacade = new UsuarioFacade();

        if (perfilFacade.findAll().isEmpty()) {
            // Se cren el perfil adinistrador y su usario para comenzar a trabajar
            logger.info("creando datos iniciales en la base de datos");
            Perfil perfil = new Perfil();
            Usuario usuario = new Usuario();

            perfil.setNombre("revisor");
            perfil.setStatus(Utilidades.ACTIVO);
            perfilFacade.create(perfil);

            perfil.setNombre("editor");
            perfil.setStatus(Utilidades.ACTIVO);
            perfilFacade.create(perfil);

            perfil.setNombre("administrador");
            perfil.setStatus(Utilidades.ACTIVO);
            perfilFacade.create(perfil);

            usuario.setApellido("Admin");
            usuario.setNombre("Admin");
            usuario.setPerfilId(perfil);
            usuario.setUsr("admin");
            usuario.setPsw(Security.encript("admin"));
            usuario.setStatus(Utilidades.ACTIVO);
            usuarioFacade.create(usuario);

            logger.info("Inicializados datos para el usuario administrdor");
        }
    }
}
