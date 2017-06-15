package mensajeria;

import com.google.gson.Gson;

import juego.Juego;

public abstract class Comando {
	// Nombre del paquete donde se encuentran las clases con las responsabilidades
	public static final String NOMBREPAQUETE = "comandos";
	public static final String[] CLASSNAMES = { "Conexion", "CrearPersonaje", "Desconectar", "InicioSesion", "MostrarMapas",
			"Movimiento", "Registro", "Salir", "Batalla", "Atacar", "FinalizarBatalla", "ActualizarPersonaje", "ActualizarPersonajeLvl", "ActualizarInventario" };
	public static final int ACTUALIZARPERSONAJE = 11;
	public static final int ATACAR = 9;
	public static final int BATALLA = 8;
	public static final int CONEXION = 0;
	public static final int CREACIONPJ = 1;
	public static final int DESCONECTAR = 2;
	public static final int FINALIZARBATALLA = 10;
	public static final int INICIOSESION = 3;
	public static final int MOSTRARMAPAS = 4;
	public static final int MOVIMIENTO = 5;
	public static final int REGISTRO = 6;
	public static final int SALIR = 7;
	public static final int ACTUALIZARINVENTARIO = 13;
	public static final int ACTUALIZARPERSONAJELV = 12;
	protected final Gson gson = new Gson();
	protected String cadenaLeida;
	protected Juego juego;
	public void setCadena(String cadenaLeida) {
		this.cadenaLeida = cadenaLeida;
	}
	public void setJuego(Juego juego) {
		this.juego = juego;
	}
	
	public abstract void ejecutar();
}
