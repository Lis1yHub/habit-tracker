package com.natasha.habit_tracker.auth;

import com.natasha.habit_tracker.DTO.*;
import com.natasha.habit_tracker.Models.Habit;
import com.natasha.habit_tracker.Models.User;
import com.natasha.habit_tracker.Repositories.UserRepository;
import com.natasha.habit_tracker.Security.JwtAuthenticationFilter;
import org.springframework.core.env.Environment;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

// запускает реальный встроенный сервер (Tomcat/Jetty) на случайном свободном порту
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthIntegrationTest {

    @MockBean // полное отключение security-фильтра
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private Environment env;

    @Test
    void printProperties() {
        System.out.println("ddl-auto = "
                + env.getProperty("spring.jpa.hibernate.ddl-auto"));

        System.out.println("hibernate.auto = "
                + env.getProperty("spring.jpa.properties.hibernate.hbm2ddl.auto"));

        System.out.println("liquibase = "
                + env.getProperty("spring.liquibase.enabled"));
    }

    @Test
    @Order(1)
    void register_shouldSaveHashedPassword() {

        RegisterRequest request = new RegisterRequest();
        request.setUsername("alice");
        request.setPassword("12345");
        request.setEmail("alice@mail.com");

        ResponseEntity<RegisterResponse> response = restTemplate
                .postForEntity("/api/auth/register", request, RegisterResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        User user = userRepository.findByUsername("alice").orElseThrow();

        assertNotEquals("12345", user.getPassword());
        assertTrue(user.getPassword().startsWith("$2a$"));
    }

    @Test
    @Order(2)
    void login_shouldReturnToken_whenPasswordIsCorrect() {

        LoginRequest request = new LoginRequest();
        request.setUsername("alice");
        request.setPassword("12345");

        ResponseEntity<LoginResponse> response = restTemplate
                .postForEntity("/api/auth/login", request, LoginResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody().getToken());

        assertEquals("alice", response.getBody().getUsername());
    }

    @Test
    @Order(3)
    void login_shouldReturn401_whenPasswordIsWrong() {

        LoginRequest request = new LoginRequest();
        request.setUsername("alice");
        request.setPassword("wrong_password");

        ResponseEntity<String> response = restTemplate
                .postForEntity("/api/auth/login", request, String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    @Order(4)
    void getHabits_shouldReturn401_withoutToken() {

        ResponseEntity<String> response = restTemplate
                .getForEntity("/api/habits", String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    @Order(5)
    void userShouldOnlySeeOwnHabits() {

        // создаём второго пользователя bob
        RegisterRequest bob = new RegisterRequest();
        bob.setUsername("bob");
        bob.setPassword("12345");
        bob.setEmail("bob@mail.com");

        restTemplate.postForEntity("/api/auth/register", bob, RegisterResponse.class);

        // логиним alice
        LoginRequest login = new LoginRequest();
        login.setUsername("alice");
        login.setPassword("12345");

        ResponseEntity<LoginResponse> loginResponse = restTemplate
                .postForEntity("/api/auth/login", login, LoginResponse.class);

        String token = loginResponse.getBody().getToken();

        // создаём headers с JWT
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        // создаём привычку alice
        HabitRequest habit = new HabitRequest();
        habit.setName("Run");

        HttpEntity<HabitRequest> requestEntity = new HttpEntity<>(habit, headers);

        restTemplate.exchange(
                "/api/habits",
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // получаем habits alice
        ResponseEntity<Habit[]> response = restTemplate.exchange(
                "/api/habits",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Habit[].class
        );

        // проверяем что только 1 привычка
        assertEquals(1, response.getBody().length);
    }
}
