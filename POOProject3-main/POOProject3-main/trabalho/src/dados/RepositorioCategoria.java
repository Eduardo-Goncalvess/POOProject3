package TrabalhoAeD.controle;

import modelo.Categoria;
import java.util.HashMap;

public class RepositorioCategoria {
    private static RepositorioCategoria instancia;
    private HashMap<Integer, Categoria> categorias;
    private int proximoCodigo;

    private RepositorioCategoria() {
        this.categorias = new HashMap<>();
        this.proximoCodigo = 1;
    }

    public static synchronized RepositorioCategoria getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioCategoria();
        }
        return instancia;
    }

    public boolean inserir(Categoria categoria) {
        if (categoria == null || categoria.getNome() == null || 
            categoria.getNome().trim().isEmpty() || 
            buscarPorNome(categoria.getNome()) != null) {
            return false;
        }
        
        categoria.setCodigo(proximoCodigo++);
        categorias.put(categoria.getCodigo(), categoria);
        return true;
    }

    public Categoria buscar(int codigo) {
        return categorias.get(codigo);
    }

    public Categoria buscarPorNome(String nome) {
        for (Categoria categoria : categorias.values()) {
            if (categoria.getNome().equalsIgnoreCase(nome)) {
                return categoria;
            }
        }
        return null;
    }

    public boolean remover(int codigo) {
        return categorias.remove(codigo) != null;
    }

    public boolean atualizar(Categoria categoria) {
        if (categoria == null || !categorias.containsKey(categoria.getCodigo())) {
            return false;
        }
        categorias.put(categoria.getCodigo(), categoria);
        return true;
    }

    public Categoria[] listarTodos() {
        return categorias.values().toArray(new Categoria[0]);
    }

    public int getProxCodigo() {
        return proximoCodigo;
    }
}