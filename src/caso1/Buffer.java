package caso1;

import java.util.ArrayList;

public class Buffer {

	private int capacidad_maxima;

	private int capacidad_actual;

	private ArrayList<Mensaje> mensajes;

	public Buffer(int pCapacidadMaxima) {
		this.capacidad_maxima = pCapacidadMaxima;
		this.capacidad_actual = 0;
		mensajes = new ArrayList<Mensaje>();
	}

	public synchronized void insertarMensajes(Cliente cliente, Mensaje mensaje) {

		System.out.println("toy dentro");

		while (capacidad_actual > capacidad_maxima) {
			System.out.println("se fue a mimir: buffer lleno");
			try {
				cliente.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}

		if (capacidad_actual + 1 <= capacidad_maxima) {
			System.out.println("intento meter mensaje con id: " + mensaje.darId());
			capacidad_actual = capacidad_actual + 1;
			mensajes.add(mensaje);
			esperarMensaje(cliente, mensaje);
		}

	}

	public synchronized void esperarMensaje(Cliente pCliente, Mensaje mensaje) {
		try {
			pCliente.wait();
			System.out.println("- - - - - - - -  - -- ");
			recogerMensaje(pCliente, mensaje);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public synchronized void recogerMensaje(Cliente pcliente, Mensaje mensaje) {
		for (int i = 0; i < mensajes.size(); i++) {
			Mensaje mensaje_actual = mensajes.get(i);

			if (mensaje_actual.darCliente().darId() == mensaje_actual.darCliente().darId()
					&& mensaje_actual.darId() == mensaje.darId()) {

				System.out.println(
						"yo cliente con id: " + pcliente.darId() + " recogí el mensaje: " + mensaje_actual.darId());
				pcliente.quitarMensaje();
				mensajes.remove(mensaje_actual);
				capacidad_actual--;
			}
		}
	}

	public ArrayList<Mensaje> darMensajes() {
		return mensajes;
	}
}