package com.devsuperior.hr_oauth.services;

import com.devsuperior.hr_oauth.models.entities.PublicKeyDTO;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void storePublicKey(String key, String publicKey) {
        redisTemplate.opsForValue().set(key, publicKey);
    }

    public List<PublicKeyDTO> getPublicKeys(String key) {

        String value = redisTemplate.opsForValue().get(key);

        if (value == null) {
            return new ArrayList<>();
        }

        if (!value.startsWith("{") || !value.endsWith("}")) {
            throw new JsonSyntaxException("Invalid JSON format");
        }

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {}.getType();
        Map<String, String> map = gson.fromJson(value, type);

        PublicKeyDTO publicKeyDTO = new PublicKeyDTO();
        publicKeyDTO.setKty(map.get("kty"));
        publicKeyDTO.setE(map.get("e"));
        publicKeyDTO.setKid(map.get("kid"));
        publicKeyDTO.setN(map.get("n"));

        List<PublicKeyDTO> publicKeyDTOList = new ArrayList<>();
        publicKeyDTOList.add(publicKeyDTO);
        return publicKeyDTOList;
    }
}
