package Main;
import cpabe.*;

public class EncDec {

	public static byte [] Encrypt(String policy, String input_data) throws Exception {
		Cpabe.setup();
		return Cpabe.enc(policy, input_data);
		
	}
	
	public static String Decrypt(byte [] enc_data,String [] attr_str) throws Exception {
		Cpabe.setup();
		Cpabe.keygen(Cpabe.arraytoString(attr_str));
		return Cpabe.dec(enc_data);
		
	}
	
	public static void main(String[] args) throws Exception {
        System.out.println("/**** Start scenario 1 success ****/");
        String policy = "foo bar fim 2of3 baf 1of2";
        String[] attr = { "baf", "fim1", "foo" };
        byte []f=Encrypt(policy,"This is my trial");
        System.out.println("encrypted_data= "+f);
        String dataa=Decrypt(f,attr);
        System.out.println("Decrypted_data= "+dataa);
        System.out.println("/**** End scenario 1 success ****/");
        System.out.println("\n");
       
  
         
       System.out.println("/**** Start scenario 2 failure ****/");
       String policy2 = "foo bar fim 2of3 baf1 1of2";
       byte []f2=Encrypt(policy2,"This is my trial" );
       System.out.println("encrypted_data= "+f2);
       String dataa2=Decrypt(f2,attr);
       System.out.println("Decrypted_data= "+dataa2);
      
       System.out.println("/**** End scenario 2 failure ****/");
}      

}


		
		