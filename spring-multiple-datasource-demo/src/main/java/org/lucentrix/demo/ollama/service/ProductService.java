package org.lucentrix.demo.ollama.service;

import org.lucentrix.demo.ollama.model.Product;
import org.lucentrix.demo.ollama.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional("ollamaTransactionManager")
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findProductByName(String name) {

        List<Product> exact = productRepository.findByNameIgnoringCase(name);
        if (!exact.isEmpty()) {
            return exact; // exact match found
        }

        // fallback to partial match ignoring case
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> getAvailableProductNames() {
        return productRepository.findTop10ByNameIsNotNull();
    }

}
