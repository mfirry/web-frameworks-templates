EXPERIMENTS on the look-and-feel (from the point of view of the *developer experience*) of a few frameworks/libraries used to develop web services in Scala.

At the end of the day all the examples should have:
- a '/' endpoint that would return [1, 2, 3] (json)
- a '/json' that would return {'message':'Hello, World!'} (json)
- a '/plaintext' that would return Hello, World!' (plaintext)


--

- [akka-http](https://doc.akka.io/docs/akka-http/current/)
- [analogweb](http://analogweb.github.io/)
- [colossus](https://github.com/tumblr/colossus)
- [finatra](https://twitter.github.io/finatra/)
- [finch](https://finagle.github.io/finch/)
- [fintrospect](http://fintrospect.io/)
- [http4s](https://http4s.org/)
- [lift](https://liftweb.net/)
- [play](https://www.playframework.com/)
- [service-container](https://github.com/vonnagy/service-container)
- [unfiltered](http://unfiltered.ws/)

[![Build Status](https://travis-ci.org/mfirry/web-frameworks-templates.png?branch=master)](https://travis-ci.org/mfirry/web-frameworks-templates)


### High-Level Server Frameworks

| Name         | akka-http                                                                     | finch  | http4s | Play                                                                                 |
|--------------|-------------------------------------------------------------------------------|--------|--------|--------------------------------------------------------------------------------------|
| Version      | 10.1.12                                                                       | 0.32.1 | 0.21.3 | 2.8.0                                                                               |
| License | ![akka-http](https://img.shields.io/github/license/akka/akka-http.svg?label=%20) | ![finch](https://img.shields.io/github/license/finagle/finch.svg?label=%20)   | ![http4s](https://img.shields.io/github/license/http4s/http4s.svg?label=%20)   | ![Play!](https://img.shields.io/github/license/playframework/playframework.svg?label=%20) |
| Github Stars | ![akka-http](https://img.shields.io/github/stars/akka/akka-http.svg?label=%20) | ![finch](https://img.shields.io/github/stars/finagle/finch.svg?label=%20)   | ![http4s](https://img.shields.io/github/stars/http4s/http4s.svg?label=%20)   | ![Play!](https://img.shields.io/github/stars/playframework/playframework.svg?label=%20) |
| Contributors | ![akka-http](https://img.shields.io/github/contributors/akka/akka-http.svg?label=%20) | ![finch](https://img.shields.io/github/contributors/finagle/finch.svg?label=%20)   | ![http4s](https://img.shields.io/github/contributors/http4s/http4s.svg?label=%20)   | ![Play!](https://img.shields.io/github/contributors/playframework/playframework.svg?label=%20) |