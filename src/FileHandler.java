import java.util.List;

import org.apache.thrift.TException;

import javafx.scene.Node;

public class FileHandler implements FileStore.Iface{

	public FileHandler(int port){
			
			
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
		for(Node node : node_list) {
			System.out.println("Node Id " + node.getId());
			System.out.println("Node ip "+ node.getIp());
			System.out.println("port " + node.getPort());
		}
		
	}

	@Override
	public NodeID findSucc(String key) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeID findPred(String key) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeID getNodeSucc() throws TException {
		// TODO Auto-generated method stub
		return null;
	}

}