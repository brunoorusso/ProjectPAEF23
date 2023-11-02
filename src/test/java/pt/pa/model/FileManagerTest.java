package pt.pa.model;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.pa.controller.FileManager;
import pt.pa.model.Folder;

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
        assertTrue(fileManager.existsElement(rootFolder));
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
        assertTrue(fileManager.existsElement(testFolder));
    }

    @Test
    @DisplayName("Check create file")
    void checkCreateFile(){
        File testFile = fileManager.createFile(rootFolder, new File(rootFolder, "Teste", "Content"));
        assertTrue(fileManager.existsElement(testFile));
    }

    @Test
    @DisplayName("Check move element")
    @Description("Create parent folder and file, both with root folder as parent. After creation, move the file to folder parentFolder.")
    void checkMoveElement(){
        Folder parentFolder = fileManager.createFolder(rootFolder, new Folder(rootFolder, "Parent Folder"));
        File file = fileManager.createFile(rootFolder, new File(rootFolder, "Teste", "Content"));
        fileManager.moveElement(file, parentFolder);
        assertEquals(fileManager.getTreeElements().parent(fileManager.getTreeElements().getPosition(file)).element().getName(), parentFolder.getName());
    }

    @Test
    @DisplayName("Check replace element")
    void checkReplaceElement(){
        File oldFile = fileManager.createFile(rootFolder, new File(rootFolder, "Old File", "Content"));
        File replaceFile = fileManager.createFile(rootFolder, new File(rootFolder, "New File", "Content"));
        fileManager.replaceElement(oldFile, replaceFile);
        assertFalse(fileManager.existsElement(oldFile));
        assertTrue(fileManager.existsElement(replaceFile));
    }

    @Test
    @DisplayName("Check remove element")
    void checkRemoveElement(){
        Folder removeFolder = fileManager.createFolder(rootFolder, new Folder(rootFolder, "Folder to delete"));
        assertTrue(fileManager.existsElement(removeFolder));
        fileManager.removeElement(removeFolder);
        assertFalse(fileManager.existsElement(removeFolder));
    }
}
