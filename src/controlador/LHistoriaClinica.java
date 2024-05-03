package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

import modelo.HistoriaClinica;
import modelo.Mascota;

public class LHistoriaClinica extends HistoriaClinica {
	HistoriaClinica HC = new HistoriaClinica();
	Mascota Ms = new Mascota();

	public void Ingresar() {

		try {
            // Cargar las mascotas existentes desde el archivo
            FileReader datosLeidos = new FileReader(".\\Documentacion\\mascotas.txt");
            BufferedReader bufferdatos = new BufferedReader(datosLeidos);
            String linea = "";

            // Mostrar las mascotas existentes y permitir al usuario seleccionar una
            String nombreMascotaSeleccionada = seleccionarMascota(bufferdatos);

            // Crear un objeto Mascota y asignarle el nombre
            Ms.setNombre(nombreMascotaSeleccionada);

            // Asignar la descripción de la historia clínica
            String descripcionHistoriaClinica = JOptionPane.showInputDialog(null, "Digite la historia clínica:");

            // Asignar la descripción a la historia clínica
            HC.setDescripcion(descripcionHistoriaClinica);

            // Asignar la historia clínica a la mascota
            Ms.setHc(HC);

            // Escribir en el archivo de Historias Clínicas
            FileWriter archivo = new FileWriter(".\\Documentacion\\HistoriaClinica.txt", true);
            BufferedWriter escribir = new BufferedWriter(archivo);
            escribir.write("Nombre de la Mascota: " + Ms.getNombre() + "\n");
            escribir.write("Descripción de la Historia Clínica: " + HC.getDescripcion() + "\n\n");
            escribir.close();

            JOptionPane.showMessageDialog(null,
                    "Historia Clínica ingresada con éxito para la mascota: " + Ms.getNombre());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String seleccionarMascota(BufferedReader bufferdatos) throws IOException {
        // Mostrar las mascotas existentes y permitir al usuario seleccionar una

        String linea = JOptionPane.showInputDialog(null,"Mascotas existentes:");
        while ((linea = bufferdatos.readLine()) != null) {
            System.out.println(linea);
        }

        return JOptionPane.showInputDialog(null,"Ingrese el nombre de la mascota a la que desea agregar la historia clínica:");
    }


	
}