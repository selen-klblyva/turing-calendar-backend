package az.edu.java.turingcalendarbackend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlotDto {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String room;
    private boolean isBooked;
    private Long bookedByUserId;
}

