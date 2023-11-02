package pt.pa.controller;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;
import pt.pa.model.Element;
import pt.pa.model.File;
import pt.pa.model.Folder;
import pt.pa.model.Observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DetailsController implements Observer {

    private VBox detailsPanel;

    public DetailsController(VBox detailsPanel) {
        this.detailsPanel = detailsPanel;
    }

    public void updateDetails(Element selectedElement) {
        if (selectedElement != null) {
            String name = selectedElement.getName();
            String creationDate = formatData(selectedElement.getCreationDate());
            String modificationDate = formatData(selectedElement.getModifiedDate());
            Integer numberOfChanges = selectedElement.getNumberOfChanges();
            Boolean isLocked = selectedElement.isLocked();

            Label nameLabel = new Label("Nome: " + name);
            Label typeLabel = new Label();
            Label changesLabel = new Label("Número de modificações: " + numberOfChanges);
            Label lockedLabel = new Label("Status: Não bloqueado.");
            Label bytesLabel = new Label();
            if(isLocked){
                lockedLabel.setText("Status: Bloqueado para edição.");
            }
            if(selectedElement instanceof Folder){
                typeLabel.setText("Tipo: Pasta");
            }else if(selectedElement instanceof File){
                long fileSize = ((File) selectedElement).getSize();
                typeLabel.setText("Tipo: Ficheiro");
                bytesLabel.setText("Tamanho: " + fileSize + " bytes.");
            }

            Label creationDateLabel = new Label("Criado em: " + creationDate);
            Label modificationDateLabel = new Label("Última modificação: " + modificationDate);

            detailsPanel.getChildren().clear();
            detailsPanel.getChildren().addAll(nameLabel, typeLabel, creationDateLabel, modificationDateLabel, changesLabel, lockedLabel, bytesLabel);
        }
    }

    @Override
    public void update(Element selectedElement) {
        updateDetails(selectedElement);
    }

    private String formatData(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = date.format(formatter);
        return formattedDateTime;
    }
}
