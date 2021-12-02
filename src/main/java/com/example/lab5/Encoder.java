package com.example.lab5;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class Encoder {
    public String encode(String argument){
        return Base64.getEncoder().encodeToString(argument.getBytes(StandardCharsets.UTF_8));

    }
    public String decode(String argument){
        byte[] base=Base64.getDecoder().decode(argument);
        return new String(base);
    }
}
