package additional.selenium;

import com.github.javafaker.Faker;

public class RandomUser {
    public User createRandomUser(int digits){
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = faker.number().digits(digits);
        String name = faker.leagueOfLegends().champion();
        return new User(name, email, password);
    }
}
