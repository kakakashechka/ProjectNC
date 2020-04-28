package com.team.app.backend.persistance.dao.mappers;

import com.team.app.backend.persistance.model.Role;
import com.team.app.backend.persistance.model.User;
import com.team.app.backend.persistance.model.UserStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rownumber) throws SQLException {
        return new User((long) resultSet.getInt("id"),
                resultSet.getString("firstname"),
                resultSet.getString("lastName"),
                resultSet.getString("userName"),
                resultSet.getString("password"),
                resultSet.getString("email"),
                resultSet.getBytes("image"),
                resultSet.getDate("registr_date"),
                resultSet.getString("activate_link"),
                new UserStatus((long)resultSet.getInt("status_id"),resultSet.getString("status_name")),
                new Role((long) resultSet.getInt("role_id"),resultSet.getString("role_name")));
    }
}