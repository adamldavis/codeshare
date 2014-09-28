CodeShare
-----------------------------

A basic Groovy, Ratpack, Mongo application for sharing snippets of code.

For learning purposes only: github (and others) do a great job of this already!

In this project you get:

* A Gradle build file
* Demo of using ratpack's Form and Groovy's JsonBuilder.
* Morphia mapped domain class under src/main/groovy/codeshare
* Groovy page templates at src/ratpack/templates/
* A routing file at src/ratpack/Ratpack.groovy
* Reloading enabled in build.gradle
* A standard project structure:

    <proj>
      |
      +- src
          |
          +- ratpack
          |     |
          |     +- ratpack.groovy
          |     +- ratpack.properties
          |     +- public          // Static assets in here
          |          |
          |          +- images
          |          +- lib
          |          +- scripts
          |          +- styles
          |
          +- main
          |   |
          |   +- groovy
                   |
                   +- // App classes in here!
          |
          +- test
              |
              +- groovy
                   |
                   +- // Spock tests in here!

That's it! You can start the basic app with

    ./gradlew run


