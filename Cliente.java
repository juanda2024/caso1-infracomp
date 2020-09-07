public class Cliente extends Thread
{
    /**
     * Cantidad de mensajes del cliente.
     */
    private int cantidad_mensajes ;
    /**
     * Identificador del cliente
     */
     private int id;
     /**
      * Referencia del buffer
      */
     private static Buffer buffer;
    /**
     * Arreglo de mensajes
     */
     private ArrayList<Mensaje> mensajes;


    public Mensaje darMensajes()
    {
        return mensajes;
    }
    public int darId(){
        return id;
    }

public Cliente(int pCantidad_mensajes, Buffer pBuffer)
{
    cantidad_mensajes = pCantidad_mensajes;
    buffer = pBuffer;
    mensajes = new ArrayList();
}

public void Run()
{
    for(int i=0; i< cantidad_mensajes;i++)
    {
        Mensaje nuevo_mensaje = new Mensaje(id,i,this));
        mensajes.add(nuevo_mensaje);   
    }
    
    enviarMensajes();

}

//TODO
public void enviarMensajes()
{
    buffer.insertarMensajes(this);
}
public void quitarMensaje(Mensaje mensaje)
{
    mensajes.remove(mensaje);
}
}