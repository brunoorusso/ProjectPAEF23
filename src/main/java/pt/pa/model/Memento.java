package pt.pa.model;

import javafx.scene.control.TreeView;
import pt.pa.adts.TreeLinked;

public class Memento {
    private TreeLinked<Element> treeState;

    public Memento(TreeLinked<Element> treeState){
        this.treeState = new TreeLinked(treeState);
    }

    public TreeLinked<Element> getTreeState(){
        return treeState;
    }
}
