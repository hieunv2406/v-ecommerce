package com.vm.services;

import com.vm.exceptions.ApiException;
import com.vm.dto.CartProductRequest;
import com.vm.dto.ResultDto;
import com.vm.dto.cart.CartDto;
import com.vm.dto.product.ProductDto;
import com.vm.entities.Cart;
import com.vm.entities.CartProduct;
import com.vm.entities.Product;
import com.vm.entities.User;
import com.vm.repository.CartProductRepository;
import com.vm.repository.CartRepository;
import com.vm.repository.ProductRepository;
import com.vm.repository.UserRepository;
import com.vm.utils.AuthUtil;
import com.vm.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public CartDto getCartByUser() {
        Optional<User> user = userRepository.findByUsername(AuthUtil.getUsernameFromJwtToken());
        if (!user.isPresent()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "User Not found");
        }
        Cart cart = cartRepository.findByUser(user.get());
        List<CartProduct> cartProducts = cartProductRepository.findByCart(cart);
        CartDto cartDto = new CartDto();
        cartDto.setUserName(user.get().getUsername());
        List<ProductDto> products = new ArrayList<>();
        ProductDto productDto;
        for (CartProduct product : cartProducts) {
            productDto = product.getProduct().toDto();
            productDto.setStatus(product.getStatus());
            productDto.setQuantity(product.getQuantity());
            products.add(productDto);
        }
        cartDto.setProducts(products);

        // send email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(user.get().getEmail());
        mailMessage.setText(cartDto.toString());
        javaMailSender.send(mailMessage);
        return cartDto;
    }

    @Override
    public ResultDto addNewProductToCart(CartProductRequest cartProductRequest) {
        ResultDto resultDto = new ResultDto(Constants.ResponseKey.SUCCESS);
        Optional<User> user = userRepository.findByUsername(AuthUtil.getUsernameFromJwtToken());
        Optional<Product> product = productRepository.findById(cartProductRequest.getProductId());
        if (!product.isPresent()) {
            return new ResultDto(Constants.ResponseKey.ERROR, "product", "not found!");
        }
        if (user.isPresent()) {
            Cart cart = cartRepository.findByUser(user.get());
            if (cart != null) {
                resultDto.setObject(saveCartProduct(cartProductRequest.getQuantity(), product.get(), cart));
            } else {
                Cart newCart = new Cart();
                newCart.setUser(user.get());
                newCart.setStatus(1L);
                Cart cartResult = cartRepository.save(newCart);
                resultDto.setObject(saveCartProduct(cartProductRequest.getQuantity(), product.get(), cartResult));
            }
        } else {
            return new ResultDto(Constants.ResponseKey.ERROR, "user", "not found!");
        }
        return resultDto;
    }

    private CartProduct saveCartProduct(Long quantity, Product product, Cart cart) {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setQuantity(quantity);
        cartProduct.setStatus(1L);
        cartProduct.setProduct(product);
        cartProduct.setCart(cart);
        return cartProductRepository.save(cartProduct);
    }
}
