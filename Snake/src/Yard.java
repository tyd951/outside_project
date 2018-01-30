import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

public class Yard extends Frame {
	
	PaintThread paintThread = new PaintThread();
	private boolean gameOver = false;
	private static Yard yard;
	private static int speed = 150;
	
	public static final int ROWS = 30;
	public static final int COLS = 30;
	public static final int BLOCK_SIZE = 15;
	
	private Font fontGameOver = new Font("plain", Font.BOLD, 50);
	private int score = 0;
	
	Snake s = new Snake(this);
	Egg e = new Egg();
	
	Image offScreenImage = null;
	
	public void stop() {
		gameOver = true;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int i) {
		score = i;
	}

	public void launch() {
		this.setLocation(750, 250);
		this.setTitle("Snake by Yida Tao");
		this.setResizable(false);
		this.setSize(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		this.setVisible(true);
		this.addKeyListener(new KeyMonitor());
		
		new Thread(paintThread).start();
	}
	
	public void quit(){
		this.setVisible(false);
		this.dispose();
	}
	
	public static void main(String[] args) {
		yard = new Yard();
		yard.launch();
	}

	
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		g.setColor(Color.DARK_GRAY);
		/*
		for(int i = 0; i < (ROWS-1); i++){
			g.drawLine(0, BLOCK_SIZE * i, COLS * BLOCK_SIZE-2, BLOCK_SIZE * i);
		}
		
		for(int i = 0; i < (COLS-1); i++){
			g.drawLine(BLOCK_SIZE * i, 0 , BLOCK_SIZE * i, ROWS * BLOCK_SIZE-2);
		}
		*/
		g.setColor(Color.RED);
		g.drawString("Restart: F2", 10, 40);
		g.drawString("Quit: ESC", 10, 60);
		g.drawString("Score:" + score, 10, 80);
		
		
		g.drawString("Speed +: PAGE_UP", 325, 40);
		g.drawString("Speed -: PAGE_DOWN", 325, 60);
		
		
		if(gameOver) {
			g.setFont(fontGameOver);
			g.drawString("Game Over", 100, 180);
			
			paintThread.pause();
		}
		
		g.setColor(c);
		
		s.eat(e);
		s.draw(g);
		e.draw(g);
		
	}
	
	
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0,  null);
	}
	
	
	/**
	 * 
	 * @author Yida
	 * inner classes: PaintThread, KeyMonitor
	 */
	private class PaintThread implements Runnable {
		private boolean running = true;
		private boolean pause = false;
		public void run() {
			while(running) {
				if(pause) continue; 
				else {
					repaint();
				}
				try{
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void pause() {
			this.pause = true;
		}
		
		public void reStart() {
			yard.quit();
			yard = new Yard();
			yard.launch();
		}
		
		public void gameOver() {
			running = false;
		}
	}
	
	private class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_F2) {
				paintThread.reStart();
			}
			else if(key == KeyEvent.VK_ESCAPE){
				System.exit(0);
			}
			
			else if(key == KeyEvent.VK_PAGE_UP){
				if(speed >= 50) speed -= 20;
				
			}
			else if(key == KeyEvent.VK_PAGE_DOWN){
				if(speed <= 400) speed += 20;
			}
			
			s.keyPressed(e);
		}
		
	}


}
