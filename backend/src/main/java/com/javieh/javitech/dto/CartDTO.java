package com.javieh.javitech.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CartDTO {
    private Long cartId;
    private List<CartItemDTO> items;
    private BigDecimal total;

    public CartDTO(Long cartId, List<CartItemDTO> items, BigDecimal total){
        this.cartId = cartId;
        this.items = items;
        this.total = total;
    }

}
