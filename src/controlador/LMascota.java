package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

import javax.swing.JOptionPane;

import modelo.Mascota;

public class LMascota extends Mascota {
	Mascota Mas = new Mascota();

	public void Ingresar() {
		String opc;

		try {
			FileWriter archivoTxt = new FileWriter(".\\Documentacion\\mascotas.txt", true);
			FileWriter archivoJson = new FileWriter(".\\Documentacion\\mascotas.json", true);

			do {
				String Tipo_Mascota = JOptionPane.showInputDialog(null, "Ingrese El tipo de mascota");
				Mas.setTipo(Tipo_Mascota);
				String nombre_Mascota = JOptionPane.showInputDialog(null, "Ingrese el nombre de la mascota");
				Mas.setNombre(nombre_Mascota);
				String raza_Mascota = JOptionPane.showInputDialog(null, "Ingrese la raza de la mascota");
				Mas.setRaza(raza_Mascota);
				int edad_Mascota = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la edad de la mascota"));
				Mas.setEdad(edad_Mascota);
				opc = JOptionPane.showInputDialog(null, "¿Ingresar Otra Mascota? S/N");

				// Guardar en el archivo de texto
				BufferedWriter escribirTxt = new BufferedWriter(archivoTxt);
				PrintWriter lineaTxt = new PrintWriter(escribirTxt);
				lineaTxt.append(
						Mas.getNombre() + "," + Mas.getTipo() + "," + Mas.getEdad() + "," + Mas.getRaza() + "\n");
				lineaTxt.close();

				// Guardar en el archivo JSON
				JSONObject mascotaJson = new JSONObject();
				mascotaJson.put("Nombre", Mas.getNombre());
				mascotaJson.put("Tipo", Mas.getTipo());
				mascotaJson.put("Edad", Mas.getEdad());
				mascotaJson.put("Raza", Mas.getRaza());

				BufferedWriter escribirJson = new BufferedWriter(archivoJson);
				PrintWriter lineaJson = new PrintWriter(escribirJson);
				lineaJson.append(mascotaJson.toString() + "\n");
				lineaJson.close();

			} while (opc.equalsIgnoreCase("S"));

			archivoTxt.close(); // Cerrar FileWriter después de su uso
			archivoJson.close(); // Cerrar FileWriter después de su uso

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Consultar() {

		try {
			FileReader datosLeidos = new FileReader(".\\Documentacion\\mascotas.txt");
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

	public void eliminar() {

		String nombreEliminar = JOptionPane.showInputDialog(null, "Ingrese el nombre de la mascota a eliminar");
		ArrayList<String> registrosMascotas = new ArrayList<>();

		try {
			FileReader datosLeidos = new FileReader(".\\Documentacion\\mascotas.txt");
			BufferedReader bufferdatos = new BufferedReader(datosLeidos);
			String linea = "";

			while (linea != null) {
				linea = bufferdatos.readLine();
				if (linea != null) {
					registrosMascotas.add(linea);
				}
			}
			datosLeidos.close();

			FileWriter archivo = new FileWriter(".\\Documentacion\\mascotas.txt");
			BufferedWriter escribir = new BufferedWriter(archivo);
			PrintWriter lineaMascota = new PrintWriter(escribir);
			Iterator<String> apuntadorListaMascotas = registrosMascotas.iterator();

			while (apuntadorListaMascotas.hasNext()) {
				String itemApuntador = apuntadorListaMascotas.next();
				String[] datosMascota = itemApuntador.split(",");

				// Verifica si el nombre de la mascota coincide con el nombre a eliminar
				if (!datosMascota[0].equalsIgnoreCase(nombreEliminar)) {
					lineaMascota.append(itemApuntador + "\n");
				}
			}

			lineaMascota.close();
			archivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String BuscarMascota() {
		String buscar = null;
		try (FileReader datosLeidos = new FileReader(".\\Documentacion\\mascotas.txt");
				BufferedReader bufferDatos = new BufferedReader(datosLeidos)) {

			buscar = JOptionPane.showInputDialog(null, "Ingrese el nombre de la mascota ");
			;

			ArrayList<String> escribirRegistro = new ArrayList<>();
			int encontro = 0;

			String linea;
			while ((linea = bufferDatos.readLine()) != null) {
				String[] datosMascota = linea.split(",");

				// Utilizar equals para comparar cadenas
				if (datosMascota.length > 0 && datosMascota[0].equalsIgnoreCase(buscar)) {
					encontro++;
					JOptionPane.showMessageDialog(null, "La mascota esta registrada en el sistema");
				} else {
					escribirRegistro.add(linea);
				}
			}

			// Abrir el archivo para escritura después de leer los datos
			try (FileWriter archivo = new FileWriter(".\\Documentacion\\mascotas.txt");
					BufferedWriter escribir = new BufferedWriter(archivo);
					PrintWriter lineaMascota = new PrintWriter(escribir)) {

				// Escribir los datos recopilados
				for (String line : escribirRegistro) {
					lineaMascota.println(line);
				}
			}

			if (encontro == 0) {
				JOptionPane.showMessageDialog(null, "El nombre de la mascota " + buscar + " no fue encontrado");
				;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return buscar;
	}

}
