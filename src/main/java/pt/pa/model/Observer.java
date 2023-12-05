package pt.pa.model;

/**
 * A interface Observer para objetos que desejam ser notificados sobre mudancas em um elemento.
 * @author Bruno Russo 202001410
 */
public interface Observer {

    /**
     * Metodo chamado para notificar o Observer sobre uma mudanca num elemento.
     *
     * @param element O elemento que sofreu uma alteracao.
     */
    void update(Element element);
}
