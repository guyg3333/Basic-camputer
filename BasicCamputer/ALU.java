package hader1.BasicCamputer;


public class ALU {
	
AC A , F ;     // Register A:=DR   
DR B  ;  // B:=AR F:=AC
int E;
boolean busy;
final static int size = 16;

ALU(AC AC, DR DR){  A = AC ; B = DR; F = AC; E=0;} // Constructor 

/**
 * if ALU.busy == true throw ALU_Expetion if ALU.busy == false return false
 * @return 
 */
public boolean ALU_busy(){
    if(busy==true) throw new ALU_Exeption(); 
    return false;
}

public void Transfer_A() {                     //Transfer_A
	if(!ALU_busy()) 
	busy=true;
	for(int i=0;i<B.register.length;i++)
		F.register[i]=B.register[i];
}  //Transfer_A


public void Transfer_A(int begin , int end){                     //Transfer_A
	if(!ALU_busy()) 
	busy=true;
	
	for(int i=begin;i<end;i++)
		F.register[i]=B.register[i];
}  //Transfer_A





public void Increment_A(){                  // Increment_A
	if(!ALU_busy()) 
	busy=true;

	for(int i=0; i<A.register.length ;i++)
		F.register[i] = A.register[i];

	if(F.register[0]==1)
		Increment_A(0);
		
		else F.register[0]=1;
		        		
	}//Increment
	private void Increment_A(int index){        //Increment_A

		if(index==F.register.length){ // end of the register
			F.SetE(1); // turn on E 
			return;
		}
		
		
		if(F.register[index]==1) 
		{
			F.register[index]=0;
			  Increment_A(index+1);
			return;
		}
		
		else F.register[index]=1; 
}//Increment_A
	
	
	public void Addition(){                            //Addition 
		if(!ALU_busy())
		busy=true;
		
		int C = (B.register[0])&(A.register[0]);
		F.register[0]= A.register[0]^B.register[0];
		Addition(1,C);
		
	}//Addition
	
	public void Addition(int index,int Cerry){      //Addition
		
		if(index==size)
			if(Cerry==1)
			{
				F.SetE(1);
				return;
			}
			else return;
		
		int C =  (B.register[index]&A.register[index])|(Cerry&B.register[index])|(Cerry&A.register[index]);
		F.register[index]= (A.register[index])^(B.register[index])^(Cerry);
		Addition(index+1,C);
		
		
	}//Addition
	
	
	
public void Add_With_Carry(){                            //   Add with carry
	
	if(!ALU_busy())
	busy=true;	
	
	    int C =  B.register[0]&A.register[0]|1&B.register[0]|1&A.register[0];
		F.register[0]= A.register[0]^B.register[0]^1;
		Add_With_Carry(1,C);
		
	}//Add with carry
	
	public void Add_With_Carry(int index,int Cerry){      //Add with carry
		
		if(index==size)
			if(Cerry==1)
			{
				F.SetE(1);
				return;
			}
			else return;
		
		int C =  (B.register[index]&A.register[index])|(Cerry&B.register[index])|(Cerry&A.register[index]);
		F.register[index]= (A.register[index])^(B.register[index])^(Cerry);
		Add_With_Carry(index+1,C);
		
		
	}//Add with carry
	
	
	
	public int not(int a){if(a==1) return 0; else return 1;} // not 
	
	
	public void Subtract_with_borrow(){                            // Subtract_with_borrow
		
		if(!ALU_busy())
		busy=true;
		
			int C = not((B.register[0]))&(A.register[0]);
			F.register[0]= A.register[0]^not(B.register[0]);
			Subtract_with_borrow(1,C);
			
		}//Subtract_with_borrow
		
		public void Subtract_with_borrow(int index,int Cerry){      //Subtract_with_borrow
			
			if(index==size)
				if(Cerry==1)
				{
					F.SetE(1);
					return;
				}
				else return;
			
			int C =  (not(B.register[index])&A.register[index])|(Cerry&not(B.register[index]))|(Cerry&A.register[index]);
			F.register[index]= A.register[index]^not(B.register[index])^Cerry;
			Subtract_with_borrow(index+1,C);
			
			
		}//Subtract_with_borrow
		
		

