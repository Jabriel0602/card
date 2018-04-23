package com.card.test;

import com.alibaba.fastjson.JSONObject;
import com.card.domain.adimage.AdImage;
import com.card.domain.card.Card;
import com.card.service.adimage.AdImageService;
import com.card.service.card.CardService;
import com.mysql.cj.xdevapi.JsonArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


/**
 * @author yangzhanbang
 * @date 2018/4/13 10:05
 * @desc
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath*:applicationContext.xml"})
public class CardTest {

	@Autowired
	CardService cardService;

	@Autowired
	AdImageService adImageService;

	@Test
	public void cardTest() {
//		Card card=new Card();
//		card.setCardId(104L);
//		card.setCardType("yct");
//		card.setCreateTime(new Date());
//		card.setModifiedTime(new Date());
//		card.setRemark("asd");
//		card.setYn((byte)1);
//		card.setUserId(234L);
//		cardService.insert(card);
		System.out.println(cardService.findCard(null));
		Card card = cardService.findCardById(104L);
		card.setRemark("asdasdasd");
		System.out.println(cardService.update(card));
		System.out.println(cardService.deleteById(104L));
	}

	@Test
	public void adImageTest() {
		AdImage adImage = new AdImage();
		adImage.setId(200L);
		adImage.setDesc("testAdimage");
		adImage.setImgUrl("testImgurl");
		adImage.setResponseUrl("testResponseUrl");
		adImage.setWeight(2);
		adImage.setPutOn(true);
		adImage.setReleaseStatus(true);
		adImage.setCreatedTime(new Date());
		adImage.setModifiedTime(new Date());
		adImage.setCreatedTime(new Date());
		adImage.setEndTime(new Date());
		adImage.setOperator("testUser");
		adImageService.insertSelective(adImage);
		AdImage adImage1 = adImageService.findAdImageById(200L);
		adImage1.setDesc("asdasdasd");
		adImageService.update(adImage1);
		adImageService.delete(200L);
	}

	@Test
	public  void jsonTest(){
		JsonArray jsonArray=new JsonArray();
//		jsonArray.get(1);
		JSONObject jsonObject=new JSONObject();
		System.out.println(jsonObject.get("a"));
	}
}
