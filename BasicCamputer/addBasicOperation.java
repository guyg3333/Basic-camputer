package hader1.BasicCamputer;

import hader1.Con_Node;
import hader1.Condition;
import hader1.test1;
import java.io.*;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.text.html.HTMLDocument;

public class addBasicOperation {

	String Opcode;
	
	BasicComputer pc;
	Registers A , B;
        String[] opArray;
        test1 _test1;
        Iterator _iterator;
        int [] B_IR;

        addBasicOperation(){
          Opcode="null";
           pc = null;
           _test1=null;
         
        }
        
	addBasicOperation(BasicComputer _pc,test1 op ) {
		pc = _pc;
                _test1 = op;
		Opcode = op.getHexOpcode();
                _iterator = _test1.Con_List.iterator();
                System.out.println(Opcode);
                B_IR = B_Opcode();
                
	}// end of addBasicOperation

	public void basicOpertaion() {

            int row =-1;
            try{
			while(_iterator.hasNext()){
				Con_Node curr = (Con_Node)_iterator.next();
                                row++;
                               if(curr.getKetNum()>pc.SC)
                                 pc.SC_Plus();
                               
                               if(curr.getKetNum()==pc.SC)
                               {
                                    if(Condition(curr.getCon()))
                                    TransaletOpertaion(_test1.getTableOpertaion(row));
                               }
				
			}
            }
            catch(NullPointerException e){}
            catch(SC_ClearExeption e){return;}
            catch(ALU_Exeption e){throw new ALU_Exeption();}

		pc.SC_Clear();

	}// end of basicOpertaion

	public void TransaletOpertaion(String st)  {
		
            st = st.replaceAll("\\s+","");
		int i=0;
		
		while(st.length()>i){
                String comend ="";
		while(st.length()>i&&st.charAt(i)!=','){
			comend = comend+st.charAt(i);i++;
		}
		
		findthecomend(comend);
		if(st.length()>i&&st.charAt(i)==',')i++;
		}
                pc.SC_Plus();
                
                	

	}// end of TransaletOpertaion
	
	public void findthecomend(String comend) {
		
                System.out.println(comend);  
		switch (comend)
		{
		case "AR←AR+1":  pc.AR.Increment();return;
		case "AR←0":     pc.AR.Clear();    return;
		case "AC←AC+1":  pc.AC.Increment();return;
		case "AC←0":     pc.AC.Clear();    return;
		case "DR←DR+1":  pc.DR.Increment();return;
		case "DR←0":     pc.DR.Clear();    return;
		case "PC←PC+1":  pc.PC.Increment();return;
		case "PC←0":     pc.PC.Clear();    return;
		case "TR←TR+1":  pc.TR.Increment();return;
		case "TR←0":     pc.TR.Clear();    return;
                case "E←0":      pc.E=0;
                case "E←'E":      pc.E = not(pc.E); return;    
                case "SC←0":      pc.SC_Clear(); throw new SC_ClearExeption();
		}
		
		switch (comend.substring(0, 3)) // all the registers transfer commend that have directed access to the buss
		{
		case "DR←":
			A = pc.DR; // A = DR
			getRegister(comend.substring(3)); 
                        A.Load(pc.buss); // A<-B
			return;  
			
		case "AR←":
			A = pc.AR; // A = AR
			getRegister(comend.substring(3));  
			A.Load(pc.buss); // A<-B
			return;  
			
		case "PC←":
			A = pc.PC; // A = PC
			getRegister(comend.substring(3));  
			A.Load(pc.buss); // A<-B
			return; 
			
		case "TR←":
			A = pc.TR; // A = TR
			getRegister(comend.substring(3));  
			A.Load(pc.buss); // A<-B
			return; 
		
                    
                    
		}// end of registers transfer commend that have directed access to the buss
                
                
                
                try{
                    switch (comend.substring(0, 6)) // Memory transfer
                    {
                        case"M[AR]←":  
                         getRegister(comend.substring(6));  
                         pc.M.Write(pc.AR, pc.buss);
                         return;
                            
                    }
                }
                catch(StringIndexOutOfBoundsException e){};
                
		
		switch (comend)
		{
		case "AC←AC&DR": pc.ALU.AND(); return;
		case "AC←AC+DR": pc.ALU.Add_With_Carry();return;
		case "AC←DR":    pc.ALU.Transfer_A();return;
		case "AC←'AC":   pc.ALU.Complement_A();return;
		case "AC←SHR":   pc.ALU.Shit_right_A_into_F();return;
		case "AC←SHL":   pc.ALU.Shit_left_A_into_F();return;
		}
		
		
	}// end of findthecomend
	
	
	public void getRegister(String st){
		
		 System.out.println(st); 
		switch(st)
		{
		case "AR":  pc.buss.Load(pc.AR);return;
		case "PC":  pc.buss.Load(pc.PC);return;
		case "TR":  pc.buss.Load(pc.TR);return;
		case "AC":  pc.buss.Load(pc.AC);return;
                case"M[AR]": pc.M.Read(pc.AR, pc.buss);return;
		default : System.out.println("error no such register " + st) ;
		}
		
	}
        public int not(int num){
            if(num==1) return 0;
            if (num==0) return 1;
            System.out.println("not was getting value doffrent from 1 or 0 !!!!!!!");
            return 1;
        }
        
