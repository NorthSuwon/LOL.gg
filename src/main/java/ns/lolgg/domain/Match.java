package ns.lolgg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
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
	private String gameCreation;
	
	@Column(name = "GAME_DURATION")
	private String gameDuration;
	
}
