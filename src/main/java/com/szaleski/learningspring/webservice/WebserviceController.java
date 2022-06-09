package com.szaleski.learningspring.webservice;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.szaleski.learningspring.business.ReservationService;
import com.szaleski.learningspring.business.RoomReservation;
import com.szaleski.learningspring.data.Guest;
import com.szaleski.learningspring.data.Room;
import com.szaleski.learningspring.util.DateUtils;

@RestController
@RequestMapping("/api")
public class WebserviceController {

    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public WebserviceController(final DateUtils dateUtils, final ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false) String dateString) {
        Date date = dateUtils.createDateFromDateString(dateString);
        return reservationService.getRoomReservationsForDate(date);
    }

    @GetMapping("guests")
    public List<Guest> getAllGuests() {
        return reservationService.getHotelGuests();
    }

    @PostMapping("guests")
    @ResponseStatus(HttpStatus.CREATED)
    public void getAllGuests(@RequestBody Guest guest) {
        reservationService.addGuest(guest);
    }

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return reservationService.getRooms();
    }

}
