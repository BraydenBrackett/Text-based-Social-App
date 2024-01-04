# Text-based-Social-App
## Operation Instructions:
1. Run the ThreadedServer.java class using the green arrow
2. Run the Client.java class using the green arrow
3. Run as many other Client.java classes as you'd like as long as you're using the green arrow or fully compliling the project
## Who Submitted Which Part:
- All Code and Subsequent Code Related Files - Erik 
- Report - Harshini 
- Presentation - Erik 
- **PLEASE NOTE: WITH REGARDS TO THE COMMIT HISTORY:** James had a malfunction with his laptop and as such was unable to commit code to the git repo. He did however write code for several classes - mainly the servers - which was sent across other communication streams. Please take this into consideration...

## About Classes:
### Account 
- Is the base class for an account object. An account within the program is an object that is comprised of a username, password, and a flag for whether that account is a teacher or student.
- The account class is a very simple object class comprised of getters and setters alongside a basic constructor.
- Account only interacts with the login system, although the files it creates are referenced by several other classes such as teacher and student.
### Login 
- Acts as the "first class" in the project execution hierarchy and runs the main method of the entire program. Welcomes user to the project and runs them through logging in and creating new classes. 
- Handles both logging into old accounts and creating new accounts via a .txt file that stores all relevant account information and checks for similarities when logging in and creating new accounts. 
- The login class holds the main method of the program so it interacts with all the classes in the project. 
### Quiz
- Contains an array of question objects and different methods allowing you to customize quiz objects. 
- Writes quizzes to a file with the writeQuiz method. This method runs each question in the arrays writeQuestion method. 
- Interacts with student and teacher. Teachers create quizzes and customize them with the quiz class methods. Students can take quizzes. 
### Question 
- Objects of type Question are written into the quiz file using the given question type's writeQuestion method. 
- The different question types extend this class in order to create different types of questions and a polymorphic array of questions in the quiz class. 
- Interacts with quiz (quiz contains an arrayList of these objects).  
### MCQuestion
- Has a question field and four different answer choices. 
- Has a writeQuestion method that overrides the method in the question class that writes multiple choice questions to the file. Writes the question then the answers choices underneath. 
- Is a subclass of Question. 
### FRQQuestion
- Has a question field. 
- Has a writeQuestion method that overrides the method in the question class that writes free response questions to the file. Writes the question then an area for the student to answer the question underneath. 
- Is a subclass of Question. 
### FillInTheBlankQuestion
- Has a question field. 
- Has a writeQuestion method that overrides the method in the question class that writes Fill in the blank questions to the file. Writes the question with a word blanked out then a blank space for the student to enter their answer for the blanked-out word. 
- Is a subclass of Question. 
### Teacher 
- Manages the teacher interface and options that a teacher has when they log into an account. Options include features such adding quizzes from files, creating quizzes, deleting quizzes, randomizing quizzes, and grading quizzes alongside other key features. 
- Checks were made to ensure that the different methods teachers could do were running properly and made the correct modifications to the .txt files. We also checked to ensure that the quizzes were being made correctly. 
- The teacher classes creates and manages courses and quiz content. The teacher class reads from accounts and quiz files edited by login and the student class. 
### Student 
- The student class manages all the options that students can make with a variety of methods that edit and modify students responses to quizzes. It also gives the students the option to access teacher class created courses and quizzes. 
- Student primarily interacts with quiz and teacher classes by reading from quiz files that the teacher creates and then returning those quiz files to the teacher. 
### Course
- The course class allows for the management of courses and the creation of courses. 
- Due to the nature of the class, it mainly interacts with the login, teacher, and student classes. 
### Client
- The Client class sends data to the server and recieves data from the server to write a file
- Client interacts with and sends and recieves data from the Login, TeacherGUI, and StudentGUI classes
### Main
- Used to test methods
### ThreadedServer
- Recieves String data from the Client and writes this data to a file
- ClearFile method to avoid duplication
- Interacts with the client class
- Fully concurent
### StudentGUI
- GUI to run the Student class
- gets data to send from client to server
- fully concurent
### TeacherGUI
- GUI to run the Teacher class
- gets data to send from client to server
- fully concurent

## Who Did What:
- Brayden - Server, Client, README, Documentation  
- Brandon - Teacher GUI, Project reformatting, Test Cases 
- James - Server, Synchronization
- Erik - Synchronization, Presentation, Test Cases 
- Harshini - Student GUI, Login Gui 

