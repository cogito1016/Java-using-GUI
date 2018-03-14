import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/*20144675김재형
 * 
 * 1주 
 * 과제 : 우주배경에서,  우주선이 포물선으로날아가는 것을 작성하여보아라.
 *
 * 2주
 * 과제 : 1. 키이벤트를받아 입력받은 키에따라 움직이는 우주선을 구현한다.
 *       2. 우주선에서 쏘아지는 미사일을 구현한다.
 *       3. 미사일을 ArrayList를 이용해 동적으로 구현하고 여러발을 쏘아대는것을 구현한다.
 *       4. 벽에닿으면 사라지는 미사일을 구현한다.
 *       
 *       
*/

class Sprite{
	public int dx;
	public int dy;
	public int x;
	public int y;
	public BufferedImage image;
	
	public int getX() {return x;}
	
	public int getY() {return y;}
	
	public Image getImage(){return image;}
	
}


//우주선의 정보를 담은 클래스 생성
class Spaceship extends Sprite{

	
	Missile m=null;
	
	
	public Spaceship()
	{
	     try {
	        image = ImageIO.read(new File("SpaceShip.png"));
	     }catch (IOException e) {
	        e.printStackTrace();
	      }
		x=500;
		y=400;
	}
	
	public void fire()
	{
		m=new Missile(x,y);
	}
	
	public Missile getMissile() {return m;}

	
	public void move()
	{
		x+=dx;
		y+=dy;
	}

	public void keyPressed(KeyEvent e)
	{
		int keycode=e.getKeyCode();
		switch(keycode)
		{
		case KeyEvent.VK_LEFT: dx=-1; break;		
		case KeyEvent.VK_RIGHT: dx=1; break;			
		case KeyEvent.VK_UP: dy=-1; break;			
		case KeyEvent.VK_DOWN: dy=1; break;		
		case KeyEvent.VK_SPACE: fire(); break;
		}
	
	}
	
	public void keyReleased(KeyEvent e)
	{
		int keycode=e.getKeyCode();
		
		switch(keycode)
		{
		case KeyEvent.VK_LEFT: dx=0; break;		
		case KeyEvent.VK_RIGHT: dx=0; break;			
		case KeyEvent.VK_UP: dy=0; break;			
		case KeyEvent.VK_DOWN: dy=0; break;
		}
	}
}

class Board extends JPanel implements ActionListener, KeyListener
{

	
	private BufferedImage image;
	
	private Timer timer;
	private Spaceship ship;
	private final int DELAY = 10;
	
	//배경이미지의 가로와 높이의길이를 넣어줄 변수를 생성. 
	private int WIDTH ,HEIGHT;
	
	public Board()
	{
		addKeyListener(this);
		setFocusable(true);
		setBackground(Color.BLACK);
		
	      try {
	    	  	 image =ImageIO.read(new File("Space.jpg"));
	      }catch (IOException e) {
	         e.printStackTrace();
	      }
	    WIDTH = image.getWidth(null);
	    HEIGHT = image.getHeight(null);
		
		ship = new Spaceship();
		timer = new Timer(DELAY,this);
		timer.start();
	}
	
	/*이미지 크기에 패널을 맞춘다
	 *getPreferredSize()는 컴포넌트가 원하는 크기를 배치 관리자에게 알리는 메소드이다
	 현재 읽은 이미지의 크기로 다시 설정한다 */
	public Dimension getPreferredSize() {
		if(image==null) {
			return new Dimension(100,100);
			}else {
				return new Dimension(WIDTH,HEIGHT);
				}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(ship.getImage(), ship.getX(),ship.getY(),this);
		if(ship.getMissile() !=null)
			g2d.drawImage(ship.getMissile().getImage(),ship.getMissile().getX(),ship.getMissile().getY(),this);
		Toolkit.getDefaultToolkit().sync();
		}
	
	public void actionPerformed(ActionEvent e) {
		ship.move();
		if(ship.getMissile() !=null)
			ship.getMissile().move();
		repaint();
	}
	
	public void keyPressed(KeyEvent e) {ship.keyPressed(e);}
	public void keyReleased(KeyEvent e) {ship.keyReleased(e);}
	public void keyTyped(KeyEvent arg0){}
}

class Missile extends Sprite{


	
	private final int MISSILE_SPEED = 2;	
	boolean visible=true;
	public Missile(int x, int y)
	{
	    try {
	    	 image =ImageIO.read(new File("Missile.png"));
	    }catch (IOException e) {
	     e.printStackTrace();
	    }
	    dx=1;
	    dy=1;
	    
		this.x=x;
		this.y=y;
	}
	public void move()
	{
		y-=MISSILE_SPEED;
		if(y<0)
		{
			visible=false;
		}
	}

}
public class MyImageFrame extends JFrame
{
	public MyImageFrame()
	{
		add(new Board());
		setTitle("star");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		MyImageFrame ex = new MyImageFrame();
	}
}

