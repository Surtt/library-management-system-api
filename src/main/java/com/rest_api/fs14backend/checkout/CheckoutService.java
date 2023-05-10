package com.rest_api.fs14backend.checkout;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutService {
  private final CheckoutRepository checkoutRepository;

  public Checkout findById(UUID id) {
    return checkoutRepository.findById(id)
            .orElse(null);
  }
}
