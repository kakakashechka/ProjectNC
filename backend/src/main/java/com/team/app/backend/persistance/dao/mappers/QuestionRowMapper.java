package com.team.app.backend.persistance.dao.mappers;

import com.team.app.backend.persistance.model.Question;
import com.team.app.backend.persistance.model.QuestionType;
import com.team.app.backend.persistance.model.Quiz;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet resultSet, int rownumber) throws SQLException {
        return new Question((long) resultSet.getInt("id"),
                resultSet.getInt("time"),
                resultSet.getString("text"),
                resultSet.getInt("max_points"),
                resultSet.getBytes("image"),
                new QuestionType((long)resultSet.getInt("type_id"),resultSet.getString("type_name")),
                (long) resultSet.getInt("quiz_id"));
    }
}
