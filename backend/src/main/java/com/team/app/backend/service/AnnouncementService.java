package com.team.app.backend.service;

import com.team.app.backend.persistance.model.Announcement;
import com.team.app.backend.persistance.model.Quiz;

import java.util.List;

public interface AnnouncementService {

    void createAnnouncement(Announcement announcement);
    Announcement  getAnnouncement(Long id);
    void updateAnnouncement(Announcement announcement);
    void deleteAnnouncement(Long id);
    List<Announcement> getAllAnnouncements();
}
