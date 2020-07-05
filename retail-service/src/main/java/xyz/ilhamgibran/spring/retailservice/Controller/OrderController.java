package xyz.ilhamgibran.spring.retailservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.ilhamgibran.spring.retailservice.Model.City;
import xyz.ilhamgibran.spring.retailservice.Model.SeatClass;
import xyz.ilhamgibran.spring.retailservice.Repository.CityRepository;
import xyz.ilhamgibran.spring.retailservice.Repository.SeatClassRepository;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private SeatClassRepository seatClassRepository;

    @Autowired
    private CityRepository cityRepository;

    @GetMapping(name = "/")
    public String showOrderForm(Model model){
        List<City> cityList = (List) cityRepository.findAll();
//        model.addAttribute("flightClass", seatClassRepository);
        model.addAttribute("city", cityList);
        return "index";
    }

    @RequestMapping(name = "/save", method = RequestMethod.POST)
    public String saveOrder(Model model){
        System.out.println(model);
        return "saved";
    }
}
