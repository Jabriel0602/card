import com.card.domain.adimage.AdImage;
import com.card.domain.card.Card;
import com.card.service.adimage.AdImageService;
import com.card.service.card.CardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @author yangzhanbang
 * @date 2018/3/18 15:12
 * @desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml"})
public class mybatisTest {

	@Autowired
	private AdImageService adImageService;

	@Test
	public void test() {
		AdImage adImage = new AdImage();
		adImage.setCreatedTime(new Date());
		adImage.setDesc("asd");
		adImage.setId(19L);
		adImage.setImgUrl("asdasd");
		adImage.setResponseUrl("sad");
		adImage.setOperator("test");


//		adImage.setModifiedTime(new Date());

		adImageService.insertSelective(adImage);
		List<AdImage> list = adImageService.findAllAdImage();
		System.out.println(list);
	}

}
