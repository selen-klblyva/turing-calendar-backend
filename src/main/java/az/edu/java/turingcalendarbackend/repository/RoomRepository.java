package az.edu.java.turingcalendarbackend.repository;


import az.edu.java.turingcalendarbackend.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    Optional<RoomEntity> findByName(String name);

    List<RoomEntity> findByIsAvailableTrue();
}

