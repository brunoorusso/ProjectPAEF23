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

/**
 * A classe DetailsController é responsável por atualizar o painel de detalhes na interface gráfica com informações sobre o elemento selecionado.
 * Implementa a interface Observer para receber notificações sobre mudanças no elemento selecionado.
 */
public class DetailsController implements Observer {

    /**
     * O painel de detalhes a ser atualizado.
     */
    private VBox detailsPanel;

    /**
     * Construtor que cria uma nova instância de DetailsController.
     *
     * @param detailsPanel O painel de detalhes a ser atualizado.
     */
    public DetailsController(VBox detailsPanel) {
        this.detailsPanel = detailsPanel;
    }

    /**
     * Atualiza o painel de detalhes com informações sobre o elemento selecionado.
     *
     * @param selectedElement O elemento selecionado cujas informações serão exibidas no painel de detalhes.
     */
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

    /**
     * Método de atualização da interface Observer. Chama o método `updateDetails` com o elemento selecionado.
     *
     * @param selectedElement O elemento selecionado cujas informações serão exibidas no painel de detalhes.
     */
    @Override
    public void update(Element selectedElement) {
        updateDetails(selectedElement);
    }

    /**
     * Formata a data e hora no formato desejado.
     *
     * @param date A data e hora a ser formatada.
     * @return A data e hora formatada como uma string.
     */
    private String formatData(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = date.format(formatter);
        return formattedDateTime;
    }
}
