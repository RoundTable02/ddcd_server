package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.AvailableSchedule;
import com.dadingcoding.web.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MemberMatchDto {
    private Long id;
    private String name;
    private List<String> availableTimes;

    public static MemberMatchDto toDto(Member member) {
        List<AvailableSchedule> availableSchedules = member.getAvailableSchedules();
        List<String> available = new ArrayList<>();
        for (AvailableSchedule availableSchedule : availableSchedules) {
            available.add(
                    availableSchedule.getDayOfWeek().toString() + availableSchedule.getTime().toString()
            );
        }

        return MemberMatchDto.builder()
                .id(member.getId())
                .name(member.getUsername())
                .availableTimes(available)
                .build();
    }
}
