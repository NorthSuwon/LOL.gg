package ns.lolgg.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LolUtil {

	private HttpClient client = HttpClientBuilder.create().build();
	private ResponseHandler<String> handler = new BasicResponseHandler();

	@Value("${lol.api.key}")
	private String key;
	
	public Map<Long, String> styles;
	
	public LolUtil() {
		
		this.styles = new HashMap<>();
		// sub
		styles.put(8100L, "7200_Domination");
		styles.put(8000L, "7201_Precision");
		styles.put(8300L, "7203_Whimsy");
		styles.put(8400L, "7204_Resolve");
		styles.put(8200L, "7202_Sorcery");
		
		// primary
		styles.put(8112L, "Domination/Electrocute/Electrocute");
		styles.put(8124L, "Domination/Predator/Predator");
		styles.put(8128L, "Domination/DarkHarvest/DarkHarvest");
		styles.put(9923L, "Domination/HailOfBlades/HailOfBlades");
		
		styles.put(9923L, "Precision/PressTheAttack/PressTheAttack");
		styles.put(8008L, "Precision/LethalTempo/LethalTempoTemp");
		styles.put(8021L, "Precision/FleetFootwork/FleetFootwork");
		styles.put(8010L, "Precision/Conqueror/Conqueror");
		
		styles.put(8351L, "Inspiration/GlacialAugment/GlacialAugment");
		styles.put(8360L, "Inspiration/UnsealedSpellbook/UnsealedSpellbook");
		styles.put(8358L, "Inspiration/MasterKey/MasterKey");
		
		styles.put(8437L, "Resolve/GraspOfTheUndying/GraspOfTheUndying");
		styles.put(8439L, "Resolve/VeteranAftershock/VeteranAftershock");
		styles.put(8465L, "Resolve/Guardian/Guardian");
		
		styles.put(8214L, "Sorcery/SummonAery/SummonAery");
		styles.put(8229L, "Sorcery/ArcaneComet/ArcaneComet");
		styles.put(8230L, "Sorcery/PhaseRush/PhaseRush");
		
		// summoner spell
		styles.put(21L, "SummonerBarrier");
		styles.put(1L, "SummonerBoost");
		styles.put(14L, "SummonerDot");
		styles.put(3L, "SummonerExhaust");
		styles.put(4L, "SummonerFlash");
		styles.put(6L, "SummonerHaste");
		styles.put(7L, "SummonerHeal");
		styles.put(13L, "SummonerMana");
		styles.put(30L, "SummonerPoroRecall");
		styles.put(31L, "SummonerPoroThrow");
		styles.put(11L, "SummonerSmite");
		styles.put(39L, "SummonerSnowURFSnowball_Mark");
		styles.put(32L, "SummonerSnowball");
		styles.put(12L, "SummonerTeleport");
	}
	
	
	public String getKey() {
		return key;
	}

	private String common(HttpGet getRequest) throws IOException {

		HttpResponse response = client.execute(getRequest);
		if (response.getStatusLine().getStatusCode() == 200) {
			return handler.handleResponse(response);
		}
		return null;
	}

	public List<String> stringToList(String s) {

		List<String> answer = new ArrayList<>();
		for (String ss : s.substring(1, s.length() - 1).split(",")) {
			answer.add(ss.substring(1, ss.length() - 1));
		}
		return answer;
	}

	/**
	 * LOL 닉네임을 통해 Summoner Id의 Stat을 반환한다.
	 * @return Summoner Stat Json 객체
	 * @param LOL 닉네임
	 */
	public JSONObject getSummoners(String userName) {
		try {
			return (JSONObject) new JSONParser().parse(common(
					new HttpGet("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + String.join("%20", userName.split(" ")) + "?api_key=" + key)));
		} catch(Exception e) {
			return null;
		}
		
	}

	/**
	 * LOL 닉네임을 통해 Summoner Id의 Stat을 반환한다.
	 * 
	 * @return Summoner Stat Json 객체
	 * @param LOL 닉네임
	 */
	public JSONObject getSummonersByPuuid(String puuid) throws ParseException, IOException {

		return (JSONObject) new JSONParser().parse(common(new HttpGet(
				"https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/" + puuid + "?api_key=" + key)));
	}

	/**
	 * Summoner의 puuid를 통해 최근 20경기 Match List를 반환한다.
	 * 
	 * @return 20개의 Match Id List
	 * @param Summoner의 puuid
	 */
	public List<String> getSummonerMatchList(String puuid) throws IOException {

		String s = common(new HttpGet(
				"https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuid + "/ids?api_key=" + key));
		return stringToList(s);
	}

	/**
	 * Match Id를 통해 해당 Match의 정보를 가져온다.
	 * 
	 * @return Match detatil Json Object
	 * @param Match Id
	 */
	public JSONObject getMatchDetail(String matchId) throws ParseException, IOException {

		return (JSONObject) new JSONParser().parse(common(
				new HttpGet("https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + key)));
	}
	
	public JSONArray getUserTier(String encryptedSummonerId) throws ParseException, IOException {
		
		return (JSONArray) new JSONParser().parse(common(
				new HttpGet("https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/" + encryptedSummonerId + "?api_key=" + key)));
	}
	
}
