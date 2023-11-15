package pt.pa.controller;

import javafx.scene.control.TreeItem;
import pt.pa.adts.TreeLinked;
import pt.pa.model.*;
import pt.pa.model.File;

import javax.swing.tree.TreeNode;
import java.io.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.function.Function;

public class FileManager{
    TreeLinked<Element> treeElements = new TreeLinked<>();
    private List<Memento> mementos = new ArrayList<>();

    public TreeLinked<Element> getTreeElements(){
        return treeElements;
    }

    public Integer getNumberOfFolders(){
        Integer number = 0;
        for(Element el : treeElements.elements()){
            if(el instanceof Folder){
                number+=1;
            }
        }
        return number;
    }

    public Integer getNumberOfFiles(){
        Integer number = 0;
        for(Element el : treeElements.elements()){
            if(el instanceof File){
                number+=1;
            }
        }
        return number;
    }

    public Integer getTreeHeight(){
        return treeElements.height();
    }

    public int getFilesSpace(){
        int space = 0;
        for(Element el : treeElements.elements()){
            if(el instanceof File){
                space += ((File) el).getSize();
            }
        }
        return space;
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

    public boolean existsElement(Element element){
        for(Element el : treeElements.elements()){
            if(el.getName().equals(element.getName())){
                return true;
            }
        }
        return false;
    }

    public Map<String, Integer> getElementsByYear(int year, Function<Element, LocalDateTime> dateExtractor) {
        Map<String, Integer> elementsByMonth = new LinkedHashMap<>();

        for (Month month : Month.values()) {
            elementsByMonth.put(month.toString(), 0);
        }

        for (Element el : treeElements.elements()) {
            LocalDateTime date = dateExtractor.apply(el);

            if (date.getYear() == year) {
                String monthKey = date.getMonth().toString();
                elementsByMonth.put(monthKey, elementsByMonth.get(monthKey) + 1);
            }
        }
        return elementsByMonth;
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
    }

    public void restoreMemento() {
        if (!mementos.isEmpty()) {
            Memento memento = mementos.get(mementos.size() - 1);
            treeElements = new TreeLinked(memento.getTreeState());
            mementos.remove(memento);
        }
    }
}
