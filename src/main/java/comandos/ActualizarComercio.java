package comandos;

import javax.swing.JOptionPane;

import dominio.Item;
import mensajeria.PaqueteComerciar;

public class ActualizarComercio extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaqueteComerciar paqueteComerciar;
		paqueteComerciar = gson.fromJson(cadenaLeida, PaqueteComerciar.class);
		if(juego.getCliente().getPaqueteComercio().getListo() == paqueteComerciar.getListo()) {
				//actualizar la lista
				juego.getCliente().getM1().getObtener().removeAllElements();
				for (Item item : paqueteComerciar.getItemsADar()) {	
					juego.getCliente().getM1().getObtener().addElement(item.getNombre());										
				}
				juego.getCliente().getPaqueteComercio().setItemsAObtener(paqueteComerciar.getItemsADar());
		} else {
			// se modifico el listo
			// me fijo si puso listo o lo saco
			if(juego.getCliente().getPaqueteComercio().getListo() < paqueteComerciar.getListo()) {
				juego.getCliente().getPaqueteComercio().aumentarListo();
			} else {
				juego.getCliente().getPaqueteComercio().disminuirListo();
			}
			// modifico la cant de listos en el jframe y tambien el lbl
			juego.getCliente().getM1().setCantListos(paqueteComerciar.getListo());
			juego.getCliente().getM1().getCantListo().setText(String.valueOf(juego.getCliente().getM1().getCantListos()) + "/2");
			if(juego.getCliente().getM1().getCantListos() == 2) {
				JOptionPane.showMessageDialog(juego.getCliente().getM1(), "Se ha realizado con exito el comercio");
				juego.getCliente().getM1().dispose();
			}
		}
	}

}
