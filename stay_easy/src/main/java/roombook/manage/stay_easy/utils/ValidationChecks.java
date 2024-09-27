package roombook.manage.stay_easy.utils;

public class ValidationChecks {

    public static boolean emailPatternCheck(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        
        // If email is null, return false
        if (email == null || email == "") {
            return false;
        }

        // Return true if the email matches the regex, otherwise false
        return email.matches(emailRegex);
    }

    public static boolean namePatternCheck(String name){
        String nameRegex = "^[a-zA-Z ]+$";
        
        // If email is null, return false
        if (name == null || name == "") {
            return false;
        }

        // Return true if the email matches the regex, otherwise false
        return name.matches(nameRegex);

    }

}
