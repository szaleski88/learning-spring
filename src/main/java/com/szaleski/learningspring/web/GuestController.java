package com.szaleski.learningspring.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.szaleski.learningspring.business.ReservationService;
import com.szaleski.learningspring.data.Guest;

@Controller
@RequestMapping("/guests")
public class GuestController {

    private final ReservationService reservationService;

    public GuestController(final ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAllGuests(Model model) {
        List<Guest> guests = reservationService.getHotelGuests();
        model.addAttribute("guests", guests);

        return "guestsPage";

    }
}
