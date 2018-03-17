package JUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

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
 *       5. Sprite로 공통된 필드와 메소드를정리한다.
 *       
 *       ArrayList로 Missile은 어떻게 구현될까?
*/





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

