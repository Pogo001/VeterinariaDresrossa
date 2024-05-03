package vista;

import java.awt.event.ActionEvent;	
import java.awt.event.ActionListener;
import  controlador.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LMascota;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;


public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String rol;
	private String username; // Agrega el nombre de usuario
	private String rutaFotoPerfil; // Agrega la ruta de la foto de perfil
	private JLabel lblFotoPerfil; //Agrega un label para la foto de perfil
	private ImageIcon fotoPerfil; //Esto lo vamos a usar para poder poner la imagen como un icono, es decir, para que sin importar el tamaño nosotros podamos verla pequeña

	/**
	 * Launch the application.
	 */

	public void run() {
		try {
			Principal frame = new Principal(rol, username, rutaFotoPerfil); //llamamos la clase principal como un objeto frame para que podamos verlo como una ventana
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	
//	Nuestro constructor para la clase principal
	public Principal(String rol, String nombreUsuario, String rutaFotoPerfil) {

		this.rol = rol;
		this.username = nombreUsuario;
		this.rutaFotoPerfil = rutaFotoPerfil;

//toda la configuracion del frame
		setUndecorated(true); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setBounds(100, 100, 750, 450);
		setContentPane(contentPane);
		contentPane.setLayout(null);
//para que salga en el centro de la pantalla
		 setLocationRelativeTo(null);
		 

		 //creamos la barra de menu donde podremos seleccionar las opciones 
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
//Esto es para crear en el menu, un apartado para poner la foto de perfil, por eso, se allade al contentpane
		lblFotoPerfil = new JLabel();
		lblFotoPerfil.setBounds(80, 10, 50, 50);
		contentPane.add(lblFotoPerfil);
//validacion de que la foto de perfil exista 
		if (rutaFotoPerfil != null && !rutaFotoPerfil.isEmpty()) {
			fotoPerfil = new ImageIcon(rutaFotoPerfil);
			lblFotoPerfil.setIcon(fotoPerfil); // asignamos la foto de perfil como icono para que no sea muy grande cuando querramos verla en el menu
		}
		
		//empezamos a poner las opciones en el menu, cada aparado es visible y para poder ver las opciones tenemos que pasar el raton por dichas opciones
        JMenu menuPerfil = new JMenu("Informacion Personal");
        menuBar.add(menuPerfil);
        //item que esta dentro de la informacion personal, tiene su respectiva logica para que cuando presionemos ver foto de perfil, podamos ver lo que guardamos cuando registramos un user
        JMenuItem verFoto = new JMenuItem("Ver Foto de Perfil");
		verFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fotoPerfil != null) {
					// Escalar la imagen a un tamaño específico (por ejemplo, 200x200 píxeles)
					Image scaledImage = fotoPerfil.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);

					// Crear un nuevo ImageIcon con la imagen escalada
					ImageIcon scaledIcon = new ImageIcon(scaledImage);

					// Mostrar la imagen en un JOptionPane
					JOptionPane.showMessageDialog(null, scaledIcon, "Foto de Perfil", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "No hay foto de perfil disponible.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		menuPerfil.add(verFoto);

//otra opcion dentro del menu bar
		JMenu ControlPersonal = new JMenu("Control Personal");
		menuBar.add(ControlPersonal);
//item del control de personal
		JMenu mnMedico = new JMenu("Medico");
		ControlPersonal.add(mnMedico);
//creamos un objeto Frame en busqueda de usar las funciones anonimas qqque nos permiten hacer las ventanas modales 
		JFrame self = this;
	//en las iocioones de medico, tenemos el apartado para ingresar un medico	
		JMenuItem ING_medico = new JMenuItem("Ingresar");
		//creamos el validador de que ponga a funcionar el boton ingresar
		ActionListener ING_med = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//creamos un objeto que muestre la vista para registrar un medico
				RegistroMedico ing_med = new RegistroMedico(self); // 'this' hace referencia al JFrame principal
				ing_med.setModal(true);
				ing_med.setVisible(true);

			}
		};
		//el objeto se añade al action listener
		ING_medico.addActionListener(ING_med);
		//y la funcion se añade al menu
		mnMedico.add(ING_medico);

		JMenuItem MOS_medico = new JMenuItem("Mostrar");
		ActionListener MOS_medic = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//creamos un objeto que muestre la vista para registrar un medico
				LMedico mos_medic = new LMedico() ;// 'this' hace referencia al JFrame principal
				mos_medic.Consultar();

			}
		};
		//el objeto se añade al action listener
		MOS_medico.addActionListener(MOS_medic);
		mnMedico.add(MOS_medico);

		JMenuItem ELM_medico = new JMenuItem("Eliminar");
		mnMedico.add(ELM_medico);

		JMenu Menu_vendedor = new JMenu("Vendedor");
		ControlPersonal.add(Menu_vendedor);

		JMenuItem ING_vendedor = new JMenuItem("Ingresar");
		ActionListener ING_vend = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistroVendedor ing_vend = new RegistroVendedor(self); // 'this' hace referencia al JFrame principal
				ing_vend.setModal(true);
				ing_vend.setVisible(true);

			}
		};
		ING_vendedor.addActionListener(ING_vend);
		Menu_vendedor.add(ING_vendedor);

		JMenuItem MOS_vendedor = new JMenuItem("Mostrar");
		ActionListener MOS_ven = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//creamos un objeto que muestre la vista para registrar un medico
				LVendedor mos_ven = new LVendedor() ;// 'this' hace referencia al JFrame principal
				mos_ven.Consultar();

			}
		};
		//el objeto se añade al action listener
		MOS_vendedor.addActionListener(MOS_ven);
		Menu_vendedor.add(MOS_vendedor);

		JMenuItem ELM_Vendedor = new JMenuItem("Eliminar");
		Menu_vendedor.add(ELM_Vendedor);
		JMenu Registros = new JMenu("Registros");
		menuBar.add(Registros);

		JMenuItem Registrar_Dueño = new JMenuItem("Registrar Dueño");
		ActionListener ING_Due = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistroDueño ing_due = new RegistroDueño(self); // 'this' hace referencia al JFrame principal
				ing_due.setModal(true);
				ing_due.setVisible(true);

			}
		};
		Registrar_Dueño.addActionListener(ING_Due);
		Registros.add(Registrar_Dueño);

		JMenuItem Registrar_Mascota = new JMenuItem("Registar Mascota");
		ActionListener ING_Mas = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LMascota ing_mas = new LMascota(); 
				ing_mas.Ingresar();
			}
		};
		Registrar_Mascota.addActionListener(ING_Mas);
		Registros.add(Registrar_Mascota);

		JMenuItem Buscar_Mascota = new JMenuItem("Buscar Mascota");
		ActionListener MOD_Mas = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LMascota mod_mas = new LMascota(); 
				mod_mas.BuscarMascota();
			}
		};
		Buscar_Mascota.addActionListener(MOD_Mas);
		Registros.add(Buscar_Mascota);

		JMenuItem Eliminar_Mascota = new JMenuItem("Eliminar Mascota");
		ActionListener ELI_Mas = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LMascota eli_mas = new LMascota(); 
				eli_mas.eliminar();
			}
		};
		Eliminar_Mascota.addActionListener(ELI_Mas);
		Registros.add(Eliminar_Mascota);

		JMenu Consultas = new JMenu("Consultas");
		menuBar.add(Consultas);

		JMenuItem Consultar_Mascota = new JMenuItem("Consultar Mascotas ");
		ActionListener CON_Mas = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LMascota con_mas = new LMascota(); 
				con_mas.Consultar();
			}
		};
		Consultar_Mascota.addActionListener(CON_Mas);
		Consultas.add(Consultar_Mascota);

		JMenu Facturas = new JMenu("Facturas");
		menuBar.add(Facturas);

		JMenu HistoriasClinicas = new JMenu("Historias Clinicas");
		menuBar.add(HistoriasClinicas);
		
		JMenuItem Historias_Clinicas = new JMenuItem("Ingresar Historia Clinica ");
		ActionListener ING_hc = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LHistoriaClinica ing_hc = new LHistoriaClinica(); 
				ing_hc.Ingresar();
			}
		};
		Historias_Clinicas.addActionListener(ING_hc);
		HistoriasClinicas.add(Historias_Clinicas);

		JMenu mnAcercaDe = new JMenu("Acerca De ");
		menuBar.add(mnAcercaDe);

		JMenuItem mntmNewMenuItem = new JMenuItem("Carlos Andres Pardo Salinas");
		mnAcercaDe.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Mattias Henao Ricaurte");
		mnAcercaDe.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Version 1.5.5");
		mnAcercaDe.add(mntmNewMenuItem_2);

		JMenu Otros = new JMenu("Otros");
		menuBar.add(Otros);

		JFrame self1 = this;
		JMenuItem mntmSalir = new JMenuItem("Salir");
		Otros.add(mntmSalir);
		ActionListener listener2 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		};
		mntmSalir.addActionListener(listener2);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("VETERINARIA DRESSROSA");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 29));
		lblNewLabel.setBounds(10, 10, 426, 33);
		contentPane.add(lblNewLabel);
		//
		if (rol.equals("Vendedor")||rol.equals("vendedor")) {
			// Desactivar opciones para empleados
			ControlPersonal.setEnabled(false); // si es empleado no puede registrar mas personal
//	        Consultas.setEnabled(false); // Consultas
			HistoriasClinicas.setEnabled(false); // Historias Clínicas
		} else if (rol.equals("Medico")||rol.equals("medico")||rol.equals("Médico")||rol.equals("Médico")) {
			// Desactivar opción de facturas para médicos
			Facturas.setEnabled(false); // Facturas
			ControlPersonal.setEnabled(false);
			Registros.setEnabled(false);
		}
	}


}