package TrabalhoAeD;

import TrabalhoAeD.controle.CadastroException;
import TrabalhoAeD.controle.NegocioException;
import TrabalhoAeD.controle.ValidacaoException;
import TrabalhoAeD.ui.UIPrincipal;


public class Main {
	public static void main(String[] args){
		 try {
			UIPrincipal uiPrincipal = new UIPrincipal();
            uiPrincipal.iniciar();
        } catch (ValidacaoException e) {
            System.err.println("❌ Erro de validação ao inicializar sistema: " + e.getMessage());
            System.exit(1);
        } catch (CadastroException e) {
            System.err.println("❌ Erro de cadastro ao inicializar sistema: " + e.getMessage());
            System.exit(1);
        } catch (NegocioException e) {
            System.err.println("❌ Erro de negócio ao inicializar sistema: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("❌ Erro inesperado ao inicializar sistema: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
	}
}
