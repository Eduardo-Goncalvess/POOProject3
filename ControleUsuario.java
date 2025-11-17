package TrabalhoAeD.controle;

import TrabalhoAeD.dados.RepositorioUsuario;
import java.util.ArrayList;
import java.util.List;

public class ControleUsuario {
    private RepositorioUsuario repoUsuario;

    public ControleUsuario() {
        this.repoUsuario = RepositorioUsuario.getInstance();
    }

    public Usuario getInstance(int codigo, String nome, int telefone, String cidade) throws ValidacaoException {
        Usuario usuario = Usuario.getInstance(codigo, nome, telefone, cidade);
        if (usuario == null) {
            throw new ValidacaoException("Dados inválidos para criar usuário");
        }
        return usuario;
    }

    public boolean add(Usuario a) throws CadastroException, ValidacaoException {
        if (a == null) {
            throw new ValidacaoException("Usuário não pode ser nulo");
        }
        if (repoUsuario.buscarPorNome(a)) {
            throw new CadastroException("Já existe um usuário com este nome: " + a.getNome());
        }
        boolean resultado = repoUsuario.add(a);
        if (!resultado) {
            throw new CadastroException("Falha ao cadastrar usuário");
        }
        return resultado;
    }

    public boolean remove(int codigo) throws NegocioException {
        boolean resultado = repoUsuario.remove(codigo);
        if (!resultado) {
            throw new NegocioException("Usuário não encontrado ou não pode ser removido. Código: " + codigo);
        }
        return resultado;
    }

    public Usuario getUsuario(int codigo) throws NegocioException {
        Usuario usuario = repoUsuario.getUsuario(codigo);
        if (usuario == null) {
            throw new NegocioException("Usuário não encontrado. Código: " + codigo);
        }
        return usuario;
    }

    public boolean atualizarNome(int codigo, String novoNome) throws NegocioException, ValidacaoException {
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new ValidacaoException("Nome não pode ser vazio");
        }
        boolean resultado = repoUsuario.atualizarNome(codigo, novoNome);
        if (!resultado) {
            throw new NegocioException("Falha ao atualizar nome do usuário");
        }
        return resultado;
    }

    public boolean atualizarTelefone(int codigo, int novoTelefone) throws NegocioException, ValidacaoException {
        if (novoTelefone < 1000 || novoTelefone > 9999) {
            throw new ValidacaoException("Telefone deve ter 4 dígitos");
        }
        boolean resultado = repoUsuario.atualizarTelefone(codigo, novoTelefone);
        if (!resultado) {
            throw new NegocioException("Falha ao atualizar telefone do usuário");
        }
        return resultado;
    }

    public boolean atualizarCidade(int codigo, String novaCidade) throws NegocioException, ValidacaoException {
        if (novaCidade == null || novaCidade.trim().isEmpty()) {
            throw new ValidacaoException("Cidade não pode ser vazia");
        }
        boolean resultado = repoUsuario.atualizarCidade(codigo, novaCidade);
        if (!resultado) {
            throw new NegocioException("Falha ao atualizar cidade do usuário");
        }
        return resultado;
    }

    public boolean pagarTodasPendencias(int codigoUsuario) throws NegocioException {
        Usuario usuario = repoUsuario.getUsuario(codigoUsuario);
        if (!usuario.pagarComprasPendentes()) {
            throw new NegocioException("Falha ao pagar pendências do usuário");
        }
        return true;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> original = repoUsuario.listarUsuarios();
        /*List<Usuario> copia = new ArrayList<>();
        for (Usuario usuario : original) {
            copia.add(new Usuario(usuario));
        }*/
        return original;
    }

    public boolean verificarNome(String nome) {
        return repoUsuario.verificarNome(nome);
    }

    public int getProxCodigo() {
        return repoUsuario.getProxCodigo();
    }

    public List<Compra> getCompras(int codigoUsuario) {
        return repoUsuario.getCompras(codigoUsuario);
    }
}