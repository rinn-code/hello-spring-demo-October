package org.launchcode.codingeventsjava11.controllers;


import org.launchcode.codingeventsjava11.data.EventData;
import org.launchcode.codingeventsjava11.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String processCreateEventForm(@RequestParam String eventName, @RequestParam String eventDescription) {
        EventData.add(new Event(eventName, eventDescription));
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

}
