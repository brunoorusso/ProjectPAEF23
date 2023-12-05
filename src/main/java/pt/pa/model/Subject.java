package pt.pa.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe Subject implementa a interface Observable e atua como um objeto observavel que pode ter observers registados.
 * Mantem uma lista de observers e notifica-os sobre mudancas no elemento selecionado.
 * @author Bruno Russo 202001410
 */
public class Subject implements Observable{
    private List<Observer> observers;
    private Element selectedElement;

    /**
     * Construtor padrao da classe Subject.
     * Inicializa a lista de observers como um ArrayList vazio.
     */
    public Subject() {
        this.observers = new ArrayList<>();
    }

    /**
     * Adiciona um observer a lista de observers deste objeto observavel.
     *
     * @param observer O observer a ser adicionado.
     */
    @Override
    public void addObserver(Observer observer){
        observers.add(observer);
    }

    /**
     * Remove um observer da lista de observers deste objeto observavel.
     *
     * @param observer O observer a ser removido.
     */
    @Override
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    /**
     * Define o elemento selecionado e notifica todos os observers registados sobre a mudanca.
     *
     * @param selectedElement O novo elemento selecionado.
     */
    public void setSelectedElement(Element selectedElement){
        this.selectedElement = selectedElement;
        notifyObservers();
    }

    /**
     * Obtem o elemento atualmente selecionado.
     *
     * @return O elemento atualmente selecionado.
     */
    public Element getSelectedElement(){
        return selectedElement;
    }

    /**
     * Notifica todos os observers registados sobre uma mudanca no elemento selecionado.
     */
    @Override
    public void notifyObservers(){
        for(Observer observer : observers){
            observer.update(selectedElement);
        }
    }
}
