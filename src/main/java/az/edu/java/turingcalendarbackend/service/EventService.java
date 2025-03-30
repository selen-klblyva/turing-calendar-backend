package az.edu.java.turingcalendarbackend.service;

import az.edu.java.turingcalendarbackend.dto.EventDto;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    List<EventDto> getAllEvents();

    List<EventDto> getEventsByRoom(Long roomId);

    List<EventDto> getEventsInTimeRange(LocalDateTime start, LocalDateTime end);

    EventDto createEvent(EventDto eventDTO);
}

