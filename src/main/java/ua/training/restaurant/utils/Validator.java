package ua.training.restaurant.utils;

import ua.training.restaurant.entity.RegexContainer;
import ua.training.restaurant.exceptions.UserDataNotValidException;

import java.util.regex.Pattern;

public class Validator {
    public static void throwExIfFundsNotValid(Long funds) {
        if (funds <= 0 || funds > 100000) {
            throw new IllegalArgumentException();
        }
    }

    public static void throwExIfUserNotValid(String username, String password, String nameEn, String nameUa) throws UserDataNotValidException {
        Pattern p  = Pattern.compile(RegexContainer.NAME_REGEX_UA, Pattern.UNICODE_CHARACTER_CLASS);

        if (!username.matches(RegexContainer.USERNAME_REGEX) || !nameEn.matches(RegexContainer.NAME_REGEX) || !p.matcher(nameUa).matches() ||
                password.length() < 3) {
            throw new UserDataNotValidException();
        }
    }
}
