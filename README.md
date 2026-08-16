# SightSeeing App

REST API and web interface for tourist attractions: browse by location, reviews with an average rating, per-user favourites, and travel journals.

Built during the TIS Academy Spring workshop 2026 as a team project (team Mljet), with fixes and improvements made afterward.

## Tech stack

| Layer | Technology |
| --- | --- |
| Application | Java 25, Spring Boot 4.1 |
| Database | Spring Data JPA, Hibernate, H2 |
| UI | Thymeleaf |
| Mapping | MapStruct |
| Documentation | springdoc OpenAPI (Swagger UI) |
| Monitoring | Spring Boot Actuator |

## Running

```bash
./mvnw spring-boot:run
```

The app starts at `http://localhost:9090/sightseeing-app`.

The database is a file-based H2 instance with `ddl-auto=create`, so the schema is rebuilt on every startup and seed data is loaded from `src/main/resources/data.sql`.

## Pages

| Page | Path |
| --- | --- |
| Browse attractions | `/browse/attractions` |
| Add a new attraction | `/browse/attractions/new` |
| Swagger UI | `/swagger-ui/index.html` |
| Health check | `/actuator/health` |

## API

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/attractions/{location}` | attractions for a location |
| `GET` | `/attractions/{location}/{attractionURLName}` | details, average rating, and reviews |
| `POST` | `/attractions` | new location with attractions |
| `POST` | `/attraction/review` | new review (rating 1 to 5) |
| `POST` | `/user` | new user |
| `GET` | `/user/{userId}` | fetch a user |
| `GET` | `/user/{userId}/favourites` | a user's favourites |
| `POST` | `/user/{userId}/favourites` | add a favourite |
| `POST` | `/travel-journal/{userId}` | new travel journal |
| `PATCH` | `/travel-journal/{travelJournalId}` | update a journal |

The details endpoint takes the attraction's **URL name**, meaning without diacritics. For "Dioklecijanova palača" the path is `/attractions/Split/Dioklecijanova%20palaca`.

The `excludeReviews`, `reviewsFrom`, and `reviewsTo` parameters are also supported for filtering reviews by date range.

## Error handling

All exceptions go through a `@ControllerAdvice`. Missing records return 404 with a message, invalid input returns 400, and unexpected errors return 500 with a reference id that's logged.
