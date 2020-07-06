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

import java.util.List;
import java.util.Optional;

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

        model.addAttribute("input", new InputFormOrder());
        model.addAttribute("flightClass", seatClasses);
        model.addAttribute("city", cityList);
        return "index";
    }

    @PostMapping(path = "/save")
    public String saveOrder(@ModelAttribute InputFormOrder input, Model model){
        City getCity;
//        Ticket tiket = (Ticket) model.getAttribute("input");
//        System.out.println("This is Ticket : "+ tiket.getDestination());
        getCity = cityRepository.getCityById(input.getDestination());
        System.out.println("Destination City is : " + getCity.getCityName());

        return "saved";
    }
}
