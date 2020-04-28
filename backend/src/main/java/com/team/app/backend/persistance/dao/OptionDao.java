package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.DefaultQuest;
import com.team.app.backend.persistance.model.Option;
import com.team.app.backend.persistance.model.SeqOption;

import java.util.List;

public interface OptionDao {

    List<Option> getOptionQuest(Long id);

    List<SeqOption> getSeqOptionQuest(Long id);

    List<DefaultQuest> getDefaultQuest(Long id);

    void addOption(Option option);

    void addSeqOption(SeqOption seqOption);

    void addDefaultOption(DefaultQuest defaultQuest);
}
