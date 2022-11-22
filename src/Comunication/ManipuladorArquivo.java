package Comunication;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ManipuladorArquivo {

	public static void leitor(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";
		ArrayList result = new ArrayList();
		while (true) {
			if (linha != null) {

				System.out.println(linha);

			} else
				break;
			linha = buffRead.readLine();
			
		}
		
		buffRead.close();
	}

}