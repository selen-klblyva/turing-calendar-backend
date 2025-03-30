package az.edu.java.turingcalendarbackend.service;

import az.edu.java.turingcalendarbackend.dto.RoomDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {

    List<RoomDto> getAllRooms();

    List<RoomDto> getAvailableRooms();

    RoomDto addRoom(RoomDto roomDTO);

    RoomDto updateRoomAvailability(Long roomId, boolean isAvailable);
}

