package co.istad.chhaya.webmvc.repository;

import co.istad.chhaya.webmvc.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
