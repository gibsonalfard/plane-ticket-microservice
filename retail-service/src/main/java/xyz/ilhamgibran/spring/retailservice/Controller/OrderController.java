package xyz.ilhamgibran.spring.retailservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import xyz.ilhamgibran.spring.retailservice.Model.*;
import xyz.ilhamgibran.spring.retailservice.Repository.*;

import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    Environment environment;

    @Autowired
    private RestTemplate restTemplate;

    InputFormOrder form = new InputFormOrder();
    URI micro2URI;

    @GetMapping(path = "/")
    public String showOrderForm(Model model){
        List<City> cityList = (List) cityRepository.findAll();
        List<SeatClass> seatClasses = (List) seatClassRepository.findAll();

        model.addAttribute("input", new InputFormOrder());
        model.addAttribute("flightClass", seatClasses);
        model.addAttribute("city", cityList);
        return "index";
    }

    @PostMapping(path = "/")
    public String saveOrder(@ModelAttribute InputFormOrder input, Model model){
        City destCity = cityRepository.getCityById(input.getDestination());
        City origCity = cityRepository.getCityById(input.getOrigin());
        SeatClass seatClass = seatClassRepository.getSeatClassById(input.getFlightClass());

        List<City> cityList = (List) cityRepository.findAll();
        List<SeatClass> seatClasses = (List) seatClassRepository.findAll();

        if(input.getAdultPass() < input.getChildPass()){
            model.addAttribute("input", new InputFormOrder());
            model.addAttribute("flightClass", seatClasses);
            model.addAttribute("city", cityList);
            model.addAttribute("status"
                    ,"Number of Adult Passager must be more than Kids Passager");
            return "index";
        }

        this.form = input;

        Ticket ticket = ticketRepository.searchAvailability(input.getDepartureDateSQLFormat()
                ,input.getOrigin(),input.getDestination(), input.getFlightClass());

        if(ticket == null){
            System.out.println("Origin City is : " + origCity.getCityName());
            System.out.println("Destination City is : " + destCity.getCityName());
            System.out.println("Departure Date : " + input.getDepartureDateSQLFormat());
            System.out.println("Flight Class City is : " + seatClass.getClassName());

            model.addAttribute("input", new InputFormOrder());
            model.addAttribute("flightClass", seatClasses);
            model.addAttribute("city", cityList);
            model.addAttribute("status", "Data Not Found");
            return "index";
        }else{
            if(ticket.getStock() < input.getAdultPass()){
                String port = environment.getProperty("server.port");
                String comp = "";
                List<ServiceInstance> list =
                        discoveryClient.getInstances("retail-service");

                try {
                    for(ServiceInstance host:list){
                        String params = "?origin="+ticket.getOrigin().getCityId()
                                +"&des="+ticket.getDestination().getCityId()
                                +"&date="+ticket.getFlightDate()
                                +"&class="+ticket.getSeatClass().getClassId()
                                +"&passager="+(input.getAdultPass()-ticket.getStock());

                        ServiceInstance service2 = host;
                        this.micro2URI = service2.getUri();
                        comp = micro2URI.getHost()+":"+micro2URI.getPort();
                        if(!comp.equals(InetAddress.getLocalHost().getHostName()+":"+port)){
                            ResponseEntity<Ticket> movieSummary = restTemplate.getForEntity(micro2URI +
                                    "/api/ticket/get"+params, Ticket.class);
                        }
                    }
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }else{
                this.micro2URI = null;
            }
            List<String> listName = new ArrayList<>();

            for(int i=0;i<input.getAdultPass();i++){listName.add("");}
            input.setPassagerName(listName);

            model.addAttribute("input", input);
            model.addAttribute("ticket", ticket);
            return "saved";
        }
    }

    @PostMapping(path = "/save")
    public String getSaveName(@ModelAttribute InputFormOrder input, HttpServletResponse httpServletResponse){
//        System.out.println(input.getPassagerName().size());
//        System.out.println(input.getPassagerName().get(2));
//        System.out.println(input.getOrigin() == 0 ? "NULL" : input.getOrigin());
//        System.out.println(input.getDestination() == 0 ? "NULL" : input.getDestination());
//        System.out.println(input.getDepartureDate() == null ? "NULL" : input.getDepartureDate());
        System.out.println("This MicroService2 : " + this.micro2URI);

//        Ticket ticket = ticketRepository.searchAvailability(input.getDepartureDateSQLFormat()
//                ,input.getOrigin(),input.getDestination(), input.getFlightClass());
//
//        if(ticket == null){
//            httpServletResponse.setHeader("Location", "/");
//            httpServletResponse.setStatus(302);
//        }else{
//            double amount = input.getAdultPass()*ticket.getPrice();
//
//            // Save Order
//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//            java.sql.Date now = new Date(Calendar.getInstance().getTime().getTime());
//            Orders myOrder = new Orders(now, amount);
//            ordersRepository.save(myOrder);
//
//            // Save Order Ticket
//            List<TicketSeat> seat = ticketSeatRepository.getSeatByStatus(ticket.getTicketId(), 1);
//            int idx = 0;
//            for(String name : input.getPassagerName()){
//                orderTicketRepository.save(new OrderTicket(myOrder, seat.get(idx), name));
//                seat.get(idx).setAvailability(0);
//                ticketSeatRepository.save(seat.get(idx));
//                idx += 1;
//            }
//
//            ticket.setStock(ticket.getStock()-input.getAdultPass());
//            ticketRepository.save(ticket);
//        }

        return "postOrder";
    }
}
