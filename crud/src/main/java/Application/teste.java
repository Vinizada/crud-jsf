package Application;

import java.util.List;

import controller.ContatoController;
import entities.Contato;

public class teste {

	public static void main(String[] args) {
		
		ContatoController contatos = new ContatoController();
		
		contatos.criacaoDaPagina();
		
		List<Contato> lista = contatos.getListaContato();
		
		for(Contato cont : lista) {
			System.out.println(cont);
		}

	}

}
