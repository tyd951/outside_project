import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient extends Frame {
	
	TextField tfTxt = new TextField();
	TextArea taContent = new TextArea();
	
	Socket s = null;
	DataOutputStream dops = null;
	DataInputStream dis = null;
	private boolean bConnected = false;
	
	Thread tRecv = new Thread(new RecvThread()); 
	
	public static void main(String[] args) {
		new ChatClient().launchFrame();

	}
	
	public void launchFrame() {
		this.setLocation(750, 250);
		this.setTitle("Chat Client by Yida Tao");
		this.setSize(300, 300);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				disconnect();
				System.exit(0);
			}
			
		});
		this.add(tfTxt , BorderLayout.SOUTH);
		this.add(taContent , BorderLayout.NORTH);
		this.pack();
		tfTxt.addActionListener(new TxtFieldListener());
		this.setVisible(true);
		
		connect();
		tRecv.start();
	}
	
	public void connect() { 
		try {
			s = new Socket("127.0.0.1", 8888);
			dops = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
			bConnected = true;
			
//System.out.println("Connected");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void disconnect() {
		try {
			dops.close();
			dis.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		try {
			bConnected = false;
			tRecv.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				dos.close();
				dis.close();
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		*/
	}
	
	private class TxtFieldListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String str = tfTxt.getText().trim();
			//taContent.setText(str);
			tfTxt.setText("");
			
			try {
				dops.writeUTF(str);
				dops.flush();
				//dops.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	private class RecvThread implements Runnable {

		public void run() {
			try {
				while(bConnected) {
					String str = dis.readUTF();
					//System.out.println(str);
					taContent.setText(taContent.getText() + str + '\n');
				}
			} catch (SocketException e) {
				System.out.println("bye!");
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
		}
		
	}
}