		public void Subtract(){                            // Subtract
			
			if(!ALU_busy())
			busy=true;
			
			    int C = (not(B.register[0])&A.register[0])|(1&not(B.register[0]))|(1&A.register[0]);
				F.register[0]= A.register[0]^not(B.register[0]);
				Subtract(1,C);
				
			}//Subtract_with_borrow
			
			public void Subtract(int index,int Cerry){      //Subtract
				
				if(index==size)
					if(Cerry==1)
					{
						F.SetE(1);
						return;
					}
					else return;
				
				int C =  (not(B.register[index])&A.register[index])|(Cerry&not(B.register[index]))|(Cerry&A.register[index]);
				F.register[index]= A.register[index]^not(B.register[index])^Cerry;
				Subtract(index+1,C);
				
				
			}//Subtract
			
		public void Decrement_A(){                          //Decrement_A 
				
			if(!ALU_busy())
			busy=true;
			
				int C = 1&A.register[0];
				F.register[0]= A.register[0]^1;
				Decrement_A(1,C);
				
			}//Addition
			
			public void Decrement_A(int index,int Cerry){      //Decrement_A
				
				if(index==size) return;
					
				
				int C = 1&A.register[index]|Cerry&1|Cerry&A.register[index];
				F.register[index]= A.register[index]^1^Cerry;
				Decrement_A(index+1,C);
				
				
			}//Decrement_A
			
			
			public void AND(){                                        //AND
				
				
                                System.out.println( "DR = " + B.toString());
                                System.out.println( "AC = " + A.toString());
				if(!ALU_busy())
				busy=true;
				
				for(int i=0;i<size;i++)
					F.register[i]=B.register[i]&A.register[i];
                                System.out.println( "AC after and = " + F.toString());
                                
			}//AND
			
			
			public void OR(){                                        //OR
				
				if(!ALU_busy())
				busy=true;
				
				for(int i=0;i<size;i++)
					F.register[i]=B.register[i]|A.register[i];
			}//OR
			
			public void XOR(){                                        //XOR
				
				if(!ALU_busy())
				busy=true;
				
				for(int i=0;i<size;i++)
					F.register[i]=B.register[i]^A.register[i];
			}//XOR
			
			public void Complement_A(){                                        //Complement_A
				
				if(!ALU_busy())
				busy=true;
				
				for(int i=0;i<size;i++)
					F.register[i]=not(A.register[i]);
			}//Complement_A
			
		
		public void Shit_right_A_into_F(){                               // Shit_right_A_into_F
			
			if(!ALU_busy())
			busy=true;
			
			int temp = E;
			E = A.register[0];
			for(int i=0;i<size-1;i++)
				F.register[i]=A.register[i+1];
			F.register[size-1]= temp;
		}//Shit_right_A_into_F
		
		
		
		public void Shit_left_A_into_F(){                               // Shit_left_A_into_F
			
			if(!ALU_busy())
			busy=true;
			
			
			int temp =E;
			E = A.register[size-1];
			for(int i=size-1;i>0;i--){
				 F.register[i]=A.register[i-1];
			}
			F.register[0]= temp;
			
			System.out.println(F.toString());
		}//Shit_left_A_into_F
		

		public void Clear_E(){                        // Clear E
			
			if(!ALU_busy())
			busy=true;
			
			E=0;
		}//Clear E
		
		
      public void Complement_E(){                        // Complement E
			
			if(!ALU_busy())
			busy=true;
			
			E=not(E);
		}//Complement_E
      
      public boolean SPA(){                                    // SPA
    	  
    	  if(!ALU_busy())
			busy=true;
    	  
    	  return A.register[15]==0;}//SPA
      
      
      
      
 public boolean SNA(){                                     // SNA
    	  
    	 if(!ALU_busy())
			busy=true;
    	  
    	  return A.register[15]==1;}//SNA
 
 
 public boolean SZE(){                                       // SZE
	  
	  if(!ALU_busy())
		busy=true;
	  
	  return E==0;}//SZE
      
 
 
 public boolean SZA(){                                       // SZA    ??????????
	  
	  if(!ALU_busy())
		busy=true;
	  
		
		for(int i=0;i<size;i++)
			if(A.register[i]!=0) return false;
		
		
	  return true;}//SZA
		


}//class
