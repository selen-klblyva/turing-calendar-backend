package az.edu.java.turingcalendarbackend.repository;


import az.edu.java.turingcalendarbackend.entity.SlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<SlotEntity, Long> {
    List<SlotEntity> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

    List<SlotEntity> findByRoomAndStartTimeBetween(String room, LocalDateTime start, LocalDateTime end);
}

