package com.OrderManagement.service;

import com.OrderManagement.exception.OrderNotFoundException;
import com.OrderManagement.model.Customer;
import com.OrderManagement.model.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
@AllArgsConstructor
public class EmailService {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final JavaMailSender javaMailSender;
    private static final String EMAIL = "tomjanuskevic@gmail.com";

    public void sendEmail(Customer customer, Long orderId) throws MessagingException, OrderNotFoundException {
        Order order = orderService.getOrderById(orderId);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(EMAIL);
        messageHelper.setTo(customer.getEmail());
        messageHelper.setSubject("Order " + order.getDescription());
        messageHelper.setText("Hello, " + customer.getName() + " " + customer.getLastname()
                + " your order was done");

        javaMailSender.send(message);
        log.info("Email was sent");
    }
}
