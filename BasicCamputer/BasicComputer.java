package hader1.BasicCamputer;

import com.sun.jmx.snmp.BerDecoder;
import hader1.Mainframe;
import hader1.test1;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


public class BasicComputer {
	
	AC AC;
	ALU ALU;
	AR AR;
	Buss buss;
	DR DR;
	IR IR;
	Memory M;
	PC PC;
	TR TR;
	int E;
	static boolean Halt = false;
        Mainframe GUI;
        addBasicOperation basicOperation;
        test1 test1;
	
	 public int SC;
	
	public BasicComputer(Mainframe _GUI,test1 operation){ //Constructor
		
            GUI = _GUI;
           
            
                AC = new AC();
		AR = new AR();
		DR = new DR();
		IR = new IR();
		PC = new PC();
                PC.LoadFromInput(GUI.getPC());
		TR = new TR();
		ALU = new ALU(AC,DR);
		M = new Memory(GUI);
		buss = new Buss();
		E=0;
                GUI=_GUI;
                test1 = operation;
                basicOperation = new addBasicOperation(this,operation);
	                } //Constructor

	
	
	public void SC_Plus() {
		if(SC==16) {System.out.println("SC = 16 !!!"); return;}
		
		SC++;
		buss.occupied=false;
		ALU.busy=false;
		System.out.print("\n T"+SC + " - ");
	}
	
	public void SC_Clear(){
		
		SC=0;
		buss.occupied=false;
		ALU.busy=false;
		System.out.print("\n\n@ T"+SC);
	}
	public void setHalt(){Halt = false;}
        
        public void setAddOperation(addBasicOperation addB){
            basicOperation = new addBasicOperation(this, null);
        } 
	
	
	
