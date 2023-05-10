package com.rest_api.fs14backend.checkout;

import com.rest_api.fs14backend.book.BookService;
import com.rest_api.fs14backend.bookCopy.BookCopy;
import com.rest_api.fs14backend.bookCopy.BookCopyService;
import com.rest_api.fs14backend.user.UserDTO;
import com.rest_api.fs14backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/borrow")
public class CheckoutController {
  private final CheckoutService checkoutService;
  private final UserService userService;
  private final BookCopyService bookCopyService;
  private final BookService bookService;

  @PostMapping
  public void checkoutBook(@RequestBody Checkout checkout) {
    UserDTO user = userService.findById(checkout.getUser()
            .getId());
    BookCopy bookCopy = bookCopyService.findById(checkout.getBookCopy()
            .getId());
    bookCopy.getBook()
            .getId();
//    System.out.println(user + "user");
    System.out.println(bookCopy + "bookCopy");
//    Book book = bookService.findById()

//    if (book.getQuntity < 1) {
//      return "The book \"" + book.getName() + "\" is out of stock!";
//    }

//    book.borrowBook();
//      bookService.createOne(bookCopy);

  }
}
