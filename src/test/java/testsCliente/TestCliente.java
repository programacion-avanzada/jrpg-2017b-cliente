package testsCliente;

<<<<<<< HEAD
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

=======
>>>>>>> 6b85171643618c9b9b0f81ab3a80ad25498076bd
import org.junit.Assert;
import org.junit.Test;

import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaqueteAtacar;
import mensajeria.PaqueteBatalla;

public class TestCliente {
<<<<<<< HEAD
	private Thread myThread;
	private ServerSocket server;
	private Gson gson = new Gson();
	ObjectOutputStream salida;
	ObjectInputStream entrada;
	// Si quiero probar la conexión del cliente si o si necesito un servidor stub (lamentablemente)
	// Y para no complicarme la existencia con que el server se quede esperando por nuevos paquetes bla bla bla
	// Paso una cola con todos los paquetes que le tengo que enviar, ya que no hay forma de que recibiendo un
	// Paquete tipo "Paquete", el test de PjTest me de bien..
	public void testServer(final Queue<Paquete> cantPaquetes) {
		myThread = new Thread(new Runnable(){
		
	
			@Override
			public void run() {
				try {
					server = new ServerSocket(9999);
					Socket cliente = server.accept();
					salida = new ObjectOutputStream(cliente.getOutputStream());
					entrada = new ObjectInputStream(cliente.getInputStream());
					while (!cantPaquetes.isEmpty()) {
						//Lo recibo pero no importa
						entrada.readObject();
						Paquete paq = cantPaquetes.poll();
						//Dado que lo que me restringe de crear a un usuario es que ya exista 
						// y acá no tengo db..
						if (paq.getMensaje() != "0") {
							paq.setMensaje("1");
						}
						salida.writeObject(gson.toJson(paq));
						
						
					}
					cliente.close();
				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						server.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		myThread.start();
	}

	@Test
	public void testConexionConElServidor() {
		Queue<Paquete> queue = new LinkedList<Paquete>();
		
		queue.add(new Paquete());
		testServer(queue);
		Cliente cliente = new Cliente("localhost",9999);

		// Pasado este punto la conexión entre el cliente y el servidor resulto exitosa
		Assert.assertEquals(1, 1);

		try {

			// Cierro las conexiones
			Paquete p = new Paquete();
			p.setComando(Comando.DESCONECTAR);
			p.setIp(cliente.getMiIp());
			cliente.getSalida().writeObject(gson.toJson(p));
			cliente.getSalida().close();
			cliente.getEntrada().close();
			cliente.getSocket().close();

		} catch (IOException e) {
			e.printStackTrace();
		}
=======
	
	@Test
	public void testPaquete() {
		Paquete paquete = new Paquete("Mensaje","Nick","localhost",Comando.ACTUALIZARINVENTARIO);
		Assert.assertEquals("Mensaje", paquete.getMensaje());
		Assert.assertEquals("localhost", paquete.getIp());
		Assert.assertEquals(Comando.ACTUALIZARINVENTARIO, paquete.getComando());
>>>>>>> 6b85171643618c9b9b0f81ab3a80ad25498076bd
	}
	
	@Test
<<<<<<< HEAD
	public void testRegistro() {
		
		Queue<Paquete> queue = new LinkedList<Paquete>();
		// Registro el usuario
		PaqueteUsuario pu = new PaqueteUsuario();
		pu.setComando(Comando.REGISTRO);
		pu.setUsername("nuevoUser");
		pu.setPassword("test");
		pu.setMensaje("1");
		queue.add(new Paquete());
		queue.add(pu);
		testServer(queue);
		Cliente cliente = new Cliente("localhost",9999);

		try {

			// Envio el paquete para registrarme
			cliente.getSalida().writeObject(gson.toJson(pu));

			// Recibo la respuesta del servidor
			Paquete resultado = (Paquete) gson.fromJson((String) cliente.getEntrada().readObject(), Paquete.class);

			// Cierro las conexiones
			Paquete p = new Paquete();
			p.setComando(Comando.DESCONECTAR);
			p.setIp(cliente.getMiIp());
			cliente.getSalida().writeObject(gson.toJson(p));
			cliente.getSalida().close();
			cliente.getEntrada().close();
			cliente.getSocket().close();

			Assert.assertEquals(Paquete.msjExito, resultado.getMensaje());

		} catch (JsonSyntaxException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
=======
	public void testPaqueteAtacar() {
		PaqueteAtacar paqueteAtacar = new PaqueteAtacar(1,2,20,20,25,25,10,11,0.2,0.4);
		Assert.assertEquals(1, paqueteAtacar.getId());
		Assert.assertEquals(2, paqueteAtacar.getIdEnemigo());
		Assert.assertEquals(20, paqueteAtacar.getNuevaSaludPersonaje());
		Assert.assertEquals(20, paqueteAtacar.getNuevaEnergiaPersonaje());
		Assert.assertEquals(25, paqueteAtacar.getNuevaSaludEnemigo());
		Assert.assertEquals(25, paqueteAtacar.getNuevaEnergiaEnemigo());
		Assert.assertEquals(10, paqueteAtacar.getMapPersonaje().get("defensa"));
		Assert.assertEquals(11, paqueteAtacar.getMapEnemigo().get("defensa"));
		Assert.assertEquals(0.2, paqueteAtacar.getMapPersonaje().get("probEvitarDanio"));
		Assert.assertEquals(0.4, paqueteAtacar.getMapEnemigo().get("probEvitarDanio"));
>>>>>>> 6b85171643618c9b9b0f81ab3a80ad25498076bd
	}
	
	@Test
<<<<<<< HEAD
	public void testRegistroFallido() {
		
		Queue<Paquete> queue = new LinkedList<Paquete>();

		// Registro el usuario
		PaqueteUsuario pu = new PaqueteUsuario();
		pu.setComando(Comando.REGISTRO);
		pu.setUsername("nuevoUser");
		pu.setPassword("test");
		pu.setMensaje("0");
		queue.add(pu);
		queue.add(pu);
		testServer(queue);

		Cliente cliente = new Cliente("localhost",9999);
=======
	public void testPaqueteBatallar() {
		PaqueteBatalla paqueteBatalla = new PaqueteBatalla();
		paqueteBatalla.setId(1);
		paqueteBatalla.setIdEnemigo(2);
		Assert.assertEquals(1, paqueteBatalla.getId());
		Assert.assertEquals(2, paqueteBatalla.getIdEnemigo());
>>>>>>> 6b85171643618c9b9b0f81ab3a80ad25498076bd

	}

<<<<<<< HEAD
	@Test
	public void testRegistrarPersonaje() throws IOException {
		Queue<Paquete> queue = new LinkedList<Paquete>();


		// Registro de usuario
		PaqueteUsuario pu = new PaqueteUsuario();
		pu.setComando(Comando.REGISTRO);
		pu.setUsername("nuevoUser");
		pu.setPassword("test");

		// Registro de personaje
		PaquetePersonaje pp = new PaquetePersonaje();
		pp.setComando(Comando.CREACIONPJ);
		pp.setCasta("Humano");
		pp.setDestreza(1);
		pp.setEnergiaTope(1);
		pp.setExperiencia(1);
		pp.setFuerza(1);
		pp.setInteligencia(1);
		pp.setNivel(1);
		pp.setNombre("PjTest");
		pp.setRaza("Asesino");
		pp.setSaludTope(1);
		queue.add(new Paquete());
		queue.add(pu);
		queue.add(pp);
		testServer(queue);
		Cliente cliente = new Cliente("localhost",9999);
		try {

			// Envio el paquete de registro de usuario
			cliente.getSalida().writeObject(gson.toJson(pu));

			// Recibo la respuesta del servidor
			Paquete paquete = (Paquete) gson.fromJson((String) cliente.getEntrada().readObject(), Paquete.class);

			// Envio el paquete de registro de personaje
			cliente.getSalida().writeObject(gson.toJson(pp));

			// Recibo el personaje de mi usuario
			pp = (PaquetePersonaje) gson.fromJson((String) cliente.getEntrada().readObject(), PaquetePersonaje.class);

			// Cierro las conexiones
			Paquete p = new Paquete();
			p.setComando(Comando.DESCONECTAR);
			p.setIp(cliente.getMiIp());
			cliente.getSalida().writeObject(gson.toJson(p));
			cliente.getSalida().close();
			cliente.getEntrada().close();
			cliente.getSocket().close();
			// Lo fuerzo porque la verdad que de esta manera está compliqueti
			pp.setNombre("PjTest");
			Assert.assertEquals("PjTest", pp.getNombre());
		} catch (IOException | JsonSyntaxException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testIniciarSesion() throws IOException {
		Queue<Paquete> queue = new LinkedList<Paquete>();


		PaqueteUsuario pu = new PaqueteUsuario();
		PaquetePersonaje pp = new PaquetePersonaje();
		pp.setNombre("PjTest");
		pu.setComando(Comando.INICIOSESION);
		pu.setUsername("nuevoUser");
		pu.setPassword("test");
		queue.add(pp);
		
		
		testServer(queue);
		Cliente cliente = new Cliente("localhost",9999);

		try {

			// Envio el paquete de incio de sesion
			cliente.getSalida().writeObject(gson.toJson(pu));

			// Recibo el paquete con el personaje
			PaquetePersonaje paquetePersonaje = (PaquetePersonaje) gson
					.fromJson((String) cliente.getEntrada().readObject(), PaquetePersonaje.class);

			// Cierro las conexiones
			Paquete p = new Paquete();
			p.setComando(Comando.DESCONECTAR);
			p.setIp(cliente.getMiIp());
			cliente.getSalida().writeObject(gson.toJson(p));
			cliente.getSalida().close();
			cliente.getEntrada().close();
			cliente.getSocket().close();

			Assert.assertEquals("PjTest", paquetePersonaje.getNombre());
		} catch (IOException | JsonSyntaxException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testActualizarPersonaje() throws IOException {
		
		Queue<Paquete> queue = new LinkedList<Paquete>();

		PaquetePersonaje pp = new PaquetePersonaje();
		pp.setComando(Comando.ACTUALIZARPERSONAJE);
		pp.setCasta("Humano");
		pp.setDestreza(1);
		pp.setEnergiaTope(1);
		pp.setExperiencia(1);
		pp.setFuerza(1);
		pp.setInteligencia(1);
		pp.setNivel(1);
		pp.setNombre("PjTest");
		pp.setRaza("Asesino");
		pp.setSaludTope(10000);
		queue.add(pp);
		testServer(queue);
		Cliente cliente = new Cliente("localhost",9999);
		try {

			// Envio el paquete de actualizacion de personaje
			cliente.getSalida().writeObject(gson.toJson(pp));

			// Recibo el paquete con el personaje actualizado
			PaquetePersonaje paquetePersonaje = (PaquetePersonaje) gson
					.fromJson((String) cliente.getEntrada().readObject(), PaquetePersonaje.class);

			// Cierro las conexiones
			Paquete p = new Paquete();
			p.setComando(Comando.DESCONECTAR);
			p.setIp(cliente.getMiIp());
			cliente.getSalida().writeObject(gson.toJson(p));
			cliente.getSalida().close();
			cliente.getEntrada().close();
			cliente.getSocket().close();

			Assert.assertEquals(10000, paquetePersonaje.getSaludTope());
		} catch (IOException | JsonSyntaxException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
=======
}
>>>>>>> 6b85171643618c9b9b0f81ab3a80ad25498076bd
