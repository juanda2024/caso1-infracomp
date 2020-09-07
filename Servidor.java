public class Servidor extends Thread
{

private int id;
private Buffer buffer;

public int darId(){
    return id;
}
public Servidor(int pId, Buffer pBuffer)
{
    id = pId;    
    buffer = pBuffer;
}

public void Run()
{
    while(true)
    {
        recogerMensajes();
     
    }
}

public synchronized  void recogerMensajes()
{
    for(int i =0; i < buffer.darMensajes().size(); i++)
    {
        Mensaje actual = buffer.darMensajes().get(i); 
        actual.cambiarEstado();
        buffer.darMensajes().set(i, actual); 
        Cliente cliente =actual.darCliente();
        buffer.notificarClientes(cliente);
        this.yiel();
    }
}



}