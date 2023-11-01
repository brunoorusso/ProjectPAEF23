package pt.pa.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.pa.controller.FileManager;
import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {
    FileManager fileManager;

    @BeforeEach
    void setUp(){
        this.fileManager = new FileManager();
    }

    @Test
    @DisplayName("Check get root folder")
    void checkGetRootFolder(){
        Folder rootFolder = fileManager.createRootFolder();
        assertTrue(fileManager.getRoot() != null);
    }

    @Test
    @DisplayName("Check create root folder")
    void checkCreateRootElement(){
        Folder rootFolder = fileManager.createRootFolder();
        assertEquals(rootFolder.getName(), fileManager.getRoot().getName());
    }
}
