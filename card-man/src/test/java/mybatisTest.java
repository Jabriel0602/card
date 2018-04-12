import com.card.domain.card.Card;
import com.card.service.card.CardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author yangzhanbang
 * @date 2018/3/18 15:12
 * @desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml"})
public class mybatisTest {

	@Autowired
	private CardService cardService;

	@Test
	public void test(){
		Card card=new Card();
		card.setId(3L);
		card.setCardId("asd111");
		card.setCardType("yct");
		card.setCreateTime(new Date());
		card.setRemark("sad");
		card.setUserPin("ss");
		card.setModifiedTime(new Date());
		card.setYn((byte)1);
		System.out.println(cardService.insert(card));
	}

}
