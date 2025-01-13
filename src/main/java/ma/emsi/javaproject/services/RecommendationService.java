package ma.emsi.javaproject.services;

import ma.emsi.javaproject.entities.Cart;
import ma.emsi.javaproject.entities.Product;
import ma.emsi.javaproject.entities.Recommandation;
import ma.emsi.javaproject.entities.User;
import ma.emsi.javaproject.repositories.CartRepository;
import ma.emsi.javaproject.repositories.ProductRepository;
import ma.emsi.javaproject.repositories.RecommendationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    //    private static final Logger logger = LoggerFactory.getLogger(CartService.class);
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    public RecommendationService(RecommendationRepository recommendationRepository, CartRepository cartRepository, ProductRepository productRepository) {
        this.recommendationRepository = recommendationRepository;
        this.cartRepository = cartRepository;
        this.productRepository=productRepository;
    }

//    private Collection<Product> products = new ArrayList<>();
public String findMostFrequentSubtitle(Cart cart) {
    logger.error("errcart object: {}", cart);

    // Vérifier si le panier est vide
    if (cart == null || cart.getProducts() == null || cart.getProducts().isEmpty()) {
        return "No subtitles found";
    }

    Map<String, Integer> subtitleCount = new HashMap<>();

    // Parcourir les produits et compter les occurrences des sous-titres
    for (Product product : cart.getProducts()) {
        String subtitle = product.getSubtitle().toLowerCase(); // Normaliser en minuscules
        subtitleCount.put(subtitle, subtitleCount.getOrDefault(subtitle, 0) + 1);
    }

    // Trouver le sous-titre le plus fréquent
    String mostFrequent = null;
    int maxCount = 0;
    for (Map.Entry<String, Integer> entry : subtitleCount.entrySet()) {
        if (entry.getValue() > maxCount) {
            mostFrequent = entry.getKey();
            maxCount = entry.getValue();
        }
    }

    return mostFrequent != null ? mostFrequent : "No subtitles found";
}

    public void addToRecommandation( User user,String subtitle) {
        logger.error("errSubtitle object: {}", subtitle);
        logger.error("errUser object: {}", user);
//        Cart cart=cartRepository.findByUser(user);
//        String mostFrequentSubtitle = findMostFrequentSubtitle(cart);
        List<Product> products=productRepository.findAll();
        List<Integer> ids = products
                .stream()
                .filter(product -> subtitle.equalsIgnoreCase(product.getSubtitle()))
                .map(Product::getId)
                .collect(Collectors.toList());
        logger.error("ids object: {}", ids);
        for (Integer id : ids) {
            Product product = productRepository.findById(id).orElse(null);
            logger.error("recProduct object: {}", product);
            Recommandation recommandation = null;
            if (recommendationRepository.findByUser(user) == null) {
                recommandation = new Recommandation();
                recommandation.setUser(user);
                recommandation.addProduct(product);
                recommendationRepository.save(recommandation);
            }else {
                Recommandation myRecommandation = recommendationRepository.findByUser(user);
                myRecommandation.addProduct(product);
                recommendationRepository.save(myRecommandation);
            }
        }

    }

    public Recommandation getRecommandation(User user){
        return recommendationRepository.findByUser(user);
    }


}
