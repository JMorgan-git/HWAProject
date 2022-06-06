Test coverage : 88.6%
# HWAProject
This is the second project for the QA training course,
I had to create a functioning front-end (using the web trifecta, html, css, js) and back-end(using Java) with automated testing, that both interact with a localy hosted database.
The projects design was completely open, it just needed to be CRUD functional.
I have designed a locally hosted website that allows you to CRUD functionally use a to-do list in the design of a kitchen pinboard with stickynotes on.
Other than basic CRUD features i have added the additionaly functionality of selecting an entry by id and also by name.

## Getting Started
My project had been built into a fatjar and therefore the back-end only needs to be run from commandline, 
the front-end just needs to be searched on a browser with the url : " http://localhost:8080/index.html "

## Prerequisites
### Option 1.) 
To run the project visually you only need java 11+ installed on your computer along with a browser, both of these can be googled with easy installation guides attached to thier respective websites.
### Option 2.) 
Inspecting the code you will need everything in the preivious option as well as eclipse(suggested)/ similar java oriented code editing software, this will allow you to inspect everything java related, all html, css and js code can be instacted using your browser if you right click the page and click inspect.
### Option 3.) 
Inpecting further and testing outside of the application, vs code(suggested)/similar for frontend, postman + SQL for testing commands.

## Running the tests
If you run from command line the test automatically run and boot the app if successfull,
If you run as springboot application in eclipse all tests will run automatically and boot the app if successfull,
If you run under the option coverage as Junit in eclipse all tests will run and show you exactly what the tests cover in the application.

## Testing
I have only tested the most relivant files in my project;
### Unit
#### Controller
The controller unit test uses mockito to test each section of my controllers CRUD "+" functionality by creating a mock SQL schema with test data to see if all of my functionalities work correctly 
#### Service
The service unit test is similar to the controller test however no sql schemas used, this test purly injects values into mocked versions of my repo and sees if everything we expect to return does

### Integration 
#### Controller
Controller Integration test is very similar to the controller uint test however this test does not mock the return value, we do not do still have to say if this is inputed expect this. this test if more raw, it actually sends date through the pipes rather than mocking what the returns are.

## Built With
Maven ; Dependency and Build Management,


Mockito ; Testing,

Junit ; Testing,

Postman ; Manual private testing,


Github ; Repository management,

Jira ; Kanban board and epic/stories,


Gitbash ; Git functionality,


## Authors
Jesse Morgan

## Acknowledgments
- Anoush
