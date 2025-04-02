package com.itheima.utils;

import com.itheima.pojo.Emp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static final String SECRET_KEY="aXRoZWltYQ=="; //密钥
    private static final long EXPIRATION_TIME=12*60*60*1000; //12小时
    /**
     * 生成token
     * @param claims 令牌中包含的信息
     * @return 生成的JWT令牌字符串
     */
    public static String generateToken(Map<String,Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .compact();
    }
    /**
     * 解析token
     * @param token 需要解析的token
     * @return 解析后的Claims对象
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
