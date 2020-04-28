package com.team.app.backend.persistance.model;

import java.util.List;

public class Question {
    private Long id;
    private Integer time;
    private String text;
    private Integer max_points;
    private byte[] image;
    private QuestionType type;
    private Long quiz_id;
    private List<OptionObj> options;

    public Question(Long id, Integer time, String text, Integer max_points, byte[] image, QuestionType type, Long quiz_id) {
        this.id = id;
        this.time = time;
        this.text = text;
        this.max_points = max_points;
        this.image = image;
        this.type = type;
        this.quiz_id = quiz_id;
    }

    public Question() {

    }

    public Long getId() {
        return id;
    }


    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getMax_points() {
        return max_points;
    }

    public void setMax_points(Integer max_points) {
        this.max_points = max_points;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public Long getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(Long quiz_id) {
        this.quiz_id = quiz_id;
    }

    public List<OptionObj> getOptions() {
        return options;
    }

    public void setOptions(List<OptionObj> options) {
        this.options = options;
    }
}
