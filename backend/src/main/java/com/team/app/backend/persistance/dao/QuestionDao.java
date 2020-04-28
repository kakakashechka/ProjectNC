package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.Question;
import com.team.app.backend.persistance.model.QuestionType;

import java.util.List;

public interface QuestionDao {

        List<Question> getQuizQusetions(Long id);

        Question getQuestion(Long id);

        Long save(Question question);

        void update(Question question);

        void delete(Long id);
}
