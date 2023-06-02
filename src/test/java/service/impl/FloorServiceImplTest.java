package service.impl;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FloorServiceImplTest {

    @Test
    void createFloor() {
        File directory = new File("");// 参数为空
        try {
            String courseFile = directory.getCanonicalPath();
            System.out.println(courseFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}