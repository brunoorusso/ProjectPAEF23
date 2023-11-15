package pt.pa.model;

import java.time.LocalDateTime;

public class File extends Element{

    private Folder root;
    private String content;
    private Integer size;

    public File(Folder root, String name, String content) {
        super(name, root);
        this.content = content;
        this.root = root;
        this.size = content.length();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                '}';
    }
}
