package xyz.ilhamgibran.spring.retailservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.ilhamgibran.spring.retailservice.Model.*;
import xyz.ilhamgibran.spring.retailservice.Repository.*;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private SeatClassRepository seatClassRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderTicketRepository orderTicketRepository;

    @Autowired
    private TicketSeatRepository ticketSeatRepository;

    @GetMapping(path = "/")
    public String showOrderForm(Model model){
        List<City> cityList = (List) cityRepository.findAll();
        List<SeatClass> seatClasses = (List) seatClassRepository.findAll();

        model.addAttribute("input", new InputFormOrder());
        model.addAttribute("flightClass", seatClasses);
        model.addAttribute("city", cityList);
        model.addAttribute("status", 0);
        return "index";
    }

    @PostMapping(path = "/")
    public String saveOrder(@ModelAttribute InputFormOrder input, Model model){
        City destCity = cityRepository.getCityById(input.getDestination());
        City origCity = cityRepository.getCityById(input.getOrigin());
        SeatClass seatClass = seatClassRepository.getSeatClassById(input.getFlightClass());

        Ticket ticket = ticketRepository.searchAvailability(input.getDepartureDateSQLFormat()
                ,input.getOrigin(),input.getDestination(), input.getFlightClass());

        if(ticket == null){
            List<City> cityList = (List) cityRepository.findAll();
            List<SeatClass> seatClasses = (List) seatClassRepository.findAll();
//            System.out.println("Origin City is : " + origCity.getCityName());
//            System.out.println("Destination City is : " + destCity.getCityName());
//            System.out.println("Departure Date : " + input.getDepartureDateSQLFormat());
//            System.out.println("Flight Class City is : " + seatClass.getClassName());
            model.addAttribute("input", new InputFormOrder());
            model.addAttribute("flightClass", seatClasses);
            model.addAttribute("city", cityList);
            model.addAttribute("status", 1);
            return "index";
        }else{
//            System.out.println("Origin City is : " + ticket.getOrigin().getCityName());
//            System.out.println("Destination City is : " + ticket.getDestination().getCityName());
//            System.out.println("Flight Class City is : " + ticket.getSeatClass().getClassName());
//            System.out.println("Departure Date : " + input.getDepartureDateSQLFormat());
//            System.out.println("Ticket available : " + ticket.getStock() + " Ticket");
            if(ticket.getStock() < input.getAdultPass()){
                // Request ke Tetangga
            }
            List<String> listName = new ArrayList<>();

            for(int i=0;i<input.getAdultPass();i++){listName.add("");}
            input.setPassagerName(listName);

            model.addAttribute("input", input);
            return "saved";
        }
    }

    @PostMapping(path = "/save")
    public String getSaveName(@ModelAttribute InputFormOrder input){
        System.out.println(input.getPassagerName().size());
        System.out.println(input.getPassagerName().get(2));

//        Ticket ticket = ticketRepository.searchAvailability(input.getDepartureDateSQLFormat()
//                ,input.getOrigin(),input.getDestination(), input.getFlightClass());
//        double amount = input.getAdultPass()*ticket.getPrice();
//
//        // Save Order
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        java.sql.Date now = new Date(Calendar.getInstance().getTime().getTime());
//        Orders myOrder = new Orders(now, amount);
//        ordersRepository.save(myOrder);
//
//        // Save Order Ticket
//        List<TicketSeat> seat = ticketSeatRepository.getAvailableSeat();
//        int idx = 0;
//        for(String name : input.getPassagerName()){
//            OrderTicket order = new OrderTicket(myOrder, seat.get(idx), name);
//            idx += 1;
//            orderTicketRepository.save(order);
//            ticketSeatRepository.takeSeat(seat.get(idx).getSeatId());
//        }

        return "postOrder";
    }
}
