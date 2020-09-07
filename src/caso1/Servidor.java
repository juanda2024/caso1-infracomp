package caso1;

public class Servidor extends Thread
{

private int id;
private Buffer buffer;

public Servidor(int pId, Buffer pBuffer)
{
    id = pId;    
    buffer = pBuffer;
}

public void run()
{
    while(true)
    {
        recogerMensajes();
     
    }
}

public synchronized void recogerMensajes()
{
    for(int i =0; i < buffer.darMensajes().size(); i++)
    {
        Mensaje actual = (Mensaje) buffer.darMensajes().get(i);
        
        if(actual.darEstado() == Mensaje.CONSULTA)
        {
            actual.cambiarEstado();	
            buffer.darMensajes().set(i, actual); 
            Cliente cliente =actual.darCliente();
            System.out.println("En el servidor: " + this.darId() + " se notifica al cliente : " + cliente.darId() + " del mensaje: " + actual.darId());
            cliente.notifyAll();
        }
        
        yield();
    }
}

public int darId(){
    return id;
}



}