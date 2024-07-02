package com.shop.demo.config.jwt;

import com.shop.demo.community.domain.User;
import com.shop.demo.community.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import java.time.Duration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProperties jwtProperties;

    @DisplayName("generateToken : 유저 정보와 만료 기간을 전달해 토큰을 만든다")
    @Test
    void generateToken() {
        userRepository.deleteAll();
        // given
        User testUser = userRepository.save(User.builder()
                .email("1234@naver.com")
                .password("1234")
                .build());

        // when
        String token = tokenProvider.generateToken(testUser, Duration.ofDays(14));

        // then
        Long userId = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);

        Assertions.assertThat(userId).isEqualTo(testUser.getId());

    }
}
