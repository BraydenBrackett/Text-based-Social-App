# CS180-Project_05
## Operation Instructions:
- TODO AT LAST TEAM MEETING <br />
## Who Submitted Which Part:
- All Code and Subsequent Code Related Files - TODO AT TUESDAY'S MEETING <br />
- Report - Brayden <br />
- Presentation - Erik <br />

## About Classes:
### TODO AT TUESDAY'S MEETING - CHECK FOR OTHER CLASSES AND WRITE THEM DOWN
### Account 
- Is the base class for an account object. An account within the program is an object that is comprised of a username, password, and a flag for whether that account is a teacher or student.
- The account class is a very simple object class comprised of getters and setters alongside a basic constructor. <br />
- Account only interacts with the login system, although the files it creates are referenced by several other classes such as teacher and student. <br />
### Login 
- Acts as the "first class" in the project execution hierarchy and runs the main method of the entire program. Welcomes user to the project and runs them through logging in and creating new classes. <br />
- Handles both logging into old accounts and creating new accounts via a .txt file that stores all relevant account information and checks for similarities when logging in and creating new accounts. <br />
- The login class holds the main method of the program so it interacts with all the classes in the project. <br />
### Quiz
- Contains an array of question objects and different methods allowing you to customize quiz objects. <br />
- Writes quizzes to a file with the writeQuiz method. This method runs each question in the arrays writeQuestion method. <br />
- Interacts with student and teacher. Teachers create quizzes and customize them with the quiz class methods. Students can take quizzes. <br />
### Question 
-Objects of type Question are written into the quiz file using the given question type's writeQuestion method. <br />
-The different question types extend this class in order to create different types of questions and a polymorphic array of questions in the quiz class. <br />
-Interacts with quiz (quiz contains an arrayList of these objects).  <br />
### MCQuestion
- Has a question field and four different answer choices. <br />
- Has a writeQuestion method that overrides the method in the question class that writes multiple choice questions to the file. Writes the question then the answers choices underneath. <br />
- Is a subclass of Question. <br />
### FRQQuestion
- Has a question field. <br />
- Has a writeQuestion method that overrides the method in the question class that writes free response questions to the file. Writes the question then an area for the student to answer the question underneath. <br />
- Is a subclass of Question. <br />
### FillInTheBlankQuestion
- Has a question field. <br />
- Has a writeQuestion method that overrides the method in the question class that writes Fill in the blank questions to the file. Writes the question with a word blanked out then a blank space for the student to enter their answer for the blanked-out word. <br />
- Is a subclass of Question. <br />
### Teacher 
- Manages the teacher interface and options that a teacher has when they log into an account. Options include features such adding quizzes from files, creating quizzes, deleting quizzes, randomizing quizzes, and grading quizzes alongside other key features. <br />
- Checks were made to ensure that the different methods teachers could do were running properly and made the correct modifications to the .txt files. We also checked to ensure that the quizzes were being made correctly. <br />
- The teacher classes creates and manages courses and quiz content. The teacher class reads from accounts and quiz files edited by login and the student class. <br />
### Student 
- The student class manages all the options that students can make with a variety of methods that edit and modify students responses to quizzes. It also gives the students the option to access teacher class created courses and quizzes. <br />
- Student primarily interacts with quiz and teacher classes by reading from quiz files that the teacher creates and then returning those quiz files to the teacher. <br />
### Course
- The course class allows for the management of courses and the creation of courses. <br />
- Due to the nature of the class, it mainly interacts with the login, teacher, and student classes. <br />

## Who Did What:
- Brayden - Server, Client, README, Documentation  <br />
- Brandon - Teacher GUI, Project reformatting <br />
- James - Server, Synchronization <br />
- Erik - Synchronization, Presentation <br />
- Harshini - Student GUI, Login Gui <br />

