package pt.pa.model;

/**
 * A classe Folder representa uma pasta no FileSystem.
 * Herda da classe Element, que representa um elemento generico no sistema.
 * @author Bruno Russo 202001410
 */
public class Folder extends Element{
    /**
     * A pasta raiz a qual este elemento pertence.
     */
    private Folder root;

    /**
     * Construtor padrao que cria uma nova instancia de Folder com o nome "root".
     * Esta pasta serve como a raiz do sistema de arquivos.
     */
    public Folder() {
        super("root", null);
        this.root = this;
    }

    /**
     * Construtor que cria uma nova instancia de Folder com um nome especifico e associada a uma pasta raiz.
     *
     * @param root A pasta raiz a qual esta pasta pertence.
     * @param name O nome da pasta.
     */
    public Folder(Folder root, String name) {
        super(name, root);
        this.root = root;
    }

    /**
     * Retorna uma representacao em string do objeto Folder.
     *
     * @return Uma string que representa o objeto Folder.
     */
    @Override
    public String toString() {
        return "Folder{" +
                "name='" + name + '\'' +
                '}';
    }
}
