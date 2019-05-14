package com.tensquare.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 测试jjwt
 * @author: Arnold
 * @since: 2019/5/12 10:05
 * @version: v1.0.0
 */
public class CreateJwtTest extends ApplicationTest {
    @Test
    public void createTest(){
        JwtBuilder jwtBuilder = Jwts.builder().setId("123123")
                .setSubject("Iron Man")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+60*1000))
                .claim("role","admin")
                .signWith(SignatureAlgorithm.HS256, "tongtong");
        System.out.println(jwtBuilder.compact());
    }
    @Test
    public void pareTest(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjMxMjMiLCJzdWIiOiJJcm9uIE1hbiIsImlhdCI6MTU1NzYyODI5NCwiZXhwIjoxNTU3NjI4MzU0LCJyb2xlIjoiYWRtaW4ifQ.230fJrphr2jhtNDOz6DWp6LkcQvC0XcmwLaFL4y0Ew0";
        Claims claims = Jwts.parser().setSigningKey("tongtong").parseClaimsJws(token).getBody();
        System.out.println(claims);
        System.out.println(claims.get("role"));
        System.out.println("创建时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
    }
}
