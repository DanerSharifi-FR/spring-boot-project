# Évolution de l'application

Nous avons implémenté plusieurs fonctionnalités et améliorations dans l'application :

## Health Check des matchs
- Ajout d'un **HealthIndicator** pour surveiller l’activité des matchs.
- Le composant `MatchHealthIndicator` :
    - Vérifie la date du dernier match enregistré.
    - Renvoie un état `DOWN` si **aucun match n’a été joué depuis plus d’un mois**.
    - Renvoie un état `UP` si au moins un match a été joué durant le dernier mois.
- Intégré avec Spring Boot Actuator pour monitorer facilement l’état de l’application.

## Modèle de données
- **Ajout d’un champ `date` à l’entité `Match`** afin de stocker la date et l’heure de déroulement d’un match.
- Ce champ est utilisé pour :
    - Les contrôles de santé applicatifs (HealthIndicator)
    - Le reporting JSON

## Sécurité
- Sécurisation de tous les endpoints Actuator :
    - Seul le rôle `ADMIN` peut accéder aux endpoints Actuator.
    - Le endpoint `/health` reste accessible publiquement pour permettre un monitoring léger.
- **Form login à la place de l’authentification Basic** :
    - Implémentation d’un formulaire de connexion personnalisé (`/login`) au lieu de l’authentification HTTP Basic.
    - Support du login avec des utilisateurs en mémoire (`user` et `admin`) avec rôles respectifs.
    - Gestion dynamique de l’état utilisateur sur la page d’accueil : affichage du nom si connecté et bouton **Logout**.
    - Logout sécurisé via `/logout` avec CSRF géré automatiquement.
    - Gestion des accès refusés avec une page 403 personnalisée pour les utilisateurs non autorisés.
