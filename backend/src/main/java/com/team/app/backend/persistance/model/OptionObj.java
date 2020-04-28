package com.team.app.backend.persistance.model;

public class OptionObj {

    private Long id;
    private Long quest_id;

    public OptionObj() {
    }

    public OptionObj(Long id, Long quest_id) {
        this.id = id;
        this.quest_id = quest_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuest_id() {
        return quest_id;
    }

    public void setQuest_id(Long quest_id) {
        this.quest_id = quest_id;
    }
}
