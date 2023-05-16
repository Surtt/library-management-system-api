package com.rest_api.fs14backend.checkout;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books")
public class CheckoutController {
  private final CheckoutService checkoutService;

  @PostMapping("/borrow")
  public Checkout borrowBook(@RequestBody CheckoutDTO checkoutDTO) {
    return checkoutService.borrowBook(checkoutDTO);
  }

  @PutMapping("/return/{checkoutId}")
  public Checkout returnBook(@RequestBody CheckoutDTO checkoutDTO, @PathVariable UUID checkoutId) {
    return checkoutService.returnBook(checkoutDTO, checkoutId);
  }

  @GetMapping("/checkouts/{userId}")
  public List<Checkout> checkoutsByUserId(@PathVariable UUID userId) {
    return checkoutService.checkoutsByUserId(userId);
  }
}
