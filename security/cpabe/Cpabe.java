package cpabe;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;

import cpabe.policy.LangPolicy;
import bswabe.Bswabe;
import bswabe.BswabeCph;
import bswabe.BswabeCphKey;
import bswabe.BswabeElementBoolean;
import bswabe.BswabeMsk;
import bswabe.BswabePrv;
import bswabe.BswabePub;
import bswabe.SerializeUtils;

import java.io.ByteArrayInputStream;

public class Cpabe {

	
	static String seedValue = "5652653467996391684";
	public static void WriteFile(String output, byte[] b) throws IOException {
		OutputStream os = new FileOutputStream(output);
		os.write(b);
		os.close();
	}
	public static byte[] ReadFile(String input) throws IOException {
		InputStream is = new FileInputStream(input);
		int size = is.available();
		byte[] data = new byte[size];

		is.read(data);

		is.close();
		return data;
	}
	
	public static void setup() throws IOException,
			ClassNotFoundException {
		
		
		byte[] pub_byte, msk_byte;
		BswabePub pub = new BswabePub();
		BswabeMsk msk = new BswabeMsk();
		Bswabe.setup(pub, msk);

		pub_byte = SerializeUtils.serializeBswabePub(pub);
		WriteFile(pubfile, pub_byte);

		msk_byte = SerializeUtils.serializeBswabeMsk(msk);
		WriteFile(mskfile, msk_byte);
	}
        
        
	public static void keygen(String attr_str) throws NoSuchAlgorithmException, IOException {
		BswabePub pub;
		BswabeMsk msk;
		byte[] pub_byte, msk_byte, prv_byte;

		pub_byte = ReadFile(pubfile);
		pub = SerializeUtils.unserializeBswabePub(pub_byte);

		msk_byte = ReadFile(mskfile);
		msk = SerializeUtils.unserializeBswabeMsk(pub, msk_byte);

		String[] attr_arr = LangPolicy.parseAttribute(attr_str);
		BswabePrv prv = Bswabe.keygen(pub, msk, attr_arr);

		prv_byte = SerializeUtils.serializeBswabePrv(prv);
		WriteFile(prvfile, prv_byte);
	}

	public static byte[] enc( String policy, String input_data) throws Exception {
		BswabePub pub;
		BswabeCph cph;
		BswabeCphKey keyCph;
		byte[] plt;
		byte[] cphBuf;
		byte[] aesBuf;
		byte[] pub_byte;
		
		byte[] s=seedValue.getBytes();
	
		pub_byte = ReadFile(pubfile);
		pub = SerializeUtils.unserializeBswabePub(pub_byte);

		keyCph = Bswabe.enc(pub, policy);
		cph = keyCph.cph;
	

		if (cph == null) {
			System.out.println("Error happed in enc");
			System.exit(0);
		}

		cphBuf = SerializeUtils.bswabeCphSerialize(cph); 
	
		plt = input_data.getBytes();
		aesBuf = AES.encrypt(s, plt);
		return Utilities.writeCpabeData2(cphBuf, aesBuf).toByteArray();
	}

	
	
	
	public static String dec(byte [] enc_data) throws Exception {
		byte[] aesBuf, cphBuf;
		byte[] plt = null;
		byte[] prv_byte;
		byte[] pub_byte;
		byte[][] dec;
		BswabeCph cph;
		BswabePrv prv;
		BswabePub pub;
       
        byte[] s=seedValue.getBytes();
		pub_byte = ReadFile(pubfile);
		pub = SerializeUtils.unserializeBswabePub(pub_byte);

                
        dec=Utilities.readCpabeData2(new ByteArrayInputStream(enc_data));
		aesBuf = dec[0];
		cphBuf = dec[1];
		cph = SerializeUtils.bswabeCphUnserialize(pub, cphBuf);

		prv_byte = ReadFile(prvfile);
		prv = SerializeUtils.unserializeBswabePrv(pub, prv_byte);

		BswabeElementBoolean beb = Bswabe.dec(pub, prv, cph);
		if (beb.b) {
			plt = AES.decrypt(s, aesBuf);
		} else {
			System.exit(0);
		}
                
                return new String(plt);
	}
        
        
        public static String arraytoString(String[] array) {
		int l = array.length;
		String str = array[0];

		for (int i = 1; i < l; i++) {
		    str += " ";
			str += array[i];
		}

		return str;
	}
	public static String prvfile="PRV.key";
	public static String pubfile="PUB.key";
	public static String mskfile="MSK.key";

}
