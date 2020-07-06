package xyz.ilhamgibran.spring.retailservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.ilhamgibran.spring.retailservice.Model.City;
import xyz.ilhamgibran.spring.retailservice.Model.InputFormOrder;
import xyz.ilhamgibran.spring.retailservice.Model.SeatClass;
import xyz.ilhamgibran.spring.retailservice.Model.Ticket;
import xyz.ilhamgibran.spring.retailservice.Repository.CityRepository;
import xyz.ilhamgibran.spring.retailservice.Repository.SeatClassRepository;
import xyz.ilhamgibran.spring.retailservice.Repository.TicketRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {
    @Autowired
    private SeatClassRepository seatClassRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping(path = "/")
    public String showOrderForm(Model model){
        List<City> cityList = (List) cityRepository.findAll();
        List<SeatClass> seatClasses = (List) seatClassRepository.findAll();

        model.addAttribute("input", new InputFormOrder());
        model.addAttribute("flightClass", seatClasses);
        model.addAttribute("city", cityList);
        return "index";
    }

    @PostMapping(path = "/save")
    public String saveOrder(@ModelAttribute InputFormOrder input, Model model){
        City destCity = cityRepository.getCityById(input.getDestination());
        City origCity = cityRepository.getCityById(input.getOrigin());
        SeatClass seatClass = seatClassRepository.getSeatClassById(input.getFlightClass());

        Ticket ticket = ticketRepository.searchAvailability(input.getDepartureDateSQLFormat()
                ,input.getOrigin(),input.getDestination(), input.getFlightClass());

        if(ticket == null){
            System.out.println("Origin City is : " + origCity.getCityName());
            System.out.println("Destination City is : " + destCity.getCityName());
            System.out.println("Departure Date : " + input.getDepartureDateSQLFormat());
            System.out.println("Flight Class City is : " + seatClass.getClassName());
        }else{
            System.out.println("Origin City is : " + ticket.getOrigin().getCityName());
            System.out.println("Destination City is : " + ticket.getDestination().getCityName());
            System.out.println("Flight Class City is : " + ticket.getSeatClass().getClassName());
            System.out.println("Departure Date : " + input.getDepartureDateSQLFormat());
            System.out.println("Ticket available : " + ticket.getStock() + " Ticket");
        }

        return "saved";
    }
}
