import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	boolean started = false;
	ServerSocket ss = null;
	List<Client> clients = new ArrayList<Client>();

	public static void main(String[] args) {
		new ChatServer().start();
	}
	
	public void start() {
		try {
			ss = new ServerSocket(8888);
			started = true;
		} catch(BindException e) {
			System.out.println("port is not available");
			System.exit(0);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		try{
			while(started) {
				Socket s = ss.accept();
				Client c = new Client(s);
				new Thread(c).start();
				clients.add(c);

				//dips.close();
			}
		}  catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	class Client implements Runnable{
		private Socket s;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		private boolean bConnected = false;
		
		public Client(Socket s) {
			this.s = s;
			
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				bConnected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void send(String str) {
			try {
				dos.writeUTF(str);
//System.out.println("Sended!");
			} catch (IOException e) {
				clients.remove(this);
			}
		}
		
		@Override
		public void run() {
			System.out.println("a cilent connected");
			try{
				while(bConnected){
					String str = dis.readUTF();
System.out.println(str);
					for(int i = 0; i < clients.size(); i++){
						Client c = clients.get(i);
						c.send(str);
//System.out.println(clients.size());
					}
					
				}
			} catch (EOFException e) {
				//e.printStackTrace();
				System.out.println("Client cloesd!");
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(dis != null) dis.close();
					if(dos != null) dos.close();
					if(s != null) s.close();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				//if(c != null) clients.remove(c);
			}

		}
		
	}
}
