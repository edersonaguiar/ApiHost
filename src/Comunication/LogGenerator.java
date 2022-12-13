package Comunication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogGenerator {

	public static void generateLog(String message) throws IOException {

		Path path = Paths.get("C:/Temp/Teste/logs/");

		if (!Files.exists(path)) {

			Files.createDirectory(path);

		}

		File log = new File("C:/Temp/Teste/logs.txt");

		if (!log.exists()) {

			log.createNewFile();

		}

		Date dataHoraAtual = new Date();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
		String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

		FileWriter fw = new FileWriter(log, true);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(data + "," + hora + ":" + message);
		bw.newLine();

		bw.close();
		fw.close();

	}
}
