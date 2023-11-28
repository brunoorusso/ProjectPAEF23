package pt.pa.model;

/**
 * A interface Observer para objetos que desejam ser notificados sobre mudanças em um elemento.
 */
public interface Observer {

    /**
     * Método chamado para notificar o Observer sobre uma mudança em um elemento.
     *
     * @param element O elemento que sofreu uma alteração.
     */
    void update(Element element);
}
