package ns.lolgg;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ns.lolgg.util.LolUtil;

@SpringBootTest
class LoLggApplicationTests {

	public void apiTest() {
		try {
			System.out.println(LolUtil.getSummoners("짱태열"));
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void apiTest2() {
		try {
			LolUtil.getSummonerMatchList("yqNTd4Zau2bRnY4Ouq5RQMGvEx-qwL6Qtm90u3q9UsrCC49oGfu2ldzodTOJGhlbCiE_rGK2gwL1RQ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void apiTest3() {
		try {
			System.out.println(LolUtil.getMatchDetail("KR_5123658648"));
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}
}
