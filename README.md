<div align="center" id="top"> 
  <img src="./.github/app.gif" alt="hatch" />

&#xa0;

</div>

<h1 align="center">Idealo Coding Challenge - Toy Robot</h1>

<hr>

<p align="center">
  <a href="#dart-about">About</a> &#xa0; | &#xa0; 
  <a href="#sparkles-how-to-run">How to run</a> &#xa0; | &#xa0;
  <a href="#thought_balloon-assumptions">Assumptions</a> &#xa0; | &#xa0; 
  <a href="#rocket-improvements">Improvements</a> &#xa0; | &#xa0;
  <a href="#rocket-technologies">Technologies</a> &#xa0; | &#xa0;
</p>

<br>

## :dart: About
Idealo coding challenge. Full stack project consiting of a Java Spring Boot backend and frontend utilizing vanilla JavaScript


## :sparkles: How to Run


```bash
./mvnw clean install
./mvnw spring-boot:run

```

If everything went well, the application should be running on localhost:8080

## :thought_balloon: Assumptions

<h4>Question:</h4>Should the frontend or the backend handle the state of the robot?
<h4>Assumption:</h4>The backend. According to the specifications of the project, the frontend sole functionality is that of sending the instructions
to the server and render the robot in the right position, as such, I assumed the frontend handling the state of robot would be the frontend handling
something it shouldn't. I also wanted for the position of the robot to persist refreshes, and while it's very simple to accomplish this
on the frontend, I once again felt this was the frontend doing more than required. In case I assumed wrongly, it is easily fixable and I would more than happy
to do so. 

<h4>Question:</h4>Can I use a frontend framework such as React?
<h4>Assumption:</h4> Yes, but I decided not to. The frontend part of the project was fairly simple so I decided to work with just vanilla JavaScript.
For the styling, I decided to go with Tailwind.

<h4>Question:</h4>How to handle the robot moving out of the grid?
<h4>Assumption:</h4> The backend should handle it. While usually, I would handle this on the frontend, as mentioned before, I assumed this perhaps
would be the frontend doing too much, as such, I'm handling it in the server. At the moment the frontend sends the dimensions of the grid alongside the
movement instructions, and in the case, the robot goes out of the grid, the server sets the robot back to the origin (0,0). 
## :checkered_flag: Approach

## :rocket: Improvements

- Increase the test coverage for both the backend and especially the frontend.
- Even though I didn't feel it was necessary for the project, an improvement could be to rebuild the frontend utilizing a framework such as React.
- Considering how constrained the instructions for the robot are, having text fields as input for the instructions might not be a good idea. Perhaps 
something like a dropdown would be much better (Although if in the future the robot ends up accepting a lot of instructions, it might be cumbersome for the user to have to scroll through to find the right one).
- Speaking of the instructions, increase the number of instructions the robot can accept. 
- As mentioned, the frontend is using tailwind, however, it's tailwind's CDN which is not recommended for production.
- Improve the overall look of the site. At the moment, while functional, both the UI and UX could definitely be improved.
- Error handling. If the instructions are misspelled or formatted the wrong way, the server throws an IllegalArgumentException. While functional, it would
be better to have a custom exception for these scenarios.
- Change the robot image. If this was a real-life project, we'd probably get sued for copyright infringement. 

## :rocket: Technologies

The following tools were used in this project:

- [Spring](https://spring.io/)
- [Lombok](https://projectlombok.org/)
- [JUnit](https://junit.org/junit5/)
- [Tailwind](https://tailwindcss.com/)
