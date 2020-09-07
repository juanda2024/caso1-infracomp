package caso1;

public class Mensaje {
	
	public final static int CONSULTA= 1;
    public final static int RESPUESTA =0;
    
	private int estado;
	private int id;
	private Cliente cliente;

	public Mensaje(int pClienteid, int pid, Cliente pcliente)
	{
		estado = CONSULTA;
		id= pid;
		cliente = pcliente;
	}
	public Cliente darCliente()
	{
		return cliente;
	}
	
	public int darEstado()
	{
		return estado;
	}
	public void cambiarEstado()
	{
		estado = RESPUESTA;
	}
	
	public int darId()
	{
		return id;
	}
}