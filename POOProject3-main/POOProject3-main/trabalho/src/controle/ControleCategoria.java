package TrabalhoAeD.controle;

import dados.RepositorioCategoria;
import modelo.Categoria;
import java.util.ArrayList;
import java.util.List;

public class ControleCategoria {
    private RepositorioCategoria repoCategoria;

    public ControleCategoria() {
        this.repoCategoria = RepositorioCategoria.getInstancia();
    }

    public boolean cadastrarCategoria(Categoria categoria) {
        return repoCategoria.inserir(categoria);
    }

    public Categoria buscarPorNome(String nome) {
        return repoCategoria.buscarPorNome(nome);
    }

    public Categoria buscarPorCodigo(int codigo) {
        return repoCategoria.buscar(codigo);
    }

    public boolean excluirCategoria(int codigo) {
        return repoCategoria.remover(codigo);
    }

    public boolean atualizarCategoria(Categoria categoria) {
        if (categoria == null) return false;
        return repoCategoria.atualizar(categoria);
    }

    public List<Categoria> listarCategorias() {
        Categoria[] categoriasArray = repoCategoria.listarTodos();
        List<Categoria> categoriasList = new ArrayList<>();
        for (Categoria categoria : categoriasArray) {
            if (categoria != null) {
                categoriasList.add(categoria);
            }
        }
        return categoriasList;
    }

    public boolean verificarCodigo(int codigo) {
        return repoCategoria.buscar(codigo) != null;
    }

    public boolean verificarNome(String nome) {
        return repoCategoria.buscarPorNome(nome) != null;
    }

    public int getProxCodigo() {
        return repoCategoria.getProxCodigo();
    }
}