package personal.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ValidateUser {
    private Pattern patternName = Pattern.compile("^\\S*$");
    private Pattern patternPhone = Pattern.compile("^\\d*$");
    public void check(User user) throws Exception {
        if(!patternName.matcher(user.getFirstName()).find()){
            throw new Exception("Illegal first name");
        } if(!patternName.matcher(user.getLastName()).find()){
            throw new Exception("Illegal last name");
        } if (!patternPhone.matcher(user.getPhone()).find()){
            throw new Exception("Illegal phone number");
        }
    }

}
