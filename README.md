About the project
==================
nba-test is a test project. It shows a list of NBA players with pagination. When click on a list item - the details are shown. On the player details screen user can also open team details screen. 
App uses https://app.balldontlie.io/ to get the information about players and teams.

Technical details
-----------
App is implemented by using MVI architecture and follows clean architecture principles.

Project packages structure:
- **/api** - contains implementation for the API communication.
- **/di** - project's dependency injection implementation.
- **/features** - contains implementations of the features of the project: players list and details, team details.
- **/ui** - contains common UI + navigation.

Used technologies: Kotlin, Coroutines and Flows, Jetpack Compose, Jetpack Navigation, Jetpack Paging for Compose, Retrofit, Moshi, Hilt.