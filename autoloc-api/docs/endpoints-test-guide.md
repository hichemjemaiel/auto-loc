# 🧪 AutoLoc REST API - Guide de Test des Endpoints

Ce document contient tous les tests pour chacun des 9 contrôleurs REST du projet **AutoLoc**, avec des exemples prêts à l'emploi (JSON, commandes `curl`, requêtes HTTP) pour tester via **Swagger UI**, **Postman** ou le **Terminal**.

---

## 📌 Informations Générales
- **Base URL** : `http://localhost:8081`
- **Swagger UI** : [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
- **OpenAPI JSON Docs** : [http://localhost:8081/v3/api-docs](http://localhost:8081/v3/api-docs)
- **Format** : `Content-Type: application/json`

---

## 📋 Ordre d'exécution recommandé
Pour respecter les relations de clés étrangères (ex: `Employe` attaché à une `Agence`, `Reservation` liée à un `Client` et `Vehicule`), il est conseillé de tester dans cet ordre :
1. **Agence**
2. **Client**
3. **Equipement**
4. **Vehicule**
5. **Employe**
6. **Reservation**
7. **Contrat**
8. **Paiement**
9. **Maintenance**

---

## 1. Agence (`/agences`)

### 1.1 Ajouter une agence (POST)
- **URL** : `http://localhost:8081/agences`
- **Méthode** : `POST`
- **Body** :
```json
{
  "nom": "Agence Tunis Centre",
  "ville": "Tunis",
  "adresse": "Avenue Habib Bourguiba, Tunis",
  "telephone": "+21671000111"
}
```
- **cURL** :
```bash
curl -X POST "http://localhost:8081/agences" \
  -H "Content-Type: application/json" \
  -d '{"nom":"Agence Tunis Centre","ville":"Tunis","adresse":"Avenue Habib Bourguiba, Tunis","telephone":"+21671000111"}'
```

### 1.2 Récupérer toutes les agences (GET)
- **URL** : `http://localhost:8081/agences`
- **Méthode** : `GET`
```bash
curl -X GET "http://localhost:8081/agences"
```

### 1.3 Récupérer une agence par ID (GET)
- **URL** : `http://localhost:8081/agences/1`
- **Méthode** : `GET`
```bash
curl -X GET "http://localhost:8081/agences/1"
```

### 1.4 Mettre à jour une agence (PUT)
- **URL** : `http://localhost:8081/agences`
- **Méthode** : `PUT`
- **Body** :
```json
{
  "idAgence": 1,
  "nom": "Agence Tunis Centre - Rénovée",
  "ville": "Tunis",
  "adresse": "12 Avenue Habib Bourguiba",
  "telephone": "+21671999888"
}
```

### 1.5 Supprimer une agence (DELETE)
- **URL** : `http://localhost:8081/agences/1`
- **Méthode** : `DELETE`
```bash
curl -X DELETE "http://localhost:8081/agences/1"
```

---

## 2. Client (`/clients`)

### 2.1 Ajouter un client (POST)
- **URL** : `http://localhost:8081/clients`
- **Méthode** : `POST`
- **Body** :
```json
{
  "nom": "Ben Salah",
  "prenom": "Mohamed",
  "email": "mohamed.bensalah@email.com",
  "telephone": "+21620123456",
  "numPermis": "TN-12345678",
  "dateInscription": "2026-10-28"
}
```
- **cURL** :
```bash
curl -X POST "http://localhost:8081/clients" \
  -H "Content-Type: application/json" \
  -d '{"nom":"Ben Salah","prenom":"Mohamed","email":"mohamed.bensalah@email.com","telephone":"+21620123456","numPermis":"TN-12345678","dateInscription":"2026-10-28"}'
```

### 2.2 Récupérer tous les clients (GET)
```bash
curl -X GET "http://localhost:8081/clients"
```

### 2.3 Récupérer un client par ID (GET)
```bash
curl -X GET "http://localhost:8081/clients/1"
```

### 2.4 Mettre à jour un client (PUT)
- **URL** : `http://localhost:8081/clients`
- **Méthode** : `PUT`
- **Body** :
```json
{
  "idClient": 1,
  "nom": "Ben Salah",
  "prenom": "Mohamed Ali",
  "email": "mohamedali.bensalah@email.com",
  "telephone": "+21620999888",
  "numPermis": "TN-12345678",
  "dateInscription": "2026-10-28"
}
```

### 2.5 Supprimer un client (DELETE)
```bash
curl -X DELETE "http://localhost:8081/clients/1"
```

---

## 3. Equipement (`/equipements`)

### 3.1 Ajouter un équipement (POST)
- **URL** : `http://localhost:8081/equipements`
- **Méthode** : `POST`
- **Body** :
```json
{
  "libelle": "GPS Navigation"
}
```
- **cURL** :
```bash
curl -X POST "http://localhost:8081/equipements" \
  -H "Content-Type: application/json" \
  -d '{"libelle":"GPS Navigation"}'
```

### 3.2 Récupérer tous les équipements (GET)
```bash
curl -X GET "http://localhost:8081/equipements"
```

### 3.3 Récupérer un équipement par ID (GET)
```bash
curl -X GET "http://localhost:8081/equipements/1"
```

### 3.4 Mettre à jour un équipement (PUT)
```json
{
  "idEquipement": 1,
  "libelle": "GPS Navigation HD + Siège Bébé"
}
```

### 3.5 Supprimer un équipement (DELETE)
```bash
curl -X DELETE "http://localhost:8081/equipements/1"
```

---

## 4. Vehicule (`/vehicules`)

> **Enum CategorieVehicule** : `CITADINE`, `BERLINE`, `SUV`, `UTILITAIRE`  
> **Enum StatutVehicule** : `DISPONIBLE`, `LOUE`, `MAINTENANCE`

### 4.1 Ajouter un véhicule (POST)
- **URL** : `http://localhost:8081/vehicules`
- **Méthode** : `POST`
- **Body** :
```json
{
  "immatriculation": "210-TN-5432",
  "marque": "Volkswagen",
  "modele": "Golf 8",
  "categorie": "BERLINE",
  "tarifJournalier": 120.00,
  "statut": "DISPONIBLE",
  "agence": {
    "idAgence": 1
  }
}
```
- **cURL** :
```bash
curl -X POST "http://localhost:8081/vehicules" \
  -H "Content-Type: application/json" \
  -d '{"immatriculation":"210-TN-5432","marque":"Volkswagen","modele":"Golf 8","categorie":"BERLINE","tarifJournalier":120.00,"statut":"DISPONIBLE","agence":{"idAgence":1}}'
```

### 4.2 Récupérer tous les véhicules (GET)
```bash
curl -X GET "http://localhost:8081/vehicules"
```

### 4.3 Récupérer un véhicule par ID (GET)
```bash
curl -X GET "http://localhost:8081/vehicules/1"
```

### 4.4 Mettre à jour un véhicule (PUT)
```json
{
  "idVehicule": 1,
  "immatriculation": "210-TN-5432",
  "marque": "Volkswagen",
  "modele": "Golf 8 Life",
  "categorie": "BERLINE",
  "tarifJournalier": 130.00,
  "statut": "DISPONIBLE",
  "agence": {
    "idAgence": 1
  }
}
```

### 4.5 Supprimer un véhicule (DELETE)
```bash
curl -X DELETE "http://localhost:8081/vehicules/1"
```

---

## 5. Employe (`/employes`)

> **Enum RoleEmploye** : `AGENT`, `MANAGER`

### 5.1 Ajouter un employé (POST)
- **URL** : `http://localhost:8081/employes`
- **Méthode** : `POST`
- **Body** :
```json
{
  "nom": "Trabelsi",
  "prenom": "Sami",
  "role": "AGENT",
  "agence": {
    "idAgence": 1
  }
}
```
- **cURL** :
```bash
curl -X POST "http://localhost:8081/employes" \
  -H "Content-Type: application/json" \
  -d '{"nom":"Trabelsi","prenom":"Sami","role":"AGENT","agence":{"idAgence":1}}'
```

### 5.2 Récupérer tous les employés (GET)
```bash
curl -X GET "http://localhost:8081/employes"
```

### 5.3 Récupérer un employé par ID (GET)
```bash
curl -X GET "http://localhost:8081/employes/1"
```

### 5.4 Mettre à jour un employé (PUT)
```json
{
  "idEmploye": 1,
  "nom": "Trabelsi",
  "prenom": "Sami",
  "role": "MANAGER",
  "agence": {
    "idAgence": 1
  }
}
```

### 5.5 Supprimer un employé (DELETE)
```bash
curl -X DELETE "http://localhost:8081/employes/1"
```

---

## 6. Reservation (`/reservations`)

> **Enum StatutReservation** : `EN_ATTENTE`, `CONFIRMEE`, `ANNULEE`, `TERMINEE`

### 6.1 Ajouter une réservation (POST)
- **URL** : `http://localhost:8081/reservations`
- **Méthode** : `POST`
- **Body** :
```json
{
  "dateDebut": "2026-11-01",
  "dateFin": "2026-11-05",
  "statut": "EN_ATTENTE",
  "client": {
    "idClient": 1
  },
  "vehicule": {
    "idVehicule": 1
  }
}
```
- **cURL** :
```bash
curl -X POST "http://localhost:8081/reservations" \
  -H "Content-Type: application/json" \
  -d '{"dateDebut":"2026-11-01","dateFin":"2026-11-05","statut":"EN_ATTENTE","client":{"idClient":1},"vehicule":{"idVehicule":1}}'
```

### 6.2 Récupérer toutes les réservations (GET)
```bash
curl -X GET "http://localhost:8081/reservations"
```

### 6.3 Récupérer une réservation par ID (GET)
```bash
curl -X GET "http://localhost:8081/reservations/1"
```

### 6.4 Mettre à jour une réservation (PUT)
```json
{
  "idReservation": 1,
  "dateDebut": "2026-11-01",
  "dateFin": "2026-11-05",
  "statut": "CONFIRMEE",
  "client": {
    "idClient": 1
  },
  "vehicule": {
    "idVehicule": 1
  }
}
```

### 6.5 Supprimer une réservation (DELETE)
```bash
curl -X DELETE "http://localhost:8081/reservations/1"
```

---

## 7. Contrat (`/contrats`)

### 7.1 Ajouter un contrat (POST)
- **URL** : `http://localhost:8081/contrats`
- **Méthode** : `POST`
- **Body** :
```json
{
  "dateSignature": "2026-10-28",
  "montantTotal": 480.00,
  "valide": true,
  "reservation": {
    "idReservation": 1
  }
}
```
- **cURL** :
```bash
curl -X POST "http://localhost:8081/contrats" \
  -H "Content-Type: application/json" \
  -d '{"dateSignature":"2026-10-28","montantTotal":480.00,"valide":true,"reservation":{"idReservation":1}}'
```

### 7.2 Récupérer tous les contrats (GET)
```bash
curl -X GET "http://localhost:8081/contrats"
```

### 7.3 Récupérer un contrat par ID (GET)
```bash
curl -X GET "http://localhost:8081/contrats/1"
```

### 7.4 Mettre à jour un contrat (PUT)
```json
{
  "idContrat": 1,
  "dateSignature": "2026-10-28",
  "montantTotal": 500.00,
  "valide": true,
  "reservation": {
    "idReservation": 1
  }
}
```

### 7.5 Supprimer un contrat (DELETE)
```bash
curl -X DELETE "http://localhost:8081/contrats/1"
```

---

## 8. Paiement (`/paiements`)

> **Enum ModePaiement** : `CARTE`, `ESPECES`, `VIREMENT`

### 8.1 Ajouter un paiement (POST)
- **URL** : `http://localhost:8081/paiements`
- **Méthode** : `POST`
- **Body** :
```json
{
  "montant": 240.00,
  "datePaiement": "2026-10-28",
  "modePaiement": "CARTE",
  "contrat": {
    "idContrat": 1
  }
}
```
- **cURL** :
```bash
curl -X POST "http://localhost:8081/paiements" \
  -H "Content-Type: application/json" \
  -d '{"montant":240.00,"datePaiement":"2026-10-28","modePaiement":"CARTE","contrat":{"idContrat":1}}'
```

### 8.2 Récupérer tous les paiements (GET)
```bash
curl -X GET "http://localhost:8081/paiements"
```

### 8.3 Récupérer un paiement par ID (GET)
```bash
curl -X GET "http://localhost:8081/paiements/1"
```

### 8.4 Mettre à jour un paiement (PUT)
```json
{
  "idPaiement": 1,
  "montant": 250.00,
  "datePaiement": "2026-10-28",
  "modePaiement": "VIREMENT",
  "contrat": {
    "idContrat": 1
  }
}
```

### 8.5 Supprimer un paiement (DELETE)
```bash
curl -X DELETE "http://localhost:8081/paiements/1"
```

---

## 9. Maintenance (`/maintenances`)

### 9.1 Ajouter une maintenance (POST)
- **URL** : `http://localhost:8081/maintenances`
- **Méthode** : `POST`
- **Body** :
```json
{
  "dateDebut": "2026-11-10",
  "dateFin": "2026-11-12",
  "description": "Vidange complète et changement des plaquettes de frein",
  "vehicule": {
    "idVehicule": 1
  }
}
```
- **cURL** :
```bash
curl -X POST "http://localhost:8081/maintenances" \
  -H "Content-Type: application/json" \
  -d '{"dateDebut":"2026-11-10","dateFin":"2026-11-12","description":"Vidange complète et changement des plaquettes de frein","vehicule":{"idVehicule":1}}'
```

### 9.2 Récupérer toutes les maintenances (GET)
```bash
curl -X GET "http://localhost:8081/maintenances"
```

### 9.3 Récupérer une maintenance par ID (GET)
```bash
curl -X GET "http://localhost:8081/maintenances/1"
```

### 9.4 Mettre à jour une maintenance (PUT)
```json
{
  "idMaintenance": 1,
  "dateDebut": "2026-11-10",
  "dateFin": "2026-11-13",
  "description": "Vidange, plaquettes et révision moteur complète",
  "vehicule": {
    "idVehicule": 1
  }
}
```

### 9.5 Supprimer une maintenance (DELETE)
```bash
curl -X DELETE "http://localhost:8081/maintenances/1"
```

---

## 🧪 Matrice Récapitulative des Endpoints

| Entité | POST (Ajouter) | PUT (Modifier) | GET (Tous) | GET (Par ID) | DELETE (Supprimer) |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Agence** | `/agences` | `/agences` | `/agences` | `/agences/{id}` | `/agences/{id}` |
| **Client** | `/clients` | `/clients` | `/clients` | `/clients/{id}` | `/clients/{id}` |
| **Equipement** | `/equipements` | `/equipements` | `/equipements` | `/equipements/{id}` | `/equipements/{id}` |
| **Vehicule** | `/vehicules` | `/vehicules` | `/vehicules` | `/vehicules/{id}` | `/vehicules/{id}` |
| **Employe** | `/employes` | `/employes` | `/employes` | `/employes/{id}` | `/employes/{id}` |
| **Reservation** | `/reservations` | `/reservations` | `/reservations` | `/reservations/{id}` | `/reservations/{id}` |
| **Contrat** | `/contrats` | `/contrats` | `/contrats` | `/contrats/{id}` | `/contrats/{id}` |
| **Paiement** | `/paiements` | `/paiements` | `/paiements` | `/paiements/{id}` | `/paiements/{id}` |
| **Maintenance** | `/maintenances` | `/maintenances` | `/maintenances` | `/maintenances/{id}` | `/maintenances/{id}` |

