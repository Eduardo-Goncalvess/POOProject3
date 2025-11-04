package TrabalhoAeD.controle;

import TrabalhoAeD.dados.RepositorioCategoria;
import java.util.ArrayList;
import java.util.List;

public class ControleCategoria {
    private RepositorioCategoria repoCategoria;

    public ControleCategoria() {
        this.repoCategoria = RepositorioCategoria.getInstance();
    }

    public Categoria getInstance(int codigo, String nome, String descricao) {
        return Categoria.getInstance(codigo, nome, descricao);
    }

    public boolean add(Categoria a) {
        if (repoCategoria.buscarPorNome(a)) {
            return false;
        }
        return repoCategoria.add(a);
    }

    public boolean remove(int codigo) {
        return repoCategoria.remove(codigo);
    }

    public Categoria getCategoria(int codigo) {
        return repoCategoria.getCategoria(codigo);
    }

    public boolean excluirCategoria(int codigo) {
        return repoCategoria.remover(codigo);
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

    public boolean atualizarNome(int codigo, String novoNome) {
        return repoCategoria.atualizarNome(codigo, novoNome);
    }

    public boolean atualizarDescricao(int codigo, String novaDescricao) {
        return repoCategoria.atualizarDescricao(codigo, novaDescricao);
    }

    public int getProxCodigo() {
        return repoCategoria.getProxCodigo();
    }
}