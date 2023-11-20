package com.imd.market.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "produtos")
@Entity(name = "Produtos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutosEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O nome do produto não pode estar vazio.")
    @Size(max = 50, message = "O nome do produto não pode ter mais que 50 caracteres.")
    private String nomeProduto;

    @NotBlank(message = "A descrição do produto não pode estar vazia.")
    @Size(max = 100, message = "A descrição do produto não pode ter mais que 100 caracteres.")
    private String descricaoProduto;

    @Positive(message = "O preço do produto deve ser positivo.")
    private Double precoProduto;

    @Size(max = 15, message = "A data de validade não deve exceder 15 caracteres.")
    private String dataValidade;

    @Positive(message = "O estoque deve ser um número positivo.")
    private int estoque;

    @Size(max = 50, message = "O nome do fornecedor não pode ter mais que 50 caracteres.")
    private String fornecedor;

    @Positive(message = "O número de vendas deve ser um número positivo.")
    private boolean ativo;
}
