import com.alibaba.fastjson.JSONObject;
import com.card.common.util.ValidatorUtils;
import com.card.domain.card.Card;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.validation.ValidationException;
import java.util.Date;

/**
 * @author yangzhanbang
 * @Email yangzhanbang@jd.com
 * @date 2018/3/9 15:37
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath*:applicationContext.xml"})
public class BeanValidationTest {

	@Test
	public void a(){
		Card card=new Card();
		card.setId(1L);
		card.setCardId("asd111");
		card.setCardType("yct");
		card.setCreateTime(new Date());
		card.setRemark("sad");
		card.setUserPin("ss");
		card.setModifiedTime(new Date());
		card.setYn((byte)1);
		try {
			System.out.println(ValidatorUtils.validate(card));
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		}
		//输出结果为：test不能为空 ;用户ID不能为空 ;用户姓名不能为空dd ;
	}

	@Test
	public  void  testFastJson(){
		Card card=new Card();
		card.setCardType("");
		System.out.println(JSONObject.toJSONString(card));
		System.out.println(JSONObject.toJSONString(null));
	}

	@Test
	public void testBloomFilter(){

	}


	@Test
	public void typeTest(){
		int a=0;
		Integer b=0;
		System.out.println(b.getClass());



	}


}



