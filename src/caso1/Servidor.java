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
 * Clase que representa el Servidor encargado de procesar mensajes del buffer.
 *
 */
public class Servidor extends Thread {
	// ---------------------------------------------------------------------------------------------------------
	// Atributos
	// ---------------------------------------------------------------------------------------------------------
	/**
	 * Atributo que representa el identificador del servidor actual
	 */
	private int id;
	/**
	 * Atributo que referencia el buffer del que se encuentra procesando mensajes.
	 */
	private Buffer buffer;

	// -----------------------------------------------------------------------------------------------------------
	// Constructor
	// ----------------------------------------------------------------------------------------------------------

	/**
	 * Metodo constructor para la clase Servidor. 
	 * <b>pre: </b> Debe existir el buffer
	 * @param pId: identificador del servidor.
	 * @param pBuffer: Referencia del buffer al cual se le procesan constantementelos mensajes. 
	 * <b>post: </b> Se creo el Servidor con pId
	 */
	public Servidor(int pId, Buffer pBuffer) {
		id = pId;
		buffer = pBuffer;
	}

	// ----------------------------------------------------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------------------------------------------------

	/**
	 * Metodo que ejecuta constantemente el metodo encargado en procesar los
	 * mensajes faltantes del buffer.
	 */
	public void run() {
		while (true) {
			recogerMensajes();

		}
	}

	/**
	 * Metodo que procesa los mensajes del correspondiente buffer. notifica a todos
	 * los clientes para que puedan recoger sus mensajes y pasa el estado del
	 * mensaje a respuesta. 
	 * <b>pre: </b> debe exitir un buffer 
	 * <b>post: se notifican a los clientes para proceder a recoger sus respectivos 
	 * mensajes ya procesados y actualiza la lista de mensajes del buffer
	 */
	public synchronized void recogerMensajes() {
		for (int i = 0; i < buffer.darMensajes().size(); i++) {
			try {
				Mensaje actual = (Mensaje) buffer.darMensajes().get(i);

				if (actual.darEstado() == Mensaje.CONSULTA) {
					actual.cambiarEstado();
					buffer.darMensajes().set(i, actual);
					Cliente cliente = actual.darCliente();
					System.out.println("En el servidor: " + this.darId() + " se notifica al cliente : "+ cliente.darId() + " del mensaje: " + actual.darId());
					synchronized (cliente) {
						cliente.notifyAll();
					}
				}
			} catch (Exception e) {

			}
			yield();
		}
	}

	/**
	 * Metodo que retorna el identificador del servidor actual.
	 * @return id: identificador del servidor.
	 */
	public int darId() {
		return id;
	}

}