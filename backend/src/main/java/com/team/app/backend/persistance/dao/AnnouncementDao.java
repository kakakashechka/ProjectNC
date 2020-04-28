package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.Announcement;

import java.util.List;

public interface AnnouncementDao {
    void create(Announcement announcement);
    Announcement  get(Long id);
    void update(Announcement announcement);
    void delete(Long id);
    List<Announcement> getAll();
}
