package pt.pa.model;

import java.time.LocalDateTime;

public class Element{
    protected String name;
    protected LocalDateTime creationDate;
    protected LocalDateTime modifiedDate;
    protected int numberOfChanges;
    protected boolean isLocked;

    public Element(String name, Element parent){
        this.name = name;
        this.creationDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
        this.numberOfChanges = 0;
        this.isLocked = false;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public LocalDateTime getCreationDate(){
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate){
        this.creationDate = creationDate;
    }

    public LocalDateTime getModifiedDate(){
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate){
        this.modifiedDate = modifiedDate;
    }

    public int getNumberOfChanges(){
        return numberOfChanges;
    }

    public void setNumberOfChanges(int numberOfChanges){
        this.numberOfChanges = numberOfChanges;
    }

    public boolean isLocked(){
        return isLocked;
    }

    public void setLocked(boolean locked){
        isLocked = locked;
    }

}
