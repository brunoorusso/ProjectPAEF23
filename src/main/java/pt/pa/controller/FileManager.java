package pt.pa.controller;

import javafx.scene.control.TreeItem;
import pt.pa.adts.TreeLinked;
import pt.pa.model.*;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class FileManager{
    TreeLinked<Element> treeElements = new TreeLinked<>();
    private List<Memento> mementos = new ArrayList<>();

    public TreeLinked<Element> getTreeElements(){
        return treeElements;
    }

    public Element getRoot(){
        return treeElements.root().element();
    }

    public Folder createRootFolder(){
        if(treeElements.isEmpty()){
            Folder rootFolder = new Folder();
            treeElements = new TreeLinked<>(rootFolder);
            return rootFolder;
        }
        return null;
    }

    public Folder createFolder(Folder rootFolder, Folder newFolder){
        if(rootFolder != null && newFolder != null){
            treeElements.insert(treeElements.getPosition(rootFolder), newFolder);
            return newFolder;
        }
        return null;
    }

    public Element getElementByName(String name){
        for(Element element : treeElements.elements()){
            if(element.getName().equals(name)){
                return element;
            }
        }
        return null;
    }

    public File createFile(Folder rootFolder, File newFile){
        if(rootFolder != null && newFile != null){
            treeElements.insert(treeElements.getPosition(rootFolder), newFile);
            return newFile;
        }
        return null;
    }

    public void moveElement(Element element, Element destinyElement){
        if(element != null && destinyElement != null){
            treeElements.move(treeElements.getPosition(element), treeElements.getPosition(destinyElement));
        }
    }

    public void replaceElement(Element element, Element newElement){
        if(element != null && newElement != null){
            treeElements.replace(treeElements.getPosition(element), newElement);
        }
    }

    public void removeElement(Element element){
        if(element != null){
            treeElements.remove(treeElements.getPosition(element));
        }
    }

    public void createMemento() {
        Memento memento = new Memento(treeElements);
        mementos.add(memento);
        System.out.println("n " + mementos.size());
    }

    public void restoreMemento() {
        if (!mementos.isEmpty()) {
            Memento memento = mementos.get(mementos.size() - 1);
            treeElements = new TreeLinked(memento.getTreeState());
            mementos.remove(memento);
            System.out.println("oi            "  + mementos.size());
        }
    }
}
