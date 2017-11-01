package cliente;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import comandos.ComandosCliente;
import frames.MenuCarga;
import frames.MenuComerciar;
import frames.MenuJugar;
import frames.MenuMapas;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaqueteComerciar;
import mensajeria.PaqueteMensaje;
import mensajeria.PaquetePersonaje;
import mensajeria.PaqueteUsuario;

/**
 * La clase Cliente tiene como función ejecutar el cliente.
 */
public class Cliente extends Thread {

    private static final int RESOLUCION_INICIAL2 = 600;
    private static final int RESOLUCION_INCIAL1 = 800;
    private Socket cliente;
    private String miIp;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    // Objeto gson
    private final Gson gson = new Gson();

    // Paquete usuario y paquete personaje
    private PaqueteUsuario paqueteUsuario;
    private PaquetePersonaje paquetePersonaje;
    private PaqueteComerciar paqueteComercio;
    private PaqueteMensaje paqueteMensaje = new PaqueteMensaje();

    // Acciones que realiza el usuario
    private int accion;

    // MENU COMERCIAR
    private MenuComerciar m1;

    // Ip y puerto
    private String ip;
    private int puerto;

    /**
     * Pide la accion
     * @return Devuelve la accion
     */
    public int getAccion() {
	return accion;
    }

    /**
     * Setea la accion
     * @param accion accion a setear
     */
    public void setAccion(final int accion) {
	this.accion = accion;
    }

    private Juego wome;
    private MenuCarga menuCarga;

    /**
     * Constructor del Cliente
     */
    public Cliente() {

	ip = JOptionPane.showInputDialog("Ingrese IP del servidor: (default localhost)");
	if (ip == null) {
	    ip = "localhost";
	}
	try {
	    Scanner sc = new Scanner(new File("config.txt"));
	    sc.nextLine();
	    puerto = sc.nextInt();
	    sc.close();
	    cliente = new Socket(ip, puerto);
	    miIp = cliente.getInetAddress().getHostAddress();
	    entrada = new ObjectInputStream(cliente.getInputStream());
	    salida = new ObjectOutputStream(cliente.getOutputStream());
	} catch (IOException e) {
	    JOptionPane.showMessageDialog(null,
		    "Fallo al iniciar la aplicación. (Cliente) " + "Revise la conexión con el servidor.");
	    System.exit(1);
	}
    }

    /**
     * Instantiates a new cliente.
     * @param ip the ip
     * @param puerto the puerto
     */
    public Cliente(final String ip, final int puerto) {
	try {
	    cliente = new Socket(ip, puerto);
	    miIp = cliente.getInetAddress().getHostAddress();
	    entrada = new ObjectInputStream(cliente.getInputStream());
	    salida = new ObjectOutputStream(cliente.getOutputStream());
	} catch (IOException e) {
	    JOptionPane.showMessageDialog(null,

		    "Fallo al iniciar la aplicación. (Cliente)" + "Revise la conexión con el servidor.");

	    System.exit(1);
	}
    }

    @Override
    public void run() {
	synchronized (this) {
	    try {
		ComandosCliente comand;
		// Creo el paquete que le voy a enviar al servidor
		paqueteUsuario = new PaqueteUsuario();
		MenuJugar menuJugar = null;
		while (!paqueteUsuario.isInicioSesion()) {

		    // Muestro el menú principal
		    if (menuJugar == null) {
			menuJugar = new MenuJugar(this);
			menuJugar.setVisible(true);

			// Creo los paquetes que le voy a enviar al servidor
			paqueteUsuario = new PaqueteUsuario();
			paquetePersonaje = new PaquetePersonaje();

			// Espero a que el usuario seleccione alguna accion
			wait();

			comand = (ComandosCliente) Paquete.getObjetoSet(Comando.NOMBREPAQUETE, getAccion());
			comand.setCadena(null);
			comand.setCliente(this);
			comand.ejecutar();

			// Le envio el paquete al servidor
			salida.writeObject(gson.toJson(paqueteUsuario));
		    }
		    // Recibo el paquete desde el servidor
		    String cadenaLeida = (String) entrada.readObject();
		    Paquete paquete = gson.fromJson(cadenaLeida, Paquete.class);

		    comand = (ComandosCliente) paquete.getObjeto(Comando.NOMBREPAQUETE);
		    comand.setCadena(cadenaLeida);
		    comand.setCliente(this);
		    comand.ejecutar();
		}

		// Creo un paquete con el comando mostrar mapas
		paquetePersonaje.setComando(Comando.MOSTRARMAPAS);

		// Abro el menu de eleccion del mapa
		MenuMapas menuElegirMapa = new MenuMapas(this);
		menuElegirMapa.setVisible(true);

		// Espero a que el usuario elija el mapa
		wait();

		// Si clickeo en la Cruz al Seleccionar mapas
		if (paquetePersonaje.getMapa() == 0) {
		    paquetePersonaje.setComando(Comando.DESCONECTAR);
		    salida.writeObject(gson.toJson(paquetePersonaje));
		} else {
		    // Establezco el mapa en el paquete personaje
		    paquetePersonaje.setIp(miIp);

		    // Le envio el paquete con el mapa seleccionado
		    salida.writeObject(gson.toJson(paquetePersonaje));

		    // Instancio el juego y cargo los recursos
		    wome = new Juego("World Of the Middle Earth", RESOLUCION_INCIAL1, RESOLUCION_INICIAL2, this,
			    paquetePersonaje);

		    // Muestro el menu de carga
		    menuCarga = new MenuCarga(this);
		    menuCarga.setVisible(true);

		    // Espero que se carguen todos los recursos
		    wait();

		    // Inicio el juego
		    wome.start();

		    // Finalizo el menu de carga
		    menuCarga.dispose();
		}
	    } catch (IOException | InterruptedException | ClassNotFoundException e) {
		JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor durante el inicio de sesión.");
		System.exit(1);
	    }
	}

    }

