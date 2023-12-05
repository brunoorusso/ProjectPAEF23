package pt.pa.model;

import java.time.LocalDateTime;

/**
 * A classe Element representa um elemento generico no FileSystem.
 * Este elemento ser uma pasta (Folder) ou um ficheiro (File).
 * @author Bruno Russo 202001410
 */
public class Element{
    /**
     * O nome do elemento.
     */
    protected String name;

    /**
     * A data e hora de criacao do elemento.
     */
    protected LocalDateTime creationDate;

    /**
     * A data e hora da ultima modificacao do elemento.
     */
    protected LocalDateTime modifiedDate;

    /**
     * O número de alteracoes feitas no elemento.
     */
    protected int numberOfChanges;

    /**
     * Indica se o elemento esta bloqueado.
     */
    protected boolean isLocked;

    /**
     * Construtor que cria uma nova instancia de Element.
     *
     * @param name   O nome do elemento.
     * @param parent O elemento pai ao qual este elemento pertence.
     */
    public Element(String name, Element parent){
        this.name = name;
        this.creationDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
        this.numberOfChanges = 0;
        this.isLocked = false;
    }

    /**
     * Obtém o nome do elemento.
     *
     * @return O nome do elemento.
     */
    public String getName(){
        return name;
    }

    /**
     * Define o nome do elemento.
     *
     * @param name O novo nome do elemento.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Obtém a data e hora de criacao do elemento.
     *
     * @return A data e hora de criacao do elemento.
     */
    public LocalDateTime getCreationDate(){
        return creationDate;
    }

    /**
     * Define a data e hora de criacao do elemento.
     *
     * @param creationDate A nova data e hora de criacao do elemento.
     */
    public void setCreationDate(LocalDateTime creationDate){
        this.creationDate = creationDate;
    }

    /**
     * Obtém a data e hora da ultima modificacao do elemento.
     *
     * @return A data e hora da ultima modificacao do elemento.
     */
    public LocalDateTime getModifiedDate(){
        return modifiedDate;
    }

    /**
     * Define a data e hora da ultima modificacao do elemento.
     *
     * @param modifiedDate A nova data e hora da ultima modificacao do elemento.
     */
    public void setModifiedDate(LocalDateTime modifiedDate){
        this.modifiedDate = modifiedDate;
    }

    /**
     * Obtém o numero de alteracoes feitas no elemento.
     *
     * @return O numero de alteracoes feitas no elemento.
     */
    public int getNumberOfChanges(){
        return numberOfChanges;
    }

    /**
     * Define o numero de alteracoes feitas no elemento.
     *
     * @param numberOfChanges O novo numero de alteracoes no elemento.
     */
    public void setNumberOfChanges(int numberOfChanges){
        this.numberOfChanges = numberOfChanges;
    }

    /**
     * Verifica se o elemento esta bloqueado.
     *
     * @return true se o elemento estiver bloqueado, false caso contrario.
     */
    public boolean isLocked(){
        return isLocked;
    }

    /**
     * Define se o elemento esta bloqueado.
     *
     * @param locked true para bloquear o elemento, false para desbloquear.
     */
    public void setLocked(boolean locked){
        isLocked = locked;
    }

}
