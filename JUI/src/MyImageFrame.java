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
 *       5.Sprite로 공통된 필드와 메소드를정리한다.
 *       
 *       
*/

//공통된 필드와메소드를 모아두는 클래스를 구현 합니다.
class Sprite{
	/*x와 y좌표의 값 필드인 x,y를 선언하고
	 *x와 y좌표의 움직이는 속도를 구현하는 필드인 dx,dy를 선언하고
	 *이미지필드 image를 선언합니다. */
	public int dx;
	public int dy;
	public int x;
	public int y;
	public BufferedImage image;
	
	//접근자 getX와 getY그리고 getImage를 구현합니다.
	public int getX() {return x;}
	public int getY() {return y;}
	public Image getImage(){return image;}
	
}


//우주선의 정보를 담은 클래스 생성합니다.
class Spaceship extends Sprite{
	//미사일크래스의 필드 m을 선언합니다.
	Missile m=null;
	
	//우주선을구현하는 클래스의생성자를 구현합니다.
	public Spaceship()
	{
		//생성자에서는 우주선의 이미지를 설정하고 예외처리로 종료하게끔 만듭니다.
	     try {
	        image = ImageIO.read(new File("SpaceShip.png"));
	     }catch (IOException e) {
	        e.printStackTrace();
	      }
	    //이것은 초기 우주선의 위치값을 설정한 것 입니다.
		x=500;
		y=400;
	}
	
	/*미사일을 발사하는 메소드 fire입니다.
	 *이를통해 선언해준 미사일클래스필드 m에 우주선의 x,y값을 인수로받은
	 *미사일객체 하나를 넣어줍니다.
	 *그럼 미사일필드m은 더이상 null이 아닌 x,y값을갖는 객체가 되겠죠?*/
	public void fire(){m=new Missile(x,y);}
	
	//미사일의 정보를 반환하는 메소드입니다.
	public Missile getMissile() {return m;}

	//움직임을 표현한 메소드 move입니다.
	public void move()
	{
		x+=dx;
		y+=dy;
	}

	//키를눌렀을때 발생하는 이벤트를 위한 클래스 KeyPressed입니다. 
	public void keyPressed(KeyEvent e)
	{
		//키를입력받으면 그 값을 keycode값에 정수형으로 저장합니다.
		int keycode=e.getKeyCode();

		/*그리고 스위치문으로 keycode를 분석하여 상하좌우, 그리고 Spacevbar를 판단합니다.
		 *상하좌우는 그방향으로 움직이기위함이고, SpaceBar는 미사일발사메소드 fire()의 값을
		 *호출하는것이 목적입니다.*/
		switch(keycode)
		{
		case KeyEvent.VK_LEFT: dx=-1; break;		
		case KeyEvent.VK_RIGHT: dx=1; break;			
		case KeyEvent.VK_UP: dy=-1; break;			
		case KeyEvent.VK_DOWN: dy=1; break;		
		case KeyEvent.VK_SPACE: fire(); break;
		}
	
	}
	
	//키를눌렀다가 떼었을때 발생하는 이벤트를 위한 클래스 KeyReleased입니다. 
	public void keyReleased(KeyEvent e)
	{
		//키를입력받으면 그 값을 keycode값에 정수형으로 저장합니다.
		int keycode=e.getKeyCode();
		
		/*그리고 스위치문으로 keycode를 분석하여 상하좌우를  판단합니다.
		 *눌렀다가 떼었을 시 상하좌우 어느방향이든 그대로 멈추게하는 동작입니다. .*/
		switch(keycode)
		{
		case KeyEvent.VK_LEFT: dx=0; break;		
		case KeyEvent.VK_RIGHT: dx=0; break;			
		case KeyEvent.VK_UP: dy=0; break;			
		case KeyEvent.VK_DOWN: dy=0; break;
		}
	}
}

//ActionListener와 KeyListener을 구현하고 JPanel을 상속받는 배경클래스를 구현합니다.
class SpaceBack extends JPanel implements ActionListener, KeyListener
{
	/*배경이미지를위한 이미지필드인 image를 선언하고 
	 *딜레이를위한 타이머 필드, 그리고 우주선필드, 그리고 딜레이읟값을 넣은 DELAY필드를 선언해줍니다.*/
	private BufferedImage image;
	private Timer timer;
	private Spaceship ship;
	private final int DELAY = 10;
	
	//배경이미지의 가로와 높이의길이를 넣어줄 변수를 생성. 
	private int WIDTH ,HEIGHT;
	
