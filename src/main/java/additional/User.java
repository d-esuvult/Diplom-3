package additional;

import com.github.javafaker.Faker;

public class User {
    private static String name;
    private static String email;
    private static String password;

    public static User getDefaultUser() {
        return new User("Name", "test@example.com", "123456");
    }

    public static User getRandomUser(int passwordDigitsNumber) {
        Faker faker = new Faker();
        return new User(faker.ancient().primordial(), faker.internet().emailAddress(), faker.number().digits(passwordDigitsNumber));
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static void setPassword(String password) {
        User.password = password;
    }
}
