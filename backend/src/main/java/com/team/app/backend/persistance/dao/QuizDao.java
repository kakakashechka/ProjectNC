package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.Question;
import com.team.app.backend.persistance.model.Quiz;

import java.util.List;

public interface QuizDao {
    Quiz get(Long id);

    List<Quiz> getByUserId(Long id);

    List<Quiz> getAll();

    Long save(Quiz quiz);

    void update(Quiz quiz);

    void delete(Long id);


}
