//package ns.lolgg;
//
//import java.io.IOException;
//import java.util.List;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.ParseException;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import ns.lolgg.domain.Match;
//import ns.lolgg.domain.MatchUser;
//import ns.lolgg.util.LolUtil;
//
//@SpringBootTest
//class LoLggApplicationTests {
//	
//	
//	
//	public void apiTest() {
//		LolUtil lolUtil = new LolUtil();
//		try {
//			System.out.println(lolUtil.getSummoners("짱태열"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (IOException e){
//			e.printStackTrace();
//		}
//	}
//	
//	public void apiTest2() {
//		LolUtil lolUtil = new LolUtil();
//		try {
//			LolUtil.getSummonerMatchList("yqNTd4Zau2bRnY4Ouq5RQMGvEx-qwL6Qtm90u3q9UsrCC49oGfu2ldzodTOJGhlbCiE_rGK2gwL1RQ");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void apiTest3() {
//		try {
//			System.out.println(LolUtil.getMatchDetail("KR_5123658648"));
//		} catch (ParseException | IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void apiTest4() throws ParseException, IOException {
//		
//		String matchId = "KR_5123658648";
//		JSONObject obj = (JSONObject) LolUtil.getMatchDetail(matchId);
//		
//		JSONObject info = (JSONObject) obj.get("info");
//		Match match = Match.builder().matchId(matchId)
//			.gameCreation((Long) info.get("gameCreation"))
//			.gameDuration((Long) info.get("gameDuration"))
//			.build();
//		System.out.println(match);
//	}
//	
//	@Test
//	public void apiTest5() throws ParseException, IOException {
//		
//		String matchId = "KR_5123658648";
//		JSONObject obj = (JSONObject) LolUtil.getMatchDetail(matchId);
//		
//		JSONObject info = (JSONObject) obj.get("info");
//		JSONObject metadata = (JSONObject) obj.get("metadata");
//		//List<String> participants = LolUtil.stringToList((String) metadata.get("participants"));
//		System.out.println(metadata.get("participants"));
//		
//		JSONArray participantsInfo = (JSONArray) info.get("participants");
//		int len = participantsInfo.size();
//		for (int idx=0; idx<len; idx++) {
//			JSONObject detail = (JSONObject) participantsInfo.get(idx);
//			
//			String[] kill = {"pentaKills", "quadraKills", "tripleKills", "doubleKills"};
//			String maxKill = "NONE";
//			for (String k : kill) {
//				if ((Long) detail.get(k)>0) {
//					maxKill = k;
//					break;
//				}
//			}
//			
//			MatchUser match = MatchUser.builder()
//				.championName((String) detail.get("championName"))
//				.kills((Long) detail.get("kills"))
//				.deaths((Long) detail.get("deaths"))
//				.assists((Long) detail.get("assists"))
//				.win((boolean) detail.get("win"))
//				.firstBloodKill((boolean) detail.get("firstBloodKill"))
//				.maxKill(maxKill)
//				.lane((String) detail.get("lane"))
//				.primaryStyle((Long) detail.get("primaryStyle"))
//				.subStyle((Long) detail.get("subStyle"))
//				.summoner1Casts((Long) detail.get("summoner1Casts"))
//				.summoner1Id((Long) detail.get("summoner1Id"))
//				.summoner2Casts((Long) detail.get("summoner2Casts"))
//				.summoner2Id((Long) detail.get("summoner2Id"))
//				.item0((Long) detail.get("item0"))
//				.item1((Long) detail.get("item1"))
//				.item2((Long) detail.get("item2"))
//				.item3((Long) detail.get("item3"))
//				.item4((Long) detail.get("item4"))
//				.item5((Long) detail.get("item5"))
//				.item6((Long) detail.get("item6"))
//				.teamId((Long) detail.get("teamId"))
//				.build();
//			System.out.println(match);
//		}
//	}
//}
