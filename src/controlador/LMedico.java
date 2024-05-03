package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.JSONObject;

import modelo.Usuario;

public class LMedico {

	public void guardarEnArchivo(Usuario usuario) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(".\\Documentacion\\Medico.txt", true))) {
			FileWriter archivoJson = new FileWriter(".\\Documentacion\\medicos.json", true);
			// Escribe la información del usuario en el archivo
			writer.write(usuario.toString());
			writer.newLine(); // Agrega una nueva línea para el próximo usuario
			// Guardar en el archivo JSON
			JSONObject medicoJson = new JSONObject();
			medicoJson.put("Medico: ", usuario.getNombre());
			
			BufferedWriter escribirJson = new BufferedWriter(archivoJson);
			PrintWriter lineaJson = new PrintWriter(escribirJson);
			lineaJson.append(medicoJson.toString() + "\n");
			lineaJson.close();

			
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al guardar en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void Consultar() {

		try {
			FileReader datosLeidos = new FileReader(".\\Documentacion\\Medico.txt");
			BufferedReader bufferdatos = new BufferedReader(datosLeidos);
			String linea = "";
			StringBuilder mensajes = new StringBuilder();

			while (linea != null) {
				linea = bufferdatos.readLine();
				if (linea != null) {
					mensajes.append(linea).append("\n");
				}
			}

			if (mensajes.length() > 0) {
				JOptionPane.showMessageDialog(null, mensajes.toString(), "Mensajes", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "No hay registros por mostrar!!", "Mensajes",
						JOptionPane.INFORMATION_MESSAGE);
			}

			bufferdatos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}