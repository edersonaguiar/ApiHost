package application;

import java.util.List;

import javafx.collections.ObservableList;

public class Modelo {

	private int id;
	private String nome;
	public static int idModelBDN;
	
	
	
	
	public static int getIdModelBDN() {
		return idModelBDN;
	}

	public static void setIdModelBDN(int idModelBDN) {
		Modelo.idModelBDN = idModelBDN;
	}

	
	
	//private List<Modelo> modelos;
	//private ObservableList<Modelo> obsModelos;

	public Modelo(String nome) {
		// TODO Auto-generated constructor stub

		this.nome = nome;
		
	}

	public Modelo() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

//	public List<Modelo> getModelos() {
//
//		return modelos;
//	}
//
//	public void setModelos(List<Modelo> modelos) {
//		this.modelos = modelos;
//	}

	@Override
	public String toString() {
		return getNome();
	}



}
