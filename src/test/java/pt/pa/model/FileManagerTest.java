package pt.pa.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.pa.controller.FileManager;
import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {
    FileManager fileManager;
    Folder rootFolder;

    @BeforeEach
    void setUp(){
        this.fileManager = new FileManager();
        rootFolder = fileManager.createRootFolder();
    }

    @Test
    @DisplayName("Check get root folder")
    void checkGetRootFolder(){
        assertTrue(fileManager.getRoot() != null);
    }

    @Test
    @DisplayName("Check create root folder")
    void checkCreateRootElement(){
        assertEquals(rootFolder.getName(), fileManager.getRoot().getName());
    }

    @Test
    @DisplayName("Check create folder")
    void checkCreateFolder(){
        Folder testFolder = fileManager.createFolder(rootFolder, new Folder(rootFolder, "Teste"));
        assertEquals(fileManager.getElementByName(testFolder.getName()).getName(), "Teste");
    }


}