	public void Instruction_Cycle(){
	try{	
            while(!Halt){
	               //T0
                    
                   
                    
		buss.Load(PC) ;  AR.Load(buss); // AR <- pc
		System.out.println(" PC-"+PC.toString());
		SC_Plus(); //T1
		
		M.Read(AR, buss); IR.Load(buss); PC.Increment(); // IR <- M[AR] , PC <- PC+1
		
		SC_Plus(); //T2
		buss.Load(IR); AR.Load(buss); // AR <- IR(0-11)
		
		
		                
	
                                    
				    String HexCode = IR.getValue();
                                    
                                    
                                    if(decode(HexCode,basicOperation.getB_IR()))basicOperation.basicOpertaion();
                                    else{
                                    
				    switch(HexCode){
	               /*Clear AC*/ case "7800": SC_Plus();/*T3 */AC.Clear();                   SC_Clear();break; // AC <- 0
	                /*Clear E*/ case "7400": SC_Plus();/*T3 */ALU.Clear_E();                SC_Clear();break; // E <-0
                  /*Complement AC*/ case "7200": SC_Plus();/*T3 */ALU.Complement_A();           SC_Clear();break; // AC <- 'AC
				    case "7100": SC_Plus();/*T3 */ALU.Complement_A();           SC_Clear();break; // E  <-'E
				    case "7080": SC_Plus();/*T3 */ALU.Shit_right_A_into_F();    SC_Clear();break; // AC <-shr AC, AC(15) <- E , E <- AC(0)
				    case "7040": SC_Plus();/*T3 */ALU.Shit_left_A_into_F();     SC_Clear();break; // AC<-shl AC, AC(0) <-E,E <-AC(15)
				    case "7020": SC_Plus();/*T3 */ALU.Increment_A();            SC_Clear();break; // AC <- AC+1
				    case "7010": SC_Plus();/*T3 */if(ALU.SPA()) PC.Increment(); SC_Clear();break; // if(AC(15)=0) then(PC <-PC+1)
				    case "7008": SC_Plus();/*T3 */if(ALU.SNA()) PC.Increment(); SC_Clear();break; // if(AC(15)=1) then(PC <-PC+1)
				    case "7004": SC_Plus();/*T3 */if(ALU.SZA()) PC.Increment(); SC_Clear();break; // if(E=0) then(PC <-PC+1)
				    case "7002": SC_Plus();/*T3 */if(ALU.SZE()) PC.Increment(); SC_Clear();break; // if(E=0) then(PC <-PC+1)
				    case "7001": SC_Plus();/*T3 */Halt = true;                  SC_Clear();break; 
				    case "F800":
				    case "F400":
				    case "F200":
				    case "F100":
				    case "F080":
				    case "F040":
				    }//switch B
				    
				    switch(HexCode.substring(0,2)){
				    case "73":
				    	
				    	SC_Plus(); buss.Load(IR); DR.Load(buss);                         //T3// DR <-IR        
				    	SC_Plus(); ALU.Transfer_A(0,8);                                  //T4// AC <-DR(0-8)       
				    	SC_Plus(); buss.Load(AC);DR.Load(buss);ALU.Complement_A();       //T5// DR <-AC, AC<-AC'  
				    	SC_Plus(); buss.Load(AC);TR.Load(buss); AC.Clear();              //T6// TR <-AC ,AC<-0    
				    	SC_Plus(); TR.Increment();                                       //T7// TR <- TR+1 T7
				    	SC_Plus(); if(TR.IfZero()) break; ALU.Addition();TR.Increment(); //T8// if(TR)=0 halt ,AC<-AC+DR 
				    	SC_Plus(); if(TR.IfZero()) break; ALU.Addition();TR.Increment(); //T9// if(TR)=0 halt ,AC<-AC+DR 
				    	SC_Plus(); if(TR.IfZero()) break; ALU.Addition();TR.Increment(); //T10//if(TR)=0 halt ,AC<-AC+DR  
				    	SC_Plus(); if(TR.IfZero()) break; ALU.Addition();TR.Increment(); //T11//if(TR)=0 halt ,AC<-AC+DR 
				    	SC_Plus(); if(TR.IfZero()) break; ALU.Addition();TR.Increment(); //T12//if(TR)=0 halt ,AC<-AC+DR 
				    	SC_Plus(); if(TR.IfZero()) break; ALU.Addition();TR.Increment(); //T13//if(TR)=0 halt ,AC<-AC+DR 
				    	SC_Plus(); if(TR.IfZero()) break; TR.Clear();                    //T14//if(TR)=0 halt ,TR<-0 
				    	SC_Plus(); TR.Increment(); break;                                //T15//TR <- TR+1 
				    	
				    
				    	
				    	
				    
				    
				    }//switch subString adding comend
				    
				    
	                  
	                			
	                char D=HexCode.charAt(0);	
	                switch(D){
	                case'0': SC_Plus();/*T3 */                                  //idle
	                         SC_Plus();/*T4 */ buss.Load(DR); M.Read(AR, buss);  //DR<-M[AR] 
	                         SC_Plus();/*T5 */ ALU.AND(); SC_Clear();break;           //AC<-AC/\DR , SC<-0
	                         
	                case'1': SC_Plus();/*T3 */                                  //idle
	                         SC_Plus();/*T4 */ M.Read(AR, buss); DR.Load(buss); //DR<-M[AR] 
	                         SC_Plus();/*T5 */ ALU.Addition(); SC_Clear();break;                   //AC<-AC+DR,E<-C_out , SC<-0
	                         
	                case'2': SC_Plus();/*T3 */                                  //idle
	                		 SC_Plus();/*T4 */ M.Read(AR, buss); DR.Load(buss);  //DR<-M[AR] 
	                		 SC_Plus();/*T5 */ ALU.Transfer_A();  SC_Clear();break;   //AC<-DR, SC<-0
	                
	                case'3': SC_Plus();/*T3 */                                  //idle
	                         SC_Plus();/*T4 */buss.Load(AC); M.Write(AR, buss); SC_Clear();break; //M[AR] <-AC , SC<-0
	                
	                
	                case'4': SC_Plus();/*T3 */                                  //idle
	                         SC_Plus();/*T4 */buss.Load(AR); PC.Load(buss); SC_Clear();break; //PC<-AR, SC<_0 
	                	
	                	
	                case'5': SC_Plus();/*T3 */                                                   //idle
	                         SC_Plus();/*T4 */ buss.Load(PC); M.Write(AR, buss); AR.Increment(); // M[AR]<-PC,AR<-AR+1
	                         SC_Plus();/*T5 */ buss.Load(AR); PC.Load(buss); SC_Clear();break;         //PC<-AR, SC<-0
	                         
	                case'6': SC_Plus();/*T3 */                                                   //idle
	                         SC_Plus();/*T4 */ M.Read(AR, buss); DR.Load(buss);               // DR<-M[AR]
	                         SC_Plus();/*T5 */ DR.Increment();                                  // DR<-DR+1
	                         SC_Plus();/*T6 */ buss.Load(DR); M.Write(AR, buss); if(DR.IfZero())PC.Increment(); SC_Clear();break; //M[AR]<-DR,if (DR=0)then(PC<-PC+1), SC<-0
	                
	              
	                	     
	                         
	                         
	                case'8' :SC_Plus();/*T3 */ M.Write(AR, buss); AR.Load(buss);          //AR<-M[AR]
	                         SC_Plus();/*T4 */ buss.Load(DR); M.Read(AR, buss);  //DR<-M[AR] 
                             SC_Plus();/*T5 */ ALU.AND(); SC_Clear();break;           //AC<-AC/\DR , SC<-0
	                
	                case'9': SC_Plus();/*T3 */ M.Write(AR, buss); AR.Load(buss);          //AR<-M[AR]
	                         SC_Plus();/*T4 */ buss.Load(DR); M.Read(AR, buss);  //DR<-M[AR] 
                             SC_Plus();/*T5 */ ALU.Addition(); SC_Clear();break;                   //AC<-AC+DR,E<-C_out , SC<-0
                             
	                case'A': SC_Plus();/*T3 */ M.Write(AR, buss); AR.Load(buss);          //AR<-M[AR]
	                         SC_Plus();/*T4 */buss.Load(DR); M.Read(AR, buss);  //DR<-M[AR] 
           	            	 SC_Plus();/*T5 */ ALU.Transfer_A();  SC_Clear();break;   //AC<-DR, SC<-0
           	            	 
	                case'B': SC_Plus();/*T3 */ M.Write(AR, buss); AR.Load(buss);          //AR<-M[AR]
	                         SC_Plus();/*T4 */buss.Load(AC); M.Write(AR, buss); SC_Clear();break; //M[AR] <-AC , SC<-0
	                         
	                case'C': SC_Plus();/*T3 */ M.Write(AR, buss); AR.Load(buss);          //AR<-M[AR]
	                         SC_Plus();/*T4 */buss.Load(AR); PC.Load(buss); SC_Clear();break; //PC<-AR, SC<_0 
		                     
	                case'D': SC_Plus();/*T3 */ M.Write(AR, buss); AR.Load(buss);          //AR<-M[AR]
	                         SC_Plus();/*T4 */ buss.Load(PC); M.Write(AR, buss); AR.Increment(); // M[AR]<-PC,AR<-AR+1
                             SC_Plus();/*T5 */ buss.Load(AR); PC.Load(buss); SC_Clear();break;         //PC<-AR, SC<-0
                    
	                case'E': SC_Plus();/*T3 */ M.Write(AR, buss); AR.Load(buss);          //AR<-M[AR]
	                         SC_Plus();/*T4 */ M.Read(AR, buss); DR.Load(buss);               // DR<-M[AR]
                             SC_Plus();/*T5 */ DR.Increment();                                  // DR<-DR+1
                             SC_Plus();/*T6 */ buss.Load(DR); M.Write(AR, buss); if(DR.IfZero())PC.Increment(); SC_Clear();break; //M[AR]<-DR,if (DR=0)then(PC<-PC+1), SC<-0
          
                             
                             
	                }//D
	                SC_Clear();
				
                     }//else
                                    
                  
		}//while
                    GUI._coLine.writecommentRed("The instruction cycle end with no error");
             } // try
                                catch(ALU_Exeption e){Halt = true; GUI._coLine.writecommentRed("ALU have alrady commend for the current clock pulse this error heppend in T" + SC );}
                                catch(buss_Exeption e){Halt = true; GUI._coLine.writecommentRed("the buss is occupied this error heppend in T" + SC );}
                                catch(emptyMemoryCellExeption e){Halt = true; GUI._coLine.writecommentRed("the memory try to read from non valid value memory cell. this error heppend in row" + PC.getValueAsHex());}
                                catch(NullPointerException e){Halt = true; GUI._coLine.writecommentRed("missing 7001 comend");}
        
		saveToTable();	
                
	}//Instruction_Cycle
	public void saveToTable(){ //save the memory status in the end of the run
                  
            for(int i=0;i<500;i++){
                GUI.setValueAt(M.M[i],i,1);
            }
           
	}
        
