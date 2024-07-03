package com.dadingcoding.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InterviewScheduleResponse {
    private String interviewerName;
    private String interviewDate;
    private String interviewTime;
    private String interviewLink;
}
