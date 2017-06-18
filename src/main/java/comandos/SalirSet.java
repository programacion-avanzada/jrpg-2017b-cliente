package comandos;

import mensajeria.Comando;

public class SalirSet extends ComandosCliente {

	@Override
	public void ejecutar() {
		cliente.getPaqueteUsuario().setIp(cliente.getMiIp());
		cliente.getPaqueteUsuario().setComando(Comando.SALIR);
	}

}
