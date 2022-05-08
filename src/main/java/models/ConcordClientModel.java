package models;


import java.rmi.*;



public class ConcordClient {
	
	public static void main(String[] args) 
	{ new  ConcordClient().go();
	}
	public void go() {
	try {
		ConcordServerInterface cs =(ConcordServerInterface) Naming.lookup("rmi://10.14.1.69/Concord");
		int a= cs.addnumbers(0, 1);
		
		System.out.println(a);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	


	}
}
