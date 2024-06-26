package ma.emsi.javaproject.web;
import ma.emsi.javaproject.entities.User;
import ma.emsi.javaproject.repositories.CartRepository;
import ma.emsi.javaproject.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ma.emsi.javaproject.services.CartService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class CartController
{
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    private final CartService cartService;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping(path = "/MyCart")
    public String cartPage(CartService cartService,Model model)
    {
        User user = getAuthenticatedUser();
        model.addAttribute("cart",cartService.getCart(user));
        return "cart";
    }
    @GetMapping(value = "/MyCart/add/{id}")
    public String addToCart(CartService cartService,@PathVariable("id") Integer id) throws Exception {
        User user = null;
        try {
            user = getAuthenticatedUser();
        } catch (Exception e) {
            throw new Exception("user is null");
        } finally {
            System.out.println(user.getFirstName());

        }

        cartService.addToCart(id,user);
        return "redirect:/MyCart";
    }
    @GetMapping(value = "/MyCart/remove/{id}")
    public String removeToCart(CartService cartService,@PathVariable("id") Integer id,@AuthenticationPrincipal User user)
    {
        cartService.removeToCart(id,user);
        return "redirect:/MyCart";
    }
//    @GetMapping(value = "/MyCart/decrease/{id}")
//    public String decrease(CartService cartService,@PathVariable("id") int id)
//    {
//        cartService.decrease(id);
//        return "redirect:/MyCart";
//    }
    @GetMapping(value = "/MyCart/removeAll")
    public String removeAll(CartService cartService,@AuthenticationPrincipal User user)
    {
        cartService.removeCartAll(user);
        return "redirect:/products";
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
