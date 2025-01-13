package ma.emsi.javaproject.web;

import ma.emsi.javaproject.entities.User;
import ma.emsi.javaproject.services.RecommendationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ma.emsi.javaproject.services.CartService;
@Controller
@RequestMapping("/recommendation")
public class RecommendationController {
    private static final Logger logger = LoggerFactory.getLogger(RecommendationController.class);

    private final RecommendationService recommendationService;
    private final CartService cartService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService, CartService cartService) {
        this.recommendationService = recommendationService;
        this.cartService = cartService;
    }

    @GetMapping(path = "")
    public String recommendationPage(Model model) {
        User user = getAuthenticatedUser();
        logger.info("usercart object: {}",cartService.getCart(user));
        String mostFreqSubtitle = recommendationService.findMostFrequentSubtitle(cartService.getCart(user));
        logger.error("MOSTfreq object: {}", mostFreqSubtitle);


        recommendationService.addToRecommandation(user, mostFreqSubtitle);
        logger.error("recommendation object: {}",recommendationService.getRecommandation(user));
        model.addAttribute("recommendation", recommendationService.getRecommandation(user));
        return "recommendations";
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            logger.info("Authentication object: {}", authentication);
            Object principal = authentication.getPrincipal();
            logger.info("Principal object: {}", principal);
            if (principal instanceof User) {
                return (User) principal;
            } else {
                logger.info("Principal is not an instance of User: {}", principal.getClass().getName());
            }
        } else {
            logger.info("No authentication object found");
        }
        return null;
    }
}