package caso1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    
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