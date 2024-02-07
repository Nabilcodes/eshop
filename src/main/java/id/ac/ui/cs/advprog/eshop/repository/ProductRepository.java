package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Product findByNameAndDelete(String productName) {
        Product ans = new Product();
        for (Product product : productData) {
            if (product.getProductName().equals(productName)) {
                ans = product;
                productData.remove(product);
                break;
            }
        }
        return ans;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