        /**
         * set the relevnt value in IR that infloance the chise of the opertion' in short the flipflop how conect to the combinathinal logic
         * @param B_IR 
         */
        public void set_B_IR(int[] B_IR){
            
            for(int i=12;i<16;i++) // copy IR(15-12)
            {
                B_IR[i]=IR.register[i]; 
            }
            
            
            for(int i=0;i<12;i++){
                if(test1.getOpcode_boolean()[i]==1) // only the relevent  IR(11-0) values
                    B_IR[i]=IR.register[i];
                else B_IR[i]=-1; // represent dont care
            }
            
        }
        
        public int not(int i)
        {
            if(i==-1) return 1; //-1 represent dont care
            if(i==1)return 0;
            else return 1;
        }
        
         public int dont(int i)
        {
            if(i==-1) return 1; //-1 represent dont care
            else return i;
        }
	/**
         * chaek if the hex value match the current IR value while match only the relevent IR value how connect to the combinational logic gate 
         * @param hex
         * @param B_IR
         * @return 
         */
         public boolean decode(String hex,int [] B_IR)
         {
            
             int temp = Integer.valueOf(hex, 16);
             String bin = Integer.toBinaryString(temp);
             String zero = "0000000000000000";
             bin = zero.substring(bin.length())+bin;
             
             for(int i=0;i<16;i++)
             {
                 if(B_IR[i]==1)
                 {
                     if(B_IR[i]!=(bin.charAt(15-i)-48)){
                         System.out.println("bin" + bin);
                         System.out.println("B_IR" + basicOperation.printB_IR());
                         System.out.println("Decode false" + i + " "+ "B_IR[i] " +B_IR[i] + " bin.charAt(15-i) " + bin.charAt(15-i) );
                         return false;
                     }
                 }
             }
              System.out.println("Decode true");
             return true; 
         }
	
	
	
	
}//class
