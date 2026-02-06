# Password Strength Checker

A comprehensive Java-based password strength analyzer that evaluates password security and provides actionable feedback to help users create stronger passwords.

## Features

### 🔒 Password Strength Classification
- **Strong**: 8+ characters with uppercase, lowercase, numbers, special characters, and no weak patterns
- **Medium**: 8+ characters with at least 3 character types
- **Weak**: Passwords that don't meet minimum security requirements

### 🛡️ Weak Pattern Detection
The application detects and warns about common security vulnerabilities:
- **Common passwords**: Identifies frequently used passwords (password, 12345678, qwerty, etc.)
- **Sequential characters**: Detects patterns like "123", "abc", "xyz"
- **Repeated characters**: Flags repetitions like "aaa", "111"
- **Keyboard patterns**: Identifies patterns like "qwerty", "asdfgh"

### 💡 Smart Suggestions
Provides specific, actionable recommendations:
- Increase password length if below 8 characters
- Add missing character types (uppercase, lowercase, numbers, special characters)
- Recommends 12+ characters for enhanced security
- Advises avoiding common patterns and sequences

## Requirements

- Java Development Kit (JDK) 8 or higher
- Command line terminal

## Installation

1. Clone the repository:
```bash
git clone https://github.com/YOUR_USERNAME/password-strength-checker.git
cd password-strength-checker
```

2. Compile the program:
```bash
javac PasswordChecker.java
```

## Usage

Run the program:
```bash
java PasswordChecker
```

Enter a password when prompted, and the program will analyze it and provide detailed feedback.

### Example Output

```
Enter your password: password123

=== Password Analysis ===
Password Strength: Weak

⚠ Weak Patterns Detected:
  - Contains common password pattern: 'password'
  - Contains sequential characters (e.g., 123, abc)

💡 Suggestions for Improvement:
  - Add at least one uppercase letter (A-Z)
  - Add at least one special character (!@#$%^&*, etc.)
  - Avoid common patterns and sequences
```

```
Enter your password: MyS3cur3P@ssw0rd!

=== Password Analysis ===
Password Strength: Strong

✓ Your password is strong!
```

## How It Works

The Password Strength Checker evaluates passwords based on:

1. **Length Requirements**: Minimum 8 characters (recommends 12+)
2. **Character Diversity**: 
   - Uppercase letters (A-Z)
   - Lowercase letters (a-z)
   - Numbers (0-9)
   - Special characters (!@#$%^&*, etc.)
3. **Pattern Analysis**: Checks for common weak patterns and sequences
4. **Security Scoring**: Combines all factors to determine overall strength

## Project Structure

```
password-strength-checker/
├── PasswordChecker.java    # Main application file
├── README.md               # Project documentation
└── .gitignore             # Git ignore file
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is open source and available under the MIT License.

## Author

Created as a Java security tool for password validation and user education.

## Future Enhancements

- [ ] Password entropy calculation
- [ ] Dictionary attack simulation
- [ ] Password generation feature
- [ ] GUI interface
- [ ] Support for multiple languages
- [ ] Password strength meter visualization

---

**Note**: This tool is for educational purposes. Always use established security libraries for production applications.
