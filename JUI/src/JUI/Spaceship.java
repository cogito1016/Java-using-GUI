package JUI;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//우주선의 정보를 담은 클래스 생성합니다.
public class Spaceship extends Sprite{
	//미사일크래스의 필드 m을 선언합니다.
	//Missile m=null;
	
	
	/*Application ArrayList Part
	 * Missile타입의 ArrayList를 선언해줍니다.*/
	ArrayList <Missile> mlist = new ArrayList<Missile>();
	//미사일객체m을 null값으로 선언하여 이값을갖는것을 ArrayList에 추가하는걸로 구조를잡아본다.
	//Missile m=null;
	//인덱스를선언합시다.
	private final int INDEX=0;
	
	
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
	//public void fire(){m= new Missile(x,y);}
	
	/*Application ArrayList Part
	 * fire()을 호출시 현재 우주선의 x,y값을 받는 Missile객체를 널값이였던 m에 넣어줍니다..
	 * 그리고 그 객체 m 을 미사일타입의 어레이리스트인 mlist에 add시켜줍니다.*/
	public void fire(){
		Missile m;
		m=new Missile(x,y);
		mlist.add(m);
		
		System.out.println("현재 리스트의크기 "+mlist.size());
		for(int j=0;j<mlist.size();j++)
			System.out.println("객체"+mlist.get(j)+"해당 객체의 위치좌표 = "+mlist.get(j).x+" "+mlist.get(j).y);
		
		}
	
	/*Application ArrayList Part
	 * 미사일의 정보를 반환하는 getMissile()메소드입니다.
	 * 평소에는 객체하나 'm'밖에없어서 그것만 반환하였었지만
	 * 이제는 ArrayList이니 리스트안에있는 첫번째요소의 값을반환하는걸로 메소드를 수정해줄 것 입니다.
	 * If문에대한설명
	 * 		If문은 mlist에 m이라는객체가 있는지 없는지를 식별합니다.
	 * 		mlist에 객체m이 없는상태인데 객체를 반환할수는없기때문입니다.
	 * 		이부분을 간과하여 오류수십개가뜨는것을 겪었고 즉시 개선하였습니다.*/
	
	public Missile getMissile() {
		if(mlist.size()>0)
				return mlist.get(mlist.size()-1);
		else
			return null;
		}
	
	/*Application ArrayList Part
	 *미사일객체가 y값을넘어가면없어져야합니다.
	 *이는 미사일타입의 어레이리스트인 mlist에서 객체를 삭제해주는것으로 동작을구현합니다.
	 * If문에대한설명
	 * 		If문은 mlist에 m이라는객체가 있는지 없는지를 식별합니다.
	 * 		mlist에 객체m이 없는상태인데 객체를 반환할수는없기때문입니다.
	 * 		이부분을 간과하여 오류수십개가뜨는것을 겪었고 즉시 개선하였습니다.*/
	public void rmMisobj() {
		if(mlist.size()>0)
		{
			System.out.println("인덱스 mlist"+INDEX+"를 삭제합니다."+" 객체는 : "+mlist.get(INDEX));
			mlist.set(INDEX, null);
			mlist.remove(INDEX);}
	}
	
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