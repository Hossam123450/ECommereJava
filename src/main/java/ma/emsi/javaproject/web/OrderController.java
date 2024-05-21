package ma.emsi.javaproject.web;

import ma.emsi.javaproject.entities.*;
import ma.emsi.javaproject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ma.emsi.javaproject.services.CartService;


@Controller
public class OrderController {
    private final ProductRepository productRepository ;
    private final CartService cartService;

    @Autowired
    public OrderController(ProductRepository productRepository, CartService cartService) {
        this.cartService = cartService;
        this.productRepository=productRepository;
    }

    @GetMapping(path ="/create")
    public String index(@AuthenticationPrincipal User user, Model model) {
        if (user == null) {
            return "redirect:/login";
        }
        Order order = new Order();
        order.setUser(user);
        model.addAttribute("order", order);
//        model.addAttribute("recapCart", cartService.getTotal());
        model.addAttribute("products",productRepository.findAll());
        return "order";
    }


}

