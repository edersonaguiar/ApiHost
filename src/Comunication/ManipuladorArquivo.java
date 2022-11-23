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

	public static void leitor(String path, int line) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		
		ArrayList<String> linhas = new ArrayList<>();
        String linha = ""; 

         while ((linha=buffRead.readLine()) != null){

             linhas.add(linha);

         }
         
         System.out.print(linhas.get(line));
		
		
//		while (true) {
//			if (linha == 1) {
//				System.out.println(buffRead.readLine());
//
//
//			} else {
//				System.out.println(buffRead.);
//			} 
//				break;
//			
//		}
		
		buffRead.close();
	}

}