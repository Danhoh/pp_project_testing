package ru.kata.spring.boot_security.demo.service.data;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTests {
    UserService userService;

    @Autowired
    public UserServiceTests(UserService userService) {
        this.userService = userService;
    }

    @BeforeEach
    void beforeTest() {
        System.out.println("Before each");
    }

    @Test
    void userServiceLoads() {
        assertNotNull(userService);
    }

    @Test
    void userServiceNotEmpty() {
        assertFalse(userService.getAllUsers().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "3,3", "4,4", "3,3"})
    void parametrizedTest(Integer a, Integer b) {
        assertEquals(a, b);
    }

    @ParameterizedTest
    @MethodSource("argMethod")
    @DisplayName("test arguments from method")
    void testArgMethod(int a, int b) {
        assertEquals(a, b);
    }

    Stream<Arguments> argMethod() {
        return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(3, 3),
                Arguments.of(5, 5)
        );
    }

    @AfterEach
    void afterTest() {
        System.out.println("After each");
    }

    @AfterAll
    void afterAll() {
        System.out.println("After all");
    }
}
