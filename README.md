# Interview for Brightwheel - two hours later...

Where did I end up?
- User can send an email with SendGrid
- Server will 500 if any of the fields are missing
- decent test coverage

What was left?
- clean up the interaction between the `SendGridService` and the SendGrid class - right now it's super clunky. It would 
have been nice to figure out a common interface between that and Mailgun and made that into a service. Also, I ended up 
having to mock the SendGrid class in the unit tests and that felt awful
 
- Properly supply environment variables in Spring for SendGrid instead of using `System.getenv("SENDGRID_API_KEY")`

- integrating with Mailgun and adding more configuration to the application to switch between that and SendGrid

- scraping out HTML from the message body (was looking at 
[this](https://stackoverflow.com/questions/240546/remove-html-tags-from-a-string) towards the end

- more tests

- setting up CI/CD :) this is one of my favorite things to do! Happy to speak to this more!

- Applying more of the [App Continuum](https://www.appcontinuum.io/). Not to tout my own Koolaid, but this is a great 
means of organizing an application to put on by some brilliant minds from our Boulder office:
1. no circular dependencies
1. no high coupling between components
1. a wonderful starting ground for (incoming buzzword) micro-service architecture
1. codifying your business requirements/concepts into a `core` component

Yada, yada. Happy to riff on this as much as y'all would like.

## Dependencies
- Backend built with Spring/Kotlin
- Mockk for Mocking
- SendGrid

## Tests

Running the API tests, run `./gradlew build`

## Running the app

Backend: `./gradlew bootrun`