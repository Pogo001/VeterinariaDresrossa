package vista;

import javax.swing.*;


import controlador.UserManager;
import modelo.Usuario;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegistroDueño extends JDialog {
	private JTextField txtName;
	private JTextField txtID;
	private String rutaFotoPerfil;


	public RegistroDueño(JFrame parent) {
		super(parent, "Registro de Vendedor", true);
		setUndecorated(true); 
		setTitle("Registro de Vendedor");
		setBounds(100, 100, 493, 251);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(parent);

		JLabel lblTitulo = new JLabel("Registro de Dueño");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(98, 10, 200, 20);
		getContentPane().add(lblTitulo);

		JLabel lblName = new JLabel("Nombre:");
		lblName.setBounds(38, 69, 150, 20);
		getContentPane().add(lblName);

		txtName = new JTextField();
		txtName.setBounds(200, 70, 150, 20);
		getContentPane().add(txtName);

	JLabel lblID = new JLabel("ID:");
		lblID.setBounds(50, 117, 150, 20);
		getContentPane().add(lblID);

		txtID = new JTextField();
		txtID.setBounds(200, 118, 150, 20);
		getContentPane().add(txtID);


		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(170, 217, 85, 21);
		getContentPane().add(btnSalir);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText().trim();
				int id = Integer.parseInt(txtID.getText().trim());
				String username = name;
//				char[] clave = null;
				String password =Integer.toString(id);
				String rol = "Dueño";		//cmbRol.getSelectedItem().toString();

				if (name.isEmpty() || id == 0) {
					JOptionPane.showMessageDialog(null, "Ingresa un nombre de usuario y contraseña válidos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Usuario nuevoUsuario = new Usuario(name, id, username, password, rol, rutaFotoPerfil);
					UserManager userManager = UserManager.getInstance();
					boolean registroExitoso = userManager.agregarUsuario(nuevoUsuario);

					if (registroExitoso) {
						JOptionPane.showMessageDialog(null, "Registro exitoso.", "Registro",
								JOptionPane.INFORMATION_MESSAGE);
//						System.out.println("Ruta de la foto de perfil: " + rutaFotoPerfil);
						guardarEnArchivo(nuevoUsuario);
						userManager.guardarUsuarios();
						dispose(); // Cierra la ventana de registro
					} else {
						JOptionPane.showMessageDialog(null, "El nombre de usuario ya está en uso.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		btnRegistrar.setBounds(135, 147, 120, 30);
		getContentPane().add(btnRegistrar);
	}
	private void guardarEnArchivo(Usuario usuario) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(".\\Documentacion\\Dueño.txt", true))) {
	        // Escribe la información del usuario en el archivo
	        writer.write(usuario.toString());
	        writer.newLine(); // Agrega una nueva línea para el próximo usuario
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al guardar en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
}