package com.team.app.backend.persistance.model;

public class DefaultQuest extends OptionObj  {

    private String answer;
    private byte[] image;

    public DefaultQuest() {
    }

    public DefaultQuest(String answer, byte[] image) {
        //this.id = id;
        this.answer = answer;
        this.image = image;
        //this.quest_id = quest_id;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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

//    public Long getQuest_id() {
//        return quest_id;
//    }
//
//    public void setQuest_id(Long quest_id) {
//        this.quest_id = quest_id;
//    }
}
