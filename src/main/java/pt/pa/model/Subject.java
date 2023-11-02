package pt.pa.model;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private Element selectedElement;

    public void addObserver(Observer observer){
        observers.add(observer);
    }

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

    private void notifyObservers(){
        for(Observer observer : observers){
            observer.update(selectedElement);
        }
    }
}
