package az.edu.java.turingcalendarbackend.service.Impl;

import az.edu.java.turingcalendarbackend.dto.SlotDto;
import az.edu.java.turingcalendarbackend.entity.SlotEntity;
import az.edu.java.turingcalendarbackend.repository.SlotRepository;
import az.edu.java.turingcalendarbackend.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SlotServiceImpl implements SlotService {

    private final SlotRepository slotRepository;

    @Override
    public SlotDto createSlot(SlotDto slotDto) {
        SlotEntity slot = SlotEntity.builder()
                .startTime(slotDto.getStartTime())
                .endTime(slotDto.getEndTime())
                .room(slotDto.getRoom())
                .isBooked(false)
                .build();

        slot = slotRepository.save(slot);
        return toDto(slot);
    }

    @Override
    public List<SlotDto> getSlotsBetween(LocalDateTime start, LocalDateTime end) {
        List<SlotEntity> slots = slotRepository.findByStartTimeBetween(start, end);
        return slots.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<SlotDto> getSlotsByRoomAndTime(String room, LocalDateTime start, LocalDateTime end) {
        List<SlotEntity> slots = slotRepository.findByRoomAndStartTimeBetween(room, start, end);
        return slots.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteSlot(Long slotId) {
        slotRepository.deleteById(slotId);
    }

    @Override
    @Transactional
    public SlotDto bookSlot(Long slotId, Long userId) {
        SlotEntity slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (slot.isBooked()) {
            throw new RuntimeException("Slot is already booked");
        }

        slot.setBooked(true);
        SlotEntity updatedSlot = slotRepository.save(slot);
        return toDto(updatedSlot, userId);
    }

    private SlotDto toDto(SlotEntity slot) {
        return SlotDto.builder()
                .id(slot.getId())
                .startTime(slot.getStartTime())
                .endTime(slot.getEndTime())
                .room(slot.getRoom())
                .isBooked(slot.isBooked())
                .build();
    }

    private SlotDto toDto(SlotEntity slot, Long userId) {
        return SlotDto.builder()
                .id(slot.getId())
                .startTime(slot.getStartTime())
                .endTime(slot.getEndTime())
                .room(slot.getRoom())
                .isBooked(slot.isBooked())
                .bookedByUserId(userId)
                .build();
    }
}
