package frames;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Cliente;
import dominio.Item;
import mensajeria.Comando;

/**
 * Menu para realizar el comercio entre personajes
 */
public class MenuComerciar extends JFrame {

    private static final int X_JL_MIS_ITEMS = 12;
    private static final int X_JL_ITEMS_A_INTERCAMBIAR = 244;
    private static final int ALTO_MENU = 363;
    private static final int ANCHO_MENU = 610;
    private static final int Y_MENU = 100;
    private static final int X_MENU = 100;
    private static final int ALTO_BOTON_CANCELAR = 25;
    private static final int ANCHO_BOTON_CANCELAR = 97;
    private static final int Y_BOTON_CANCELAR = 245;
    private static final int X_BOTON_CANCELAR = 276;
    private static final int BORDE = 5;
    private static final int X_LISTA_MIS_ITEMS = 12;
    private static final int X_LISTA_A_DAR = 244;
    private static final int X_LISTA_OBTENER = 428;
    private static final int COLUMNA_LISTA = 42;
    private static final int ALTO_ITEMS = 162;
    private static final int ANCHO_ITEMS = 157;
    private static final int COLUMNA_ITEMS = 13;
    private static final int X_JL_ITEMS = 428;
    private static final int Y_JL_INI_SALUD = 217;
    private static final int X_JL_INI_SALUD = 12;
    private static final int Y_JL_INI_ENE = 240;
    private static final int X_JL_INI_ENE = 12;
    private static final int Y_JL_INI_FZA = 217;
    private static final int X_JL_INI_FZA = 113;
    private static final int Y_JL_INI_DES = 240;
    private static final int X_JL_INI_DES = 113;
    private static final int ANCHO_INI_INIT = 71;
    private static final int Y_JL_INI_INT = 263;
    private static final int X_JL_INI_INT = 12;
    private static final int Y_JL_INI_SALUD_ENEMY = 217;
    private static final int X_JL_INI_SALUD_ENEMY = 387;
    private static final int Y_JL_INI_ENER_ENEMY = 240;
    private static final int X_JL_INI_ENER_ENEMY = 387;
    private static final int Y_JL_INI_FZA_ENEMY = 217;
    private static final int X_JL_INI_FZA_ENEMY = 497;
    private static final int Y_JL_INI_DES_ENEMY = 240;
    private static final int X_JL_INI_DES_ENEMY = 497;
    private static final int ANCHO_INI_INT_ENEMY = 71;
    private static final int Y_JL_INI_INT_ENEMY = 263;
    private static final int X_JL_INI_INT_ENEMY = 387;
    private static final int Y_JL_LISTO = 279;
    private static final int X_JL_LISTO = 276;
    private static final int Y_JL_BONUS_VIDA = 217;
    private static final int X_JL_BONUS_VIDA = 51;
    private static final int Y_JL_BONUS_ENER = 240;
    private static final int X_JL_BONUS_ENER = 51;
    private static final int Y_JL_BONUS_FZA = 217;
    private static final int X_JL_BONUS_FZA = 176;
    private static final int Y_JL_BONUS_DES = 240;
    private static final int X_JL_BONUS_DES = 176;
    private static final int Y_JL_BONUS_INT = 263;
    private static final int X_JL_BONUS_INT = 51;
    private static final int Y_JL_VIDA_ENEMY = 217;
    private static final int X_JL_VIDA_ENEMY = 428;
    private static final int Y_JL_ENE_ENEMY = 240;
    private static final int X_JL_ENE_ENEMY = 428;
    private static final int Y_JL_FZA_ENEMY = 217;
    private static final int X_JL_FZA_ENEMY = 536;
    private static final int ALTO_JL_GENERICO = 16;
    private static final int ANCHO_JL_GENERICO = 56;
    private static final int Y_JL_DES_ENEMY = 240;
    private static final int X_JL_DES_ENEMY = 536;
    private static final int Y_JL_INT_ENEMY = 263;
    private static final int X_JL_INT_ENEMY = 428;
    private static final int Y_LEYENDA = 299;
    private static final int X_LEYENDA = 12;
    private static final int ALTO_BOTON_AGREGAR = 25;
    private static final int ANCHO_BOTON_AGREGAR = 51;
    private static final int Y_BOTON_AGREGAR = 93;
    private static final int X_BOTON_AGREGAR = 181;
    private static final int MAX_CANT_ITEM = 9;
    private static final int ALTO_BOTON_SACAR = 25;
    private static final int ANCHO_BOTON_SACAR = 51;
    private static final int Y_BOTON_SACAR = 131;
    private static final int X_BOTON_SACAR = 181;
    private static final int ALTO_CANTLISTO = ALTO_JL_GENERICO;
    private static final int ANCHO_CANTLISTO = ANCHO_JL_GENERICO;
    private static final int Y_CANTLISTO = 278;
    private static final int X_CANTLISTO = 317;
    private static final int ALTO_CBOX_LISTO = 25;
    private static final int ANCHO_CBOX_LISTO = 71;
    private static final int Y_CBOX_LISTO = 213;
    private static final int X_CBOX_LISTO = 289;
    private static final int ESCALA_Y_BACK = 416;
    private static final int ESCALA_X_BACK = 610;
    private static final int ALTO_BACK = 336;
    private static final int ANCHO_BACK = 628;
    private static final int Y_BACK = 0;
    private static final int X_BACK = -12;
    private JPanel contentPane;
    private DefaultListModel<String> misItems = new DefaultListModel<String>();
    private DefaultListModel<String> dar = new DefaultListModel<String>();
    private DefaultListModel<String> obtener = new DefaultListModel<String>();
    private int cantListos = 0;
    private JLabel cantListo;
    private Item item1;
    private int count = 0;
    private final Gson gson = new Gson();
    private int sizeItems;
    private JCheckBox chckbxListo;
    private JLabel leyenda;

