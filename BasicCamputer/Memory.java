package hader1.BasicCamputer;

import hader1.Mainframe;
import java.io.*;
import java.util.Scanner;

public class Memory {

	String[] M;
	Mainframe GUI;
	
	Memory(Mainframe _GUI){ // Constructor
            GUI = _GUI;
	M = new String[500];
	

        for(int i=0;i<500;i++){
            M[i] = GUI.getValueAt(i,1); // load information from table
        }
        
        System.out.println(M[1]);
       
	
	} //Constructor
	
	public void Read(AR address,Buss buss){                                        //Read
		if(buss.occupied){ throw new buss_Exeption();}
		
		int AR = getValue(address);
		System.out.println("AR in memory =" + AR);
		System.out.println("M[AR] =" + M[AR]);
                if(M[AR].length()==0)throw new emptyMemoryCellExeption();
		int temp = hex2decimal(M[AR]);
		String bin = Integer.toString(temp,2);
		String zero = "0000000000000000";
		bin = zero.substring(bin.length())+bin;
		
		for(int i =0;i<16;i++)
			buss.buss[i]= (bin.charAt(15-i)-48);
		
		buss.occupied=true;
	}//Read
	
	
	
	public void Write(AR address,Buss buss){                                        //Write
		
		int AR = getValue(address);
		int temp= buss.getValue();
		String hex = Integer.toString(temp, 16);
		String zero = "0000";
		hex = zero.substring(hex.length())+hex;
		
		M[AR]=hex;
		
	}//Write
	
	
	
	
	
	
	public int getValue(Registers addres){
		int ans=0;
		for(int i=0;i<addres.register.length;i++){
			ans =ans+ (int)Math.pow(2, i)*(addres.register[i]);
		    }
			return ans;
	}
	
	 public static int hex2decimal(String s) {
		 s=s.replaceAll("\\s+","");
         String digits = "0123456789ABCDEF";
         s = s.toUpperCase();
         int val = 0;
         for (int i = 0; i < s.length(); i++) {
             char c = s.charAt(i);
             int d = digits.indexOf(c);
             val = 16*val + d;
         }
         return val;
     }
         
       
	

}
