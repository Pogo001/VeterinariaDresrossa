package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import modelo.Usuario;

public class LAdmin {
	
	public void guardarEnArchivo(Usuario usuario) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(".\\Documentacion\\Admin.txt", true))) {
	        // Escribe la información del usuario en el archivo
	        writer.write(usuario.toString());
	        writer.newLine(); // Agrega una nueva línea para el próximo usuario
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al guardar en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	public void Consultar() {

		try {
			FileReader datosLeidos = new FileReader(".\\Documentacion\\Admin.txt");
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
