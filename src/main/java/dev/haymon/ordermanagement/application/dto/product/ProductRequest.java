package dev.haymon.ordermanagement.application.dto.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductRequest {

    @NotBlank(message = "Nome do produto é obrigatório")
    private String name;
    @NotNull(message = "Preço do produto é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    private BigDecimal price;
    @Size(max = 500, message = "Descrição do produto só pode ter até 500 caracteres")
    private String description;
}
