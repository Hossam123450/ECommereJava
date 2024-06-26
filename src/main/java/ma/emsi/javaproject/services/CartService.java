package ma.emsi.javaproject.services;

import ma.emsi.javaproject.entities.Cart;
import ma.emsi.javaproject.entities.User;
import ma.emsi.javaproject.repositories.CartRepository;
import ma.emsi.javaproject.repositories.ProductRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import  ma.emsi.javaproject.entities.Product;
import org.slf4j.LoggerFactory;

@Service
public class CartService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
//    private static final Logger logger = LoggerFactory.getLogger(CartService.class);
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    public CartService(ProductRepository productRepository, CartRepository cartRepository) {
        this.productRepository=productRepository;
        this.cartRepository = cartRepository;

    }

public void addToCart(Integer id, User user) {
    Product product = productRepository.findById(id).orElse(null);
    Cart cart = null;
    if (cartRepository.findByUser(user) == null) {
        cart = new Cart();
        cart.setUser(user);
        cart.addProduct(product);
        cartRepository.save(cart);
    }else {
    Cart myCart = cartRepository.findByUser(user);
    myCart.addProduct(product);
    cartRepository.save(myCart);
    }
}

    public void removeToCart(Integer id,User user) {
        Product product=productRepository.findById(id).orElse(null);
        Cart cart=cartRepository.findByUser(user);
        assert product != null;
        cart.removeProduct(product);
        cartRepository.save(cart);
    }


    public void removeCartAll(User user) {
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
    public Cart getCart(User user){
        return cartRepository.findByUser(user);
    }

}
