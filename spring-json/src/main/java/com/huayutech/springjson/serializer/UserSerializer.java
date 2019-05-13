package com.huayutech.springjson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.huayutech.springjson.domain.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserSerializer extends StdSerializer<User> {

    public UserSerializer() {
        this(null);
    }

    public UserSerializer(Class<User> T) {
        super(T);
    }

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        Map<String, Object> map = new HashMap<>();

        map.put("id", user.getId());
        map.put("name", user.getName());

        jsonGenerator.writeObject(map);
    }
}
