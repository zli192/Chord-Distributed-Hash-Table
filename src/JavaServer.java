
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;


import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;



import java.util.HashMap;

public class JavaServer {

	private static FileHandler fileHandler;
	private static FileStore.Processor<FileStore.Iface> processor;
	
	public static void main(String args[]){
		
		if(args.length!=1){
			System.out.println("Please enter port number");
			System.exit(0);
		}
		int portNumber = Integer.parseInt(args[0].trim());
		fileHandler = new FileHandler(portNumber);
		processor = new FileStore.Processor<FileStore.Iface>(fileHandler);
		startServer(processor,portNumber);
		
		
	}

	private static void startServer(FileStore.Processor<FileStore.Iface> processor,int portNumber) {
		try {
			
			TServerTransport serverTransport = new TServerSocket(portNumber);
			TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
			System.out.println("Staring the file server");
			server.serve();
			serverTransport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
}
}

