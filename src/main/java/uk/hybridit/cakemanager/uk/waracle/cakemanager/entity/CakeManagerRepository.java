package uk.hybridit.cakemanager.uk.waracle.cakemanager.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CakeManagerRepository extends JpaRepository<Cake, Long> {
}
