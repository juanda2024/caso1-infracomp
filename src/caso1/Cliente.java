/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación  
 *
 * Infraestructura computacional
 * Ejercicio: caso 1
 * Autor:Miguel Parra
 * Autor: Juan David Monsalve
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package caso1;
/**
 * Clase que represnta un cliente, este tiene una cantidad de mensajes que envia al buffer para ser procesados,
 * cada cliente cuenta con identificador unico. 
 * 
 */

public class Cliente extends Thread {
	//---------------------------------------------------------------------------------------------------------
	//Atributos
	//--------------------------------------------------------------------------------------------------------
	/**
	 * Cantidad de mensajes del cliente.
	 */
	private int cantidad_mensajes;

	/*
	 * Cantidad de mensajes procesados 
	 */
	private int cantidad_mensajes_procesados;
	/**
	 * Identificador del cliente
	 */
	private int id;
	/**
	 * Referencia del buffer
	 */
	private  Buffer buffer;
	//-----------------------------------------------------------------------------------------------------------
	//Constructor
	//----------------------------------------------------------------------------------------------------------
	/**
	 * Crea un objeto cliente con una cantidad de mensajes, un identificador y una referencia al buffer
	 * <b>pre: </b> Debe existir el buffer
	 * @param pCantidad_mensajes: representa la cantidad de mensajes que va a enviar
	 * @param pBuffer: referencia al buffer
	 * @param pId: identificador unico del cliente 
	 * <b>post: </b> Se creo el clinete con pId
	 */
	public Cliente(int pCantidad_mensajes, Buffer pBuffer, int pId) {
		this.cantidad_mensajes = pCantidad_mensajes;
		buffer = pBuffer;
		this.id = pId;
		this.cantidad_mensajes_procesados=0;
	}
	//----------------------------------------------------------------------------------------------------------
	//Metodos
	//--------------------------------------------------------------------------------------------------------
	
	/**
	 * Metodo que crea mensajes, duerme para que otros clientes puedan crear sus mensajes  y los envia al buffer
	 * <b>pre: </b> debe exitir un buffer
	 * <b>post: </b> se crea mensajes y los coloca en el buffer 
	 */
	public void run() {
		for (int i = 0; i < cantidad_mensajes; i++) {
			Mensaje nuevo_mensaje = new Mensaje(id, i, this);
			yield();
			buffer.insertarMensajes(this,nuevo_mensaje);
		}

	}
	/**
	 * Metodo que aumenta la cantidad de mensajes procesados y se encarga de definir si el cliente finalizo el proceso
	 * <b>pre: </b> se debio recoger un mensaje
	 * <b>post: </b> se aunmenta la cantidad de mensajes proceados
	 */
	public synchronized void quitarMensaje()
	{
		this.cantidad_mensajes_procesados++;
		if(this.cantidad_mensajes == this.cantidad_mensajes_procesados)
		{
			System.out.println("----------------------");
			System.out.println("El cliente con id: " + id + " ya termino");
			System.out.println("----------------------");
		}
	}
	/**
	 * Metodo que retorna el id del cliente
	 * @return identificador del cliente
	 */
	public int darId() {
		return id;
	}
}