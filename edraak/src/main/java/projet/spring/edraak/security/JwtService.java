package projet.spring.edraak.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;


@Service
public class JwtService {
    //@Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    //@Value("${application.security.jwt.secret-key}")
    private String secretKey;
    private static final Logger logger = Logger.getLogger(JwtService.class.getName());
    public JwtService(
            @Value("${application.security.jwt.expiration}") long jwtExpiration,
            @Value("${application.security.jwt.secret-key}") String secretKey) {
        if (secretKey == null || secretKey.length() < 32) {
            throw new IllegalArgumentException("Secret key must be at least 32 characters");
        }
        this.jwtExpiration = jwtExpiration;
        this.secretKey = secretKey;

    }

    @PostConstruct
    public void init() {
        logger.info("JWT Expiration: " + jwtExpiration);
        logger.info("JWT Secret Key: " + secretKey);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }
    private String generateToken(Map<String, Object> claims, UserDetails userDetails){
        return buildToken(claims, userDetails,jwtExpiration);
    }

    private String buildToken(Map<String, Object> extraClaims,
                              UserDetails userDetails,
                              long jwtExpiration)
    {
        var authorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
            return Jwts
                    .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .claim("authorities", authorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                    .claim("authorities", authorities)
                .signWith(getSignInKey())
                .compact();
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        // extract the username from the token
        final String username = extractUsername(token);
        // check if the username in the token is the same as the username in the userDetails and the token is not expired
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] secretBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
