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
    void writeIntExceptionTest() {

        ByteArrayInputStream bais = new ByteArrayInputStream("Yes".getBytes());
        InputStream input = System.in;
        System.setIn(bais);
        
        assertThrows(IllegalStateException.class, () -> ChecksUtils.writeInt());

        System.setIn(input);
    }
}
