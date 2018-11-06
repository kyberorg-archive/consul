package example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.app.domain.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	
}
