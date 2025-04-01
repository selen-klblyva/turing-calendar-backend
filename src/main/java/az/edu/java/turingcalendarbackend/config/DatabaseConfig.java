package az.edu.java.turingcalendarbackend.config;

import az.edu.java.turingcalendarbackend.entity.RoomEntity;
import az.edu.java.turingcalendarbackend.repository.RoomRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class DatabaseConfig {

    @Bean
    public boolean loadRooms(RoomRepository roomRepository) {
        if (roomRepository.count() == 0) {
            List<RoomEntity> rooms = List.of(
                    new RoomEntity(null, "Room Atom", true, null),
                    new RoomEntity(null, "Room Kelvin", true, null),
                    new RoomEntity(null, "Room Uno", true, null),
                    new RoomEntity(null, "Room Candela", false, null),
                    new RoomEntity(null, "Room Byte", true, null),
                    new RoomEntity(null, "Room Pixel", true, null),
                    new RoomEntity(null, "Room Curie", false, null),
                    new RoomEntity(null, "Room Elektron", true, null)
            );
            roomRepository.saveAll(rooms);
            return true; // Rooms were saved
        }
        return false; // No rooms were saved
    }
}