    /**
     * Crea el frame del menu comerciar
     * @param cliente cliente que llama al menu
     */
    public MenuComerciar(final Cliente cliente) {
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setResizable(false);
	this.setBounds(X_MENU, Y_MENU, ANCHO_MENU, ALTO_MENU);
	this.setLocationRelativeTo(null);
	this.setTitle("Comercio");

	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(BORDE, BORDE, BORDE, BORDE));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(final WindowEvent e) {
		cliente.setM1(null);
		dispose();
	    }
	});

	BufferedImage imagenFondo = null;
	try {
	    imagenFondo = ImageIO.read(new File("recursos//background.jpg"));
	} catch (IOException e) {
	    JOptionPane.showMessageDialog(null, "No se pudo cargar el fondo");

	}

	final JButton btnCancelar = new JButton("Cancelar");
	btnCancelar.setIcon(new ImageIcon("recursos//volver.png"));
	btnCancelar.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		cliente.setM1(null);
		dispose();
	    }
	});
	btnCancelar.setBounds(X_BOTON_CANCELAR, Y_BOTON_CANCELAR, ANCHO_BOTON_CANCELAR, ALTO_BOTON_CANCELAR);
	contentPane.add(btnCancelar);

	final JList<String> listMisItems = new JList<String>();
	listMisItems.setBounds(X_LISTA_MIS_ITEMS, COLUMNA_LISTA, ANCHO_ITEMS, ALTO_ITEMS);
	contentPane.add(listMisItems);

	final JList<String> listADar = new JList<String>();
	listADar.setBounds(X_LISTA_A_DAR, COLUMNA_LISTA, ANCHO_ITEMS, ALTO_ITEMS);
	contentPane.add(listADar);

	final JList<String> listAObtener = new JList<String>();
	listAObtener.setBounds(X_LISTA_OBTENER, COLUMNA_LISTA, ANCHO_ITEMS, ALTO_ITEMS);
	contentPane.add(listAObtener);

	final JLabel lblMisItems = new JLabel("Mis Items");
	lblMisItems.setForeground(Color.WHITE);
	lblMisItems.setHorizontalAlignment(SwingConstants.CENTER);
	lblMisItems.setBounds(X_JL_MIS_ITEMS, COLUMNA_ITEMS, ANCHO_ITEMS, ALTO_JL_GENERICO);
	contentPane.add(lblMisItems);

	final JLabel lblItemsAIntercambiar = new JLabel("Items a Dar");
	lblItemsAIntercambiar.setForeground(Color.WHITE);
	lblItemsAIntercambiar.setHorizontalAlignment(SwingConstants.CENTER);
	lblItemsAIntercambiar.setBounds(X_JL_ITEMS_A_INTERCAMBIAR, COLUMNA_ITEMS, ANCHO_ITEMS, ALTO_JL_GENERICO);
	contentPane.add(lblItemsAIntercambiar);

	final JLabel lblItemsAObtener = new JLabel("Items a Obtener");
	lblItemsAObtener.setForeground(Color.WHITE);
	lblItemsAObtener.setHorizontalAlignment(SwingConstants.CENTER);
	lblItemsAObtener.setBounds(X_JL_ITEMS, COLUMNA_ITEMS, ANCHO_ITEMS, ALTO_JL_GENERICO);
	contentPane.add(lblItemsAObtener);

	final JLabel lblSalud = new JLabel("Salud");
	lblSalud.setForeground(Color.WHITE);
	lblSalud.setBounds(X_JL_INI_SALUD, Y_JL_INI_SALUD, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(lblSalud);

	final JLabel lblEnerga = new JLabel("Energía");
	lblEnerga.setForeground(Color.WHITE);
	lblEnerga.setBounds(X_JL_INI_ENE, Y_JL_INI_ENE, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(lblEnerga);

	final JLabel lblFuerza = new JLabel("Fuerza");
	lblFuerza.setForeground(Color.WHITE);
	lblFuerza.setBounds(X_JL_INI_FZA, Y_JL_INI_FZA, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(lblFuerza);

	final JLabel lblDestreza = new JLabel("Destreza");
	lblDestreza.setForeground(Color.WHITE);
	lblDestreza.setBounds(X_JL_INI_DES, Y_JL_INI_DES, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(lblDestreza);

	final JLabel lblInteligencia = new JLabel("Inteligencia");
	lblInteligencia.setForeground(Color.WHITE);
	lblInteligencia.setBounds(X_JL_INI_INT, Y_JL_INI_INT, ANCHO_INI_INIT, ALTO_JL_GENERICO);
	contentPane.add(lblInteligencia);

	final JLabel lblSaludEnemy = new JLabel("Salud");
	lblSaludEnemy.setForeground(Color.WHITE);
	lblSaludEnemy.setBounds(X_JL_INI_SALUD_ENEMY, Y_JL_INI_SALUD_ENEMY, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(lblSaludEnemy);

	final JLabel lblEnergiaEnemy = new JLabel("Energía");
	lblEnergiaEnemy.setForeground(Color.WHITE);
	lblEnergiaEnemy.setBounds(X_JL_INI_ENER_ENEMY, Y_JL_INI_ENER_ENEMY, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(lblEnergiaEnemy);

	final JLabel lblFzaEnemy = new JLabel("Fuerza");
	lblFzaEnemy.setForeground(Color.WHITE);
	lblFzaEnemy.setBounds(X_JL_INI_FZA_ENEMY, Y_JL_INI_FZA_ENEMY, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(lblFzaEnemy);

	final JLabel lblDesEnemy = new JLabel("Destreza");
	lblDesEnemy.setForeground(Color.WHITE);
	lblDesEnemy.setBounds(X_JL_INI_DES_ENEMY, Y_JL_INI_DES_ENEMY, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(lblDesEnemy);

	final JLabel lblIntEnemy = new JLabel("Inteligencia");
	lblIntEnemy.setForeground(Color.WHITE);
	lblIntEnemy.setBounds(X_JL_INI_INT_ENEMY, Y_JL_INI_INT_ENEMY, ANCHO_INI_INT_ENEMY, ALTO_JL_GENERICO);
	contentPane.add(lblIntEnemy);

	final JLabel lblListo = new JLabel("Listo");
	lblListo.setForeground(Color.WHITE);
	lblListo.setBounds(X_JL_LISTO, Y_JL_LISTO, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(lblListo);

	final JLabel bonusSalud = new JLabel("");
	bonusSalud.setForeground(Color.WHITE);
	bonusSalud.setHorizontalAlignment(SwingConstants.RIGHT);
	bonusSalud.setBounds(X_JL_BONUS_VIDA, Y_JL_BONUS_VIDA, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(bonusSalud);

	final JLabel bonusEnergia = new JLabel("");
	bonusEnergia.setForeground(Color.WHITE);
	bonusEnergia.setHorizontalAlignment(SwingConstants.RIGHT);
	bonusEnergia.setBounds(X_JL_BONUS_ENER, Y_JL_BONUS_ENER, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(bonusEnergia);

	final JLabel bonusFuerza = new JLabel("");
	bonusFuerza.setForeground(Color.WHITE);
	bonusFuerza.setHorizontalAlignment(SwingConstants.RIGHT);
	bonusFuerza.setBounds(X_JL_BONUS_FZA, Y_JL_BONUS_FZA, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(bonusFuerza);

	final JLabel bonusDes = new JLabel("");
	bonusDes.setForeground(Color.WHITE);
	bonusDes.setHorizontalAlignment(SwingConstants.RIGHT);
	bonusDes.setBounds(X_JL_BONUS_DES, Y_JL_BONUS_DES, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(bonusDes);

	final JLabel bonusInt = new JLabel("");
	bonusInt.setForeground(Color.WHITE);
	bonusInt.setHorizontalAlignment(SwingConstants.RIGHT);
	bonusInt.setBounds(X_JL_BONUS_INT, Y_JL_BONUS_INT, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(bonusInt);

	final JLabel saludEnemy = new JLabel("");
	saludEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
	saludEnemy.setForeground(Color.WHITE);
	saludEnemy.setBounds(X_JL_VIDA_ENEMY, Y_JL_VIDA_ENEMY, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(saludEnemy);

	final JLabel energyEnemy = new JLabel("");
	energyEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
	energyEnemy.setForeground(Color.WHITE);
	energyEnemy.setBounds(X_JL_ENE_ENEMY, Y_JL_ENE_ENEMY, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(energyEnemy);

	final JLabel fzaEnemy = new JLabel("");
	fzaEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
	fzaEnemy.setForeground(Color.WHITE);
	fzaEnemy.setBounds(X_JL_FZA_ENEMY, Y_JL_FZA_ENEMY, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(fzaEnemy);

	final JLabel desEnemy = new JLabel("");
	desEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
	desEnemy.setForeground(Color.WHITE);
	desEnemy.setBounds(X_JL_DES_ENEMY, Y_JL_DES_ENEMY, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(desEnemy);

	final JLabel intEnemy = new JLabel("");
	intEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
	intEnemy.setForeground(Color.WHITE);
	intEnemy.setBounds(X_JL_INT_ENEMY, Y_JL_INT_ENEMY, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(intEnemy);

	chckbxListo = new JCheckBox("Listo");
	chckbxListo.setForeground(Color.WHITE);
	chckbxListo.setBackground(Color.BLACK);
	// Arranca deshabilitada
	chckbxListo.setEnabled(false);

	leyenda = new JLabel("Recuerda que la máxima cantidad de items es 9");
	leyenda.setForeground(Color.WHITE);
	leyenda.setBounds(X_LEYENDA, Y_LEYENDA, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	contentPane.add(leyenda);
	leyenda.setVisible(false);

	final JButton btnAgregar = new JButton("-->");
	btnAgregar.setIcon(new ImageIcon("recursos//flechaDer.png"));
	btnAgregar.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent arg0) {
		if (listMisItems.getSelectedValue() != null) {
		    dar.addElement(listMisItems.getSelectedValue());
		    if (obtener.size() != 0) {
			if (sizeItems - dar.size() + obtener.size() <= MAX_CANT_ITEM) {
			    chckbxListo.setEnabled(true);
			    leyenda.setVisible(false);
			}
		    }
		    // Pongo el primer item y pregunto si es igual al
		    // seleccionado
		    // Entonces mientras que sean distinto lo busca
		    // Cuando sea igual sale del while y lo agrega en la lista
		    item1 = cliente.getPaquetePersonaje().getItems().get(count);
		    while (!item1.getNombre().equals(listMisItems.getSelectedValue())) {
			count++;
			item1 = cliente.getPaquetePersonaje().getItems().get(count);
		    }
		    count = 0;
		    cliente.getPaqueteComercio().getItemsADar().add(item1);
		    misItems.removeElement(listMisItems.getSelectedValue());
		    cliente.getPaqueteComercio().setComando(Comando.ACTUALIZARCOMERCIO);
		    try {
			cliente.getSalida().writeObject(gson.toJson(cliente.getPaqueteComercio()));
		    } catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se pudo actualizar comercio");
		    }
		    if (misItems.size() == 0) {
			bonusSalud.setText("");
			bonusEnergia.setText("");
			bonusFuerza.setText("");
			bonusDes.setText("");
			bonusInt.setText("");
		    }
		}
	    }
	});
	btnAgregar.setBounds(X_BOTON_AGREGAR, Y_BOTON_AGREGAR, ANCHO_BOTON_AGREGAR, ALTO_BOTON_AGREGAR);
	contentPane.add(btnAgregar);

	final JButton btnSacar = new JButton("<--");
	btnSacar.setIcon(new ImageIcon("recursos//flechaIzq.png"));
	btnSacar.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent arg0) {
		if (listADar.getSelectedValue() != null) {
		    misItems.addElement(listADar.getSelectedValue());
		    for (Item item : cliente.getPaquetePersonaje().getItems()) {
			if (item.getNombre().equals(listADar.getSelectedValue())) {
			    cliente.getPaqueteComercio().getItemsADar().remove(item);
			}
		    }
		    dar.removeElement(listADar.getSelectedValue());
		    // Si saque el item y la lista no tiene nada deshabilito el
		    // check
		    if (dar.size() == 0) {
			chckbxListo.setEnabled(false);
		    }
		    // Si los items en total es mayor a 9 no puedo comerciar
		    if (sizeItems - dar.size() + obtener.size() > MAX_CANT_ITEM) {
			chckbxListo.setEnabled(false);
			leyenda.setVisible(true);
		    }
		    cliente.getPaqueteComercio().setComando(Comando.ACTUALIZARCOMERCIO);
		    try {
			cliente.getSalida().writeObject(gson.toJson(cliente.getPaqueteComercio()));
		    } catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se pudo actualizar comercio");
		    }
		    // Cuando paso un item de ofertar a no ofertado muestro el
		    // que movi
		    int i = misItems.size();
		    if (i >= 1) {
			for (Item item : cliente.getPaquetePersonaje().getItems()) {
			    if (misItems.getElementAt(i - 1).equals(item.getNombre())) {
				bonusSalud.setText("+ " + item.getBonusSalud());
				bonusEnergia.setText("+ " + item.getBonusEnergia());
				bonusFuerza.setText("+ " + item.getBonusFuerza());
				bonusDes.setText("+ " + item.getBonusDestreza());
				bonusInt.setText("+ " + item.getBonusInteligencia());
			    }
			}
		    }
		}
	    }
	});
	btnSacar.setBounds(X_BOTON_SACAR, Y_BOTON_SACAR, ANCHO_BOTON_SACAR, ALTO_BOTON_SACAR);
	contentPane.add(btnSacar);

	// List Listener para cargar stats del item mio clickeado
	listMisItems.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(final MouseEvent arg0) {
		if (arg0.getClickCount() == 1) {
		    if (listMisItems.getSelectedValue() != null) {
			for (Item item : cliente.getPaquetePersonaje().getItems()) {
			    if (listMisItems.getSelectedValue().equals(item.getNombre())) {
				bonusSalud.setText("+ " + item.getBonusSalud());
				bonusEnergia.setText("+ " + item.getBonusEnergia());
				bonusFuerza.setText("+ " + item.getBonusFuerza());
				bonusDes.setText("+ " + item.getBonusDestreza());
				bonusInt.setText("+ " + item.getBonusInteligencia());
			    }
			}
		    }
		}
	    }
	});

	// List Listener para cargar stats del item del enemigo clickeado
	listAObtener.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(final MouseEvent arg0) {
		if (arg0.getClickCount() == 1) {
		    if (obtener.size() != 0) {
			// cambiar la variable del for each a la lista que va a
			// venir del otro pj
			for (Item item : cliente.getPaqueteComercio().getItemsAObtener()) {
			    if (listAObtener.getSelectedValue().equals(item.getNombre())) {
				saludEnemy.setText("+ " + item.getBonusSalud());
				energyEnemy.setText("+ " + item.getBonusEnergia());
				fzaEnemy.setText("+ " + item.getBonusFuerza());
				desEnemy.setText("+ " + item.getBonusDestreza());
				intEnemy.setText("+ " + item.getBonusInteligencia());
			    }
			}
		    }
		}
	    }
	});

	// CARGO MIS ITEMS
	for (Item item : cliente.getPaquetePersonaje().getItems()) {
	    misItems.addElement(item.getNombre());
	}

	// Seteo la cantidad de mis items en mi mochila
	sizeItems = misItems.size();

	// Seteo de JList
	listMisItems.setModel(misItems);
	listADar.setModel(dar);
	listAObtener.setModel(obtener);

	cantListo = new JLabel("0/2");
	cantListo.setHorizontalAlignment(SwingConstants.RIGHT);
	cantListo.setForeground(Color.WHITE);
	cantListo.setBounds(X_CANTLISTO, Y_CANTLISTO, ANCHO_CANTLISTO, ALTO_CANTLISTO);
	contentPane.add(cantListo);

	chckbxListo.addItemListener(new ItemListener() {
	    @Override
	    public void itemStateChanged(final ItemEvent arg0) {
		if (chckbxListo.isSelected()) {
		    // Si ya la persona con la que voy a comerciar esta en LISTO
		    if (cantListos == 1) {
			cantListos++;
			// Primero actualizo el label de cant Listos
			cantListo.setText(cantListos + "/2");
			// Le envio al otro que toque listo y esta 2/2 listo
			// para trueque
			cliente.getPaqueteComercio().aumentarListo();
			cliente.getPaqueteComercio().setComando(Comando.ACTUALIZARCOMERCIO);
			try {
			    cliente.getSalida().writeObject(gson.toJson(cliente.getPaqueteComercio()));
			} catch (IOException e) {
			    JOptionPane.showMessageDialog(null, "No se pudo actualizar comercio");
			}
			////////
			// Ahora le digo que haga el trueque
			cliente.getPaqueteComercio().setComando(Comando.TRUEQUE);
			// Le informo al otro que vamos a hacer el trueque
			try {
			    cliente.getSalida().writeObject(gson.toJson(cliente.getPaqueteComercio()));
			} catch (IOException e) {
			    JOptionPane.showMessageDialog(null, "No se pudo actualizar comercio");
			}
			JOptionPane.showMessageDialog(cliente.getM1(), "Se ha realizado con exito el comercio");
			dispose();
		    } else {
			// Si todavía LISTO = 0, le informo al otro
			cantListos++;
			// Deshabilito los botones para que no pueda agregar
			// nada
			btnAgregar.setEnabled(false);
			btnSacar.setEnabled(false);
			cliente.getPaqueteComercio().aumentarListo();
			cliente.getPaqueteComercio().setComando(Comando.ACTUALIZARCOMERCIO);
			// Tambien le tiene que avisar el LISTO al otro jugador
			try {
			    cliente.getSalida().writeObject(gson.toJson(cliente.getPaqueteComercio()));
			} catch (IOException e) {
			    JOptionPane.showMessageDialog(null, "No se pudo actualizar comercio");
			}
			cantListo.setText(cantListos + "/2");
		    }
		} else {
		    // Si habia clickeado LISTO, pero lo desclickie entonces le
		    // digo
		    // que disminuya en el otro cliente
		    if (cantListos != 2) {
			// Si no tenia nada en la lista no tengo que disminuir
			// la cant
			// de listos
			cantListos--;
			cliente.getPaqueteComercio().disminuirListo();
			btnAgregar.setEnabled(true);
			btnSacar.setEnabled(true);
			cliente.getPaqueteComercio().setComando(Comando.ACTUALIZARCOMERCIO);
			// Tambien le tiene que avisar el NO LISTO al otro
			// jugador
			try {
			    cliente.getSalida().writeObject(gson.toJson(cliente.getPaqueteComercio()));
			} catch (IOException e) {
			    JOptionPane.showMessageDialog(null, "No se pudo actualizar comercio");
			}
			cantListo.setText(cantListos + "/2");
		    }
		}
	    }
	});
	chckbxListo.setHorizontalAlignment(SwingConstants.CENTER);
	chckbxListo.setBounds(X_CBOX_LISTO, Y_CBOX_LISTO, ANCHO_CBOX_LISTO, ALTO_CBOX_LISTO);
	contentPane.add(chckbxListo);

	final JLabel background = new JLabel(
		new ImageIcon(imagenFondo.getScaledInstance(ESCALA_X_BACK, ESCALA_Y_BACK, Image.SCALE_DEFAULT)));
	background.setBounds(X_BACK, Y_BACK, ANCHO_BACK, ALTO_BACK);
	contentPane.add(background);
    }

    /**
     * Gets cant listos.
     * @return cant listos
     */
    public int getCantListos() {
	return cantListos;
    }

    /**
     * Sets cant listos.
     * @param cantListos new cant listos
     */
    public void setCantListos(final int cantListos) {
	this.cantListos = cantListos;
    }

    /**
     * Gets cant listo.
     * @return cant listo
     */
    public JLabel getCantListo() {
	return cantListo;
    }

    /**
     * Sets obtener.
     * @param obtener new obtener
     */
    public void setObtener(final DefaultListModel<String> obtener) {
	this.obtener = obtener;
    }

    /**
     * Gets obtener.
     * @return obtener
     */
    public DefaultListModel<String> getObtener() {
	return obtener;
    }

    /**
     * Gets dar.
     * @return dar
     */
    public DefaultListModel<String> getDar() {
	return dar;
    }

    /**
     * Gets size items.
     * @return size items
     */
    public int getSizeItems() {
	return sizeItems;
    }

    /**
     * Gets chckbx listo.
     * @return chckbx listo
     */
    public JCheckBox getChckbxListo() {
	return chckbxListo;
    }

    /**
     * Gets leyenda.
     * @return leyenda
     */
    public JLabel getLeyenda() {
	return leyenda;
    }
}
