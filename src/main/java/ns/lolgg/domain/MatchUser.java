package ns.lolgg.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

@Entity(name = "MATCH_USER")
public class MatchUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long matchUserNum;
	//private int participantId;
	
	private String championName;
	
	private int kills;
	private int deaths;
	private int assists;
	
	private boolean win;
	private boolean firstBloodKill;
	private int maxKill;
	
	private String lane;
	private int primaryStyle;
	private int subStyle;
	
    private int summoner1Casts;
    private int summoner1Id;
    private int summoner2Casts;
    private int summoner2Id;
	
	private int item0;
	private int item1;
	private int item2;
	private int item3;
	private int item4;
	private int item5;
	private int item6;
	
	private int teamId;
	
	@ManyToOne
	@JoinColumn(name = "MATCH_ID")
	private Match match;
	
	@ManyToOne
	@JoinColumn(name = "USER_NUM")
	private User user;
}
