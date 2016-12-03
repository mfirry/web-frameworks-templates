###basics
http://fintrospect.io

Run the `FintrospectServer`, or `sbt run` to start it. The server binds to port 9999.

###endpoints
- JSON list (using circe) @ http://localhost:9999/
- echo string input @ http://localhost:9999/echo/<mystring>

###bonus
- Generated Swagger docs live @ http://localhost:9999/api-docs
- We can rebind the HTTP contract to a client function, as shown by running `FintrospectClient`:
 using the path parameter `-->()` to bind a type-safe value into a request. This also works with all parts 
 of the HTTP contract (body, header, query, form...)
