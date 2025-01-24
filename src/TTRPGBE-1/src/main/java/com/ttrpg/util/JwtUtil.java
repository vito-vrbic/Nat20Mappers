package com.ttrpg.util;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

// Ovu klasu mi je Luka poslao
public class JwtUtil {
    private static final String SECRET_KEY ="2345675643524354342387699w457566334355674634543456554345654";
    //ovo kasnije možemo sakriti negdje sa strane
    private static final long toExpire = 43200000;     //12 sati

    public static String generateJWT(String username) {
        SecretKey superSecretKey = new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
        //generiramo tip ključa iz stringa
        return Jwts.builder()
                .setSubject(username)     //subjekt jwta je username jer je jedinstven
                .setIssuedAt(new Date(System.currentTimeMillis()))     //trenutno vrijeme
                .setExpiration(new Date(System.currentTimeMillis() + toExpire)) //trenutno vrijeme plus pola dana
                .signWith(superSecretKey)  //koristimo ključ
                .compact();

    }
    public static Claims validateJWT(String jwt) {
        try {
            SecretKey superSecretKey = new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
            return Jwts.parserBuilder().setSigningKey(superSecretKey).build().parseClaimsJws(jwt).getBody();
        } catch (ExpiredJwtException e) {
            System.out.println("Token expired: " + e.getMessage());
            throw new RuntimeException("The token has expired", e);
        } catch (SignatureException e) {
            System.out.println("Invalid token signature: " + e.getMessage());
            throw new RuntimeException("The token signature is invalid", e);
        }
    }

}
