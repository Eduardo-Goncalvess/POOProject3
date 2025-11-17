package TrabalhoAeD.dados;

import TrabalhoAeD.controle.Compra;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCompra {
    private static RepositorioCompra instancia;
    private List<Compra> compras;
    private int proximoCodigo;

    private RepositorioCompra() {
        this.compras = new ArrayList<Compra>();
        this.proximoCodigo = 1;
    }

    public static RepositorioCompra getInstance() {
        if (instancia == null) {
            instancia = new RepositorioCompra();
        }
        return instancia;
    }

    public boolean add(Compra compra) {
        if (compra != null) {
            compras.add(compra);
            proximoCodigo++;
            return true;
        }
        return false;
    }

    public Compra getCompra(int codigo) {
        for (int i = 0; i < compras.size(); i++) {
            Compra c = compras.get(i);
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }

    /*public List<Compra> getComprasPorUsuario(int codigoUsuario) {
        List<Compra> comprasUsuario = new ArrayList<>();
        for (int i = 0; i < compras.size(); i++) {
            Compra c = compras.get(i);
            if (c.getCodigoUsuario() == codigoUsuario) {
                comprasUsuario.add(c);
            }
        }
        return comprasUsuario;
    }*/

    public int getProxCodigo() {
        return proximoCodigo;
    }

    public List<Compra> listarCompras() {
        List<Compra> copia = new ArrayList<>();
        for (Compra compra : compras) {
            copia.add(new Compra(compra));
        }
        return copia;
    }
}