package hader1.BasicCamputer;

import java.math.BigInteger;


public class AR extends Registers {

AR(){register = new int[12];} // Constructor 	

public String toHex(String arg) {
    return String.format("%040x", new BigInteger(1, arg.getBytes(/*YOUR_CHARSET?*/)));
}

public String getValue(){
	String ans="";
	for(int i=0;i<12;i++){
		ans =ans+ (int)Math.pow(2, i)*(this.register[i]);
	    }
		return toHex(ans);
}

public String getValueAsHex(){
	int ans=0;
	for(int i=0;i<12;i++){
		ans =ans+ (int)Math.pow(2, i)*(this.register[i]);
	    }
	    String hexStr = Integer.toString(ans,16);
	    String zero = "000";
            hexStr = zero.substring(hexStr.length())+hexStr;
		return (hexStr.toUpperCase());}


}//class
