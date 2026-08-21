@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public BigDecimal getProductStock(String productId) {
        // Logique métier :
        // 1. Vérifier si le produit existe
        // 2. Récupérer tous les mouvements IN et OUT via le repository
        // 3. Calculer la somme : Total = (Somme IN) - (Somme OUT)
        return productRepository.calculateTotalStock(productId);
    }
}