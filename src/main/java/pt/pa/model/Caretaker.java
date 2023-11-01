package pt.pa.model;

import java.util.Stack;

public class Caretaker<Memento> {
    private Stack<Memento> mementos = new Stack<>();

    public void saveMemento(Memento memento) {
        mementos.push(memento);
    }

    public Memento getPreviousMemento() {
        if (!mementos.isEmpty()) {
            return mementos.peek();
        }
        return null;
    }
}
