package ma.emsi.javaproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import  ma.emsi.javaproject.entities.Product;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CartService {

    private final EntityManager entityManager;

    @Autowired
    public CartService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addToCart(int id) {
        HttpSession session = getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        cart.put(id, cart.getOrDefault(id, 0) + 1);
        session.setAttribute("cart", cart);
    }

    public void removeToCart(int id) {
        HttpSession session = getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cart != null) {
            cart.remove(id);
            session.setAttribute("cart", cart);
        }
    }

    public void decrease(int id) {
        HttpSession session = getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cart != null) {
            int quantity = cart.getOrDefault(id, 0);
            if (quantity > 1) {
                cart.put(id, quantity - 1);
            } else {
                cart.remove(id);
            }
            session.setAttribute("cart", cart);
        }
    }

    public void removeCartAll() {
        HttpSession session = getSession();
        session.removeAttribute("cart");
    }

    public List<CartItem> getTotal() {
        HttpSession session = getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        List<CartItem> cartData = new ArrayList<>();

        if (cart != null) {
            for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                int id = entry.getKey();
                int quantity = entry.getValue();
                Product product = entityManager.find(Product.class, id);
                if (product == null) {
                    removeToCart(id);
                    continue;
                }
                cartData.add(new CartItem(product, quantity));
            }
        }

        return cartData;
    }

    private HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (HttpSession) attr.getRequest().getSession(true); // true == allow create
    }

    public static class CartItem {
        private Product product;
        private int quantity;

        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
