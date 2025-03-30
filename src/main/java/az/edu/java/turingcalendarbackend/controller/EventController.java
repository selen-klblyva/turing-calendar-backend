package az.edu.java.turingcalendarbackend.controller;

import az.edu.java.turingcalendarbackend.dto.EventDto;
import az.edu.java.turingcalendarbackend.service.Impl.EventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventServiceImpl eventService;

    @GetMapping
    public ResponseEntity<List<EventDto>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<EventDto>> getEventsByRoom(@PathVariable Long roomId) {
        return ResponseEntity.ok(eventService.getEventsByRoom(roomId));
    }

    @GetMapping("/time-range")
    public ResponseEntity<List<EventDto>> getEventsInTimeRange(
            @RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return ResponseEntity.ok(eventService.getEventsInTimeRange(start, end));
    }

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDTO) {
        return ResponseEntity.ok(eventService.createEvent(eventDTO));
    }
}
