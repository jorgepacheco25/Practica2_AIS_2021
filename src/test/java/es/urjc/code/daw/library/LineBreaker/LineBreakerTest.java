package es.urjc.code.daw.library.LineBreaker;

import org.junit.jupiter.api.Test;

import es.urjc.code.daw.library.book.LineBreaker;
import static org.assertj.core.api.Assertions.assertThat;

public class LineBreakerTest {

    @Test
    void test1() {

        //When
        String resultado = LineBreaker.breakText("", 2);

        //Then
        assertThat(resultado).isEqualTo("");
    }

    @Test
    void test2() {

        //When
        String resultado = LineBreaker.breakText("test", 4);

        //Then
        assertThat(resultado).isEqualTo("test");
    }

    @Test
    void test3() {

        //When
        String resultado = LineBreaker.breakText("test", 5);

        //Then
        assertThat(resultado).isEqualTo("test");
    }
    
    @Test
    void test4() {

        //When
        String resultado = LineBreaker.breakText("test test", 4);

        //Then
        assertThat(resultado).isEqualTo("test\ntest");
    }

    @Test
    void test5() {

        //When
        String resultado = LineBreaker.breakText("test test", 5);

        //Then
        assertThat(resultado).isEqualTo("test\ntest");
    }

    @Test
    void test6() {

        //When
        String resultado = LineBreaker.breakText("test test", 6);

        //Then
        assertThat(resultado).isEqualTo("test\ntest");
    }

    @Test
    void test7() {

        //When
        String resultado = LineBreaker.breakText("test test test test", 9);

        //Then
        assertThat(resultado).isEqualTo("test test\ntest test");
    }

    @Test
    void test8() {

        //When
        String resultado = LineBreaker.breakText("test  test", 4);

        //Then
        assertThat(resultado).isEqualTo("test\ntest");
    }

   @Test
    void test9() {

        //When
        String resultado = LineBreaker.breakText("test   test", 6);

        //Then
        assertThat(resultado).isEqualTo("test\ntest");
    }

    @Test
    void test10() {

        //When
        String resultado = LineBreaker.breakText("testtest", 5);

        //Then
        assertThat(resultado).isEqualTo("test-\ntest");
    }

    @Test
    void test11() {

        //When
        String resultado = LineBreaker.breakText("testtesttest", 5);

        //Then
        assertThat(resultado).isEqualTo("test-\ntest-\ntest");
    }

    @Test
    void test12() {

        //When
        String resultado = LineBreaker.breakText("test test", 3);

        //Then
        assertThat(resultado).isEqualTo("te-\nst\nte-\nst");
    }

    @Test
    void test13() {

        //When
        String resultado = LineBreaker.breakText("test 1234567 test", 6);

        //Then
        assertThat(resultado).isEqualTo("test\n12345-\n67\ntest");
    }
    
    @Test
    void test14() {

        //When
        String resultado = LineBreaker.breakText("123456789", 3);

        //Then
        assertThat(resultado).isEqualTo("12-\n34-\n56-\n789");
    }
}
