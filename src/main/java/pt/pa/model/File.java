package pt.pa.model;

/**
 * Classe File representa um ficheiro no FileSystem.
 * Herda da classe Element, que representa um elemento generico.
 * @author Bruno Russo 202001410
 */
public class File extends Element{

    /**
     * A pasta raiz a qual este ficheiro pertence.
     */
    private Folder root;

    /**
     * O conteudo do ficheiro.
     */
    private String content;

    /**
     * O tamanho do ficheiro em termos de caracteres no conteudo.
     */
    private Integer size;

    /**
     * Construtor que cria uma nova instancia de File.
     *
     * @param root    A pasta raiz a qual o ficheiro pertence.
     * @param name    O nome do ficheiro.
     * @param content O conteudo do ficheiro.
     */
    public File(Folder root, String name, String content) {
        super(name, root);
        this.content = content;
        this.root = root;
        this.size = content.length();
    }

    /**
     * Obtém o conteudo do ficheiro.
     *
     * @return conteudo do ficheiro.
     */
    public String getContent() {
        return content;
    }

    /**
     * Define o conteudo do ficheiro.
     *
     * @param content O novo conteudo do ficheiro.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Obtem o tamanho do ficheiro.
     *
     * @return O tamanho do ficheiro com base nos caracteres do conteudo.
     */
    public int getSize() {
        return size;
    }

    /**
     * Define o tamanho do ficheiro.
     *
     * @param size O novo tamanho do ficheiro com base nos caracteres do conteudo.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Retorna uma representacao em string do objeto File.
     *
     * @return Uma string que representa o objeto File.
     */
    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                '}';
    }
}
