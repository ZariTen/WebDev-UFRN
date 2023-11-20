package com.imd.market.controller;
import com.imd.market.entity.ProdutosEntity;
import com.imd.market.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public List<ProdutosEntity> getAll() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutosEntity> getById(@PathVariable Long id) {
        Optional<ProdutosEntity> produto = produtoRepository.findById(id);
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProdutosEntity postProduct(@RequestBody ProdutosEntity produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutosEntity> updateProduct(@PathVariable Long id, @RequestBody ProdutosEntity produtoDetails) {
        return produtoRepository.findByIdAndAtivoTrue(id)
                .map(existingProduct -> {
                    if (produtoDetails.getNomeProduto() != null) {
                        existingProduct.setNomeProduto(produtoDetails.getNomeProduto());
                    }
                    if (produtoDetails.getDescricaoProduto() != null) {
                        existingProduct.setDescricaoProduto(produtoDetails.getDescricaoProduto());
                    }
                    if (produtoDetails.getPrecoProduto() != null) {
                        existingProduct.setPrecoProduto(produtoDetails.getPrecoProduto());
                    }
                    if (produtoDetails.getDataValidade() != null) {
                        existingProduct.setDataValidade(produtoDetails.getDataValidade());
                    }
                    if (produtoDetails.getEstoque() != null) {
                        existingProduct.setEstoque(produtoDetails.getEstoque());
                    }
                    if (produtoDetails.getFornecedor() != null) {
                        existingProduct.setFornecedor(produtoDetails.getFornecedor());
                    }
                    ProdutosEntity updatedProduto = produtoRepository.save(existingProduct);
                    return ResponseEntity.ok(updatedProduto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produtoRepository.delete(produto);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<?> deleteLogic(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setAtivo(false);
                    produtoRepository.save(produto);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
