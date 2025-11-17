package TrabalhoAeD.controle;

import TrabalhoAeD.dados.RepositorioCategoria;
import java.util.ArrayList;
import java.util.List;

public class ControleCategoria {
    private RepositorioCategoria repoCategoria;

    public ControleCategoria() {
        this.repoCategoria = RepositorioCategoria.getInstance();
    }

    public Categoria getInstance(int codigo, String nome, String descricao) throws ValidacaoException {
        Categoria categoria = Categoria.getInstance(codigo, nome, descricao);
        if (categoria == null) {
            throw new ValidacaoException("Dados inválidos para criar categoria");
        }
        return categoria;
    }

    public boolean add(Categoria a) throws CadastroException, ValidacaoException {
        if (a == null) {
            throw new ValidacaoException("Categoria não pode ser nula");
        }
        if (repoCategoria.buscarPorNome(a)) {
            throw new CadastroException("Já existe uma categoria com este nome: " + a.getNome());
        }
        boolean resultado = repoCategoria.add(a);
        if (!resultado) {
            throw new CadastroException("Falha ao cadastrar categoria");
        }
        return resultado;
    }

    public boolean remove(int codigo) throws NegocioException {
        boolean resultado = repoCategoria.remove(codigo);
        if (!resultado) {
            throw new NegocioException("Categoria não encontrada ou não pode ser removida. Código: " + codigo);
        }
        return resultado;
    }

    public Categoria getCategoria(int codigo) throws NegocioException {
        Categoria categoria = repoCategoria.getCategoria(codigo);
        if (categoria == null) {
            throw new NegocioException("Categoria não encontrada. Código: " + codigo);
        }
        return categoria;
    }

    public List<Categoria> listarCategorias() {
        List<Categoria> original = repoCategoria.listarCategorias();
        List<Categoria> copia = new ArrayList<>();
        for (Categoria categoria : original) {
            copia.add(new Categoria(categoria));
        }
        return copia;
    }

    public boolean verificarNome(String nome) {
        return repoCategoria.verificarNome(nome);
    }

    public boolean atualizarNome(int codigo, String novoNome) throws NegocioException, ValidacaoException {
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new ValidacaoException("Nome não pode ser vazio");
        }
        boolean resultado = repoCategoria.atualizarNome(codigo, novoNome);
        if (!resultado) {
            throw new NegocioException("Falha ao atualizar nome da categoria");
        }
        return resultado;
    }

    public boolean atualizarDescricao(int codigo, String novaDescricao) throws NegocioException, ValidacaoException {
        if (novaDescricao == null || novaDescricao.trim().isEmpty()) {
            throw new ValidacaoException("Descrição não pode ser vazia");
        }
        boolean resultado = repoCategoria.atualizarDescricao(codigo, novaDescricao);
        if (!resultado) {
            throw new NegocioException("Falha ao atualizar descrição da categoria");
        }
        return resultado;
    }

    public int getProxCodigo() {
        return repoCategoria.getProxCodigo();
    }
}