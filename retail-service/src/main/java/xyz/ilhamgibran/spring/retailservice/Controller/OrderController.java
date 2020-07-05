package xyz.ilhamgibran.spring.retailservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.ilhamgibran.spring.retailservice.Model.City;
import xyz.ilhamgibran.spring.retailservice.Model.SeatClass;
import xyz.ilhamgibran.spring.retailservice.Model.Ticket;
import xyz.ilhamgibran.spring.retailservice.Repository.CityRepository;
import xyz.ilhamgibran.spring.retailservice.Repository.SeatClassRepository;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private SeatClassRepository seatClassRepository;

    @Autowired
    private CityRepository cityRepository;

    @GetMapping(path = "/")
    public String showOrderForm(Model model){
        List<City> cityList = (List) cityRepository.findAll();
        List<SeatClass> seatClasses = (List) seatClassRepository.findAll();

        model.addAttribute("ticket", new Ticket());
        model.addAttribute("flightClass", seatClasses);
        model.addAttribute("city", cityList);
        return "index";
    }

    @PostMapping(path = "/save")
    public String saveOrder(@ModelAttribute Ticket ticket, Model model){
        Ticket tiket = (Ticket) model.getAttribute("ticket");
        System.out.println("This is Ticket : "+ tiket.getDestination());
        System.out.println("This is Ticket-Ticket : "+ ticket.getDestination());
        return "saved";
    }
}
