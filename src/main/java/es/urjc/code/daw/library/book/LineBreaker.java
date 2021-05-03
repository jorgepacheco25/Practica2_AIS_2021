package es.urjc.code.daw.library.book;


import org.springframework.stereotype.Service;


@Service
public class LineBreaker {

    //CASOS

    //#    //TEXTO                 LineLength      SALIDA

    //1    //""                        2           ""
    //2    //"test"                    4           "test"
    //3    //"test"                    5           "test"
    //4    //"test test"               4           "test\ntest"
    //5    //"test test"               5           "test\ntest"
    //6    //"test test"               6           "test\ntest"
    //7    //"test test test test"     9           "test test\ntest test"
    //8    //"test  test"              4           "test\ntest"
    //9    //"test   test"             6           "test\ntest"
    //10   //"testtest"                5           "test-\ntest"
    //11   //"testtesttest"            5           "test-\ntest-\ntest"
    //12   //"test test"               3           "te-\nst\nte-\nst"
    //13   //"test 1234567 test"       6           "test\n12345-\n67\ntest"
    //14   //"123456789"               3           "12-\n34-\n56-\n789"


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

            //no cabe en la linea
            else {
                if (s.length() > lineLength) {
                    int n = 1;
                    int c = 0;
                    for (int i = 0; i < s.length(); i++) {
                        if ((i == (lineLength*n)-n) && (i != s.length()-1)) {
                            salida += "-\n" + s.charAt(i);
                            n++;
                        }
                        else if ((contadorLetrasEscritas + s.length() > lineLength) && (c == 0) &&
                        (lineLength > 5)) {
                            salida += "\n" + s.charAt(i);
                            c++;
                        }
                        else {
                            salida += s.charAt(i);
                        }
                    }
                    contadorLetrasEscritas += s.length();
                    if (contadorLetrasEscritas + s.length() > lineLength) {
                        salida += "\n";
                    }
                }
                else {
                    salida += "\n" + s;
                    contadorLetrasEscritas = s.length();
                }
                if ((contadorPalabrasEscritas == entrada.length-1) && (lineLength < s.length()))
                    salida = salida.substring(0, salida.length()-1);
            }
            contadorPalabrasEscritas++;
        }
        //salida = salida.replaceAll("\\s", ".");
        //salida = salida.replaceAll("\n", "_");
        return salida;
    }
    
}
