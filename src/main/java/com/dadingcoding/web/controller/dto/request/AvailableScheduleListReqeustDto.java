package com.dadingcoding.web.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AvailableScheduleListReqeustDto {
    private List<AvailableScheduleReqeustDto> availableTimes;
}
