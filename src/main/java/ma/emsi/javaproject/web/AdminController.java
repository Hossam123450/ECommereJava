package ma.emsi.javaproject.web;

import jakarta.annotation.security.RolesAllowed;
import ma.emsi.javaproject.entities.Product;
import ma.emsi.javaproject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//@RolesAllowed("ADMIN")

@Controller
@RequestMapping("/admin")
public class AdminController {
    public static String UPLOAD_DIRECTORY = "C:/Users/achah/IdeaProjects/javaProject/uploads";
    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "/")
    public String adminPage()
    {
        return "Admin/admin";
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
        return "Admin/products";
    }
    @GetMapping(path="/create")
    public String createProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "Admin/formAddProduct";

    }
    @PostMapping(path = "/save")
    public String saveProduct(Model model, Product s,
                                  @RequestParam("attachmentFile") MultipartFile file,
                              @RequestParam(name="currentPage", defaultValue = "0") int page,
                              @RequestParam(name="size", defaultValue = "3") int size,
                              @RequestParam(name="searchName", defaultValue = "") String search) throws IOException{
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        productRepository.save(s);

        return "redirect:/admin/product?page="+page+"&size="+size+"&search="+search;


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

        return "Admin/formEditProduct";



    }

}
