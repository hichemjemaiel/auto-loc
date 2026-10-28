package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.entity.Maintenance;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
}
