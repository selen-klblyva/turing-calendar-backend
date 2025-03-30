package az.edu.java.turingcalendarbackend.service.Impl;

import az.edu.java.turingcalendarbackend.dto.RoomDto;
import az.edu.java.turingcalendarbackend.entity.RoomEntity;
import az.edu.java.turingcalendarbackend.repository.RoomRepository;
import az.edu.java.turingcalendarbackend.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<RoomDto> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> getAvailableRooms() {
        return roomRepository.findByIsAvailableTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto addRoom(RoomDto roomDTO) {
        RoomEntity room = RoomEntity.builder()
                .name(roomDTO.getName())
                .isAvailable(roomDTO.isAvailable())
                .build();

        room = roomRepository.save(room);
        return convertToDTO(room);
    }

    @Override
    public RoomDto updateRoomAvailability(Long roomId, boolean isAvailable) {
        RoomEntity room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        room.setAvailable(isAvailable);
        roomRepository.save(room);

        return convertToDTO(room);
    }

    private RoomDto convertToDTO(RoomEntity room) {
        return RoomDto.builder()
                .id(room.getId())
                .name(room.getName())
                .isAvailable(room.isAvailable())
                .build();
    }
}
