package pt.pa;

import pt.pa.adts.TreeLinked;
import pt.pa.controller.FileManager;
import pt.pa.model.Element;
import pt.pa.model.File;
import pt.pa.model.Folder;

/**
 *
 * @author amfs
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*Folder rootFolder = new Folder();
        TreeLinked<Element> treeElements = new TreeLinked<>(rootFolder);
        Folder escola = new Folder(rootFolder, "Escola");
        Folder pessoal = new Folder(escola, "Pessoal");
        treeElements.insert(treeElements.getPosition(rootFolder), escola);
        treeElements.insert(treeElements.getPosition(rootFolder), pessoal);

        File teste = new File(escola, "Teste do Bruno", "Muito mau");

        treeElements.insert(treeElements.getPosition(escola), teste);
        System.out.println(treeElements.toString());*/

        FileManager fileManager = new FileManager();
        fileManager.createRootFolder();


    }


}
