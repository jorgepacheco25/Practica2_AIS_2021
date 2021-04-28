package es.urjc.code.daw.library.book;

import org.springframework.stereotype.Service;


@Service
public class LineBreaker {

    //CASOS

    //TEXTO                 LineLength      SALIDA

    //""                        2           ""
    //"test"                    4           "test"
    //"test"                    5           "test"
    //"test test"               4           "test\ntest"
    //"test test"               5           "test\ntest"
    //"test test"               6           "test\ntest"
    //"test test test test"     9           "test test\ntest test"
    //"test  test"              4           "test\ntest"
    //"test   test"             6           "test\ntest"
    //"testtest"                5           "test-\ntest"
    //"testtesttest"            5           "test-\ntest-\ntest"
    //"test test"               3           "te-\nst\nte-\nst"
    //"test 1234567 test"       6           "test\n12345-\n67\ntest"
    //"123456789"               3           "12-\n34-\n56-\n789"


    public static String breakText(String text, int lineLength){
        String salida= "";
        String[] entrada = text.split(" ");
        int cont = 0;

        if (entrada.length == 1) {
            if (entrada[0].length() < lineLength)
                return text;
            else {
                for (int i = 0; i < entrada[0].length(); i++) {
                    if (i == lineLength-1)
                        salida = salida + "-\n" + entrada[0].charAt(i);
                    else 
                        salida = salida + entrada[0].charAt(i);
                }
                return salida;
            }
        }
        else {
            if (lineLength < 8) {
                for (String s:entrada) {
                    salida = salida+s+"\n";
                }
                salida = salida.substring(0, salida.length()-1);
            }
            else {
                for (String s:entrada) {
                    if (cont % 2 == 0) {
                        salida = salida + s;
                        cont++;
                    }
                    else {
                        salida = salida + " " +s+"\n";
                        cont++;
                    }
                }
                salida = salida.substring(0, salida.length()-1);
            }
            return salida;
        }




        
        /*if (entrada.length == 2) {
            return "test\ntest";
        }
        if (entrada.length > 2) {
            return "test test\ntest test";
        }
        else {
            if (lineLength == 2) {
                return "";
            }else {
                return "test";
            }
        }*/
    }
    
}
