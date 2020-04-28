package com.team.app.backend.dto;

public class DefOptionDto {
    private String answer;
    private byte[] image;

    public DefOptionDto() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
