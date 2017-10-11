import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;

public class JavaClient {

	public static void main(String[] args) {
		
		if (args.length != 2) {
		      System.out.println("Please enter [ip] [port]");
		      System.exit(0);
		}
		
		
	    try {
	      TTransport transport;
	   
	      transport = new TSocket(args[0], Integer.valueOf(args[1]));
	      transport.open();	     

	      TProtocol protocol = new  TBinaryProtocol(transport);
	      FileStore.Client client = new FileStore.Client(protocol);
	
		    //NodeID node = client.getNodeSucc();
		    //System.out.println("Succ of " + args[1] + "is---  " + node.getId() + " ip: " + node.getIp());
	
	       // String Key = "008db28ca4b72d8a7963fed58cdf2569ef3ed6c1835f041d98032e40a69e5a10";
	        //NodeID node1 = client.findPred(Key);
	        //System.out.println("findPred Id -> " + node1.getId());
	
	        //NodeID node2 = client.findSucc(Key);
	        //System.out.println("findSucc id -> "+ node2.getId());

	      String owner = "sarang";
	      String fileN = "example1.txt";
	      String value = fileN + ":" + owner;
	      
	      String fileId = JavaClient.getSHA256Hash(value);
        
	      NodeID serverOwner = client.findSucc(fileId);
	      System.out.println("Server ID which owns given file is -> " + serverOwner.getId());
	      System.out.println("");
        	
	      //code to call write 
        	
	       // RFile file = new RFile();
        //	RFileMetadata metadata = new RFileMetadata();
    		   
    		//metadata.setFilename(fileN);
        	//metadata.setOwner(owner);
        	
        	//file.setContent("This is new file1");
        //	file.setMeta(metadata);
        	
        	//client.writeFile(file);
        	
        	/*RFile file1 = null;
        	System.out.println("Call to example.txt");
        	file1 = client.readFile("example.txt", "sarang");
        	System.out.println("content-> "+file1.getContent());
        	System.out.println("version-> "+ file1.getMeta().getVersion());
        	System.out.println("owner-> "+ file1.getMeta().getOwner());
        	System.out.println("filename-> "+ file1.getMeta().getFilename());
        	
        	file1 = client.readFile("sldfja.txt", "pawan");*/
        	
        	
	      transport.close();
	    } catch (TException x) {
	      x.printStackTrace();
	    }

	}
	
	private static String getSHA256Hash(String str) {
		
		try{
			MessageDigest  digest = MessageDigest.getInstance("SHA-256");
		
			byte[] encodedhash = digest.digest(str.getBytes("UTF-8"));
			
			 StringBuffer hexString = new StringBuffer();
			 
			 for (int i = 0; i < encodedhash.length; i++) {	
		    		String hex = Integer.toHexString(0xff & encodedhash[i]);
		    
		    		if(hex.length() == 1)
		    			hexString.append('0');
		        
		    		hexString.append(hex);
		    	}
		    
		    return hexString.toString();
	
		} catch(NoSuchAlgorithmException e){
			e.printStackTrace();	    
			throw new RuntimeException(e);
		}catch (UnsupportedEncodingException exception){
			exception.printStackTrace();
			throw new RuntimeException(exception);
		}
	}

}
