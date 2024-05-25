package ma.emsi.javaproject.web;

import ma.emsi.javaproject.entities.Product;
import ma.emsi.javaproject.repositories.ProductRepository;
import ma.emsi.javaproject.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import static jdk.internal.jrtfs.JrtFileAttributeView.AttrID.size;

@Controller
public class ProductController {

//    private final ProductRepository productRepository ;
//    public ProductController(ProductRepository productRepository){
//        this.productRepository=productRepository;
//    }
    @Autowired
    private ProductRepository productRepository;
    @GetMapping(path = "/product")
    public String allProducts(Model model,
                              @RequestParam(name="page",defaultValue = "0") int page,
                              @RequestParam(name="size",defaultValue = "3")int size,
                              @RequestParam(name = "search",defaultValue = "")String searchName)
    {
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
}
