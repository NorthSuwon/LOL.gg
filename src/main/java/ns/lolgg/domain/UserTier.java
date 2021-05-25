package ns.lolgg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder

@Entity(name = "USER_TIER")
public class UserTier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_TIER_NUM")
	private Long tierNum;
	
	private String soloTier;
	
	private String soloRank;
	
	private Long soloPoint;
	
	private Long soloWin;
	
	private Long soloLoss;
	
	private String fiveTier;
	
	private String fiveRank;
	
	private Long fivePoint;
	
	private Long fiveWin;
	
	private Long fiveLoss;
	
	public UserTier() {
		this.soloTier = "None";
		this.soloRank = "None";
		this.soloPoint = 0L;
		this.soloWin = 0L;
		this.soloLoss = 0L;
		
		this.fiveTier = "None";
		this.fiveRank = "None";
		this.fivePoint = 0L;
		this.fiveWin = 0L;
		this.fiveLoss = 0L;
	}
	
	public void refresh(JSONArray obj) {
		
		try {
			JSONObject solo = (JSONObject) obj.get(0);
			JSONObject five = (JSONObject) obj.get(1);
			
			this.soloTier = (String) solo.get("tier");
			this.soloRank = (String) solo.get("rank");
			this.soloPoint = (Long) solo.get("leaguePoints");
			this.soloWin = (Long) solo.get("wins");
			this.soloLoss = (Long) solo.get("losses");
			
			this.fiveTier = (String) five.get("tier");
			this.fiveRank = (String) five.get("rank");
			this.fivePoint = (Long) five.get("leaguePoints");
			this.fiveWin = (Long) five.get("wins");
			this.fiveLoss = (Long) five.get("losses");
		} catch (Exception e){
		}
		
	}
}
