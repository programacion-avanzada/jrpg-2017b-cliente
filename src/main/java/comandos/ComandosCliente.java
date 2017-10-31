package comandos;

import cliente.Cliente;
import mensajeria.Comando;

public abstract class ComandosCliente extends Comando {
    protected Cliente cliente;

    /**
     * Sets the cliente.
     * @param cliente the new cliente
     */
    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

}
