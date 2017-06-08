package testsCliente;

import org.junit.Assert;
import org.junit.Test;

import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaqueteAtacar;
import mensajeria.PaqueteBatalla;

public class TestCliente {
	
	@Test
	public void testPaquete() {
		Paquete paquete = new Paquete("Mensaje","Nick","localhost",Comando.ACTUALIZARINVENTARIO);
		Assert.assertEquals("Mensaje", paquete.getMensaje());
		Assert.assertEquals("localhost", paquete.getIp());
		Assert.assertEquals(Comando.ACTUALIZARINVENTARIO, paquete.getComando());
	}
	
	@Test
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
	}
	
	@Test
	public void testPaqueteBatallar() {
		PaqueteBatalla paqueteBatalla = new PaqueteBatalla();
		paqueteBatalla.setId(1);
		paqueteBatalla.setIdEnemigo(2);
		Assert.assertEquals(1, paqueteBatalla.getId());
		Assert.assertEquals(2, paqueteBatalla.getIdEnemigo());

	}

}
