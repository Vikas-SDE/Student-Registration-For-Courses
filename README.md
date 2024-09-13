Summary
Entity: Defines student data and ensures unique constraints.
Repository: Manages data access.
Service: Contains business logic for registration.
Controller: Handles HTTP requests and responses.
Email/SMS Configuration: Sets up services for notifications.
This setup ensures that each student's registration is unique, valid, and confirmed via email and SMS.




Student Registration Form

Fields: Name, Email ID, Mobile Number, and Course Name.
Validation:
Name: Required (mandatory).
Email ID: Must be unique and valid email format.
Mobile Number: Must be exactly 10 digits.
Course Name: Required (mandatory).
Unique Constraints

Email: Ensures that each student’s email is unique across the database.
Mobile Number: Ensures that each student’s mobile number is unique across the database.
Email and SMS Confirmation

Email: After successful registration, the student receives a confirmation email.
SMS: After successful registration, the student receives a confirmation SMS.
Components
Entity Class (Student)

Represents the student with fields for name, email, mobile number, and course name.
Ensures unique email and mobile number using unique constraints.
Validates mobile number to be exactly 10 digits.
Repository Interface (StudentRepository)

Extends JpaRepository to handle CRUD operations.
Provides methods to find students by email and mobile number.
Service Class (StudentService)

Contains the business logic for student registration.
Checks for existing email and mobile number before saving.
Saves the student to the database.
Controller Class (StudentController)

Defines the REST endpoint for student registration (/api/students/register).
Validates the input and calls the service to register the student.
Handles sending of email and SMS confirmations (placeholders for actual implementation).
Exception Handling

Handles exceptions such as validation errors or duplicate entries using a global exception handler.
Configuration for Email and SMS

Email: Configured using properties like SMTP server details in application.properties.
SMS: Placeholder for integration with an SMS service like Twilio.
Dependencies
Spring Boot Starters: For JPA, Web, Validation, and Mail.
MySQL Connector: If using MySQL as the database.
