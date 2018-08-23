package hader1.BasicCamputer;

import java.math.BigInteger;


public class IR extends Registers {
	

IR(){register = new int[16]; }          //Constructor
public void Increment(){System.out.print("There is no Increment in IR \n");} //There is no Increment in IR
public void Clear(){System.out.print("There is no Clear in IR \n");}         //There is no Clear in IR



public String toHex(String arg) {
    return String.format("%040x", new BigInteger(1, arg.getBytes(/*YOUR_CHARSET?*/)));}

public String getValue(){
	int ans=0;
	for(int i=0;i<16;i++){
		ans =ans+ (int)Math.pow(2, i)*(this.register[i]);
	    }
	    String hexStr = Integer.toString(ans,16);
            String zero = "0000";
            hexStr = zero.substring(hexStr.length())+hexStr;
	    
		return (hexStr.toUpperCase());
}



}//class