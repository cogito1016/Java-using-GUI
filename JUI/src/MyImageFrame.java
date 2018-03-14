 
import java.awt.*; 
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/*20144675김재형
 * 
 * 첫째 
 
  과제 : 우주배경에서,  우주선이 포물선으로날아가는 것을 작성하여보아라. 
*/

//ActionListener인터페이스를 구현하고 JPanel을 상속받는 클래스 MyPanel생성.
class MyPanel extends JPanel implements ActionListener{

   //우주선의 시작위치 X값ㅂ과 Y값을넣줄변수 생성. 
   private final int START_X = 0;
   private final int START_Y = 350;
   //배경이미지의 가로와 높이의길이를 넣어줄 변수를 생성. 
   private int WIDTH ,HEIGHT;
   //이미지 필드 space와 ship을 각각 생성. 
   private BufferedImage SPACE, SHIP;
   //딜레이를 위한 타이머변수 생성.
   private Timer TIMER;
   //우주선의 X위치값과 Y위치값을 넣어줄변수 생성.
   private int POS_X, POS_Y;
   
   //MyPanel의 생성
   public MyPanel() {
	  //이 컴퍼넌트가 버퍼를 사용해 표현 할지 어떨지를 설정합니다.
      setDoubleBuffered(true);
      //이미지를 읽고 오류가 발생하면 프로그램을 종료하는 예외처리 
      try {
    	  	 SPACE =ImageIO.read(new File("Space.jpg"));
         SHIP = ImageIO.read(new File("SpaceShip.png"));
      }catch (IOException e) {
         e.printStackTrace();
      }
      
     //X와 Y의 위치ㅡ를 조정 
      POS_X = START_X;
      POS_Y = START_Y;
      WIDTH = SPACE.getWidth(null);
      HEIGHT = SPACE.getHeight(null);
      
      //딜레이를위한타이머 실행
      TIMER = new Timer(20, this);
      TIMER.start();
   }
	/*이미지 크기에 패널을 맞춘다
	 *getPreferredSize()는 컴포넌트가 원하는 크기를 배치 관리자에게 알리는 메소드이다
	 현재 읽은 이미지의 크기로 다시 설정한다 */
	public Dimension getPreferredSize() {
		if(SPACE==null) {
			return new Dimension(100,100);
		}else {
			return new Dimension(WIDTH,HEIGHT);
		}
	}
   //그림을 그리는 코드를넣는 페인트 콤퍼넌트를 재정의합니다.
	@Override
	public void paintComponent(Graphics g) {
	   //부모클래스가 그려야될 부분도 있기 때문에 호출하는 메소드 
	   super.paintComponent(g);
	   //Spaced와 Ship을그려준다   
	   g.drawImage(SPACE,0,0,this);	
	   g.drawImage(SHIP,POS_X,POS_Y,this);
	}
	
	//액션이벤트가 동작하는 액션퍼폼듣를 재정의합니다.
	@Override
	public void actionPerformed(ActionEvent e) {
	   //X값은 10씩증가하고,
	   POS_X+=10;
	   //Y값은 이러한 방적식으로 증가합니다.
	   POS_Y = (int)((0.05*POS_X-20)*(0.05*POS_X-20));
	   //만약 X값이 배경화면의 가로길이보다 커지면 처음으로 X와Y의값을 처음으로 되돌립니다.
	   if (POS_X > WIDTH) {
	      POS_X = START_X;
	      POS_Y = START_Y;
	   }
	   //위의 바뀌는 정보들로 하여금 다시 그려줍니다. 
	   repaint();
	}
}
   
//JFrame클래스를 상속받는 클래스 MyImageFrame을 만들어줍니다. 
public class MyImageFrame extends JFrame {
	
   //MyImageFram클래스의 생성자를 선언해준다
   public MyImageFrame() {
	  //제목을 정해준다.
      setTitle("SpaceTravel");
      //X버튼 누를 시 종료 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  //MyPanel객체를 프레임에 추가한다.
      add(new MyPanel());
	  //크기를 맞추어 압축시키는 역할 (이것을 빼고 쓰니까 Dimension이 작동을안함)
      pack();
      //화면에 표시 TRUE
      setVisible(true);
   }
   
   //메인입니다. 
   public static void main(String[] args) {
	  //MyImageFrame의 객체를선언하고 실행시킵니다.
      new MyImageFrame();   
   }
}