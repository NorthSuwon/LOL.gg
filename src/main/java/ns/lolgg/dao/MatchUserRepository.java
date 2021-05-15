package ns.lolgg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ns.lolgg.domain.MatchUser;

public interface MatchUserRepository extends JpaRepository<MatchUser, Long> {

}
