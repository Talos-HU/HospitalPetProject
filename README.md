Ahhoz hogy futtassuk a programunkat, indítsuk el a "docker_run.bat" file-t.
Ha mindent letöltött és betöltött a docker, akkor már el is indult a programunk.

### localhost:8080 URL-en elérhető az adatbázisunk.

#### localhost:8080/employee
#### localhost:8080/patient
#### localhost:8080/supply

Ez a három modellünk van, ezekre lehet különböző kéréseket küldeni amiket lentebb részletezem.

### EMPLOYEE
#### GET
localhost:8080/employee URL-re ha GET kérést küldünk láthatjuk az összes elérhető dolgozót kilistázva.


localhost:8080/employee/<id> URL-re ha GET kérést küldünk az <id> helyére egy megfelelő **UUID**-val, akkor
egy adott dolgozó részletes adatait tekinthetjük meg.
#### POST
localhost:8080/employee URL-re ha POST kérést küldünk a megfelelő JSON formában akkor hozzáadhatunk egy
új dolgozót a listához.
Példa:

```
{
  "firstName": "Albert",
  "lastName": "Szent-Györgyi",
  "birthDate": "1893-09-22",
  "gender": "MALE",
  "position": "RESEARCHER",
  "age": 37,
  "address": "Szeged, Dugonics tér 13, 6720",
  "phoneNumber": "06201357756",
  "status": true
}
```
#### PUT

localhost:8080/employee/<id> URL-re ha PUT kérést küldünk az <id> helyére egy megfelelő **UUID**-val, illetve
egy megfelelő formátumú JSON-nel akkor frissíthetjük egy dolgozó adatait.
#### Példa:

`
localhost:8080/employee/5b7315fa-569b-4f08-8e59-06527fb53f14
`

```
{
  "firstName": "Roland",
  "lastName": "Selymes",
  "birthDate": "1996-10-01",
  "gender": "FEMALE",
  "position": "MEDICAL_STUDENT",
  "age": 25,
  "address": "Szeged, Hargitai utca 53",
  "phoneNumber": "06203638976",
  "status": true
}
```
#### DELETE
localhost:8080/employee/<id> URL-re ha DELETE kérést küldünk az <id> helyére egy megfelelő **UUID**-val, akkor
kitörölhetünk egy adott dolgozót az adatbázisból.

### PATIENT

#### GET

localhost:8080/patient URL-re ha GET kérést küldünk láthatjuk az összes elérhető beteget kilistázva.

localhost:8080/patient/<id> URL-re ha GET kérést küldünk az <id> helyére egy megfelelő **UUID**-val, akkor
egy adott beteg részletes adatait tekinthetjük meg.

#### POST

localhost:8080/patient URL-re ha POST kérést küldünk a megfelelő JSON formában akkor hozzáadhatunk egy
új pácienst a listához. Ahhoz hogy egy pácienst fel tudjunk venni szükségünk van egy meglévő betegnek
az **UUID**-jéhez. (Ez a valóságon alapul, amíg nincs felelős orvos addig nem lehet hivatalosan felvenni egy beteget)
#### Példa:

```
{
  "firstName": "Máté",
  "lastName": "Károli",
  "birthDate": "1994-06-08",
  "admissionDate": "2022-06-08",
  "symptomsAtAdmission": "coughing, high temperature",
  "doctorUUID": "5b7315fa-569b-4f08-8e59-06527fb53f14"
}
```

localhost:8080/patient/<patientId>/addSupplies/<supplyId> URL-re POST kérést küldünk akkor a patientId helyére
egy páciens **UUID**-ját írva, illetve a supplyId helyére egy gyógyszer **UUID**-ját írva hozzáadhatunk egy orvosságot
egy beteghez. Természetesen ehhez kell egy létező gyógyszer, illetve egy létező beteg, nekünk van egy
automatikusan generált.


#### PUT
localhost:8080/patient/<id> URL-re ha PUT kérést küldünk az <id> helyére egy megfelelő **UUID**-val, illetve
egy megfelelő formátumú JSON-nel akkor frissíthetjük egy beteg adatait.

#### Példa:

`
localhost:8080/patient/b1cebfa7-68ca-4eea-be57-fbfee8faec62
`

```
{
  "firstName": "Máté",
  "lastName": "Karoli",
  "birthDate": "1994-06-08",
  "admissionDate": "2022-06-08",
  "symptomsAtAdmission": "coughing, high temperature, abdominal pain",
  "doctorUUID" : "5b7315fa-569b-4f08-8e59-06527fb53f14"
}
```

#### DELETE
localhost:8080/patient/<id> URL-re ha DELETE kérést küldünk az <id> helyére egy megfelelő **UUID**-val, akkor
kitörölhetünk egy adott beteget az adatbázisból.

#### Példa:

`
localhost:8080/patient/b1cebfa7-68ca-4eea-be57-fbfee8faec62/addSupplies/5dc3e5f3-278c-41f5-bbaa-69f14e08dcb4
`

### SUPPLY
#### GET

localhost:8080/supply URL-re ha GET kérést küldünk láthatjuk az összes elérhető gyógyszert kilistázva.


localhost:8080/supply/<id> URL-re ha GET kérést küldünk az <id> helyére egy megfelelő **UUID**-val, akkor
egy adott gyógyszer részletes adatait tekinthetjük meg.

#### POST

localhost:8080/supply URL-re ha POST kérést küldünk a megfelelő JSON formában akkor hozzáadhatunk egy
új gyógyszert a listához.

#### Példa:

```
{
  "name": "Metylphenidate XL 0.36mg",
  "pretence": "NORMATIV",
  "amountOnStorage": 10,
  "priceWithoutCoverage": 5000,
  "priceWithCoverage": 3400
}
```

#### PUT

localhost:8080/supply/<id> URL-re ha PUT kérést küldünk az <id> helyére egy megfelelő **UUID**-val, illetve
egy megfelelő formátumú JSON-nel akkor frissíthetjük egy gyógyszer adatait.

#### Példa:
`
localhost:8080/supply/5dc3e5f3-278c-41f5-bbaa-69f14e08dcb4
`

```
{
  "name": "Fenatyl 0.50mg",
  "pretence": "HM",
  "amountOnStorage": 1,
  "priceWithoutCoverage": 1000,
  "priceWithCoverage": 500
}
```

#### DELETE
localhost:8080/supply/<id> URL-re ha DELETE kérést küldünk az <id> helyére egy megfelelő UUID-val, akkor
kitörölhetünk egy adott gyógyszert az adatbázisból.