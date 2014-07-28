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

import com.spontecorp.losarboles.jpa.UsuarioFacade;
import com.spontecorp.losarboles.model.Usuario;
import javax.persistence.NoResultException;

public class Authenticator {
    
    private static Usuario authenticate(String username, char[] password) {
        String pswEncripted = Security.encript(password);
        UsuarioFacade service = new UsuarioFacade();
        try {
            Usuario usuario = service.findUser(username);
            if (!pswEncripted.equals(usuario.getPsw())) {
                return null;
            } else {
                if (usuario.getStatus() == Utilidades.INACTIVO) {
                    return null;
                } else {
                    return usuario;
                }
            }
        } catch (NoResultException | NullPointerException e) {
            return null;
        }

    }

    public static Usuario authenticate(String username, String password) {
        return authenticate(username, password.toCharArray());
    }
}