package com.itheima;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void testGenerateJwt(){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","admin");
        String jwt = Jwts.builder().
                signWith(SignatureAlgorithm.HS256, "axRoZwltYQ==")
                .addClaims(dataMap)//添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置过期时间
                .compact();//生成token
        System.out.println(jwt);
    }
    @Test
    public void testParseJwt(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc0MjEwMzI3OH0.KuX4xmoFtgJlMwav_OLogy_bCooEb6VH9rWo_lEY1cQ";
        Claims claims = Jwts.parser()
                .setSigningKey("axRoZwltYQ==")
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);
    }
}
