package com.somejavaeestuff.jsonb;

import javax.json.bind.JsonbBuilder;

public class JsonBUser {

    public static void main(String[] args) throws Exception {
        var user = new User("john doe", "j.doe@test.test");
        try (var jb = JsonbBuilder.create()) {
            var jsonUser = jb.toJson(user);
            System.out.println("jsonUser = " + jsonUser);
            var u = jb.fromJson(jsonUser, User.class);
            System.out.println("u = " + u);
        }
    }
}
