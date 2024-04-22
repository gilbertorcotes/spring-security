package mx.darthill.api.springsecurity.service.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.expiration-in-minutes}")
    private Long EXPIRATION_IN_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    public String generateToken(UserDetails usuario, Map<String, Object> extraClaims) {

        Date issuerAt = new Date(System.currentTimeMillis());
        Date expiration = new Date( (EXPIRATION_IN_MINUTES * 60 * 1000) + issuerAt.getTime() );
//        String jwt = Jwts.builder()
//                .setClaims(extraClaims)
//                .setSubject(usuario.getUsername())
//                .setIssuedAt(issuerAt)
//                .setExpiration(expiration)
//                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
//                .signWith(generateKey(), SignatureAlgorithm.HS256)
//                .compact();

        String jwt = Jwts.builder()
                .header()
                    .type("JWT")
                    .and()
                .subject(usuario.getUsername())
                .issuedAt(issuerAt)
                .expiration(expiration)
                .claims(extraClaims)
                .signWith(generateKey(), Jwts.SIG.HS256)
                .compact();
        return jwt;
    }

    private SecretKey generateKey() {
        byte[] passwordDecoder = Decoders.BASE64.decode(SECRET_KEY);
        System.out.println(new String(passwordDecoder));
        return Keys.hmacShaKeyFor(passwordDecoder);
    }

    public String extractusername(String jwt) {

        return extractAllClaims(jwt).getSubject();

    }

    private Claims extractAllClaims(String jwt) {

        return Jwts.parser().verifyWith( generateKey() ).build()
                .parseSignedClaims(jwt).getPayload();

    }
}
