package ns.lolgg.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

@Entity(name = "MATCH")
public class Match {
	
	@Id
	@Column(name = "MATCH_ID")
	private String matchId;
	
	@Column(name = "GAME_CREATION")
	private Long gameCreation;
	
	@Column(name = "GAME_DURATION")
	private Long gameDuration;
	
	@OneToMany(mappedBy = "match", fetch=FetchType.LAZY)
	private List<MatchUser> matchUser;
}
