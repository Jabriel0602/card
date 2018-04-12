import java.util.*;

/**
 * @author yangzhanbang
 * @date 2018/3/31 10:42
 * @desc
 */
public class volatileTest {

	private static boolean isSwitch=false;
	public static void main(String[] args) {
		new Thread(()->clickSwicth()).start();
		for (int i=0;i<20;i++){
			System.out.println(isSwitch);
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		List list= Arrays.asList(1,2,3);
		Collections.sort(list, (Integer x,Integer y)->x-y);
	}

	static void clickSwicth(){
		isSwitch=true;
	}
}
