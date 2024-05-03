package vista;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controlador.LMedico;
import controlador.UserManager;
import modelo.Usuario;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegistroMedico extends JDialog{
	private JTextField txtName;
	private JTextField txtID;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private JLabel lblFoto;
	private JButton btnCargarFoto;
	private String rutaFotoPerfil;
	private ImageIcon foto;
	private JComboBox<String> cmbRol;

	public void cargarFoto() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png", "gif");
		fileChooser.setFileFilter(filter);

		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			String filePath = fileChooser.getSelectedFile().getAbsolutePath();
			foto = new ImageIcon(filePath);
			// Guarda la ruta de la imagen en la variable de clase
			rutaFotoPerfil = filePath;
		}
	}

	public RegistroMedico(JFrame parent) {
		super(parent, "Registro de Vendedor", true);
		setUndecorated(true); 
		setTitle("Registro de Vendedor");
		setBounds(100, 100, 493, 251);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(parent);

		JLabel lblTitulo = new JLabel("Registro de Medico");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(98, 10, 200, 20);
		getContentPane().add(lblTitulo);

		JLabel lblName = new JLabel("Nombre:");
		lblName.setBounds(50, 31, 150, 20);
		getContentPane().add(lblName);

		txtName = new JTextField();
		txtName.setBounds(200, 32, 150, 20);
		getContentPane().add(txtName);

		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(50, 61, 150, 20);
		getContentPane().add(lblID);

		txtID = new JTextField();
		txtID.setBounds(200, 62, 150, 20);
		getContentPane().add(txtID);

		JLabel lblUsername = new JLabel("Nombre de Usuario:");
		lblUsername.setBounds(50, 91, 150, 20);
		getContentPane().add(lblUsername);

		txtUsername = new JTextField();
		txtUsername.setBounds(200, 92, 150, 20);
		getContentPane().add(txtUsername);

		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setBounds(50, 121, 150, 20);
		getContentPane().add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(200, 122, 150, 20);
		getContentPane().add(passwordField);

		lblFoto = new JLabel("Foto de Perfil:");
		lblFoto.setBounds(40, 151, 150, 20);
		getContentPane().add(lblFoto);

		btnCargarFoto = new JButton("Cargar Foto");
		btnCargarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarFoto();
			}
		});
		btnCargarFoto.setBounds(200, 151, 120, 20);
		getContentPane().add(btnCargarFoto);

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
				String username = txtUsername.getText().trim();
				char[] clave = passwordField.getPassword();
				String password = new String(clave).trim();
				//String rol = "Medico";		//cmbRol.getSelectedItem().toString();

				if (username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ingresa un nombre de usuario y contraseña válidos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Usuario nuevoUsuario = new Usuario(name, id, username, password, "Medico", rutaFotoPerfil);
					UserManager userManager = UserManager.getInstance();
					boolean registroExitoso = userManager.agregarUsuario(nuevoUsuario);

					if (registroExitoso) {
						JOptionPane.showMessageDialog(null, "Registro exitoso.", "Registro",
								JOptionPane.INFORMATION_MESSAGE);
						System.out.println("Ruta de la foto de perfil: " + rutaFotoPerfil);
						new LMedico().guardarEnArchivo(nuevoUsuario);
						userManager.guardarUsuarios();
						dispose(); // Cierra la ventana de registro
					} else {
						JOptionPane.showMessageDialog(null, "El nombre de usuario ya está en uso.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		btnRegistrar.setBounds(200, 181, 120, 30);
		getContentPane().add(btnRegistrar);
	}

}