package pt.pa.model;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.pa.controller.FileManager;
import pt.pa.model.Folder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A classe FileManagerTest contem testes unitários para validar o comportamento da classe FileManager.
 */
public class FileManagerTest {
    FileManager fileManager;
    Folder rootFolder;

    /**
     * Configuração inicial para os testes, criando uma instância de FileManager e a pasta raiz (rootFolder).
     */
    @BeforeEach
    void setUp(){
        this.fileManager = new FileManager();
        rootFolder = fileManager.createRootFolder();
    }

    /**
     * Testa se a pasta raiz é obtida através do método getRoot().
     */
    @Test
    @DisplayName("Check get root folder")
    void checkGetRootFolder(){
        assertTrue(fileManager.existsElement(rootFolder));
    }

    /**
     * Testa se a criação da pasta raiz foi efetuada com sucesso, comparando o nome da pasta raiz com o obtido através do método getRoot().
     */
    @Test
    @DisplayName("Check create root folder")
    void checkCreateRootElement(){
        assertEquals(rootFolder.getName(), fileManager.getRoot().getName());
    }

    /**
     * Testa a criação de uma pasta e verifica se a mesma existe após a criação.
     */
    @Test
    @DisplayName("Check create folder")
    void checkCreateFolder(){
        Folder testFolder = fileManager.createFolder(rootFolder, new Folder(rootFolder, "Teste"));
        assertTrue(fileManager.existsElement(testFolder));
    }

    /**
     * Testa a criação de um ficheiro e verifica se o mesmo existe após a criação.
     */
    @Test
    @DisplayName("Check create file")
    void checkCreateFile(){
        File testFile = fileManager.createFile(rootFolder, new File(rootFolder, "Teste", "Content"));
        assertTrue(fileManager.existsElement(testFile));
    }

    /**
     * Testa o movimento de um elemento para uma nova pasta e verifica se o movimento foi realizado corretamente.
     */
    @Test
    @DisplayName("Check move element")
    void checkMoveElement(){
        Folder parentFolder = fileManager.createFolder(rootFolder, new Folder(rootFolder, "Parent Folder"));
        File file = fileManager.createFile(rootFolder, new File(rootFolder, "Teste", "Content"));
        fileManager.moveElement(file, parentFolder);
        assertEquals(fileManager.getTreeElements().parent(fileManager.getTreeElements().getPosition(file)).element().getName(), parentFolder.getName());
    }

    /**
     * Testa a substituição de um elemento por outro e verifica se a substituição foi realizada corretamente.
     */
    @Test
    @DisplayName("Check replace element")
    void checkReplaceElement(){
        File oldFile = fileManager.createFile(rootFolder, new File(rootFolder, "Old File", "Content"));
        File replaceFile = fileManager.createFile(rootFolder, new File(rootFolder, "New File", "Content"));
        fileManager.replaceElement(oldFile, replaceFile);
        assertFalse(fileManager.existsElement(oldFile));
        assertTrue(fileManager.existsElement(replaceFile));
    }

    /**
     * Testa a remoção de um elemento e verifica se ele não existe após a remoção.
     */
    @Test
    @DisplayName("Check remove element")
    void checkRemoveElement(){
        Folder removeFolder = fileManager.createFolder(rootFolder, new Folder(rootFolder, "Folder to delete"));
        assertTrue(fileManager.existsElement(removeFolder));
        fileManager.removeElement(removeFolder);
        assertFalse(fileManager.existsElement(removeFolder));
    }
}