        /**
         * 
     * @param Con
         *
         * @return 
         */
        public boolean Condition(Condition Con){
            int [] C_AC = new int [16];
            int [] C_DR = new int [16];
            int [] C_IR = new int [12];
            int C_E=1 ;
          
            
            
            Condition temp = Con;
            
            for(int i=0;i<16;i++)
            {
                if(temp.get_AC()[i]!=-1)
                {
                    if(temp.get_AC()[i]==1) C_AC[i]=pc.AC.register[i];
                    if(temp.get_AC()[i]==0) C_AC[i]=not(pc.AC.register[i]);
                }
                else C_AC[i]=1;
            }// end of dor AC
            
            for(int i=0;i<16;i++)
            {
                if(temp.get_DR()[i]!=-1)
                {
                    if(temp.get_DR()[i]==1) C_DR[i]=pc.DR.register[i];
                    if(temp.get_DR()[i]==0) C_DR[i]=not(pc.DR.register[i]);
                }
                else C_DR[i]=1;
            }// end of for DR
            
            
            for(int i=0;i<12;i++)
            {
                if(temp.get_IR()[i]!=-1)
                {
                    if(temp.get_IR()[i]==1) C_IR[i]=pc.IR.register[i];
                    if(temp.get_IR()[i]==0) C_IR[i]=not(pc.IR.register[i]);
                }
                else C_IR[i]=1;
            }// end of for DR
            
            
            if(temp.get_E()!=-1)
            {
            if(temp.get_E()==1) C_E=pc.E;
            if(temp.get_E()==0) C_E=not(pc.E);
            }
            else C_E=1;
            
            
            System.out.println("C_IR " + print(C_IR));
            System.out.println("C_AR " + print(C_AC));
            System.out.println("C_DR " + print(C_DR));
            
            int ans = C_E&
                    C_IR[0]&C_IR[1]&C_IR[2]&C_IR[3]&C_IR[4]&C_IR[5]&C_IR[6]&C_IR[7]&C_IR[8]&C_IR[9]&C_IR[10]&C_IR[11]&
                    C_AC[0]&C_AC[1]&C_AC[2]&C_AC[3]&C_AC[4]&C_AC[5]&C_AC[6]&C_AC[7]&C_AC[8]&C_AC[9]&C_AC[10]&C_AC[11]&C_AC[12]&C_AC[13]&C_AC[14]&C_AC[15]&
                    C_DR[0]&C_DR[1]&C_DR[2]&C_DR[3]&C_DR[4]&C_DR[5]&C_DR[6]&C_DR[7]&C_DR[8]&C_DR[9]&C_DR[10]&C_DR[11]&C_DR[12]&C_DR[13]&C_DR[14]&C_DR[15];
            
    
            System.out.println( pc.SC + "  " +(ans==1));
             return (ans==1); 
        }

    public int[] B_Opcode() {
    
        int temp = Integer.valueOf(Opcode, 16);
        String bin = Integer.toBinaryString(temp);
        String zero = "0000000000000000";
        bin = zero.substring(bin.length())+bin;
        
        int [] ans = new int [16];
        
        for(int i=0;i<16;i++){   
            ans[i]=(bin.charAt(15-i)-48);
        }
     return ans;   
    }
    
    public int[] getB_IR(){
        return B_IR;
    }
    
    public String printB_IR(){
        String str = "";
        
        for(int i=0;i<16;i++)
        {
            str = B_IR[i] + str ;
        }
        return str;
    }
    
    
    public String print(int[] arr){
        String str = "";
        
        for(int i=0;i<arr.length;i++)
        {
            str = arr[i] + str ;
        }
        return str;
    }

}// Class addBasicOperation
