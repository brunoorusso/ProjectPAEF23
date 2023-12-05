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
 * A classe FileManager efetua a gestao da estrutura da arvore de elementos (pastas e ficheiros) num FileSystem.
 * Fornece operacoes para manipular a arvore, calcular estatisticas.
 * @author Bruno Russo 202001410 & Rafael Carvalho Martins 201700039
 */
public class FileManager{

    /**
     * A estrutura da arvore que guarda os elementos do FileSystem.
     */
    TreeLinked<Element> treeElements = new TreeLinked<>();

    /**
     * Lista de mementos para realizar controlo de versoes.
     */
    private List<Memento> mementos = new ArrayList<>();

    /**
     * Obtém a estrutura da arvore que armazena os elementos do FileSystem.
     *
     * @return A estrutura da arvore de elementos.
     */
    public TreeLinked<Element> getTreeElements(){
        return treeElements;
    }

    /**
     * Obtém o numero de pastas na arvore de elementos.
     *
     * @return O numero de pastas na arvore.
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
     * Obtem o numero de ficheiros na arvore de elementos.
     *
     * @return O numero de ficheiros na arvore.
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
     * Obtem a altura da arvore de elementos.
     *
     * @return A altura da arvore.
     */
    public Integer getTreeHeight(){
        return treeElements.height();
    }

    /**
     * Obtem o espaco total ocupado pelos ficheiros na arvore de elementos.
     *
     * @return O espaco total ocupado pelos ficheiros em megabytes.
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
     * Obtem o elemento raiz da arvore.
     *
     * @return O elemento raiz da arvore.
     */
    public Element getRoot(){
        return treeElements.root().element();
    }

    /**
     * Cria a pasta raiz se a arvore estiver vazia.
     *
     * @return A pasta raiz criada ou nulo se a arvore já contiver elementos.
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
     * Cria uma nova pasta na arvore de elementos.
     *
     * @param rootFolder A pasta pai onde a nova pasta sera criada.
     * @param newFolder  A nova pasta a ser criada.
     * @return A nova pasta criada ou nulo se os parametros forem invalidos.
     */
    public Folder createFolder(Folder rootFolder, Folder newFolder){
        if(rootFolder != null && newFolder != null){
            treeElements.insert(treeElements.getPosition(rootFolder), newFolder);
            return newFolder;
        }
        return null;
    }

    /**
     * Verifica se um elemento com o mesmo nome ja existe na arvore.
     *
     * @param element O elemento a ser verificado.
     * @return true se um elemento com o mesmo nome já existe, false caso contrario.
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
     * Obtem estatisticas sobre a quantidade de elementos criados por mes num determinado ano.
     *
     * @param year          O ano para o qual se deseja obter as estatisticas.
     * @param dateExtractor Uma funcao para extrair a data de criacao/modificacao de um elemento.
     * @return Um mapa que contem o numero de elementos criados por mes no ano especificado.
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
     * @return O elemento com o nome especificado ou nulo se nao existir.
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
     * Cria um novo ficheiro na arvore de elementos.
     *
     * @param rootFolder A pasta pai onde o novo ficheiro sera criado.
     * @param newFile    O novo ficheiro a ser criado.
     * @return O ficheiro criado ou null se os parametros forem inválidos.
     */
    public File createFile(Folder rootFolder, File newFile){
        if(rootFolder != null && newFile != null){
            treeElements.insert(treeElements.getPosition(rootFolder), newFile);
            return newFile;
        }
        return null;
    }

    /**
     * Move um elemento na arvore para um novo local.
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
     * Substitui um elemento na arvore por um novo elemento.
     *
     * @param element    O elemento a ser substituido.
     * @param newElement O novo elemento que substituira o elemento existente.
     */
    public void replaceElement(Element element, Element newElement){
        if(element != null && newElement != null){
            treeElements.replace(treeElements.getPosition(element), newElement);
        }
    }

    /**
     * Remove um elemento da arvore.
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
     * Cria um memento para realizar o controlo de versoes.
     * nao se encontra funcional
     */
    public void createMemento() {
        Memento memento = new Memento(treeElements);
        mementos.add(memento);
    }

    /**
     * Restaura o estado da arvore para um estado anterior utilizando um memento.
     * Remove o ultimo memento da lista de historico.
     * nao se encontra funcional
     */
    public void restoreMemento() {
        if (!mementos.isEmpty()) {
            Memento memento = mementos.get(mementos.size() - 1);
            treeElements = new TreeLinked(memento.getTreeState());
            mementos.remove(memento);
        }
    }
}
