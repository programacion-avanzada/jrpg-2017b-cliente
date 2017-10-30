package comandos;

import mensajeria.Comando;

public class InicioSesionSet extends ComandosCliente {

    @Override
    public void ejecutar() {
	cliente.getPaqueteUsuario().setComando(Comando.INICIOSESION);
    }

}
