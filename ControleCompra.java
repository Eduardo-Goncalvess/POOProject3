package TrabalhoAeD.controle;

import TrabalhoAeD.dados.RepositorioCompra;

public class ControleCompra {
    private RepositorioCompra repoCompra;

    public ControleCompra() {
        this.repoCompra = RepositorioCompra.getInstance();
    }

    public boolean registrarCompra(double valorTotal, int quantidade, Produto produto, boolean paga, Usuario comprador)
            throws NegocioException, ValidacaoException {
        if (produto == null) {
            throw new ValidacaoException("Produto não pode ser nulo");
        }
        if (valorTotal < 0) {
            throw new ValidacaoException("Valor total não pode ser negativo");
        }
        if (quantidade <= 0) {
            throw new ValidacaoException("Quantidade deve ser maior que zero");
        }
        if (comprador == null) {
            throw new ValidacaoException("Comprador não pode ser nulo");
        }

        int codigoCompra = repoCompra.getProxCodigo();
        Compra compra = Compra.getInstance(codigoCompra, valorTotal, quantidade, produto, paga);

        if (compra == null) {
            throw new ValidacaoException("Dados inválidos para criar compra");
        }

        if (repoCompra.add(compra)) {
            comprador.adicionarCompra(compra);
            return true;
        }
        throw new NegocioException("Falha ao registrar compra");
    }

    public int getProxCodigo() {
        return repoCompra.getProxCodigo();
    }
}