package com.javieh.javitech.controller;

import com.javieh.javitech.dto.CartDTO;
import com.javieh.javitech.dto.CartItemDTO;
import com.javieh.javitech.dto.CartItemRequestDTO;
import com.javieh.javitech.entity.Cart;
import com.javieh.javitech.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }
    @PostMapping("/{userId}/items")
    public ResponseEntity<CartItemDTO> addProductToCart(@RequestBody CartItemRequestDTO request, @PathVariable Long userId){
        CartItemDTO result = cartService.addProductToCart(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long userId) {
        CartDTO result = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(result);
    }


}
