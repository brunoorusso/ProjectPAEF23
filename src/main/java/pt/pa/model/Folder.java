package pt.pa.model;

public class Folder extends Element{

    private Element element;
    private Folder root;

    public Folder() {
        super("root", null);
        this.root = this;
    }

    public Folder(Folder root, String name) {
        super(name, root);
        this.root = root;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "name='" + name + '\'' +
                '}';
    }
}
