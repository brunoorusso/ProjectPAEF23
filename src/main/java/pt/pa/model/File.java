package pt.pa.model;

/**
 * Classe File representa um ficheiro no FileSystem.
 * Herda da classe Element, que representa um elemento genérico.
 */
public class File extends Element{

    /**
     * A pasta raiz à qual este arquivo pertence.
     */
    private Folder root;

    /**
     * O conteúdo do arquivo.
     */
    private String content;

    /**
     * O tamanho do arquivo em termos de caracteres no conteúdo.
     */
    private Integer size;

    /**
     * Construtor que cria uma nova instância de File.
     *
     * @param root    A pasta raiz à qual o ficheiro pertence.
     * @param name    O nome do ficheiro.
     * @param content O conteúdo do ficheiro.
     */
    public File(Folder root, String name, String content) {
        super(name, root);
        this.content = content;
        this.root = root;
        this.size = content.length();
    }

    /**
     * Obtém o conteúdo do ficheiro.
     *
     * @return Contéudo do ficheiro.
     */
    public String getContent() {
        return content;
    }

    /**
     * Define o conteúdo do ficheiro.
     *
     * @param content O novo conteúdo do ficheiro.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Obtém o tamanho do ficheiro.
     *
     * @return O tamanho do ficheiro com base nos caracteres do conteúdo.
     */
    public int getSize() {
        return size;
    }

    /**
     * Define o tamanho do ficheiro.
     *
     * @param size O novo tamanho do ficheiro com base nos caracteres do conteúdo.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Retorna uma representação em string do objeto File.
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
