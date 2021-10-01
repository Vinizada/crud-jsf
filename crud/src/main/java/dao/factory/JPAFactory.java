package dao.factory;


import dao.jpa.ContatoDAOJPA;

public class JPAFactory {
	
	public static ContatoDAOJPA createContatoJPA() {
		
		return new ContatoDAOJPA();
	}

}
