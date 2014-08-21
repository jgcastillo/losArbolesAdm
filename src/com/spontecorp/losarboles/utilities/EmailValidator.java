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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Casper
 */
public class EmailValidator {
    private final Pattern pattern;
    private Matcher matcher;
    
    /*
    Explicación
    ^			# comienzo de la linea
     [_A-Za-z0-9-\\+]+	# debe xomenzar con string en los corchetes [ ], debe contener uno o más (+)
     (			# comienzo del grupo #1
     \\.[_A-Za-z0-9-]+	# seguido por un punto "." y string en los corchete [ ], debe contener uno o más (+)
     )*			# fin del grupo #1, este grupo es opcional (*)
     @			# debe contener un simbolo "@" 
     [A-Za-z0-9-]+      # seguido por string en los corchetes [ ], debe contener uno o más (+)
     (			# comienzo del grupo #2 - primer nivel de chequeo TLD 
     \\.[A-Za-z0-9]+    # seguido por un punto "." y string en los corchete [ ], debe contener uno o más (+)
     )*		        # fin del grupo #2, este grupo es opcional (*)
     (			# comienzo del grupo #3 - segundo nivel de chequeo TLD
     \\.[A-Za-z]{2,}    # seguido por un punto "." y string en los corchete [ ], con un mínimo de longitud 2
     )			# fin del grupo  #3
     $			# fin de la línea
    */
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /**
     * Validate hex with regular expression
     *
     * @param hex hex for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validate(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
}
