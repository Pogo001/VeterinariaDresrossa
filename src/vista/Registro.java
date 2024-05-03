
package vista;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controlador.LAdmin;
import controlador.LMedico;
import controlador.LVendedor;
import controlador.UserManager;
import modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registro extends JDialog {
	private JTextField txtName;// campo para el nombre
	private JTextField txtID;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private JComboBox<String> cmbRol;
	private List<Usuario> listaDeUsuarios;
	private JLabel lblFoto;
	private JButton btnCargarFoto;
	private ImageIcon foto;
	private String rutaFotoPerfil;  // Declarar la variable a nivel de clase
	Usuario nuevoUsuario;
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

	public Registro(JFrame parent) {

		
		super(parent, "Registro de Usuario", true); // El 'true' hace que la ventana sea modal
		setUndecorated(true); 
		setTitle("Registro de Usuario");
		setBounds(100, 100, 493, 288);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(parent);

		listaDeUsuarios = new ArrayList<>();
		JLabel lblTitulo = new JLabel("Registro de Usuario");
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
		lblID.setBounds(50, 151, 150, 20);
		getContentPane().add(lblID);

		txtID = new JTextField();
		txtID.setBounds(200, 152, 150, 20);
		getContentPane().add(txtID);
		JLabel lblUsername = new JLabel("Nombre de Usuario:");
		lblUsername.setBounds(50, 61, 150, 20);
		getContentPane().add(lblUsername);

		txtUsername = new JTextField();
		txtUsername.setBounds(200, 62, 150, 20);
		getContentPane().add(txtUsername);

		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setBounds(50, 91, 150, 20);
		getContentPane().add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(200, 92, 150, 20);
		getContentPane().add(passwordField);

		JLabel lblRol = new JLabel("Rol:");
		lblRol.setBounds(50, 121, 150, 20);
		getContentPane().add(lblRol);



		lblFoto = new JLabel("Foto de Perfil:");
		lblFoto.setBounds(40, 181, 150, 20);
		getContentPane().add(lblFoto);

		btnCargarFoto = new JButton("Cargar Foto");
		btnCargarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarFoto();
			}
		});
		btnCargarFoto.setBounds(277, 182, 87, 20);
		getContentPane().add(btnCargarFoto);

		String[] roles = { "Admin", "Médico", "Vendedor" };
		cmbRol = new JComboBox<>(roles);
		cmbRol.setBounds(200, 122, 150, 20);
		getContentPane().add(cmbRol);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  // Obtén la ruta de la foto del campo de la clase registro
				String rutaFotoPerfil = Registro.this.rutaFotoPerfil;
				String name = txtName.getText().trim();
				int id = Integer.parseInt(txtID.getText().trim());
				String username = txtUsername.getText().trim();
				char[] clave = passwordField.getPassword();
				String password = new String(clave).trim();
				String rol = cmbRol.getSelectedItem().toString();
				

				if (username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ingresa un nombre de usuario y contraseña válidos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					 nuevoUsuario = new Usuario(name, id, username, password, rol, rutaFotoPerfil);
					UserManager userManager = UserManager.getInstance();
					boolean registroExitoso = userManager.agregarUsuario(nuevoUsuario);

					if (registroExitoso) {
						JOptionPane.showMessageDialog(null, "Registro exitoso.", "Registro",
								JOptionPane.INFORMATION_MESSAGE);
						System.out.println("Ruta de la foto de perfil: " + rutaFotoPerfil);
						userManager.guardarUsuarios();
						dispose(); // Cierra la ventana de registro
					} else {
						JOptionPane.showMessageDialog(null, "El nombre de usuario ya está en uso.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				if(rol.equals("Medico")) {
					new LMedico().guardarEnArchivo(nuevoUsuario);
					
				}else if(rol.equals("Vendedor")) {
					new LVendedor().guardarEnArchivo(nuevoUsuario);
				}else {
					new LAdmin().guardarEnArchivo(nuevoUsuario);
					
				}
				
				
			}
		});

		btnRegistrar.setBounds(125, 212, 100, 30);
		getContentPane().add(btnRegistrar);
	}

}