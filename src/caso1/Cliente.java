package caso1;


public class Cliente extends Thread {
	/**
	 * Cantidad de mensajes del cliente.
	 */
	private int cantidad_mensajes;
	
	
	private int cantidad_mensajes_procesados;
	/**
	 * Identificador del cliente
	 */
	private int id;
	/**
	 * Referencia del buffer
	 */
	private  Buffer buffer;

	public Cliente(int pCantidad_mensajes, Buffer pBuffer, int pId) {
		this.cantidad_mensajes = pCantidad_mensajes;
		buffer = pBuffer;
		this.id = pId;
		this.cantidad_mensajes_procesados=0;
	}

	public void run() {
		for (int i = 0; i < cantidad_mensajes; i++) {
			Mensaje nuevo_mensaje = new Mensaje(id, i, this);
			buffer.insertarMensajes(this,nuevo_mensaje);
		}

	}
	
	public synchronized void quitarMensaje()
	{
		this.cantidad_mensajes_procesados++;
		if(this.cantidad_mensajes == this.cantidad_mensajes_procesados)
		{
			System.out.println("----------------------");
			System.out.println("el cliente con id: " + id + " ya terminó");
			System.out.println("----------------------");
		}
	}

	public int darId() {
		return id;
	}
}