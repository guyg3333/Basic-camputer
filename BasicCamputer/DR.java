package hader1.BasicCamputer;


public class DR extends Registers {
DR(){register = new int[16];}// Constructor 

public boolean IfZero(){
	for(int i=0;i<16;i++)
		if(this.register[i]!=0) return false;
	return true;
}
}//class
