package models;

import java.util.ArrayList;

public class ServerManager
{
	Server server;
	ArrayList<Server> SM= new ArrayList<Server>();
	
	public void addServer(Server server) {
		SM.add(server);
	}
	
	
	
	/**
	 * @param sM
	 */
	public ServerManager(ArrayList<Server> sM) {
		super();
		SM = sM;
	}
	
	public ServerManager() {
		
	}



	public Server getServer() {
		return server;
	}



	public void setServer(Server server) {
		this.server = server;
	}



	public ArrayList<Server> getSM() {
		return SM;
	}



	public void setSM(ArrayList<Server> sM) {
		SM = sM;
	}



	public String getuserservers(User user) {
		String userservers="";
		for (int i=0; i < SM.size(); i++) {
			if (SM.get(i).server.containsKey(user)==true) {
				if (i==0) {
				userservers=userservers +(SM.get(i).name);
			}
				else {
					userservers=userservers +", "+ (SM.get(i).name);
				}
		}
	} return userservers;
}
}