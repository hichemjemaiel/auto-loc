# Strategie de fetch et de cascade - AutoLoc

Convention generale : toutes les associations sont en `FetchType.LAZY` (on force LAZY sur les
`@ManyToOne` et `@OneToOne`, EAGER par defaut en JPA) afin d'eviter de charger toute la chaine
d'objets lies a chaque lecture. Les collections (`@OneToMany`, `@ManyToMany`) sont LAZY par defaut
et le restent. Une cascade n'est utilisee que lorsque l'enfant ne peut pas vivre sans son parent
(composition) ou lorsque l'enregistrement du parent doit naturellement enregistrer ses enfants.

| Association | Fetch | Cascade | Justification |
|---|---|---|---|
| Contrat -> Paiement | LAZY | ALL + orphanRemoval | Un paiement n'existe que rattache a son contrat (composition) ; supprimer le contrat supprime ses paiements, et retirer un paiement de la liste le supprime en base. Cote proprietaire : `Paiement.contrat` (`contrat_id_contrat`). |
| Agence -> Vehicule | LAZY | Aucune | Un vehicule survit a la suppression de son agence (il peut etre reaffecte). Charger une agence ne doit pas charger toute sa flotte. Cote proprietaire : `Vehicule.agence` (`agence_id_agence`). |
| Agence -> Employe | LAZY | Aucune | Un employe est une entite independante de l'agence ; sa suppression ne doit pas decouler de celle de l'agence. Cote proprietaire : `Employe.agence` (`agence_id_agence`). |
| Vehicule <-> Equipement | LAZY | Aucune | Association `@ManyToMany` : les equipements (GPS, siege bebe...) sont partages entre vehicules, supprimer un vehicule ne doit pas supprimer un equipement utilise ailleurs. `Set` pour eviter les doublons. Cote proprietaire : `Vehicule` avec la table de jointure `vehicule_equipement`. |
| Client -> Reservation | LAZY | PERSIST | Enregistrer un client avec ses nouvelles reservations les enregistre ensemble ; pas de REMOVE car l'historique des reservations doit pouvoir etre conserve. Cote proprietaire : `Reservation.client` (`client_id_client`). |
| Reservation -> Vehicule | LAZY | Aucune | Un vehicule existe independamment de ses reservations ; supprimer une reservation ne doit jamais supprimer le vehicule. Cote proprietaire : `Reservation.vehicule` (`vehicule_id_vehicule`). |
| Reservation <-> Contrat | LAZY | ALL (cote Reservation) | Un contrat n'a de sens que pour sa reservation : sauvegarde, mise a jour et suppression se propagent. Cote proprietaire : `Contrat.reservation` (`reservation_id_reservation`). Note : cote inverse, Hibernate ne peut pas toujours appliquer LAZY sur un `@OneToOne` sans amelioration du bytecode (il doit interroger la base pour savoir si l'association est nulle). |
| Vehicule -> Maintenance | LAZY | PERSIST | Une maintenance est enregistree avec son vehicule ; pas de REMOVE pour garder l'historique d'entretien. Cote proprietaire : `Maintenance.vehicule` (`vehicule_id_vehicule`). |

## Points d'attention

- Pas de `@Data` sur les entites : `toString`/`hashCode` parcourraient les deux cotes d'une
  association bidirectionnelle (`StackOverflowError`). On garde `@Getter`/`@Setter` cibles.
- Les deux cotes d'une association bidirectionnelle sont synchronises via des methodes utilitaires
  (`addPaiement`, `addReservation`, `addMaintenance`, `addEquipement`...).
- Un acces a une collection LAZY hors transaction leve une `LazyInitializationException` : a
  traiter avec une requete dediee (Atelier 7).
