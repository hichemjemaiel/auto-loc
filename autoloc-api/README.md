# autoloc-api - Atelier 2 : associations JPA, cascade et fetch

Java 17+ / Spring Boot 3.5 / Spring Data JPA / Hibernate / MySQL / Lombok.

## Lancer

1. Demarrer MySQL et adapter `src/main/resources/application.properties` (utilisateur / mot de passe).
2. `mvn spring-boot:run` (ou lancer `AutolocApiApplication` depuis IntelliJ IDEA).
3. Verifier dans les logs et dans MySQL les cles etrangeres generees
   (`contrat_id_contrat`, `agence_id_agence`, ... et la table `vehicule_equipement`).

## Tests (H2 en memoire, sans MySQL)

`mvn test` - verifie cascade, orphanRemoval et table de jointure.

## Contenu

- `entity/` : les 9 entites avec leurs associations
- `enums/` : ModePaiement, RoleEmploye, CategorieVehicule, StatutVehicule, StatutReservation
- `repository/` : un `JpaRepository` par entite
- `docs/fetch-strategy.md` : justification des choix de fetch et de cascade