	//클래스의 생성자를 구현합니다.
	public SpaceBack()
	{
		//키리스너를이곳에 포함시킵니다.
		addKeyListener(this);
		setFocusable(true);

		//생성자에서는 우주배경 이미지를 설정하고 예외처리로 종료하게끔 만듭니다.
	      try {
	    	  	 image =ImageIO.read(new File("Space.jpg"));
	      }catch (IOException e) {
	         e.printStackTrace();
	      }

	     //WIDThdㅘ HEIGHT는 읽은 배경화면이미지의 가로 높이의 값을 받습니다.
	    WIDTH = image.getWidth(null);
	    HEIGHT = image.getHeight(null);
		
		/*선언해준 필드 ship에 우주선의 객체를 넣어줍니다.
		 *마찬가지로 Timer도 DELAY의 값을 넣은 객체를 넣어주고
		 *Timer을 시작합니다.*/
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
	
	//그림을 그리는 paintComponent메소드입니다.	
	public void paintComponent(Graphics g) {
		//부모클래스의 메소드를 호출하기위해 super을 사용합니다.
		super.paintComponent(g);
		//0,0위치에 image를 그립니다 여기서 image란 배경화면을 뜻합니다.
		g.drawImage(image, 0, 0, this);
		//		
		Graphics2D g2d = (Graphics2D) g;
		//우주선의 이미지를 우주선의현재위치(x,y)값에 그려줍니다.
		g2d.drawImage(ship.getImage(), ship.getX(),ship.getY(),this);
		/*만약에 미사일의값이 null이아니면 => 이 뜻은 우주선에서 미사일을 Fire()시켰다는 의미이먀
		 *따라서 getmisile()의 값은 null이 아닌, 우주선의 현재위치(x,y)값이있는 객체를 의미합니다.
		 *그렇기때문에 미사일을그리기위한 목적의 drawImage메소드에는
		 *우주선에있는 미사일의 이미지, 우주선에있는 미사일의 위치(x,y)가 입력받아지고 그대로 그리면 됩니다.*/
		if(ship.getMissile() !=null)
			g2d.drawImage(ship.getMissile().getImage(),ship.getMissile().getX(),ship.getMissile().getY(),this);
		Toolkit.getDefaultToolkit().sync();
		}
	
	//이는 액션이벤트를 처리하는 메소드입니다.
	public void actionPerformed(ActionEvent e) {
		//우주선을움직이는 메소드를 호출합니다.
		ship.move();
		/*만약 미사일의 값이 null이 아니면(이는 181~184라인에입력한 주석의내용과 일치합니다.)
		 *우주선에있는 미사일을 움직이는 메소드를 호출합니다. 미사일이 나타난다는것이겠죠!*/
		if(ship.getMissile() !=null)
			ship.getMissile().move();

		//계속하여 다시 그려줍니다.
		repaint();
	}
	
	/*키를받는 메소드들입니다.
	 *키를 입력받았을 때에 우주선에있는 메소드를 호출하여 우주선의 이동값을 변경시켜
	 *결과적으로 키를눌렀을 시 그 방향으로 움직이게하고, 떼었을 시 우주선을 멈추게하는
	 *그런동작을 하게끔 해주는 메소드들입니다.*/
	public void keyPressed(KeyEvent e) {ship.keyPressed(e);}
	public void keyReleased(KeyEvent e) {ship.keyReleased(e);}
	public void keyTyped(KeyEvent arg0){}
}

//미사일의 정보가있는 클래스입니다.
class Missile extends Sprite{
	//미사일스피드를 정하는 필드를 선언합니다 초기값은 2입니다.
	private final int MISSILE_SPEED = 2;
	//visible의값을 넣는 필드를 선언합니다. (불리언 타입입니다)	
	boolean visible=true;

	//미사일클래스의 생성자를 선언합니다 미사일클래스는 매개변수 x와y를 받습니다.
	public Missile(int x, int y)
	{
		//생성자에서는 미사일 이미지를 설정하고 예외처리로 종료하게끔 만듭니다.
	    try {
	    	 image =ImageIO.read(new File("Missile.png"));
	    }catch (IOException e) {
	     e.printStackTrace();
	    }
	    //입력받은 매개변수 x,y의 값을 이 현재 객체 x,y에 할당합니다.
		this.x=x;
		this.y=y;
	}

	//움직이는것을 구현한 메소등비니다.
	public void move()
	{
		/*y의값을 미사일의스피드만큼 점점 감소시킵니다. 점점위로올라간다는 얘기이겠습니다.
		 *쭉쭉올라가서 이제 미사일이 화면끝까지 올라가면 더이상 그릴필요가없습니다.
		 *따라서 안보이게 visible=false시킵니다.*/
		y-=MISSILE_SPEED;		
		if(y<0){visible=false;}
	}
}

//이제 프레임을 그려주는 클래스를 구현합니다.
public class MyImageFrame extends JFrame
{
	//프레임의생성자입니다.
	public MyImageFrame()
	{
		/*우주배경화면의 객체를 추가시킵니다.
		 *제목을 설정하고 X를누를시 종료하는것을 설정해줍니다.
		 *Pack은 압축하여 올려주는것을 의미합니다.
		 *SetVisible(True)로서 화면에 이를 표시합니다.*/
		add(new SpaceBack());
		setTitle("star");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	//메인메소드에서는 프레임객체를 만들어줌으로서 프로그램을 실행시킵니다.
	public static void main(String[] args) {
		MyImageFrame ex = new MyImageFrame();
	}
}

