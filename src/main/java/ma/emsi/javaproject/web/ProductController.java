package ma.emsi.javaproject.web;

import ma.emsi.javaproject.repositories.ProductRepository;
import ma.emsi.javaproject.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    private final ProductRepository productRepository ;
    public ProductController(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    @GetMapping(path = "/Product")
    public String allProducts(CartService cartService, Model model)
    {
        model.addAttribute("products",productRepository.findAll());
        return "product";
    }
}
