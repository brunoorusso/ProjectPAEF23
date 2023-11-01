package pt.pa.model;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomTreeItem extends TreeItem<String> {
    private Image icon;

    public CustomTreeItem(String name, Image icon) {
        super(name);
        this.icon = icon;
    }

    public Image getIcon() {
        return icon;
    }
}
