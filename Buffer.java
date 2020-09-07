public class Buffer
{

    private int capacidad_maxima;

    private int capacidad_actual;
    
    private ArrayList<Mensaje> mensajes;

    

    public Buffer(int pCapacidadMaxima)
    {
        this.capacidad_maxima = pCapacidadMaxima;

        this.capacidad_actual = 0;

        mensajes = new ArrayList();
    }

    public  synchronized void insertarMensajes(Cliente pcliente)
    {
        i =0;
        while(pcliente.darMensajes().size() != 0)
        {
            if(capacidad_actual+1 <= capacidad_maxima))
            {
                capacidad_actual = capacidad_actual + 1;
                mensajes.add(pcliente.darMensajes.get(i));
                i++;
            }
            else
            {
                pcliente.wait();              
            }  
              recogerMensaje(pcliente);     
        } 
    }

   

    public  synchronized void recogerMensaje(Cliente pcliente)
    {
        for(int i=0; i < mensajes.size() ; i++)
        {
            Mensaje mensaje_actual = mensajes.get(i);

            if(mensaje_actual.darCliente() == pcliente.darId() && mensaje_actual.darEstado() == Mensaje.RESPUESTA)
            {
                    pcliente.quitarMensaje(mensaje_actual.darId());
                    mensajes.remove(mensaje_actual);
                    capacidad_actual--;     
            }
        }
            
    }

    public void notificarClientes(Cliente pCliente)
    {
        pCliente.notifyAll();
    }

    public void retirarMensaje(Mensaje pMensaje)
    {

    }
    
    public void dormirMensaje(Mensaje pMensaje)
    {
       
    }
    public ArrayList darMensajes()
    {
        return mensajes;
    }
}