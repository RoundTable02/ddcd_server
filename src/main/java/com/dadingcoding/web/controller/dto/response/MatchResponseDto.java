package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class MatchResponseDto {
    private MemberMatchDto mentor;
    private MemberMatchDto mentee;
}
