package hader1.BasicCamputer;


public class AC extends Registers {

	private int E;              // E
	
	AC(){register = new int[16];}// Constructor 
	
	public void Load(DR input){    // Load
		 System.out.println("AC can Load only from the ALU ther for use ALU.transfer insted");
	}//Load
	
	public void SetE(int input){
		E=input;
	}
	
	public int GetE(){
		return E;
	}
	
	
}//class
