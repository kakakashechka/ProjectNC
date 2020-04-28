package com.team.app.backend.service.impl;

import com.team.app.backend.persistance.dao.AnnouncementDao;
import com.team.app.backend.persistance.model.Announcement;
import com.team.app.backend.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLDataException;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@Transactional
public class AnnouncemrntServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementDao announcementDao;

    public void createAnnouncement(Announcement announcement) {
        announcement.setDate(LocalDateTime.now().toString());
        announcement.setCategoryId(1L);
        announcement.setStatusId(1L);

        announcementDao.create(announcement);
    }
    public Announcement  getAnnouncement(Long id) {
       return new Announcement();
    }
    public void updateAnnouncement(Announcement announcement) {
        announcementDao.update(announcement);

    }
    public void deleteAnnouncement(Long id) {
        announcementDao.delete(id);
    }

}
