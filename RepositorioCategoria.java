package TrabalhoAeD.dados;

import TrabalhoAeD.controle.Categoria;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCategoria {
    private static RepositorioCategoria instancia;
    private List<Categoria> categorias;
    private int proximoCodigo;

    private RepositorioCategoria() {
        this.categorias = new ArrayList<Categoria>();
        this.proximoCodigo = 1;
    }

    public static RepositorioCategoria getInstance() {
        if (instancia == null) {
            instancia = new RepositorioCategoria();
        }
        return instancia;
    }

    public boolean add(Categoria c) {
        if (c != null) {
            categorias.add(c);
            proximoCodigo++;
            return true;
        }
        return false;
    }

    public boolean remove(int codigo) {
        for (int i = 0; i < categorias.size(); i++) {
            Categoria c = categorias.get(i);
            if (c.getCodigo() == codigo) {
                RepositorioProduto repoProduto = RepositorioProduto.getInstance();
                repoProduto.removerCategoriaDosProdutos(codigo);
                
                categorias.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean verificarNome(String nome) {
        for (int i = 0; i < categorias.size(); i++) {
            Categoria c = categorias.get(i);
            if (c.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public boolean buscarPorNome(Categoria categoria) {
        for (int i = 0; i < categorias.size(); i++) {
            Categoria c = categorias.get(i);
            if (c != null && c.getNome().equalsIgnoreCase(categoria.getNome())) {
                return true;
            }
        }
        return false;
    }

    public Categoria getCategoria(int codigo) {
        for (int i = 0; i < categorias.size(); i++) {
            Categoria c = categorias.get(i);
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }

    public boolean atualizarNome(int codigo, String novoNome) {
        Categoria categoria = getCategoria(codigo);
        if (categoria != null) {
            categoria.setNome(novoNome);
            return true;
        }
        return false;
    }

    public boolean atualizarDescricao(int codigo, String novaDescricao) {
        Categoria categoria = getCategoria(codigo);
        if (categoria != null) {
            categoria.setDescricao(novaDescricao);
            return true;
        }
        return false;
    }

    public boolean remover(int codigo) {
        return categorias.remove(codigo) != null;
    }

    public List<Categoria> listarCategorias() {
        return categorias;
    }

    public int getProxCodigo() {
        return proximoCodigo;
    }
}