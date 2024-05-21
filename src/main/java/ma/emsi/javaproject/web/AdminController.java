package ma.emsi.javaproject.web;

import ma.emsi.javaproject.entities.Product;
import ma.emsi.javaproject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping(path = "/menu")
    public String adminPage()
    {

        return "admin";
    }
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
    @GetMapping(path="/create")
    public String createProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "formAddProduct";

    }
    @PostMapping(path = "/save")
    public String saveProduct(Model model, Product s,
                              @RequestParam(name="currentPage", defaultValue = "0") int page,
                              @RequestParam(name="size", defaultValue = "3") int size,
                              @RequestParam(name="searchName", defaultValue = "") String search){
        productRepository.save(s);
        return "redirect:/product?page="+page+"&size="+size+"&search="+search;


    }

    @GetMapping(path="/delete")
    public String deleteProduct         (
            int page, int size, String search,
            @RequestParam(name="id") Integer id){
        productRepository.deleteById(id);

        return "redirect:/product?page="+page+"&size="+size+"&search="+search;
    }


    @GetMapping(path = "/edit")
    public String editProduct(Model model , int page, int size, String search, Integer id){
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) throw new RuntimeException("Erreur");
        model.addAttribute("product", product);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", search);

        return "formEditProduct";



    }

}
