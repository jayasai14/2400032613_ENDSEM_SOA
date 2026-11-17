# JWT Validation and Claims Extraction

A simple Spring Boot REST API for the SOA lab task.

## Tasks covered

1. **Token validation functions** – `validateToken()` checks the JWT signature and expiry. `extractClaims()` reads the claims only after successful verification.
2. **Validation endpoint** – `POST /api/auth/validate` accepts a token in JSON and returns whether it is valid, along with its claims.
3. **Swagger testing** – Swagger UI is available at `http://localhost:8080/swagger-ui.html`.

## Requirements

- Java 17+
- Maven 3.8+

## Run the project

From this folder, run:

```bash
mvn spring-boot:run
```

Or package and run:

```bash
mvn clean package
java -jar target/jwt-validation-1.0.0.jar
```

## Test using Swagger

1. Start the application.
2. Open [Swagger UI](http://localhost:8080/swagger-ui.html).
3. Expand **POST /api/auth/validate**.
4. Select **Try it out**.
5. Enter a valid JWT in this request body and select **Execute**:

```json
{
  "token": "PASTE_A_VALID_JWT_HERE"
}
```

A valid token returns HTTP 200 with `valid: true` and its claims. An invalid, expired, or incorrectly signed token returns HTTP 401 with `valid: false`.

## Important: testing token

The endpoint validates tokens signed with the configured HMAC secret. A token created with a different secret or algorithm will fail validation. For a local test only, you can generate a compatible HS256 token using the Base64 secret configured in `application.properties`. In a real deployment, set a strong secret using the `JWT_SECRET` environment variable and never commit production secrets.

## Main files

- `JwtTokenService.java` – validation and claims extraction functions.
- `JwtValidationController.java` – REST validation endpoint.
- `TokenRequest.java` – request body.
- `TokenValidationResponse.java` – API response.
