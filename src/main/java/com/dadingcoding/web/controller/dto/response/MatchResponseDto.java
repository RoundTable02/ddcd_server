package com.dadingcoding.web.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class MatchResponseDto {
    private MemberMatchDto mentor;
    private MemberMatchDto mentee;
}
