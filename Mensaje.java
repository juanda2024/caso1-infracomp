public class Mensaje {
	
	private final static int CONSULTA= 1;
    private final static int RESPUESTA =0;
    
	private int estado;
	private int cliente;
	private int id;
	private Cliente cliente;

	public Mensaje(int pClienteid, int pid, Cliente pcliente)
	{
        cliente = pClienteid;
		estado = CONSULTA;
		id= pid;
		cliente = pcliente;
	}
	public Cliente darCliente()
	{
		return cliente;
	}
	public void cambiarEstado()
	{
		estado = RESPUESTA;
	}
}