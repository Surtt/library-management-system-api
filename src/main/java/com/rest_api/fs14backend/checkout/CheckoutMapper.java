package com.rest_api.fs14backend.checkout;

import com.rest_api.fs14backend.bookCopy.BookCopy;
import com.rest_api.fs14backend.user.User;
import org.springframework.stereotype.Component;

@Component
public class CheckoutMapper {
  public Checkout toCheckout(BookCopy bookCopy, User user) {
    return new Checkout(bookCopy, user);
  }
}
