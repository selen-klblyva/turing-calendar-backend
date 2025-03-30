package az.edu.java.turingcalendarbackend.repository;

import az.edu.java.turingcalendarbackend.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    List<EventEntity> findByRoomId(Long roomId);

    List<EventEntity> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
