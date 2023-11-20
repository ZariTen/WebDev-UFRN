package com.imd.market.repository;
import com.imd.market.entity.ProdutosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutosEntity, Long> {

    @Override
    @Transactional(readOnly = true)
    default List<ProdutosEntity> findAll() {
        return findByAtivoTrue();
    }

    List<ProdutosEntity> findByAtivoTrue();

    @Override
    @Transactional(readOnly = true)
    default Optional<ProdutosEntity> findById(Long id) {
        return findByIdAndAtivoTrue(id);
    }

    Optional<ProdutosEntity> findByIdAndAtivoTrue(Long id);


}
