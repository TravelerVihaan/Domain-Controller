# Domain-Controller
Application checking if domains have autorenew option enabled (It is desinged for specific API)

# Used technologies:
Java 11<br>
Spring Boot<br>
Maven<br>
Mongo DB<br>
Thymeleaf<br>
Bootstrap

# What does this application do?

This application is designed to sending requests to API one of the big polish website specialized in domains.
List of domains is stored in MongoDB collection which was connected with this app.
In answer from API is information about autorenew feature - if it is enabled or disabled.
If autorenew function is disabled for domain, app sends e-mail message to addresses from list with information which domains needs attention.
