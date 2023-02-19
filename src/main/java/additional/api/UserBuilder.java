package additional.api;

import com.github.javafaker.Faker;

public class UserBuilder {
    public static JSONUser createRandomUser() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = faker.number().digits(6);
        String name = faker.leagueOfLegends().champion();
        return new JSONUser(email, password, name);
    }
}