package com.team.app.backend.rest;

import com.team.app.backend.persistance.model.Announcement;
import com.team.app.backend.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/announcement")
public class AnnouncementController {

    @Autowired
    AnnouncementService announcementService;

    @CrossOrigin
    @PostMapping("/create")
    public Map<String, String> createAnnouncement(@RequestBody Announcement announcement) {
        Map<String, String> response = new HashMap<>();
        try {
            announcementService.createAnnouncement(announcement);
        }
        catch(DataAccessException sqlEx){
            System.out.println(sqlEx);
            response.put("message","There is a problem while creating announcement");
            return  response;
        }
        response.put("message","Created!");
        return  response;
    }

    @PutMapping("/update")
    public ResponseEntity updateAnnouncement(@RequestBody Announcement announcement) {
        Map<String, String> model = new HashMap<String, String>();
        try {
            announcementService.updateAnnouncement(announcement);

        }
        catch(DataAccessException sqlEx){
            System.out.println(sqlEx);
            model.put("message","There is a problem while updating announcement");
            return ResponseEntity.badRequest().body(model);
        }
        model.put("message","Updated");
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity updateAnnouncement(@PathVariable("id") long id) {
        Map<String, String> model = new HashMap<String, String>();
        try {
            announcementService.deleteAnnouncement(id);

        }
        catch(DataAccessException sqlEx){
            System.out.println(sqlEx);
           model.put("message","There is a problem while updating announcement");
            return ResponseEntity.badRequest().body(model);
        }
        model.put("message","Updated");
        return ResponseEntity.ok(model);
    }
}
