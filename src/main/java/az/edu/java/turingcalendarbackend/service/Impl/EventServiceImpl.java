package az.edu.java.turingcalendarbackend.service.Impl;

import az.edu.java.turingcalendarbackend.dto.EventDto;
import az.edu.java.turingcalendarbackend.entity.EventEntity;
import az.edu.java.turingcalendarbackend.entity.RoomEntity;
import az.edu.java.turingcalendarbackend.repository.EventRepository;
import az.edu.java.turingcalendarbackend.repository.RoomRepository;
import az.edu.java.turingcalendarbackend.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final RoomRepository roomRepository;

    @Override
    public List<EventDto> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getEventsByRoom(Long roomId) {
        return eventRepository.findByRoomId(roomId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getEventsInTimeRange(LocalDateTime start, LocalDateTime end) {
        return eventRepository.findByStartTimeBetween(start, end)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EventDto createEvent(EventDto eventDTO) {
        RoomEntity room = roomRepository.findById(eventDTO.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        EventEntity event = EventEntity.builder()
                .name(eventDTO.getName())
                .description(eventDTO.getDescription())
                .startTime(eventDTO.getStartTime())
                .endTime(eventDTO.getEndTime())
                .room(room)
                .organizer(eventDTO.getOrganizer())
                .build();

        event = eventRepository.save(event);
        return convertToDTO(event);
    }

    private EventDto convertToDTO(EventEntity event) {
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .roomId(event.getRoom().getId())
                .organizer(event.getOrganizer())
                .build();
    }

}
