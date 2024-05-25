package ma.emsi.javaproject.services;

import ma.emsi.javaproject.entities.Cart;
import ma.emsi.javaproject.entities.User;
import ma.emsi.javaproject.repositories.CartRepository;
import ma.emsi.javaproject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import  ma.emsi.javaproject.entities.Product;


@Service
public class CartService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    public CartService(ProductRepository productRepository, CartRepository cartRepository) {
        if (productRepository == null) {
            System.out.println("productRepository is null");
        } else {
            System.out.println("productRepository is initialized");
        }
        this.productRepository=productRepository;
        this.cartRepository = cartRepository;
        if (productRepository == null) {
            System.out.println("productRepository is null");
        } else {
            System.out.println("productRepository is initialized");
        }
    }


    public void addToCart(Integer id,@AuthenticationPrincipal User user) {
//makidkholch lhna ga3
        if (productRepository == null) {
            System.out.println("productRepository is null");
        } else {
            System.out.println("productRepository is initialized");
        }
        assert productRepository != null;
        Product product = productRepository.findById(id).orElse(null);
        Cart cart=cartRepository.findByUser(user);
        if (product != null) {
            cart.addProduct(product);
        }
        cartRepository.save(cart);
    }

    public void removeToCart(Integer id,@AuthenticationPrincipal User user) {
        Product product=productRepository.findById(id).orElse(null);
        Cart cart=cartRepository.findByUser(user);
        if (product != null) {
            cart.removeProduct(product);
        }
        cartRepository.save(cart);
    }


    public void removeCartAll(@AuthenticationPrincipal User user) {
        Cart cart=cartRepository.findByUser(user);
        cart.removeAll();
        cartRepository.save(cart);
    }

//    public  getTotal() {
//il doit retourner le product et sa quantity
//    }

//    public RecapDetails getTotal(@AuthenticationPrincipal User user){
//        Cart cart=cartRepository.findByUser(user);
//        Collection<Product> products=cart.getProducts();
//
//    }
    public Cart getCart(@AuthenticationPrincipal User user){
        return cartRepository.findByUser(user);
    }

}
