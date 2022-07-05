package com.example.reviewmate.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


@Component
public class TokenProvider implements InitializingBean {
    // JWT 토큰을 발행하고  ( createToken() )
    //  Payload에 들어간 클레임을 통해 User객체를 생성하여 (getAuthentication () )
    // Authentication 객체를 반환
    // HTTP Request Header로부터 토큰을 가져오고 (validateToken())
    // 토큰을 검증하는 모든 기능을 TokenProvider에 구현
    // 유저 정보로 JWT 토큰을 만들거나 토큰을 바탕으로 유저정보를 가져온다.
    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private String secretKey;
    private  final long tokenValidityInMilliseconds;
    

    private Key Key;

    public TokenProvider(
           @Value("${jwt.secretKey}") String secretKey,
           @Value("${jwt.token-validity-in-seconds}") long tokenValidityInMilliseconds) {
        this.secretKey = secretKey;
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds * 1000;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.Key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 적절한 설정을 통해 토큰을 생성하여 반환
     * @param authentication
     * @return
     */
    public String createToken(Authentication authentication){
        //권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        //access token 생성
        return Jwts.builder() // jwt 빌드
                .setSubject(authentication.getName()) //토큰에 저장될 데이터를 지정해준다.
                //넘겨받은 유저정보의 이 메소드가 username을 가져옴
                .claim(AUTHORITIES_KEY,authorities)
                .signWith(Key, SignatureAlgorithm.HS512) // 파라미터로 받은 키로 sha512 알고리즘 사용하여 서명
                .setExpiration(validity) // 토큰 만료 시간
                .compact();

    }


    /**
     * 토큰으로부터 클레임을 만들고, 이를 통해 User 객체를 생성 -> Authentication 객체르 반환
     * @param accessToken
     * @return
     */
    public Authentication getAuthentication (String accessToken){
        //토큰 복호화 하기 -> 토큰에 들어가 있는 정보를 꺼낸다.
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();

        if(claims.get(AUTHORITIES_KEY)==null){
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");}

            //claim에서 권한 정보 가져오기
            Collection<? extends GrantedAuthority>authorities =
                    Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

            User principal = new User(claims.getSubject(),"", authorities);

            return new UsernamePasswordAuthenticationToken(principal,"",authorities);
        }

    /**
     * 토큰을 검증
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        //토큰 정보를 검증한다.
            try {
                Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
                return true;
            } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
                logger.info("잘못된 JWT 서명입니다.");
            } catch (ExpiredJwtException e) {
                logger.info("만료된 JWT 토큰입니다.");
            } catch (UnsupportedJwtException e) {
                logger.info("지원되지 않는 JWT 토큰입니다.");
            } catch (IllegalArgumentException e) {
                logger.info("JWT 토큰이 잘못되었습니다.");
            }
            return false;
        }
    }



