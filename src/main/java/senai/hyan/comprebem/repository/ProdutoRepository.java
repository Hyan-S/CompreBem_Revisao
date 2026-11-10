package senai.hyan.comprebem.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import senai.hyan.comprebem.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
