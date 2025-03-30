package az.edu.java.turingcalendarbackend.controller;

import az.edu.java.turingcalendarbackend.dto.RoomDto;
import az.edu.java.turingcalendarbackend.service.Impl.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomServiceImpl roomService;

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/available")
    public ResponseEntity<List<RoomDto>> getAvailableRooms() {
        return ResponseEntity.ok(roomService.getAvailableRooms());
    }

    @PostMapping
    public ResponseEntity<RoomDto> addRoom(@RequestBody RoomDto roomDTO) {
        return ResponseEntity.ok(roomService.addRoom(roomDTO));
    }

    @PatchMapping("/{roomId}/availability")
    public ResponseEntity<RoomDto> updateRoomAvailability(
            @PathVariable Long roomId,
            @RequestParam boolean isAvailable) {
        return ResponseEntity.ok(roomService.updateRoomAvailability(roomId, isAvailable));
    }
}

