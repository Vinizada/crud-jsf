package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.SelectEvent;

import entities.Contato;
import services.ContatoService;


@Named(value ="contatos")
@ViewScoped
public class ContatoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ContatoService contatoService;
	
	private String evento;
	
    private Contato contato;
	private List<Contato> listaContato;
	
	@PostConstruct
	public void criacaoDaPagina() {
		
		this.novo();
		
		this.evento = "Novo Contato";
		
	}
	
	public void novo() {
		
		try {
			
			this.contato = new Contato();
			
			this.contatoService = new ContatoService();
			
			this.listaContato = contatoService.findAll();
			
		}catch (Exception e) {

			e.getMessage();
		}
		
	}
	
	public void criaContato() {
		
		
		contatoService.saveOrUpdate(this.contato);
		this.novo();
		
		
	}
	
	public void excluir(Contato contato) {
		
		try {
			contatoService.remove(contato);
			
			this.novo();
			
		}catch (Exception e) {
			e.getMessage();
		}
		
	}

	public void editar(SelectEvent event) {
		
		contato = (Contato) event.getObject();
		
		this.setEvento("Editar Contato");
		
		contatoService.saveOrUpdate(contato);
	}
	
	public List<Contato> getListaContato() {
		return listaContato;
	}

	public void setListaContato(List<Contato> listaContato) {
		this.listaContato = listaContato;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}


}
