package ma.emsi.javaproject.web;

import ma.emsi.javaproject.entities.Product;
import ma.emsi.javaproject.entities.User;
import ma.emsi.javaproject.repositories.ProductRepository;
import ma.emsi.javaproject.services.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import static jdk.internal.jrtfs.JrtFileAttributeView.AttrID.size;

@Controller
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

//    private final ProductRepository productRepository ;
//    public ProductController(ProductRepository productRepository){
//        this.productRepository=productRepository;
//    }
    @Autowired
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/product")
    public String allProducts(Model model,
                              @RequestParam(name="page",defaultValue = "0") int page,
                              @RequestParam(name="size",defaultValue = "3")int size,
                              @RequestParam(name = "search",defaultValue = "")String searchName)
    {
//        User user = getAuthenticatedUser();
//        logger.error("user object: {}",user.getRole());
        Page<Product> pageProducts = productRepository.findByFullNameContains(searchName, PageRequest.of(page,size));
        int[] pages=new int[pageProducts.getTotalPages()];
        for(int i=0;i<pages.length;i++)
            pages[i]=i;

        model.addAttribute("pagesProducts", pageProducts.getContent());
        model.addAttribute("tabPages",pages);
        model.addAttribute("size",size);
        model.addAttribute("currentPage",page);
        model.addAttribute("searchName",searchName);
        return "products";
    }
//    private User getAuthenticatedUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            logger.info("Authentication object: {}", authentication);
//            Object principal = authentication.getPrincipal();
//            logger.info("Principal object: {}", principal);
//            if (principal instanceof User) {
//                return (User) principal;
//            } else {
//                logger.info("Principal is not an instance of User: {}", principal.getClass().getName());
//            }
//        } else {
//            logger.info("No authentication object found");
//        }
//        return null;
//    }
}
