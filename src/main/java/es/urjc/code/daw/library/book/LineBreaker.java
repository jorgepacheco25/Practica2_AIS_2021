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

        if (entrada.length == 1) {
            return text;
        }
        else {
            if (lineLength < 8) {
                for (int i = 0; i < entrada.length-1; i++) {
                    salida = salida+entrada[i]+"\n";
                }
                salida = salida+entrada[entrada.length];
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
