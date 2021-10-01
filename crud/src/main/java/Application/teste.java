package Application;

import java.util.List;

import controller.ContatoController;
import entities.Contato;
import services.ContatoService;

public class teste {

	public static void main(String[] args) throws Exception {
		
		ContatoController controller = new ContatoController();
		
		controller.criacaoDaPagina();
		
		ContatoService service = new ContatoService();
		
		List<Contato> contatos = controller.getListaContato();
		
		
		for(Contato cont : contatos) {
			System.out.println(cont);
		}
		
	}

}
