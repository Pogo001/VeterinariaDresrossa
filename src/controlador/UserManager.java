package controlador;

import java.io.FileInputStream;		
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import modelo.Usuario;

public class UserManager {
    private static UserManager instance = null;
    private List<Usuario> usuarios = new ArrayList<>();
	private ImageIcon foto;
	private String rutaFotoPerfil;  // Declarar la variable a nivel de clase

    private UserManager() {
        // Se cargan los usuarios registrados en los archivos de texto
        cargarUsuarios();
    }
    //Metodo para guardar los usuarios registrados en la ventana de registros ( Se guardan serializados por seguridad)
    public void guardarUsuarios() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(".\\Documentacion\\usuarios.dat"))) {
            out.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked") //suprimir advertencia que hace el compilador
    //Se cargan los usuarios guardados en los archivos de texto, los cuales estan siendo guardados en una lista 
    public void cargarUsuarios() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(".\\Documentacion\\usuarios.dat"))) {
            usuarios = (List<Usuario>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            usuarios = new ArrayList<>();
        }
    }
    
    public String obtenerRutaFotoPerfil(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equalsIgnoreCase(username)) {
                return usuario.getRutaFotoPerfil();
            }
        }
        return null; // Usuario no encontrado o sin foto de perfil
    }
    

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }
//Se agragan los usuarios a la lista que sera guardada en los archivos de texto
    public boolean agregarUsuario(Usuario usuario) {
        // Verifica si el usuario ya existe en la lista
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(usuario.getUsername())) {
                return false; // El nombre de usuario ya está en uso
            }
        }
        usuarios.add(usuario);
        return true; // Registro exitoso
    }
// Metodo para obtener el rol seleccionado a la hora de hacer el respectivo registro
    public String obtenerRol(String username, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equalsIgnoreCase(username) && usuario.getPassword().equals(password)) {
                return usuario.getRol();
            }
        }
        return null; // Usuario no encontrado
    }
}
