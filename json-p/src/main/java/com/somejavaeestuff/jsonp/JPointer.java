package com.somejavaeestuff.jsonp;

import javax.json.Json;
import java.io.IOException;

public class JPointer {

    public static void main(String[] args) {
        try (
                var is = JPointer.class.getClassLoader().getResourceAsStream("user.json");
                var jr = Json.createReader(is)
        ) {
            var js = jr.read();
            var jp = Json.createPointer("/user/profile");
            var value = jp.getValue(js);
            System.out.println("profile: " + value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
