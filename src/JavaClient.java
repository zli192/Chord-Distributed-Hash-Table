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

	    NodeID node = client.getNodeSucc();
	    
	    System.out.println("Succ of " + args[1] + "is---  " + node.getId() + " ip: " + node.getIp());

	      transport.close();
	    } catch (TException x) {
	      x.printStackTrace();
	    }

	}
}
