package comandos;

import frames.MenuComerciar;
import mensajeria.PaqueteComerciar;

public class Comercio extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaqueteComerciar paqueteComerciar;
		paqueteComerciar = gson.fromJson(cadenaLeida, PaqueteComerciar.class);
		
		// Cuando recibo el paquete de comercio actualizado intercambio user/ destino
		paqueteComerciar.setIdEnemigo(paqueteComerciar.getId());
		paqueteComerciar.setId(juego.getCliente().getPaquetePersonaje().getId());
		
		juego.getCliente().setPaqueteComercio(paqueteComerciar);
		juego.getCliente().setM1(new MenuComerciar(juego.getCliente()));
		juego.getCliente().getM1().setVisible(true);

	}

}
