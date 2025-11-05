package pro.keepitalive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.keepitalive.Website;

import java.util.UUID;

public interface WebsiteRepository extends JpaRepository<Website, UUID> {
}