package com.vaddi.spring_graphql.entity;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@CrossOrigin("*")
@Controller
public class BookResolver {
	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

    private final Sinks.Many<String> messageSink = Sinks.many().multicast().onBackpressureBuffer();

	@QueryMapping
	public List<Book> getBooks() {
		return bookService.getBooks();
	}
	
	@QueryMapping
    public String getMessage() {
        return "Hello, GraphQL - Subscription purpose!";
    }

	@QueryMapping
	public Book getBookById(@Argument Long id) {
		return bookService.getBookById(id).orElse(null);
	}
	
	@MutationMapping
    public String sendMessage(@Argument String message) {
        messageSink.tryEmitNext(message); // Emit the message to subscribers
        return "Message sent: " + message;
    }

	@MutationMapping
    public Book createBook(@Argument String title, @Argument String author) {
        return bookService.createBook(title, author);
    }

	@MutationMapping
	public User createUser(@Argument String name, 
			@Argument String phone, @Argument String email, @Argument String password) {
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setPhone(phone);
		return userService.createUser(user);
    }
	
	@MutationMapping
	public User createUsers(@Argument("user") User user) {
		return userService.createUser(user);
    }
	
	@SubscriptionMapping
    public Flux<String> messageStream() {
        return messageSink.asFlux().delayElements(Duration.ofSeconds(1)); // Stream messages every second
    }
}
