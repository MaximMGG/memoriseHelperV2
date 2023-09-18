package com.memmorise.app.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

public class ChecksUtilsTest {
    
    @Test
    void writeIntTest() {
        int expectidResult = 2;
        ByteArrayInputStream bais = new ByteArrayInputStream("2".getBytes());
        InputStream input = System.in;
        System.setIn(bais);

        int result = ChecksUtils.writeInt();

        System.setIn(input);

        assertTrue(expectidResult == result);
    }

    @Test
    void writeStringTest() {
        ByteArrayInputStream bais = new ByteArrayInputStream("Hello".getBytes());
        InputStream input = System.in;
        System.setIn(bais);
        String expectedResult = "Hello";

        String result = ChecksUtils.writeString();

        assertTrue(result.equals(expectedResult));

        System.setIn(input);

    }
}
