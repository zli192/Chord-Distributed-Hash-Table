Implemented basic distributed hash table (DHT) with an architecture similar to the Chord System

Desciption::
Implemented following 6 server side methods

writeFile(): Given a name, owner, and contents, the corresponding file is written to the server.

readFile(): File with a given name and owner, server returns both the contents and meta-information. 

setFingertable(): Sets the current node’s fingertable to the fingertable provided in the argument of the function.

findSucc(): Given an identifier in the DHT’s key space, returns the DHT node that owns the id. 

findPred(): Given an identifier in the DHT’s key space, this function  returns the DHT node that immediately
precedes the id. 

getNodeSucc(): Returns the closest DHT node that follows the current node in the Chord key space. A SystemException
should be thrown if no fingertable exists for the current node.

Steps:

Initialize fingertable 
$./init node.txt

