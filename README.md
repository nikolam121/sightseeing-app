# SightSeeing App

REST API i web sučelje za turističke atrakcije: pregled po lokacijama, recenzije s prosječnom ocjenom, favoriti po korisniku i putni dnevnik.

Nastalo na TIS Academy Spring radionici 2026 kao timski projekt (tim Mljet), uz naknadne ispravke i doradu.

## Tehnologije

| Sloj | Tehnologija |
| --- | --- |
| Aplikacija | Java 25, Spring Boot 4.1 |
| Baza | Spring Data JPA, Hibernate, H2 |
| Sučelje | Thymeleaf |
| Mapiranje | MapStruct |
| Dokumentacija | springdoc OpenAPI (Swagger UI) |
| Nadzor | Spring Boot Actuator |

## Pokretanje

```bash
./mvnw spring-boot:run
```

Aplikacija se diže na `http://localhost:9090/sightseeing-app`.

Baza je H2 u datoteci s `ddl-auto=create`, pa se shema gradi iznova pri svakom pokretanju, a početni podaci učitavaju iz `src/main/resources/data.sql`.

## Sučelje

| Stranica | Putanja |
| --- | --- |
| Pregled atrakcija | `/browse/attractions` |
| Unos nove atrakcije | `/browse/attractions/new` |
| Swagger UI | `/swagger-ui/index.html` |
| Health check | `/actuator/health` |

## API

| Metoda | Putanja | Opis |
| --- | --- | --- |
| `GET` | `/attractions/{location}` | atrakcije za lokaciju |
| `GET` | `/attractions/{location}/{attractionURLName}` | detalji, prosjek ocjena i recenzije |
| `POST` | `/attractions` | nova lokacija s atrakcijama |
| `POST` | `/attraction/review` | nova recenzija (ocjena 1 do 5) |
| `POST` | `/user` | novi korisnik |
| `GET` | `/user/{userId}` | dohvat korisnika |
| `GET` | `/user/{userId}/favourites` | favoriti korisnika |
| `POST` | `/user/{userId}/favourites` | dodavanje favorita |
| `POST` | `/travel-journal/{userId}` | novi putni dnevnik |
| `PATCH` | `/travel-journal/{travelJournalId}` | izmjena dnevnika |

Endpoint za detalje prima **URL naziv** atrakcije, dakle bez dijakritičkih znakova. Za "Dioklecijanova palača" putanja je `/attractions/Split/Dioklecijanova%20palaca`.

Podržani su i parametri `excludeReviews`, `reviewsFrom` i `reviewsTo` za filtriranje recenzija po razdoblju.

## Rukovanje greškama

Sve iznimke prolaze kroz `@ControllerAdvice`. Nepostojeći zapisi vraćaju 404 uz poruku, neispravan unos 400, a neočekivane greške 500 s referentnim identifikatorom koji se zapisuje u log.
