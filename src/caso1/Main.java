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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Clase que se encarga de crear e inicializar los threads de cliente 
 * y de servidor, ademas esta clase tiene la responsabilidad de leer un 
 * archivo con los parametros de  numero de clientes, mensajes, servidores y capacidad del buffer.
 */
public class Main {


	//---------------------------------------------------------------------------------------------------
	// Main
	//------------------------------------------------------------------------------------------
	/**
	 * Metodo que lee el archivo docs/configuration.properties donde esta la informacion de
	 * numero de clientes, mensajes, servidores y capacidad del buffer. Ademas debe crear e inicializar los 
	 * threads respectivos. 
	 * <b>pre: </b> debe existir el archivo docs/configuration.properties con el fromato
	 * clientes.numero = #. numero determinado en el archivo properties
	 * clientes.mensajes = #. numero determinado en el archivo properties
	 * servidor.numero = #. numero determinado en el archivo properties
	 * buffer.size = #. numero determinado en el archivo properties
	 * @param args
	 * <b>post: </b> Se creo el buffer con un tamanio y se crearon e inicializaron los threads de cliente y servidor
	 */
	public static void main(String[] args) {

		Properties properties = new Properties();
		InputStream is = null;

		try {
			is = new FileInputStream("docs/configuration.properties");
			properties.load(is);
		} catch(IOException e) {
			System.out.println(e.toString());
		}

		int numero_clientes = Integer.parseInt(properties.getProperty("clientes.numero"));
		int numero_mensajes = Integer.parseInt(properties.getProperty("clientes.mensajes"));
		int numero_servidores = Integer.parseInt(properties.getProperty("servidor.numero"));
		int numero_buffer = Integer.parseInt(properties.getProperty("buffer.size"));

		Buffer buffer = new Buffer(numero_buffer);

		for(int i=0; i<numero_servidores; i++){
			Servidor servidor = new Servidor(i,buffer);
			servidor.start();
		}


		for(int i=0; i<numero_clientes; i++){
			Cliente cliente = new Cliente(numero_mensajes, buffer, i);
			cliente.start();
		}

	}

}