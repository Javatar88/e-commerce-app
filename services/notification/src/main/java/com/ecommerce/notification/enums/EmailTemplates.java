package com.ecommerce.notification.enums;

import lombok.Getter;

@Getter
public enum EmailTemplates {
    PAYMENT_CONFIRMATION("payment-confirmation.html","Payment Successfully processed"),
    ORDER_CONFIRMATION("order-confirmation.html","Order Confirmation");


    private final String template;

    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
