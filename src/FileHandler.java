import java.net.InetAddress;
import java.security.MessageDigest;
import java.util.*;

import org.apache.thrift.TException;

import javafx.scene.Node;

public class FileHandler implements FileStore.Iface{

	private static List<NodeID> nodeList;
	private int currentNodePortNum = 0;
	
	public FileHandler(int port){
		currentNodePortNum = port;
		nodeList = new ArrayList<NodeID>();		
			
	}

	@Override
	public void writeFile(RFile rFile) throws TException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RFile readFile(String filename, String owner) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFingertable(List<NodeID> node_list) throws TException {
		// TODO Auto-generated method stub
		for(NodeID node : node_list) {
			nodeList.add(node);
			
			System.out.println("Node Id " + node.getId());
			System.out.println("Node ip "+ node.getIp());
			System.out.println("port " + node.getPort());
		}
		
	}

	@Override
	public NodeID findSucc(String key) throws TException {
		NodeID succNode = null;
		NodeID predecessorNode = null; 
		
		predecessorNode = findPred(key);
		
		//get its successor
		if(predecessorNode != null) {
			  try {
				    TTransport transport;
				    String host = predecessorNode.getIp();
				    int portNum = predecessorNode.getPort();
				    
				    System.out.println("Predecessor Node ip and port " + host + "-" + portNum);
			        transport = new TSocket(host,portNum);
			        transport.open();	     
	
			        TProtocol protocol = new  TBinaryProtocol(transport);
			        FileStore.Client client = new FileStore.Client(protocol);
	
			        try {
			        	succNode = client.getNodeSucc();  
			        }catch(SystemException systemException) {
				    	throw systemException;
				    	System.exit(0);
				    }	
	
				    transport.close();
			    } 		  
			  	catch (TException x) {
			      x.printStackTrace();
			      System.exit(0);
			    }
		}
		
		return succNode;
	}

	@Override
	public NodeID findPred(String key) throws TException {
		
		String currentNodeIPAddr;
		String nodeId;
		
		try {
			currentNodeIPAddr = InetAddress.getLocalHost().getHostAddress();
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println("currentNodeIp addr is " + currentNodeIPAddr + ":" + currentNodePortNum);
		
		String value = currentNodeIPAddr + ":" +Integer.toString(currentNodePortNum);
		nodeId = getSHA256Hash(value);
		System.out.println("generate Nodeid is " + nodeId);
		
		NodeID currentNode = new NodeID();
		currentNode.setId(nodeId);
		currentNode.setIp(currentNodeIPAddr);
		currentNode.setPort(currentNodePortNum);
		
		return currentNode;
	}

	@Override
	public NodeID getNodeSucc() throws TException {
		NodeID successorNode = null;
		if(nodeList.size()>0){
			successorNode = nodeList.get(0);
		}else {
			throw new SystemException();
		}
		return successorNode;				
	}
	
	/**
	 * This method generates SHA-256 hash of input string
	 * Reference-http://www.baeldung.com/sha-256-hashing-java
	 * @param str
	 * @return
	 */
	private String getSHA256Hash(String str) {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
		
		 StringBuffer hexString = new StringBuffer();
		 
	    for (int i = 0; i < encodedhash.length; i++) {	
	    	String hex = Integer.toHexString(0xff & hash[i]);
	    
	    	if(hex.length() == 1)
	    		hexString.append('0');
	        
	    	hexString.append(hex);
	    }
	    
	    return hexString.toString();
	}

}
