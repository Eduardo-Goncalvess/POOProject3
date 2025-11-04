package TrabalhoAeD.dados;

import java.util.ArrayList;
import java.util.List;

import TrabalhoAeD.controle.Empresa;
import TrabalhoAeD.controle.Produto;

public class RepositorioEmpresa {

    private static RepositorioEmpresa instancia;
    private List<Empresa> empresas;
    private int proximoCodigo;

    private RepositorioEmpresa() {
        this.empresas = new ArrayList<Empresa>();
        this.proximoCodigo = 1;
    }

    public static RepositorioEmpresa getInstance() {
        if (instancia == null) {
            instancia = new RepositorioEmpresa();
        }
        return instancia;
    }

    public boolean add(Empresa e) {
        if (e != null) {
            empresas.add(e);
            proximoCodigo++;
            return true;
        }
        return false;
    }

    public boolean remove(int codigo) {
        for (int i = 0; i < empresas.size(); i++) {
            Empresa e = empresas.get(i);
            if (e.getCodigo() == codigo) {
                RepositorioProduto repoProduto = RepositorioProduto.getInstance();
                repoProduto.removerFornecedorDosProdutos(codigo);

                empresas.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getProxCodigo() {
        return proximoCodigo;
    }

    public boolean verificarNome(String nome) {
        for (int i = 0; i < empresas.size(); i++) {
            Empresa e = empresas.get(i);
            if (e.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarProdutoNaEmpresa(int codigoEmpresa, String nomeProduto) {
        Empresa empresa = getEmpresa(codigoEmpresa);
        if (empresa != null) {
            for (Produto p : empresa.getProdutos()) {
                if (p.getNome().equalsIgnoreCase(nomeProduto)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean buscarPorNome(Empresa empresa) {
        for (int i = 0; i < empresas.size(); i++) {
            Empresa e = empresas.get(i);
            if (e != null && e.getNome().equalsIgnoreCase(empresa.getNome())) {
                return true;
            }
        }
        return false;
    }

    public Empresa getEmpresa(int codigo) {
        for (int i = 0; i < empresas.size(); i++) {
            Empresa e = empresas.get(i);
            if (e.getCodigo() == codigo) {
                return e;
            }
        }
        return null;
    }

    public Empresa adicionarProdutoFornecedor(int codigoEmpresa, Produto produto) {
        Empresa empresa = getEmpresa(codigoEmpresa);
        if (empresa != null && produto != null) {
            if (!verificarProdutoNaEmpresa(codigoEmpresa, produto.getNome())) {
                return null;
            }

            return empresa;
        }
        return null;
    }

    public boolean atualizarNome(int codigo, String novoNome) {
        Empresa empresa = getEmpresa(codigo);
        if (empresa != null) {
            empresa.setNome(novoNome);
            return true;
        }
        return false;
    }

    public boolean atualizarTelefone(int codigo, int novoTelefone) {
        Empresa empresa = getEmpresa(codigo);
        if (empresa != null) {
            empresa.setTelefone(novoTelefone);
            return true;
        }
        return false;
    }

    public boolean atualizarCidade(int codigo, String novaCidade) {
        Empresa empresa = getEmpresa(codigo);
        if (empresa != null) {
            empresa.setCidade(novaCidade);
            return true;
        }
        return false;
    }

    public List<Empresa> listarEmpresas() {
        return empresas;
    }
}
