package JUI;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//미사일의 정보가있는 클래스입니다.
public class Missile extends Sprite{
	//미사일스피드를 정하는 필드를 선언합니다 초기값은 2입니다.
	private final int MISSILE_SPEED = 2;
	
	//visible의값을 넣는 필드를 선언합니다. (불리언 타입입니다)	
	//필요없는 코드가 아닌가...
	//boolean visible=true;
	
	/*Application ArrayList Part
	 * 객체가 화면을벗어나면 삭제되느것을 구현하기위한 spaceship메소드를사용하기위해 spaceship변수 ship을 선언합니다.*/
	//아니, 이것도필요가없는거같습니다..
	//private Spaceship ship;
	
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
		if(y>0)
			y-=MISSILE_SPEED;	
		
		/*
		if(y<0){
			
			//필요없는 코드가 아닌가..
			//visible=false;
			
			Application ArrayList Part
			 *y의값을넘어가면 mlist의 객체 list를 삭제합니다.
			//아니지,, 여기서 ship은 이 미사일에서 만든 ship필드니까.
			//ship.mlist.remove(0);
			}
			*/
			
	}
}
