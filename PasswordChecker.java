import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class PasswordChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        
        PasswordResult result = analyzePassword(password);
        System.out.println("\n=== Password Analysis ===");
        System.out.println("Password Strength: " + result.strength);
        
        if (!result.weakPatterns.isEmpty()) {
            System.out.println("\n⚠ Weak Patterns Detected:");
            for (String pattern : result.weakPatterns) {
                System.out.println("  - " + pattern);
            }
        }
        
        if (!result.suggestions.isEmpty()) {
            System.out.println("\n💡 Suggestions for Improvement:");
            for (String suggestion : result.suggestions) {
                System.out.println("  - " + suggestion);
            }
        }
        
        if (result.strength.equals("Strong") && result.weakPatterns.isEmpty()) {
            System.out.println("\n✓ Your password is strong!");
        }
        
        scanner.close();
    }

    public static PasswordResult analyzePassword(String password) {
        int length = password.length();
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        String specialChars = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";
        
        List<String> suggestions = new ArrayList<>();
        List<String> weakPatterns = new ArrayList<>();

        // Check character types
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (specialChars.indexOf(c) != -1) hasSpecial = true;
        }

        // Check for weak patterns
        detectWeakPatterns(password, weakPatterns);

        // Generate suggestions based on missing criteria
        if (length < 8) {
            suggestions.add("Increase password length to at least 8 characters (current: " + length + ")");
        }
        if (!hasUpper) {
            suggestions.add("Add at least one uppercase letter (A-Z)");
        }
        if (!hasLower) {
            suggestions.add("Add at least one lowercase letter (a-z)");
        }
        if (!hasDigit) {
            suggestions.add("Add at least one number (0-9)");
        }
        if (!hasSpecial) {
            suggestions.add("Add at least one special character (!@#$%^&*, etc.)");
        }
        if (length >= 8 && length < 12) {
            suggestions.add("Consider using 12+ characters for even better security");
        }

        // Calculate strength
        int criteriaCount = 0;
        if (hasUpper) criteriaCount++;
        if (hasLower) criteriaCount++;
        if (hasDigit) criteriaCount++;
        if (hasSpecial) criteriaCount++;

        String strength;
        if (length >= 8 && criteriaCount == 4 && weakPatterns.isEmpty()) {
            strength = "Strong";
        } else if (length >= 8 && criteriaCount >= 3) {
            strength = "Medium";
        } else {
            strength = "Weak";
        }
        
        // Downgrade if weak patterns detected
        if (!weakPatterns.isEmpty() && strength.equals("Strong")) {
            strength = "Medium";
            suggestions.add("Avoid common patterns and sequences");
        }

        return new PasswordResult(strength, suggestions, weakPatterns);
    }

    private static void detectWeakPatterns(String password, List<String> weakPatterns) {
        String lowerPassword = password.toLowerCase();
        
        // Common weak passwords
        List<String> commonPasswords = Arrays.asList(
            "password", "12345678", "qwerty", "abc123", "letmein", 
            "welcome", "monkey", "123456789", "password1", "admin"
        );
        
        for (String common : commonPasswords) {
            if (lowerPassword.contains(common)) {
                weakPatterns.add("Contains common password pattern: '" + common + "'");
                break;
            }
        }
        
        // Sequential characters (123, abc, etc.)
        if (hasSequentialChars(password)) {
            weakPatterns.add("Contains sequential characters (e.g., 123, abc)");
        }
        
        // Repeated characters (aaa, 111, etc.)
        if (hasRepeatedChars(password)) {
            weakPatterns.add("Contains repeated characters (e.g., aaa, 111)");
        }
        
        // Keyboard patterns (qwerty, asdf, etc.)
        if (hasKeyboardPattern(lowerPassword)) {
            weakPatterns.add("Contains keyboard pattern (e.g., qwerty, asdf)");
        }
    }

    private static boolean hasSequentialChars(String password) {
        for (int i = 0; i < password.length() - 2; i++) {
            char c1 = password.charAt(i);
            char c2 = password.charAt(i + 1);
            char c3 = password.charAt(i + 2);
            
            if ((c2 == c1 + 1 && c3 == c2 + 1) || (c2 == c1 - 1 && c3 == c2 - 1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasRepeatedChars(String password) {
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i + 1) && 
                password.charAt(i) == password.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasKeyboardPattern(String password) {
        String[] patterns = {"qwerty", "asdfgh", "zxcvbn", "qwertz"};
        for (String pattern : patterns) {
            if (password.contains(pattern)) {
                return true;
            }
        }
        return false;
    }

    static class PasswordResult {
        String strength;
        List<String> suggestions;
        List<String> weakPatterns;

        public PasswordResult(String strength, List<String> suggestions, List<String> weakPatterns) {
            this.strength = strength;
            this.suggestions = suggestions;
            this.weakPatterns = weakPatterns;
        }
    }
}
