package pt.pa.controller;

import javafx.scene.control.TreeItem;
import pt.pa.adts.TreeLinked;
import pt.pa.model.*;
import pt.pa.model.File;

import javax.swing.tree.TreeNode;
import java.io.*;
import java.sql.Array;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.function.Function;


/**
 * A classe FileManager efetua a gestão da estrutura da árvore de elementos (pastas e ficheiros) num FileSystem.
 * Fornece operações para manipular a árvore, calcular estatísticas.
 */
public class FileManager{

    /**
     * A estrutura da árvore que guarda os elementos do FileSystem.
     */
    TreeLinked<Element> treeElements = new TreeLinked<>();

    /**
     * Lista de mementos para realizar controlo de versões.
     */
    private List<Memento> mementos = new ArrayList<>();

    /**
     * Obtém a estrutura da árvore que armazena os elementos do FileSystem.
     *
     * @return A estrutura da árvore de elementos.
     */
    public TreeLinked<Element> getTreeElements(){
        return treeElements;
    }

    /**
     * Obtém o número de pastas na árvore de elementos.
     *
     * @return O número de pastas na árvore.
     */
    public Integer getNumberOfFolders(){
        Integer number = 0;
        for(Element el : treeElements.elements()){
            if(el instanceof Folder){
                number+=1;
            }
        }
        return number;
    }

    /**
     * Obtém o número de ficheiros na árvore de elementos.
     *
     * @return O número de ficheiros na árvore.
     */
    public Integer getNumberOfFiles(){
        Integer number = 0;
        for(Element el : treeElements.elements()){
            if(el instanceof File){
                number+=1;
            }
        }
        return number;
    }

    /**
     * Obtém a altura da árvore de elementos.
     *
     * @return A altura da árvore.
     */
    public Integer getTreeHeight(){
        return treeElements.height();
    }

    /**
     * Obtém o espaço total ocupado pelos ficheiros na árvore de elementos.
     *
     * @return O espaço total ocupado pelos ficheiros em megabytes.
     */
    public int getFilesSpace(){
        int space = 0;
        for(Element el : treeElements.elements()){
            if(el instanceof File){
                space += ((File) el).getSize();
            }
        }
        return space;
    }

    /**
     * Obtém o elemento raiz da árvore.
     *
     * @return O elemento raiz da árvore.
     */
    public Element getRoot(){
        return treeElements.root().element();
    }

    /**
     * Cria a pasta raiz se a árvore estiver vazia.
     *
     * @return A pasta raiz criada ou nulo se a árvore já contiver elementos.
     */
    public Folder createRootFolder(){
        if(treeElements.isEmpty()){
            Folder rootFolder = new Folder();
            treeElements = new TreeLinked<>(rootFolder);
            return rootFolder;
        }
        return null;
    }

    /**
     * Cria uma nova pasta na árvore de elementos.
     *
     * @param rootFolder A pasta pai onde a nova pasta será criada.
     * @param newFolder  A nova pasta a ser criada.
     * @return A nova pasta criada ou nulo se os parâmetros forem inválidos.
     */
    public Folder createFolder(Folder rootFolder, Folder newFolder){
        if(rootFolder != null && newFolder != null){
            treeElements.insert(treeElements.getPosition(rootFolder), newFolder);
            return newFolder;
        }
        return null;
    }

    /**
     * Verifica se um elemento com o mesmo nome já existe na árvore.
     *
     * @param element O elemento a ser verificado.
     * @return true se um elemento com o mesmo nome já existe, false caso contrário.
     */
    public boolean existsElement(Element element){
        for(Element el : treeElements.elements()){
            if(el.getName().equals(element.getName())){
                return true;
            }
        }
        return false;
    }

    /**
     * Obtém estatísticas sobre a quantidade de elementos criados por mês num determinado ano.
     *
     * @param year          O ano para o qual se deseja obter as estatísticas.
     * @param dateExtractor Uma função para extrair a data de criação/modificação de um elemento.
     * @return Um mapa que contém o número de elementos criados por mês no ano especificado.
     */
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

    /**
     * Obtém um elemento pelo nome.
     *
     * @param name O nome do elemento a ser obtido.
     * @return O elemento com o nome especificado ou nulo se não existir.
     */
    public Element getElementByName(String name){
        for(Element element : treeElements.elements()){
            if(element.getName().equals(name)){
                return element;
            }
        }
        return null;
    }

    /**
     * Cria um novo ficheiro na árvore de elementos.
     *
     * @param rootFolder A pasta pai onde o novo ficheiro será criado.
     * @param newFile    O novo ficheiro a ser criado.
     * @return O ficheiro criado ou null se os parâmetros forem inválidos.
     */
    public File createFile(Folder rootFolder, File newFile){
        if(rootFolder != null && newFile != null){
            treeElements.insert(treeElements.getPosition(rootFolder), newFile);
            return newFile;
        }
        return null;
    }

    /**
     * Move um elemento na árvore para um novo local.
     *
     * @param element        O elemento a ser movido.
     * @param destinyElement O novo local para o qual o elemento será movido.
     */
    public void moveElement(Element element, Element destinyElement){
        if(element != null && destinyElement != null){
            treeElements.move(treeElements.getPosition(element), treeElements.getPosition(destinyElement));
        }
    }

    /**
     * Substitui um elemento na árvore por um novo elemento.
     *
     * @param element    O elemento a ser substituído.
     * @param newElement O novo elemento que substituirá o elemento existente.
     */
    public void replaceElement(Element element, Element newElement){
        if(element != null && newElement != null){
            treeElements.replace(treeElements.getPosition(element), newElement);
        }
    }

    /**
     * Remove um elemento da árvore.
     *
     * @param element O elemento a ser removido.
     */
    public void removeElement(Element element){
        if(element != null){
            treeElements.remove(treeElements.getPosition(element));
        }
    }

    public List<Folder> getTopFoldersWithDescendants(){
        List<Folder> allFolders = getAllFolders();
        return null;
    }

    /**
     * Retorna todos os elementos do tipo Folder existentes na árvore.
     */
    private List<Folder> getAllFolders() {
        List<Folder> allFolders = new ArrayList<>();
        for(Element el : treeElements.elements()){
            if(el instanceof Folder){
                allFolders.add((Folder) el);
            }
        }
        return allFolders;
    }

    /**
     * Cria um memento para realizar o controlo de versões.
     */
    public void createMemento() {
        Memento memento = new Memento(treeElements);
        mementos.add(memento);
    }

    /**
     * Restaura o estado da árvore para um estado anterior utilizando um memento.
     * Remove o último memento da lista de histórico.
     */
    public void restoreMemento() {
        if (!mementos.isEmpty()) {
            Memento memento = mementos.get(mementos.size() - 1);
            treeElements = new TreeLinked(memento.getTreeState());
            mementos.remove(memento);
        }
    }
}
