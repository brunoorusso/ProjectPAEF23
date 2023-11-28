package pt.pa.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe Subject implementa a interface Observable e atua como um objeto observável que pode ter observers registados.
 * Mantém uma lista de observers e notifica-os sobre mudanças no elemento selecionado.
 */
public class Subject implements Observable{
    private List<Observer> observers;
    private Element selectedElement;

    /**
     * Construtor padrão da classe Subject.
     * Inicializa a lista de observers como um ArrayList vazio.
     */
    public Subject() {
        this.observers = new ArrayList<>();
    }

    /**
     * Adiciona um observer à lista de observers deste objeto observável.
     *
     * @param observer O observer a ser adicionado.
     */
    @Override
    public void addObserver(Observer observer){
        observers.add(observer);
    }

    /**
     * Remove um observer da lista de observers deste objeto observável.
     *
     * @param observer O observer a ser removido.
     */
    @Override
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    /**
     * Define o elemento selecionado e notifica todos os observers registados sobre a mudança.
     *
     * @param selectedElement O novo elemento selecionado.
     */
    public void setSelectedElement(Element selectedElement){
        this.selectedElement = selectedElement;
        notifyObservers();
    }

    /**
     * Obtém o elemento atualmente selecionado.
     *
     * @return O elemento atualmente selecionado.
     */
    public Element getSelectedElement(){
        return selectedElement;
    }

    /**
     * Notifica todos os observers registados sobre uma mudança no elemento selecionado.
     */
    @Override
    public void notifyObservers(){
        for(Observer observer : observers){
            observer.update(selectedElement);
        }
    }
}
