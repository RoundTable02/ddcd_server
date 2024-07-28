package com.dadingcoding.web.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassScheduleResponse {
    private String menteeName;
    private String time;
    private String title;
    private String link;
    private int sessionNumber;
    private String message;
}
