package ns.lolgg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ns.lolgg.domain.UserTier;

public interface UserTierRepository extends JpaRepository<UserTier, Long>{

}
