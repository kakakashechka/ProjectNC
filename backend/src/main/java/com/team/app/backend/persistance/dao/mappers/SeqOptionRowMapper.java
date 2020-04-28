package com.team.app.backend.persistance.dao.mappers;

import com.team.app.backend.persistance.model.SeqOption;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SeqOptionRowMapper implements RowMapper<SeqOption> {
    @Override
    public SeqOption mapRow(ResultSet resultSet, int rownumber) throws SQLException {
        return new SeqOption((long) resultSet.getInt("id"),
                resultSet.getInt("serial_num"),
                resultSet.getString("text"),
                resultSet.getBytes("image"),
                (long) resultSet.getInt("quest_id"));
    }
}