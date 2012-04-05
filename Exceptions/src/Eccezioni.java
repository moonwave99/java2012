public class Eccezioni{
	
	public static void main(String[] args)
	{
		
		OperazioneBinaria ob = new OperazioneBinaria(10, 0);
		
		try{
			
			System.out.println(ob.dividi());			
			
		}catch(MathException e){
			
			System.out.println(e);
			
		}finally{
			
			System.out.println("Io vengo eseguito comunque");
			
		}

	}
	
}

class OperazioneBinaria
{
	
	public double operando1, operando2;
	
	public OperazioneBinaria(double operando1, double operando2)
	{
	
		this.operando1 = operando1;
		this.operando2 = operando2;
		
	}
	
	public double somma(){
		
		return operando1 + operando2;
		
	}
	
	public double dividi() throws MathException
	{
	
		if(operando2 == 0)
			throw new MathException("Non si può dividere per zero.");
	
		return operando1 / operando2;
		
	}
	
}

class MathException extends Exception
{

	public MathException(String message)
	{
		
		super(message);
		
	}
	
}