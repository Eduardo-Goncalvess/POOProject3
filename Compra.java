package TrabalhoAeD.controle;

public class Compra {
    private int codigo;
    private double valorTotal;
    private int quantidade;
    private Produto produto;
    private boolean paga;

    public Compra(int codigo, double valorTotal, int quantidade, Produto produto, boolean paga) {
        this.codigo = codigo;
        this.valorTotal = valorTotal;
        this.quantidade = quantidade;
        this.produto = produto;
        this.paga = paga;
    }

    public Compra(Compra outra) {
        this.codigo = outra.codigo;
        this.valorTotal = outra.valorTotal;
        this.quantidade = outra.quantidade;
        this.produto = (outra.produto != null) ? new Produto(outra.produto) : null;
        this.paga = outra.paga;
    }

    static public Compra getInstance(int codigo, double valorTotal, int quantidade, Produto produto, boolean paga) {
        if (codigo < 0 || valorTotal < 0 || quantidade < 0 || produto == null) {
            return null;
        } else {
            return new Compra(codigo, valorTotal, quantidade, produto, paga);
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public boolean isPaga() {
        return paga;
    }

    public void setPaga(boolean paga) {
        this.paga = paga;
    }

    @Override
    public String toString() {
        return String.format("%-6d | R$ %-8.2f | %-8d | %-15s | %-8s",
                codigo,
                valorTotal,
                quantidade,
                produto != null ? (produto.getNome().length() > 15 ? produto.getNome().substring(0, 12) + "..." : produto.getNome()) : "N/A",
                paga ? "PAGA" : "PENDENTE");
    }
}