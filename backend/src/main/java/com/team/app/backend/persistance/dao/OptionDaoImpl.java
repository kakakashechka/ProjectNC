package com.team.app.backend.persistance.dao;


import com.team.app.backend.persistance.dao.OptionDao;
import com.team.app.backend.persistance.dao.mappers.DefOptionRowMapper;
import com.team.app.backend.persistance.dao.mappers.OptionRowMapper;
import com.team.app.backend.persistance.dao.mappers.SeqOptionRowMapper;
import com.team.app.backend.persistance.model.DefaultQuest;
import com.team.app.backend.persistance.model.Option;
import com.team.app.backend.persistance.model.SeqOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class OptionDaoImpl implements OptionDao {

    private JdbcTemplate jdbcTemplate;
    public OptionDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    private OptionRowMapper optionRowMapper;

    @Autowired
    private SeqOptionRowMapper seqoptionRowMapper;

    @Autowired
    private DefOptionRowMapper defoptionRowMapper;


    @Override
    public List<Option> getOptionQuest(Long id) {
        return jdbcTemplate.query("SELECT id, is_correct, text, image, quest_id FROM option WHERE quest_id = ?"
                ,new Object[] { id },
                optionRowMapper);
    }

    @Override
    public List<SeqOption> getSeqOptionQuest(Long id) {
        return jdbcTemplate.query("SELECT id, serial_num, text, image, quest_id FROM seq_option WHERE quest_id = ?"
                ,new Object[] { id },
                seqoptionRowMapper);
    }

    @Override
    public List<DefaultQuest> getDefaultQuest(Long id) {
        return jdbcTemplate.query("SELECT id, answer, quest_id, image FROM default_quest WHERE quest_id = ?"
                ,new Object[] { id },
                defoptionRowMapper);
    }

    @Override
    public void addOption(Option option) {
        jdbcTemplate.update(
                "INSERT INTO public.option(is_correct, text, image, quest_id) VALUES (?, ?, ?, ?);",
                option.getIs_correct(),
                option.getText(),
                option.getImage(),
                option.getQuest_id()
                );
    }

    @Override
    public void addSeqOption(SeqOption seqOption) {
        jdbcTemplate.update(
                "INSERT INTO public.seq_option(serial_num, text, image, quest_id) VALUES (?, ?, ?, ?);",
                seqOption.getSerial_num(),
                seqOption.getText(),
                seqOption.getImage(),
                seqOption.getQuest_id()
                );
    }

    @Override
    public void addDefaultOption(DefaultQuest defaultQuest) {
       jdbcTemplate.update(
                "INSERT INTO public.default_quest( answer, image, quest_id) VALUES ( ?, ?, ?)",
                defaultQuest.getAnswer(),
                defaultQuest.getImage(),
                defaultQuest.getQuest_id()
       );
    }
}
