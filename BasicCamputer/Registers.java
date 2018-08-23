package hader1.BasicCamputer;


public abstract class Registers {

int[] register;

public void Increment(){                             //Increment 
	
	if(register[0]==1)
		Increment(0);
	
	else register[0]=1;
	        
			
}//Increment
private void Increment(int index){        //Increment

	if(index==this.register.length){ // end of the register
		return;
	}
	
	
	if(register[index]==1) 
	{
		register[index]=0;
		this.Increment(index+1);
		return;
	}
	
	else register[index]=1; 
}//Increment

public void Clear(){                                 //Clear
	
	for(int i=0;i<register.length;i++)
		register[i]=0;
}//Clear

public void Load(Buss other){                   //Load
	
	for(int i=0;i<this.register.length;i++)
		this.register[i]=other.buss[i];
}//Load

public void LoadFromInput(String other){                   //LoadFromInput
	
	for(int i=this.register.length-1;i>=0;i--)
		this.register[i]=other.charAt((-i)+(this.register.length-1))-48;
}//LoadFromInput


public String toString(){                 //toString
	
	    String ans="";
	    
		for(int i=0;i<this.register.length;i++)
			ans = this.register[i] + ans ;

		return ans;
}//toString

public boolean IfZero(){
	for(int i=0;i<register.length;i++)
		if(this.register[i]!=0) return false;
	return true;
}



}//Class
