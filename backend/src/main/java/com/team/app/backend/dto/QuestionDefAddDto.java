package com.team.app.backend.dto;

import java.util.List;

public class QuestionDefAddDto extends QuestionDto {

    private List<DefOptionDto> options;

    public List<DefOptionDto> getOptions() {
        return options;
    }

    public void setOptions(List<DefOptionDto> options) {
        this.options = options;
    }


}
