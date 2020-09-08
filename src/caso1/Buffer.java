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

import java.util.ArrayList;
/**
 * Clase que represnta al buffer, esta es la contenbedora de mensajes y tiene un tamanio maximo
 * 
 */
public class Buffer {
	//------------------------------------------------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------------------------------------------
	/**
	 * Tamanio maximo del Buffer
	 */
	private int capacidad_maxima;
	/**
	 * Cantidad de mensajes dentro del Buffer
	 */
	private int capacidad_actual;
	/**
	 * Arreglo de mensajes 
	 */
	private ArrayList<Mensaje> mensajes;
	//--------------------------------------------------------------------------------------------------------
	//Constructor
	//-------------------------------------------------------------------------------------------------------
	/**
	 * Metodo constructor de Buffer dond crea el buffer con un tamanio arbitrario
	 * <b>pre: </b> tamnio debe ser mayor a 0
	 * @param pCapacidadMaxima:Tamanio maximo del Buffer
	 * <b>post: </b> inicializa el buffer 
	 */
	public Buffer(int pCapacidadMaxima) {
		this.capacidad_maxima = pCapacidadMaxima;
		this.capacidad_actual = 0;
		mensajes = new ArrayList<Mensaje>();
	}
	//--------------------------------------------------------------------------------------------------------
	//Metodos 
	//-------------------------------------------------------------------------------------------------------
	/**
	 * Metodo en el que el cliente deposita el un mensaje y procede a esperar su respuesta, de estar el buffer lleno espera
	 * a que ocurra una vacante
	 * <b>pre: </b> la tupla de cliente y mensaje debe ser unica
	 * <b>post: </b> se inserta el mensaje del cliente
	 * @param cliente: Cliente que desea insertar el mensaje
	 * @param mensaje: mensaje a insertar 
	 */
	public synchronized void insertarMensajes(Cliente cliente, Mensaje mensaje) {

		System.out.println("El cliente " +cliente.darId()+" entra ");

		while (capacidad_actual > capacidad_maxima) {
			System.out.println("El cliente " +cliente.darId()+" se duerme ya que se lleno el buffer ");
			try {
				cliente.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}

		if (capacidad_actual + 1 <= capacidad_maxima) {
			System.out.println("El cliente " +cliente.darId()+ "metio mensaje con id: " + mensaje.darId());
			capacidad_actual = capacidad_actual + 1;
			mensajes.add(mensaje);
		
			esperarMensaje(cliente, mensaje);
		}

	}
	/**
	 * Metodo en el cual el cliente espera la respuesta de su mensaje, cuando este sea respondio procede a intentar 
	 * recogerlo
	 * <b>pre: </b>el cliente debio depositar almenos un mensaje en el buffer
	 * @param pCliente: cliente que va a esperar
	 * @param mensaje: mensaje que intenta recoger 
	 * <b>post: </b> el cliente durmio y espero a la respuesta de su mensaje
	 */
	public synchronized void esperarMensaje(Cliente pCliente, Mensaje mensaje) {
		try {
			synchronized (pCliente) {
				pCliente.wait();	
			}
			recogerMensaje(pCliente, mensaje);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Metodo en el cual un cliente intenta recoger mensajes que ya fueron respondidos
	 * <b>pre: </b> debe haber almenos un mensaje respondido
	 * @param pcliente: el cliente que quiere recoger sus mensajes respndidos
	 * @param mensaje: instancia del mensaje al que se le busca la respuesta
	 * <b>post: </b> el cliente recogio el mensaje si y solo si es de el y esta respondido 
	 */
	public synchronized void recogerMensaje(Cliente pcliente, Mensaje mensaje) {
		for (int i = 0; i < mensajes.size(); i++) {
			Mensaje mensaje_actual = mensajes.get(i);

			if (mensaje_actual.darCliente().darId() == mensaje_actual.darCliente().darId()
					&& mensaje_actual.darId() == mensaje.darId() ) {

				System.out.println(
						"yo cliente con id: " + pcliente.darId() + " recogÃ­ el mensaje: " + mensaje_actual.darId());
				pcliente.quitarMensaje();
				mensajes.remove(mensaje_actual);
				capacidad_actual--;
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - -");
			}
		}
	}
	/**
	 * Metodo que retorna la lista de mensajes en el buffer 	
	 * @return lista de mensajes
	 */
	public ArrayList<Mensaje> darMensajes() {
		return mensajes;
	}
}