package ma.emsi.javaproject.web;

import ma.emsi.javaproject.entities.Cart;
import ma.emsi.javaproject.entities.User;
import ma.emsi.javaproject.repositories.CartRepository;
import ma.emsi.javaproject.repositories.ProductRepository;
import ma.emsi.javaproject.services.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Details")
public class DetailsController {
    private static final Logger logger = LoggerFactory.getLogger(DetailsController.class);
    private final CartRepository cartRepository;
    private final CartService cartService;
    @Autowired
    public DetailsController(CartRepository cartRepository, CartService cartService) {
        this.cartRepository = cartRepository;
        this.cartService = cartService;
    }
    @GetMapping(path = "")
    public String detailsPage(Model model)
    {
        double total=0.0;
        User user = getAuthenticatedUser();
        Cart cart= cartService.getCart(user);
        total=cart.getTotal();
        model.addAttribute("cart",cart);
        model.addAttribute("total",total);
        return "detail";
    }
    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            logger.info("Authentication object: {}", authentication);
            Object principal = authentication.getPrincipal();
            logger.info("Principal object: {}", principal);
            if (principal instanceof User) {
                return (User) principal;
            } else {
                logger.info("Principal is not an instance of User: {}", principal.getClass().getName());
            }
        } else {
            logger.info("No authentication object found");
        }
        return null;
    }
}
