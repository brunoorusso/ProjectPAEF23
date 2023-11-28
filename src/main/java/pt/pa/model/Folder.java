package pt.pa.model;

/**
 * A classe Folder representa uma pasta no FileSystem.
 * Herda da classe Element, que representa um elemento genérico no sistema.
 */
public class Folder extends Element{
    /**
     * A pasta raiz à qual este elemento pertence.
     */
    private Folder root;

    /**
     * Construtor padrão que cria uma nova instância de Folder com o nome "root".
     * Esta pasta serve como a raiz do sistema de arquivos.
     */
    public Folder() {
        super("root", null);
        this.root = this;
    }

    /**
     * Construtor que cria uma nova instância de Folder com um nome específico e associada a uma pasta raiz.
     *
     * @param root A pasta raiz à qual esta pasta pertence.
     * @param name O nome da pasta.
     */
    public Folder(Folder root, String name) {
        super(name, root);
        this.root = root;
    }

    /**
     * Retorna uma representação em string do objeto Folder.
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
