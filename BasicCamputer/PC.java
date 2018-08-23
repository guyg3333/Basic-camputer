package hader1.BasicCamputer;


public class PC extends Registers {

PC(){register=new int[12]; this.Clear();} // Constructor


public String getValueAsHex(){
	int ans=0;
	for(int i=0;i<12;i++){
		ans =ans+ (int)Math.pow(2, i)*(this.register[i]);
	    }
	    String hexStr = Integer.toString(ans,16);
	    String zero = "000";
            hexStr = zero.substring(hexStr.length())+hexStr;
		return (hexStr.toUpperCase());}
}
