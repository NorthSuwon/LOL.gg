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
	private Long matchUserNum;
	
	private String championName;
	private Long level;
	private Long cs;
	
	private Long kills;
	private Long deaths;
	private Long assists;
	
	private boolean win;
	private boolean firstBloodKill;
	private String maxKill;
	
	private String lane;
	private String primaryStyle;
	private String subStyle;
	
    private String summoner1Id;
    private String summoner2Id;
	
	private Long item0;
	private Long item1;
	private Long item2;
	private Long item3;
	private Long item4;
	private Long item5;
	private Long item6;
	
	private Long teamId;
	
	@ManyToOne
	@JoinColumn(name = "MATCH_ID")
	private Match match;
	
	@ManyToOne
	@JoinColumn(name = "USER_NUM")
	private User user;
	
}
