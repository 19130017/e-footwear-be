package vn.edu.hcmuaf.fit.efootwearspringboot.services.account;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Account;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


@Service
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public String generateToken(Account account, Collection<? extends GrantedAuthority> authorities) {
        return JWT
                .create()
                .withSubject(account.getUsername())
                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpiration))
                .sign(Algorithm.HMAC256(secretKey.getBytes()));
    }

    public String refreshToken(Account account) {
        return JWT
                .create()
                .withSubject(account.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshExpiration))
                .sign(Algorithm.HMAC256(secretKey.getBytes()));
    }

    public String getSubject(String token) throws TokenExpiredException, SignatureVerificationException {
        JWTVerifier verifier = getVerifier();
        return verifier.verify(token).getSubject();
    }
    public JWTVerifier getVerifier() {
        JWTVerifier verifier;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
            verifier = JWT.require(algorithm).build();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Token không hợp lệ");
        }
        return verifier;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getVerifier().verify(token).getSubject();
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        JWTVerifier verifier = getVerifier();
        Date expirationDate = verifier.verify(token).getExpiresAt();
        return expirationDate.before(new Date());
    }
}
