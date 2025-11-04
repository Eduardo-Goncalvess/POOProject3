package TrabalhoAeD.controle;

public class Categoria {
    private int codigo;
    private String nome;
    private String descricao;

    private Categoria(int codigo, String nome, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Categoria(Categoria outra) {
        this.codigo = outra.codigo;
        this.nome = outra.nome;
        this.descricao = outra.descricao;
    }

    static public Categoria getInstance(int codigo, String nome, String descricao) {
        if (codigo < 0 || nome == null || descricao == null) {
            return null;
        } else {
            return new Categoria(codigo, nome, descricao);
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return String.format("%-6d | %-15s | %-20s",
                codigo,
                nome.length() > 15 ? nome.substring(0, 12) + "..." : nome,
                descricao.length() > 20 ? descricao.substring(0, 17) + "..." : descricao);
    }
}