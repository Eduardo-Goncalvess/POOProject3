package TrabalhoAeD.dados;

import TrabalhoAeD.controle.Compra;
import TrabalhoAeD.controle.Usuario;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuario {
    private static RepositorioUsuario instancia;
    private List<Usuario> usuarios;
    private int proximoCodigo;

    private RepositorioUsuario() {
        this.usuarios = new ArrayList<Usuario>();
        this.proximoCodigo = 1;
    }

    public static RepositorioUsuario getInstance() {
        if (instancia == null) {
            instancia = new RepositorioUsuario();
        }
        return instancia;
    }

    public boolean add(Usuario u) {
        if (u != null) {
            usuarios.add(u);
            proximoCodigo++;
            return true;
        }
        return false;
    }

    public boolean remove(int codigo) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.getCodigo() == codigo) {
                usuarios.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean verificarNome(String nome) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public boolean buscarPorNome(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u != null && u.getNome().equalsIgnoreCase(usuario.getNome())) {
                return true;
            }
        }
        return false;
    }

    public Usuario getUsuario(int codigo) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.getCodigo() == codigo) {
                return u;
            }
        }
        return null;
    }

    public List<Compra> getCompras(int codigo) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.getCodigo() == codigo) {
                return u.getCompras();
            }
        }
        return null;
    }

    public boolean atualizarNome(int codigo, String novoNome) {
        Usuario usuario = getUsuario(codigo);
        if (usuario != null) {
            usuario.setNome(novoNome);
            return true;
        }
        return false;
    }

    public boolean atualizarTelefone(int codigo, int novoTelefone) {
        Usuario usuario = getUsuario(codigo);
        if (usuario != null) {
            usuario.setTelefone(novoTelefone);
            return true;
        }
        return false;
    }

    public boolean atualizarCidade(int codigo, String novaCidade) {
        Usuario usuario = getUsuario(codigo);
        if (usuario != null) {
            usuario.setCidade(novaCidade);
            return true;
        }
        return false;
    }

    public boolean remover(int codigo) {
        return usuarios.remove(codigo) != null;
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public int getProxCodigo() {
        return proximoCodigo;
    }
}
