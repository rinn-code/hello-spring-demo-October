package org.launchcode.codingeventsjava11.controllers;


import org.launchcode.codingeventsjava11.data.EventData;
import org.launchcode.codingeventsjava11.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController{

    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }
    //lives at/events/create
    @GetMapping("create")
    public String displayCreateEventForm(){

        return "events/create";
    }
    //lives at /events/create
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute Event newEvent) {
        EventData.add(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds) {

       if (eventIds != null) {
           for (int id : eventIds) {
               EventData.remove(id);
           }
       }
        return "redirect:";
    }
    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        Event eventToEdit = EventData.getById(eventId);
        model.addAttribute("event", eventToEdit);
        String title = "Edit Event " + eventToEdit.getName() + " (id=" + eventToEdit.getId() + ")";
        model.addAttribute("title", title);
        return "events/edit";
    }
    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description) {
        Event eventToEdit = EventData.getById(eventId);
        eventToEdit.setName(name);
        eventToEdit.setDescription(description);
        return "redirect";
    }

}