    /**
     * Pide el cliente
     * @return Devuelve el cliente
     */
    public Socket getSocket() {
	return cliente;
    }

    /**
     * Setea el cliente
     * @param clienteParam cliente a setear
     */
    public void setSocket(final Socket clienteParam) {
	this.cliente = clienteParam;
    }

    /**
     * Pide la ip
     * @return Devuelve la ip
     */
    public String getMiIp() {
	return miIp;
    }

    /**
     * Setea la ip
     * @param miIp ip a setear
     */
    public void setMiIp(final String miIp) {
	this.miIp = miIp;
    }

    /**
     * Pide la entrada
     * @return Devuelve la entrada
     */
    public ObjectInputStream getEntrada() {
	return entrada;
    }

    /**
     * Setea la entrada
     * @param entrada entrada a setear
     */
    public void setEntrada(final ObjectInputStream entrada) {
	this.entrada = entrada;
    }

    /**
     * Pide la salida
     * @return Devuelve la salida
     */
    public ObjectOutputStream getSalida() {
	return salida;
    }

    /**
     * Setea la salida
     * @param salida salida a setear
     */
    public void setSalida(final ObjectOutputStream salida) {
	this.salida = salida;
    }

    /**
     * Pide el paquete usuario
     * @return Devuelve el paquete usuario
     */
    public PaqueteUsuario getPaqueteUsuario() {
	return paqueteUsuario;
    }

    /**
     * Pide el paquete personaje
     * @return Devuelve el paquete personaje
     */
    public PaquetePersonaje getPaquetePersonaje() {
	return paquetePersonaje;
    }

    /**
     * Pide el juego
     * @return Devuelve el juego
     */
    public Juego getJuego() {
	return wome;
    }

    /**
     * Pide el menu de carga
     * @return Devuelve el menu de carga
     */
    public MenuCarga getMenuCarga() {
	return menuCarga;
    }

    /**
     * Actualizar items.
     * @param paqueteActualizado the paquete actualizado
     */
    public void actualizarItems(final PaquetePersonaje paqueteActualizado) {
	if (paquetePersonaje.getCantItems() != 0
		&& paquetePersonaje.getCantItems() != paqueteActualizado.getCantItems()) {
	    paquetePersonaje.anadirItem(paqueteActualizado.getItems().get(paqueteActualizado.getItems().size() - 1));
	}
    }

    /**
     * Gets the ip.
     * @return the ip
     */
    public String getIp() {
	return ip;
    }

    /**
     * Actualizar personaje.
     * @param pP the p P
     */
    public void actualizarPersonaje(final PaquetePersonaje pP) {
	paquetePersonaje = pP;
    }

    /**
     * Gets the wome.
     * @return the wome
     */
    public Juego getWome() {
	return wome;
    }

    /**
     * Sets the wome.
     * @param wome the new wome
     */
    public void setWome(final Juego wome) {
	this.wome = wome;
    }

    /**
     * Gets the puerto.
     * @return the puerto
     */
    public int getPuerto() {
	return puerto;
    }

    /**
     * Sets the paquete usuario.
     * @param paqueteUsuario the new paquete usuario
     */
    public void setPaqueteUsuario(final PaqueteUsuario paqueteUsuario) {
	this.paqueteUsuario = paqueteUsuario;
    }

    /**
     * Sets the paquete personaje.
     * @param paquetePersonaje the new paquete personaje
     */
    public void setPaquetePersonaje(final PaquetePersonaje paquetePersonaje) {
	this.paquetePersonaje = paquetePersonaje;
    }

    /**
     * Sets the ip.
     * @param ip the new ip
     */
    public void setIp(final String ip) {
	this.ip = ip;
    }

    /**
     * Sets the menu carga.
     * @param menuCarga the new menu carga
     */
    public void setMenuCarga(final MenuCarga menuCarga) {
	this.menuCarga = menuCarga;
    }

    /**
     * Gets the m1.
     * @return the m1
     */
    public MenuComerciar getM1() {
	return m1;
    }

    /**
     * Sets the m1.
     * @param m1 the new m1
     */
    public void setM1(final MenuComerciar m1) {
	this.m1 = m1;
    }

    /**
     * Gets the paquete comercio.
     * @return the paquete comercio
     */
    public PaqueteComerciar getPaqueteComercio() {
	return paqueteComercio;
    }

    /**
     * Sets the paquete comercio.
     * @param paqueteComercio the new paquete comercio
     */
    public void setPaqueteComercio(final PaqueteComerciar paqueteComercio) {
	this.paqueteComercio = paqueteComercio;
    }

    /**
     * Gets the paquete mensaje.
     * @return the paquete mensaje
     */
    public PaqueteMensaje getPaqueteMensaje() {
	return paqueteMensaje;
    }

    /**
     * Sets the paquete mensaje.
     * @param paqueteMensaje the new paquete mensaje
     */
    public void setPaqueteMensaje(final PaqueteMensaje paqueteMensaje) {
	this.paqueteMensaje = paqueteMensaje;
    }
}
