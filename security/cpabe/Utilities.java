package cpabe;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class Utilities {
 
        
        public static ByteArrayOutputStream writeCpabeData2(
			byte[] cphBuf, byte[] aesBuf) throws IOException {
  
            int i;
            ByteArrayOutputStream os=new ByteArrayOutputStream();;
		for (i = 3; i >= 0; i--)
			os.write(((aesBuf.length & (0xff << 8 * i)) >> 8 * i));
		    os.write(aesBuf);
		for (i = 3; i >= 0; i--)
			os.write(((cphBuf.length & (0xff << 8 * i)) >> 8 * i));
		    os.write(cphBuf);
		    os.close();
            
		return os;
	}
        
        
        public static byte[][] readCpabeData2(ByteArrayInputStream is) throws IOException {
		
            int i, len;
		byte[][] res = new byte[2][];
		byte[] aesBuf, cphBuf;

		len = 0;
		for (i = 3; i >= 0; i--)
			len |= is.read() << (i * 8);
		    aesBuf = new byte[len];

		    is.read(aesBuf);
		len = 0;
		for (i = 3; i >= 0; i--)
			len |= is.read() << (i * 8);
		cphBuf = new byte[len];

		is.read(cphBuf);

		is.close();

		res[0] = aesBuf;
		res[1] = cphBuf;
		return res;
            
            
	}
}
