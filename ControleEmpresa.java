package TrabalhoAeD.controle;

import TrabalhoAeD.dados.RepositorioEmpresa;
import java.util.ArrayList;
import java.util.List;

public class ControleEmpresa {
    private RepositorioEmpresa repoEmpresa;

    public ControleEmpresa() {
        repoEmpresa = RepositorioEmpresa.getInstance();
    }

    public Empresa getInstance(int codigo, String nome, int telefone, String cidade) throws ValidacaoException {
        Empresa empresa = Empresa.getInstance(codigo, nome, telefone, cidade);
        if (empresa == null) {
            throw new ValidacaoException("Dados inválidos para criar empresa");
        }
        return empresa;
    }

    public boolean add(Empresa a) throws CadastroException, ValidacaoException {
        if (a == null) {
            throw new ValidacaoException("Empresa não pode ser nula");
        }
        if (repoEmpresa.buscarPorNome(a)) {
            throw new CadastroException("Já existe uma empresa com este nome: " + a.getNome());
        }
        boolean resultado = repoEmpresa.add(a);
        if (!resultado) {
            throw new CadastroException("Falha ao cadastrar empresa");
        }
        return resultado;
    }

    public boolean remove(int codigo) throws NegocioException {
        boolean resultado = repoEmpresa.remove(codigo);
        if (!resultado) {
            throw new NegocioException("Empresa não encontrada ou não pode ser removida. Código: " + codigo);
        }
        return resultado;
    }

    public int getProxCodigo() {
        return repoEmpresa.getProxCodigo();
    }

    public boolean verificarNome(String nome) {
        return repoEmpresa.verificarNome(nome);
    }

    public Empresa getEmpresa(int codigo) throws NegocioException {
        Empresa empresa = repoEmpresa.getEmpresa(codigo);
        if (empresa == null) {
            throw new NegocioException("Empresa não encontrada. Código: " + codigo);
        }
        return empresa;
    }

    public boolean verificarProdutoNaEmpresa(int codigoEmpresa, String nomeProduto) throws NegocioException {
        getEmpresa(codigoEmpresa);
        return repoEmpresa.verificarProdutoNaEmpresa(codigoEmpresa, nomeProduto);
    }

    public boolean adicionarProdutoFornecedor(int codigoEmpresa, Produto produto) throws NegocioException, ValidacaoException {
        if (produto == null) {
            throw new ValidacaoException("Produto não pode ser nulo");
        }
        Empresa e = repoEmpresa.adicionarProdutoFornecedor(codigoEmpresa, produto);
        if (e == null) {
            throw new NegocioException("Empresa não encontrada");
        }
        boolean resultado = e.adicionarProduto(produto);
        if (!resultado) {
            throw new NegocioException("Falha ao adicionar produto ao fornecedor");
        }
        return resultado;
    }

    public boolean removerProdutoFornecedor(Produto produto, int codF) throws NegocioException, ValidacaoException {
        if (produto == null) {
            throw new ValidacaoException("Produto não pode ser nulo");
        }
        Empresa e = getEmpresa(codF);
        boolean resultado = e.removerProduto(produto);
        if (!resultado) {
            throw new NegocioException("Falha ao remover produto do fornecedor");
        }
        return resultado;
    }

    public boolean atualizarNome(int codigo, String novoNome) throws NegocioException, ValidacaoException {
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new ValidacaoException("Nome não pode ser vazio");
        }
        boolean resultado = repoEmpresa.atualizarNome(codigo, novoNome);
        if (!resultado) {
            throw new NegocioException("Falha ao atualizar nome da empresa");
        }
        return resultado;
    }

    public boolean atualizarTelefone(int codigo, int novoTelefone) throws NegocioException, ValidacaoException {
        if (novoTelefone < 1000 || novoTelefone > 9999) {
            throw new ValidacaoException("Telefone deve ter 4 dígitos");
        }
        boolean resultado = repoEmpresa.atualizarTelefone(codigo, novoTelefone);
        if (!resultado) {
            throw new NegocioException("Falha ao atualizar telefone da empresa");
        }
        return resultado;
    }

    public boolean atualizarCidade(int codigo, String novaCidade) throws NegocioException, ValidacaoException {
        if (novaCidade == null || novaCidade.trim().isEmpty()) {
            throw new ValidacaoException("Cidade não pode ser vazia");
        }
        boolean resultado = repoEmpresa.atualizarCidade(codigo, novaCidade);
        if (!resultado) {
            throw new NegocioException("Falha ao atualizar cidade da empresa");
        }
        return resultado;
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