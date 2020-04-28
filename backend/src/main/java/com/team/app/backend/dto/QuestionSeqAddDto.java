package com.team.app.backend.dto;

import java.util.List;

public class QuestionSeqAddDto extends QuestionDto {

    private List<SeqOptionDto> options;

    public List<SeqOptionDto> getOptions() {
        return options;
    }

    public void setOptions(List<SeqOptionDto> options) {
        this.options = options;
    }
}
