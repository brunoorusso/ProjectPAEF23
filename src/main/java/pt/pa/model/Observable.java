package pt.pa.model;

/**
 * A interface Observable define os metodo para objetos observaveis que podem ter observers registados.
 * @author Bruno Russo 202001410
 */
public interface Observable {

    /**
     * Adiciona um observer a lista de observers deste objeto observavel.
     *
     * @param observer O observer a ser adicionado.
     */
    void addObserver(Observer observer);

    /**
     * Remove um observer da lista de observers deste objeto observavel.
     *
     * @param observer O observer a ser removido.
     */
    void removeObserver(Observer observer);

    /**
     * Notifica todos os observers registados sobre uma mudança no objeto observavel.
     */
    void notifyObservers();
}
