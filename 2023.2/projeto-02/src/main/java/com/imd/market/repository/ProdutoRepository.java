package com.imd.market.repository;
import com.imd.market.entity.ProdutosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutosEntity, Long> {
}
