package es.urjc.code.daw.library.book;


import org.springframework.stereotype.Service;


@Service
public class LineBreaker {
    
    public static String breakText(String text, int lineLength){  
        String salida= "";
        String[] entrada = text.split(" +");
        int contadorPalabrasEscritas = 0;
        int contadorLetrasEscritas = 0;

        for (String s:entrada) {
            //cabe en la línea
            if (s.length() + contadorLetrasEscritas <= lineLength) {
                if (contadorLetrasEscritas == 0) {
                    salida += s;
                }
                else {
                    salida += " " + s;
                }

                contadorLetrasEscritas += s.length();
            }

            //no cabe en la linea, escribimos la palabra letra a letra para poder controlar los saltos de linea y los guiones
            else {
                if (s.length() > lineLength) {
                    //usamos numeroSaltos para saber el numero de veces que ha saltado de linea
                    int numeroSaltos = 1;
                    int primera = 0; //Para que solo entre en el bucle una vez
                    for (int i = 0; i < s.length(); i++) {
                         // cuando la palabra esta a medias de escribirse y ya no entran mas letras en la linea
                        if ((i == (lineLength*numeroSaltos)-numeroSaltos) && (i != s.length()-1)) {
                            salida += "-\n" + s.charAt(i);
                            numeroSaltos++;
                            contadorLetrasEscritas++;
                        }
                        // cuando la palabra esta escrita y ya no entran mas letras en la linea
                        else if ((contadorLetrasEscritas + s.length() > lineLength) && (primera == 0) && 
                        (lineLength > 5)) {
                            salida += "\n" + s.charAt(i);
                            primera++;
                            contadorLetrasEscritas++;
                        }
                        else { // Caso base para escribir las palabras letra a letra
                            salida += s.charAt(i);
                            contadorLetrasEscritas++;
                        }
                    }
                    // para introducir saltos de linea despues de escribir una palabra si ya no entran mas letras en la linea
                    if (contadorLetrasEscritas + s.length() > lineLength) { 
                        salida += "\n";
                    }
                }
                else {
                    salida += "\n" + s; 
                    contadorLetrasEscritas = s.length();
                }
                 // para quitar el ultimo salto de linea que tiene la salida
                if ((contadorPalabrasEscritas == entrada.length-1) && (lineLength < s.length()))
                    salida = salida.substring(0, salida.length()-1);
            }
            contadorPalabrasEscritas++;
        }
        return salida;
    } 
}
