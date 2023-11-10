package pt.pa.model;

import java.util.ArrayList;
import java.util.List;

public class Subject implements Observable{
    private List<Observer> observers;
    private Element selectedElement;

    public Subject() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer){
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public void setSelectedElement(Element selectedElement){
        this.selectedElement = selectedElement;
        notifyObservers();
    }

    public Element getSelectedElement(){
        return selectedElement;
    }

    @Override
    public void notifyObservers(){
        for(Observer observer : observers){
            observer.update(selectedElement);
        }
    }
}
