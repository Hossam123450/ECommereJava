package ma.emsi.javaproject.web;
import ma.emsi.javaproject.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @GetMapping(path = "/MyCart")
    public String cartPage(CartService cartService,Model model,@AuthenticationPrincipal User user)
    {
        model.addAttribute("cart",cartService.getCart(user));
        return "cart";
    }
    @GetMapping(value = "/MyCart/add/{id}")
    public String addToCart(CartService cartService,@PathVariable("id") int id,@AuthenticationPrincipal User user)
    {
        cartService.addToCart(id,user);
        return "redirect:/MyCart";
    }
    @GetMapping(value = "/MyCart/remove/{id}")
    public String removeToCart(CartService cartService,@PathVariable("id") int id,@AuthenticationPrincipal User user)
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

}
