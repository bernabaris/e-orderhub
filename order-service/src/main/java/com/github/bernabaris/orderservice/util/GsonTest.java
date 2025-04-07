package com.github.bernabaris.orderservice.util;

import com.github.bernabaris.common.model.Order;
import com.google.gson.Gson;

public class GsonTest {
    public static void main(String[] args) {
        Order savedOrder = gson.fromJson(message, Order.class);
        System.out.println(savedOrder);
    }
    private static final Gson gson = new Gson();
    static String message = "{\n" +
            "  \"id\": 1,\n" +
            "  \"userId\": 101,\n" +
            "  \"productId\": 202,\n" +
            "  \"quantity\": 3,\n" +
            "  \"status\": \"PENDING\"\n" +
            "}";

}


