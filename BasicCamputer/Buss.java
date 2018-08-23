package hader1.BasicCamputer;


public class Buss extends Registers {
	
	int[] buss;
	boolean occupied;
	
	Buss(){buss = new int[16] ; occupied=false; } //Constructor 
	
	
        public boolean Busy(){
            if(occupied) throw new buss_Exeption();
            return false;
        }
        
	public void Load(Registers other){                   //Load from register to bus
		if(!Busy())
		
		
		for(int i=0;i<other.register.length;i++)
			this.buss[i]=other.register[i];
		    occupied=true;
	}//Load
	

	public int getValue(){
		int ans=0;
		for(int i=0;i<16;i++){
			ans =ans+ (int)Math.pow(2, i)*(buss[i]);
		    }
			return ans;
	}
	
	public String toString(){                 //toString
		
	    String ans="";
	    
		for(int i=0;i<16;i++)
			ans = buss[i] + ans ;

		return ans;
}//toString
}
