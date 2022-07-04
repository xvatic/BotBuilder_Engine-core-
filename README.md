<div align="center">
   <img src="/assets/banner.png"/>
</div>

# BotBuilder Engine

This is **work-in-progress** chat-bot builder application,
which allows users to easily create and deploy bots for their business.

## Features
*  User registration and authentication
*  New empty bot inicialization
*  Setting up of available user commands and corresponding bot responses
*  Telegram bot deploy *(Work In Progress: other platforms)*

## Description

**Web-Framework:** Spring Boot
 * Half-written in Java, half in Kotlin
 * Uses [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html)
 * Uses [Spring Dependency Injection](https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/using-boot-spring-beans-and-dependency-injection.html)
 * [3-layer architecture](https://www.ibm.com/cloud/learn/three-tier-architecture) for REST implementation
 * Uses [MongoDb](https://www.mongodb.com)
 * Uses [Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) as build tool
