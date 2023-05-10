package com.rest_api.fs14backend.checkout;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books")
public class CheckoutController {
  private final CheckoutService checkoutService;

  @PostMapping("/borrow")
  public Checkout borrowBook(@RequestBody CheckoutDTO checkoutDTO) {

    return checkoutService.borrowBook(checkoutDTO);

  }
}
