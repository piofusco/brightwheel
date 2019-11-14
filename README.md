# Interview for Brightwheel - two hours later...

## ✈️ Running the app ✈️

`./gradlew bootrun`

## 🧪 Tests 🧪

Running the API tests, run `./gradlew build`

## 🛠 Dependencies 🛠
- Backend built with Spring/Kotlin (I prefer a statically typed language and between Swift and Kotlin, Kotlin is the 
best these days 🤷🏻‍♂️In terms of building an API stupid fast, one cannot go wrong with Spring. It can be a scooter or a 
rocket ship, depending on who is in the drivers seat. This application called for one of those electric scooters...)
- Mockk for Mocking (best in clas for Kotlin)
- SendGrid (Not sure if I was allowed to use this since the other built in clients weren't allowed, but it made things 
a move a lot quicker for me)

✅ Where did I end up? ✅
- User can send an email with SendGrid
- Server will 500 if any of the fields are missing
- decent test coverage

⌚️ What was left? ⌚️
- clean up the interaction between the `SendGridService` and the SendGrid class - right now it's super clunky. It would 
have been nice to figure out a common interface between that and Mailgun and made that into a service. Also, I ended up 
having to mock the SendGrid class in the unit tests and that felt awful
 
- Properly supply environment variables in Spring for SendGrid instead of using `System.getenv("SENDGRID_API_KEY")` - 
this is what ate up most of my time 😬 so I settled on this

- integrating with Mailgun and adding more configuration to the application to switch between that and SendGrid

- scraping out HTML from the message body (was looking at 
[this](https://stackoverflow.com/questions/240546/remove-html-tags-from-a-string) towards the end

- more tests

- better error handling

- setting up CI/CD :) this is one of my favorite things to do! Happy to speak to this more!

- Applying more of the [App Continuum](https://www.appcontinuum.io/). Not to tout my own Koolaid, but this is a great 
means of organizing an application to put on by some brilliant minds from our Boulder office:
1. no circular dependencies
1. no high coupling between components
1. a wonderful starting ground for (incoming buzzword) micro-service architecture
1. codifying your business requirements/concepts into a `core` component

Yada, yada. Happy to riff on this as much as y'all would like.
