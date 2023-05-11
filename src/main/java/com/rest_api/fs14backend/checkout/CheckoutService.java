package com.rest_api.fs14backend.checkout;

import com.rest_api.fs14backend.bookCopy.BookCopy;
import com.rest_api.fs14backend.bookCopy.BookCopyRepository;
import com.rest_api.fs14backend.user.User;
import com.rest_api.fs14backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutService {
  private final CheckoutRepository checkoutRepository;
  private final BookCopyRepository bookCopyRepository;
  private final UserRepository userRepository;
  private final CheckoutMapper checkoutMapper;

  public Checkout findById(UUID id) {
    return checkoutRepository.findById(id)
            .orElse(null);
  }

  public Checkout borrowBook(CheckoutDTO checkoutDTO) {
    List<Checkout> checkoutList = null;
    List<BookCopy> bookCopyList = null;
    UUID bookCopyId = checkoutDTO.getBookCopyId();
    BookCopy bookCopy = bookCopyRepository.findById(bookCopyId)
            .get();

    UUID userId = checkoutDTO.getUserId();
    User user = userRepository.findById(userId)
            .get();

    Checkout checkout = checkoutMapper.toCheckout(bookCopy, user);

    checkoutList = bookCopy.getCheckoutList();
    checkoutList.add(checkout);
    bookCopy.setCheckoutList(checkoutList);
    bookCopyList = bookCopy.getBook()
            .getBookCopyList();
    bookCopyList.remove(bookCopy);
    bookCopy.getBook()
            .decreaseBookQuantity();
    return checkoutRepository.save(checkout);
  }

  public Checkout returnBook(CheckoutDTO checkoutDTO, UUID checkoutId) {
    List<Checkout> checkoutList = null;

    Checkout checkout = checkoutRepository.findById(checkoutId)
            .get();

    checkout.setReturnDate(new Date());

    UUID bookCopyId = checkoutDTO.getBookCopyId();
    BookCopy bookCopy = bookCopyRepository.findById(bookCopyId)
            .get();

    checkoutList = bookCopy.getCheckoutList();
    checkoutList.remove(checkout);
    bookCopy.setCheckoutList(checkoutList);
    bookCopy.getBook()
            .increaseBookQuantity();
    checkout.setReturnDate(new Date());
    return checkoutRepository.save(checkout);
  }
}
