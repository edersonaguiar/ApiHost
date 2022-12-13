package Comunication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ManipuladorArquivo {

	public static String leitor(String path, int line) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));

		ArrayList<String> linhas = new ArrayList<>();
		String linha = "";

		while ((linha = buffRead.readLine()) != null) {

			linhas.add(linha);

		}

		String paramentro = linhas.get(line);

		buffRead.close();

		return paramentro;
	}

}