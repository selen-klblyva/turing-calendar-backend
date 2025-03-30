package az.edu.java.turingcalendarbackend.controller;

import az.edu.java.turingcalendarbackend.dto.SlotDto;
import az.edu.java.turingcalendarbackend.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/slots")
@RequiredArgsConstructor
public class SlotController {

    private final SlotService slotService;

    @PostMapping
    public ResponseEntity<SlotDto> createSlot(@RequestBody SlotDto slotDto) {
        SlotDto createdSlot = slotService.createSlot(slotDto);
        return ResponseEntity.ok(createdSlot);
    }

    @GetMapping
    public ResponseEntity<List<SlotDto>> getSlotsBetween(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        List<SlotDto> slots = slotService.getSlotsBetween(start, end);
        return ResponseEntity.ok(slots);
    }

    @GetMapping("/room")
    public ResponseEntity<List<SlotDto>> getSlotsByRoomAndTime(
            @RequestParam String room,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        List<SlotDto> slots = slotService.getSlotsByRoomAndTime(room, start, end);
        return ResponseEntity.ok(slots);
    }

    @DeleteMapping("/{slotId}")
    public ResponseEntity<Void> deleteSlot(@PathVariable Long slotId) {
        slotService.deleteSlot(slotId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{slotId}/book")
    public ResponseEntity<SlotDto> bookSlot(
            @PathVariable Long slotId,
            @RequestParam Long userId) {
        SlotDto bookedSlot = slotService.bookSlot(slotId, userId);
        return ResponseEntity.ok(bookedSlot);
    }
}