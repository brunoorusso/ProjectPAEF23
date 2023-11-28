package pt.pa.model;

/**
 * A interface Observable define os métodos para objetos observáveis que podem ter observers registrados.
 */
public interface Observable {

    /**
     * Adiciona um observer à lista de observers deste objeto observável.
     *
     * @param observer O observer a ser adicionado.
     */
    void addObserver(Observer observer);

    /**
     * Remove um observer da lista de observers deste objeto observável.
     *
     * @param observer O observer a ser removido.
     */
    void removeObserver(Observer observer);

    /**
     * Notifica todos os observers registrados sobre uma mudança no objeto observável.
     */
    void notifyObservers();
}
