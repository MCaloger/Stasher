# Stasher
Full-stack Secret Stashing Application

I created Stasher to gain some experience with encryption and security practices. The front-end is a TypeScript React app that uses [React Router](https://reactrouter.com/) for navigation.

Stasher improves security by creating messages that can only be accessed once and in a one-hour time period. This improves the sharing of credentials or sensitive information over a chat application by removing plaintext passwords in a communication channel, and ensuring that credential access can only be ateempted a single time.

The server portion was written in Java with the Spring Framework as the web layer. JPA is used with an H2 database for storage.

## Screenshots

![1_home](https://user-images.githubusercontent.com/3233613/169929898-9b48b3be-c1f8-43b4-aa74-434683af49e4.png)
Gives users the ability to enter a message and optionally apply a password required to access the message.

![2_new_secret](https://user-images.githubusercontent.com/3233613/169929971-6a9cf9a9-1f9b-4883-8e40-22d9940d20cb.png)
After submitting a message, the user will receieve a URL they can share to give a one-time access link.

![3_read_secret](https://user-images.githubusercontent.com/3233613/169929996-457a36f1-3a24-4074-95f3-9b2db456d2e3.png)
If the link hasn't been used and the time limit hasn't expired, following the link will show the message.

![4_secured_secret](https://user-images.githubusercontent.com/3233613/169930043-7f70f18e-7d72-4882-891b-e004d26f451a.png)
Adding a password to the message provides an extra layer of security.

![5_new_secured](https://user-images.githubusercontent.com/3233613/169930054-4b15623a-239a-4636-b87e-1fee7cba5215.png)
Password request screen for messages locked with a password.

![6_correct_password](https://user-images.githubusercontent.com/3233613/169930078-d95a9988-d280-4c27-a0d3-012948347cc5.png)
If the password is correct, the user will see the message.

![7_incorrect_password](https://user-images.githubusercontent.com/3233613/169930093-9b5420b7-bcb1-4d7a-848a-3efe76fa5750.png)
If the password is incorrect, the message won't be accessible and will be deleted.
