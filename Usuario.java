package TrabalhoAeD.controle;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int codigo;
    private String nome;
    private int telefone;
    private String cidade;
    private List<Compra> compras; 

    protected Usuario(int codigo, String nome, int telefone, String cidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.cidade = cidade;
        this.compras = new ArrayList<Compra>(); 
    }

    public Usuario(Usuario outro) {
        this.codigo = outro.codigo;
        this.nome = outro.nome;
        this.telefone = outro.telefone;
        this.cidade = outro.cidade;
        this.compras = new ArrayList<Compra>();
        for (Compra compra : outro.compras) {
            this.compras.add(new Compra(compra));
        }
    }

    static public Usuario getInstance(int codigo, String nome, int telefone, String cidade) {
        if (codigo < 0 || nome == null || telefone < 0 || cidade == null) {
            return null;
        } else {
            return new Usuario(codigo, nome, telefone, cidade);
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

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public List<Compra> getCompras() {
        List<Compra> copia = new ArrayList<>();
        for (Compra compra : compras) {
            copia.add(new Compra(compra));
        }
        return copia;
    }

    public boolean adicionarCompra(Compra compra) {
        if (compra != null) {
            compras.add(compra);
            return true;
        }
        return false;
    }

    public boolean removerCompra(Compra compra) {
        if (compra != null && compras.contains(compra)) {
            compras.remove(compra);
            return true;
        }
        return false;
    }

    public int getQuantidadeCompras() {
        return compras.size();
    }

    public boolean pagarComprasPendentes() {
        boolean todasPagas = true;
        for (Compra compra : compras) {
            if (!compra.isPaga()) {
                compra.setPaga(true);
            }
        }
        return todasPagas;
    }

    @Override
    public String toString() {
        return String.format("%-6d | %-15s | %-8d | %-12s",
                codigo,
                nome.length() > 15 ? nome.substring(0, 12) + "..." : nome,
                telefone,
                cidade.length() > 12 ? cidade.substring(0, 9) + "..." : cidade);
    }
}