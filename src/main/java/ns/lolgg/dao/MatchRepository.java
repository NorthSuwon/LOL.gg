package ns.lolgg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ns.lolgg.domain.Match;

public interface MatchRepository extends JpaRepository<Match, String>{
	
}
