package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controlador.UserManager;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField JpassContraseña;
	private JTextField txtUser;

//clase de arranque, que hace que sea posible ver el frame
	public void run() {
		try {
			Login frame = new Login();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */

	public Login() {
		// todos los aspectos visuales de la ventana de logueo ( campos de texto,
		// botones, etc)
		setUndecorated(true); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		setLocationRelativeTo(null);

		
		JLabel lblNewLabel = new JLabel("Ventana De Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(25, 10, 374, 56);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(25, 121, 86, 18);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Contraseña:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(25, 149, 124, 19);
		contentPane.add(lblNewLabel_2);

		JpassContraseña = new JPasswordField();
		JpassContraseña.setBounds(125, 149, 96, 19);
		contentPane.add(JpassContraseña);

		txtUser = new JTextField();
		txtUser.setBounds(125, 124, 96, 19);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

// Implementacion del funcionamiento del boton ingresar, donde si se validan todos los campos son correctos con la informacion, entonces se habre la pagina principal
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUser.getText().trim();
				char[] clave = JpassContraseña.getPassword();
				String password = new String(clave).trim();

				UserManager userManager = UserManager.getInstance();
				String rol = userManager.obtenerRol(username, password);

				if (rol != null) {
				    String rutaFotoPerfil = userManager.obtenerRutaFotoPerfil(username);
				    JOptionPane.showMessageDialog(null, "¡Bienvenido al Sistema!", "INGRESASTE!!", JOptionPane.INFORMATION_MESSAGE);
				    
				    Principal P = new Principal(rol, username, rutaFotoPerfil);
				 		    
				    dispose();
				    P.setVisible(true);
				} else {
				    JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnIngresar.setBounds(114, 180, 96, 21);
		contentPane.add(btnIngresar);
//Implementacion del boton salir, que si se presiona se cierra la ventana de logueo
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(170, 217, 85, 21);
		contentPane.add(btnSalir);

		JFrame registro = this; // la instancia "This" se esta usando debido a que queremos hacer modales las
								// ventanas, asi que para referirnos a los JFrames les asignamos this cuando
								// tengamos que llamarlos en una clase
		// implementacion del boton registrar, si se presiona nos lleva a la ventana de
		// registros
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro btnRe = new Registro(registro);
				btnRe.setVisible(true);
			}
		});
		btnRegistrar.setBounds(206, 180, 103, 21);
		contentPane.add(btnRegistrar);

	}
}