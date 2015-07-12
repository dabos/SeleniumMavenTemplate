I've provided a Maven project,

In order to run the test suite you can run the following command, assuming Maven is installed and was added to the PATH:

"mvn clean verify -Dbrowser=chrome -Dtoken=yourtoken -Dusername=yourusername" - make sure to replace yourtoken and yourusername

            -Dbrowser - the browser used to run the front end tests - I used chrome, but you can also use firefox, ie, opera, phantomjs
			-Dtoken - Github token - used for authentication
			-Dusername - Github username - the username is only used to list a user's gists

This will download all of the dependencies and also all of the drivers used by Selenium (Windows executables).

All of the tests will start running - section 1 - AnagramTest 
							        - section 2 - front end tests
							        - section 2 - API tests will run

All of the libraries I used can be found in pom.xml.
									
I used Ardesco's Selenium Maven template - https://github.com/Ardesco/Selenium-Maven-Template in order to make sure the tests will also work on your end
									
I used the following libraries for the front-end tests:	selenium-java, testng, guava, and hamcrest matchers

For the API testing I used rest-assured and json-schema-validator - they made the API testing part really easy and fun to implement


If for some reason the tests won't start: use Intellij IDEA, import the Maven project and right click on a test in Intellij and select 'Run Test'.

SECTION01:
	Anagram.java - I used a regex to remove the non-letters, I sorted the two strings and then I compared them
	Main.java - I wrote my comments in the file
	
SECTION02:
	A) Front end tests - PropertiesTest.java
	
		I used the Page Objects pattern. I created two objects for the SearchPage and SearchResultsPage
			- features - when a page object is instantiated it will check the url for a unique string so it knows it is on the right page
					   - I'm a big fan of polling the DOM with predicates - I believe they are the cure to flaky Selenium tests
					   
	B) API tests - GistTest.java
	
	this is the first time I use rest-assured (at work we use custom http clients for calling services), and it was very nice
					there is one hack I had to use "filter(sign(token))." the regular method "auth().oauth2(...)" didn't authenticate my client 
					the "Authorization" header was missing.
					
					
		