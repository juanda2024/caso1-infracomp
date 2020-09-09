/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogota - Colombia)
 * Departamento de Ingenieria de Sistemas y Computacion  
 *
 * Infraestructura computacional
 * Ejercicio: caso 1
 * Autor: Miguel Parra - 201814632
 * Autor: Juan David Monsalve - 201814295
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package caso1;

/**
 * Clase que representa la instancia de Mensaje. El mensaje es generado por
 * clientes.
 *
 */
public class Mensaje {
	// ---------------------------------------------------------------------------------------------------------
	// Atributos
	// ---------------------------------------------------------------------------------------------------------
	/**
	 * Constante para representar el estado del mensaje en espera para consulta.
	 */
	public final static int CONSULTA = 1;
	/**
	 * Constante para representar el estado del mensaje en procesado por el servidor
	 * (mensaje respondido).
	 */
	public final static int RESPUESTA = 0;

	/**
	 * Atributo que almacena el estado del mensaje.
	 */
	private int estado;
	/**
	 * Atributo que representa el identificador del mensaje.
	 */
	private int id;
	/**
	 * Atributo que referencia el cliente creador del mensaje.
	 */
	private Cliente cliente;

	// ---------------------------------------------------------------------------------------------------
	// Mensaje
	// ---------------------------------------------------------------------------------------------------
	/**
	 * Método constructor para una instancia de Mensaje.
	 * <b>pre: </b> Debe existir el cliente.
	 * @param pid: identificador del mensaje creado.
	 * @param pcliente: Instancia del cliente creador del mensaje.
	 * <b>post: </b> Se crea la instancia del Mensaje deseada..
	 */
	public Mensaje(int pid, Cliente pcliente) {
		estado = CONSULTA;
		id = pid;
		cliente = pcliente;
	}
	
	// ---------------------------------------------------------------------------------------------------
	// Metodos
	// ---------------------------------------------------------------------------------------------------
	/**
	 * Metodo que retorna la referencia del cliente creador del mensaje.
	 * 
	 * @return cliente: Cliente creador del mensaje.
	 */
	public Cliente darCliente() {
		return cliente;
	}

	/**
	 * Metodo que retorna el estado del Mensaje.
	 * 
	 * @return estado: Estado actual del mensaje (consulta/respuesta)
	 */
	public int darEstado() {
		return estado;
	}

	/**
	 * Metodo que retorna el id del Mensaje.
	 * 
	 * @return id: identificador del mensaje.
	 */
	public int darId() {
		return id;
	}

	/**
	 * Metodo que cambia el estado del Mensaje a Respuesta, esto quiere decir que ya
	 * fue procesado por el servidor.
	 */
	public void cambiarEstado() {
		estado = RESPUESTA;
	}
}