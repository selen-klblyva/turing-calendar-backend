package az.edu.java.turingcalendarbackend.service;

import az.edu.java.turingcalendarbackend.dto.SlotDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface SlotService {

    SlotDto createSlot(SlotDto slotDto);

    List<SlotDto> getSlotsBetween(LocalDateTime start, LocalDateTime end);

    List<SlotDto> getSlotsByRoomAndTime(String room, LocalDateTime start, LocalDateTime end);

    void deleteSlot(Long slotId);

    SlotDto bookSlot(Long slotId, Long userId);
}
