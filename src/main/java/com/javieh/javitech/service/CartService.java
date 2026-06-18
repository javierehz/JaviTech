package com.javieh.javitech.service;

import com.javieh.javitech.dto.CartItemDTO;
import com.javieh.javitech.dto.CartItemRequestDTO;
import com.javieh.javitech.entity.Cart;
import com.javieh.javitech.entity.CartItem;
import com.javieh.javitech.entity.Product;
import com.javieh.javitech.entity.User;
import com.javieh.javitech.repository.CartItemRepository;
import com.javieh.javitech.repository.CartRepository;
import com.javieh.javitech.repository.ProductRepository;
import com.javieh.javitech.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final CartItemRepository cartItemRepository;

    private final CartRepository cartRepository;

    public CartService(ProductRepository productRepository, UserRepository userRepository,
                       CartItemRepository cartItemRepository, CartRepository cartRepository){
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
    }

    public CartItemDTO addProductToCart(Long userId, CartItemRequestDTO request){
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> crearCarritoNuevo(userId));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        var existingItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId());

        int cantidadFinal;
        CartItem itemAGuardar;

        if(existingItem.isPresent()){
            itemAGuardar = existingItem.get();
            cantidadFinal = itemAGuardar.getQuantity() + request.getQuantity();
        } else {
            itemAGuardar = new CartItem();
            itemAGuardar.setCart(cart);
            itemAGuardar.setProduct(product);
            cantidadFinal = request.getQuantity();
        }

        if(product.getStock() < cantidadFinal){
            throw new RuntimeException("Stock insuficiente. Disponible: " + product.getStock());
        }

        itemAGuardar.setQuantity(cantidadFinal);
        CartItem saved = cartItemRepository.save(itemAGuardar);

        return new CartItemDTO(
                saved.getId(),
                product.getId(),
                product.getName(),
                product.getPrice(),
                saved.getQuantity()
        );
    }

    public Cart crearCarritoNuevo(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Cart nuevoCarrito = new Cart();
        nuevoCarrito.setUser(user);
        return cartRepository.save(nuevoCarrito);

    }

}
