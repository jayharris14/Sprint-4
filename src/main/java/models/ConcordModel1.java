package models;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class ConcordServer implements Remote {

		
	private static final long serialVersionUID= -2;
	private static final String SERIALIZED_FILE_NAME1="Concord.xml";
	
	private int visits=0;
	private String c;
	String run;
	Concord concord;
    Server server;
	User user;
	String check;
	DirectConversation directconversation;
	Channel channel;
    Role role;
    UserManager usermanager;
	private String password;
	String Y;
	ServerManager servermanager;
	
	ObservableList<Server> servers= FXCollections.observableArrayList();





	public Server getServer() {
		return server;
	}


	public ServerManager getServermanager() {
		return servermanager;
	}


	public void setServermanager(ServerManager servermanager) {
		this.servermanager = servermanager;
	}


	public void setServer(Server server) {
		this.server = server;
	}

	
	public String verify(String u, String pw) {
		visits++;
		System.out.println("verifying");
		int d = 0;
		check="";
		ConcordModel diskf2=this.ReadFromDisk();
		if (diskf2!=null)
		{
		for (int i=0; i<diskf2.usermanager.UM.size(); i++) {
			if (diskf2.usermanager.UM.get(i).userName.equals(u))
			{	System.out.println("hey");
				check="yes";
				d=i;
			}
		}
		if (check.equals("yes")){
			if (diskf2.usermanager.UM.get(d).password.equals(pw)) {
				c="permission granted";
			}
				
		}
		else {
			c="Access Denied";
			check="no";
		}
		}
		else {
			c="Access Denied";
		}
		return c;
	}
	
	public static void main(String[] args)
	{
		try
		{
			ConcordModel M = new ConcordModel();
			Naming.rebind("Concord.xml", M);
			Registry registry=LocateRegistry.createRegistry(2079);
			registry.bind("Concord", M);
			System.out.println("The RMI_Server is ready and running");
			
		 }catch (RemoteException e)
		{
			 e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	


	public int getVisits() {
		return visits;
	}


	public void setVisits(int visits) {
		this.visits = visits;
	}
	
	public String invite(User admin, User user) {
		String accept=server.invite(admin, user);
		return accept;
	}
	
	public String kick(User admin, User user) {
		String d=server.kick(user, admin);
		this.storeToDisk();
		return d;
	}
	
	public String addChannel(User admin, Channel channel){
		if (check=="yes") {
			Y= server.addChannel(admin, channel);
			if (Y=="yes") {
				run="success";
			}
			else {
				run="not authorized";
			}
		}
		else {
			run="not authorized";
			
		}
		this.storeToDisk();
		return run;
	}
	public String deleteChannel(User admin, Channel channel) {
		if (check=="yes") {
			Y=server.deleteChannel(admin, channel);
			if (Y=="yes") {
				run="success";
			}
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	public String addPin(Channel channel) {
		if (check=="yes") {
			server.addPin(channel);
				run="success";
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	public String unPin(Channel channel) {
		if (check=="yes") {
			server.unPin(channel);
				run="success";
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	public String addMember(User admin, User user) {
		if (check=="yes") {
			Y=server.addMember(admin, user);
			if (Y=="yes") {
				run="success";
			}
			
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	public String removeMember(User admin, User user) {
		if (check=="yes"){
			Y=server.removeMember(admin, user);
			if (Y=="yes") {
				run="success";
			}
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	
	public String changeRole(Role newrole, User user) {
		if (check=="yes") {
			server.changeRole(newrole, user);
				run="success";
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	
	public String sendMessage(Message message, User user, User user2)
	{
		if (check=="yes") {
			directconversation.sendMessage(message, user, user2);
			run="success";
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	public DirectConversation getDirectconversation() {
		return directconversation;
	}


	public void setDirectconversation(DirectConversation directconversation) {
		this.directconversation = directconversation;
	}


	public String setRoleBuilder(User user, Role role)
	{
		if (check=="yes") {
			server.setRoleBuilder(user, role);
			run="success";
			
		}
		else {
			run="not authorized";
			
		}
		this.storeToDisk();
		return run;
	}
	
	public void setPassword(User newuser, String password) {
		newuser.setPassword(password);
		this.storeToDisk();
	}

		public void addBlock(User BlockedUser)
		{
			user.addBlock(user);
			this.storeToDisk();
		}
		public void removeBlock(User BlockedUser)
		{
			user.removeBlock(user);
			this.storeToDisk();
		}
		public void setProfileData(String profileData)
		{
			user.setProfileData(profileData);
			this.storeToDisk();
		}
		public void setUserName(User newuser, String realName, String userName)
		{	
			newuser.realName=realName;
			newuser.userName=userName;
			this.storeToDisk();
			
		}
		
		
	
	
	public void storeToDisk()
	{
		XMLEncoder encoder=null;
		try {
			encoder=new XMLEncoder(new BufferedOutputStream
					(new FileOutputStream("Concord.xml")));
		}catch(FileNotFoundException fileNotFound) {
			System.out.println("ERROR: While Creating or Opening");
			
		}
	
		encoder.writeObject(this);
		encoder.close();

	}

	public UserManager getUsermanager() {
		return usermanager;
	}


	public void setUsermanager(UserManager usermanager) {
		this.usermanager = usermanager;
	}


	public static ConcordModel ReadFromDisk() 
	{	
			String decide="";
			XMLDecoder decoder=null;
			ConcordModel concorddata=null;
			try {
				decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream("Concord.xml")));
			} catch (FileNotFoundException e) {
				System.out.println("ERROR: File Concord.xml not found");
				decide="y";
			}
			if(decide!="y") {
				concorddata=(ConcordModel)decoder.readObject();
			}
			return concorddata;
			}
			
			


	public boolean serverequals(ConcordServer that) {
		ConcordModel x;
		x=this.ReadFromDisk();
		boolean y;
		if ((server.server.size())==(that.server.server.size())){
			y=true;
		}
		else {
			y=false;
		}
			return y;
			
		}
	
	public boolean channelsequals(ConcordServer that) {
		ConcordModel x;
		x=this.ReadFromDisk();
		boolean y;
		if (server.channels.size()==(that.server.channels.size())){
			y=true;
		}
		else {
			y=false;
		}
			return y;
			
		}
	
	public boolean directconversationequals(ConcordServer that) {
		ConcordModel x;
		x=this.ReadFromDisk();
		boolean y;
		if (directconversation.messages.size()==(that.directconversation.messages.size())){
			y=true;
		}
		else {
			y=false;
		}
			return y;
			
		}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	public void CreateUser(String realname, String username, String password, ConcordModel diskf2){
		if (diskf2!=null)
		{
		if (diskf2.usermanager.UM.size()==0) {
		ArrayList<User> UM=new ArrayList<User>();
		usermanager=new UserManager(UM);
		}
		else {
		user=new User(null, username, null, realname, 0, null, null, password);
		usermanager=diskf2.usermanager;
		this.usermanager.addUser(user);
		user=new User();
		}}
		else {
			ArrayList<User> UM=new ArrayList<User>();
			usermanager=new UserManager(UM);
			user=new User(null, username, null, realname, 0, null, null, password);
			this.usermanager.addUser(user);
			user=new User();
			
		}

		this.storeToDisk();
	}


	/**
	 * @param usermanager
	 */
	public ConcordModel() {
		super();
	
	}
	public ObservableList<Server> getuserservers(User user) {
		ConcordModel diskf2=this.ReadFromDisk();
		if (diskf2!=null)
		{
		String userservers="";
		for (int i=0; i < diskf2.servermanager.SM.size(); i++) {
			if (diskf2.servermanager.SM.get(i).server.containsKey(user)==true) {
				if (i==0) {
				userservers=userservers +(diskf2.servermanager.SM.get(i).name);
			}
				else {
					userservers=userservers +", "+ (diskf2.servermanager.SM.get(i).name);
				}
			}
		}
		}
		return servers;
	}

	public void CreateServer(String name, ConcordModel diskf2, User user) {
		if (diskf2!=null)
		{
		if (diskf2.servermanager.SM.size()==0) {
			ArrayList<Server> SM=new ArrayList<Server>();
			servermanager=new ServerManager(SM);
			}
			else {
			server=new Server(null, null, null, name, user);
			servermanager=diskf2.servermanager;
			this.servermanager.addServer(server);
			server=new Server();
			}}
			else {
				ArrayList<Server> SM=new ArrayList<Server>();
				servermanager=new ServerManager(SM);
				server=new Server(null, null, null, name, user);
				this.servermanager.addServer(server);
				server=new Server();
			
			
		}
		
	}

	/**
	 * 
	 */



	/**
	 *
	 */
	

	

	}


		
		




		




