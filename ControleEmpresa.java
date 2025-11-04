package TrabalhoAeD.controle;

import TrabalhoAeD.dados.RepositorioEmpresa;
import java.util.ArrayList;
import java.util.List;

public class ControleEmpresa {
    private RepositorioEmpresa repoEmpresa;

    public ControleEmpresa() {
        repoEmpresa = RepositorioEmpresa.getInstance();
    }

    public Empresa getInstance(int codigo, String nome, int telefone, String cidade) {
        return Empresa.getInstance(codigo, nome, telefone, cidade);
    }

    public boolean add(Empresa a) {
        if (repoEmpresa.buscarPorNome(a)) {
            return false;
        }
        return repoEmpresa.add(a);
    }

    public boolean remove(int codigo) {
        return repoEmpresa.remove(codigo);
    }

    public int getProxCodigo() {
        return repoEmpresa.getProxCodigo();
    }

    public boolean verificarNome(String nome) {
        return repoEmpresa.verificarNome(nome);
    }

    public Empresa getEmpresa(int codigo) {
        return repoEmpresa.getEmpresa(codigo);
    }

    public boolean verificarProdutoNaEmpresa(int codigoEmpresa, String nomeProduto) {
        return repoEmpresa.verificarProdutoNaEmpresa(codigoEmpresa, nomeProduto);
    }

    public boolean adicionarProdutoFornecedor(int codigoEmpresa, Produto produto) {
        Empresa e = repoEmpresa.adicionarProdutoFornecedor(codigoEmpresa, produto);
        if (e.adicionarProduto(produto)) {
            return true;
        }
        return false;
    }

    public boolean removerProdutoFornecedor(Produto produto, int codF) {
        Empresa e = repoEmpresa.getEmpresa(codF);
        if (e.removerProduto(produto)) {
            return true;
        }
        return false;
    }

    public boolean atualizarNome(int codigo, String novoNome) {
        return repoEmpresa.atualizarNome(codigo, novoNome);
    }

    public boolean atualizarTelefone(int codigo, int novoTelefone) {
        return repoEmpresa.atualizarTelefone(codigo, novoTelefone);
    }

    public boolean atualizarCidade(int codigo, String novaCidade) {
        return repoEmpresa.atualizarCidade(codigo, novaCidade);
    }

    public List<Empresa> listarEmpresas() {
        List<Empresa> original = repoEmpresa.listarEmpresas();
        List<Empresa> copia = new ArrayList<>();
        for (Empresa empresa : original) {
            copia.add(new Empresa(empresa));
        }
        return copia;
    }
}
