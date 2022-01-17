package com.example.bookServe.tool;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//生成token的工具类
public class JwtTool {
    static String secret = "token";
    //生成token
    public String createToken(Map<String, Object> claims) {
        Map<String, Object> header = new HashMap<String, Object>();
        header.put(JwsHeader.TYPE, JwsHeader.JWT_TYPE);
        header.put(JwsHeader.ALGORITHM, "HS256");
        // 生成token
        String jwt = Jwts.builder()
                .setHeader(header)  //header，可省略
                .setClaims(claims) //payload，存放数据的位置，不能放置敏感数据，如：密码等
                .signWith(SignatureAlgorithm.HS256, secret) //设置加密方法和加密盐
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 1)) //设置过期时间，3秒后过期
                .compact();
        return jwt;
    }

    //校验token
    public void decodeToken(String token) {
        try {
            // 通过token解析数据
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println(body);
        } catch (ExpiredJwtException e) {
            System.out.println("token已经过期！");
        } catch (Exception e) {
            System.out.println("token不合法！");
        }
    }
}

