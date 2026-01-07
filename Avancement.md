# Évolution de l'application

Nous avons implémenté plusieurs fonctionnalités et améliorations dans l'application :

## Health Check des matchs
- Ajout d'un **HealthIndicator** pour vérifier l’état des matchs en base de données.
- Le composant `MatchHealthIndicator` :
    - Vérifie le nombre de matchs présents dans la base.
    - Renvoie un état `DOWN` si aucun match n’est enregistré avec un message d’erreur : "Aucun match en base de données".
    - Renvoie un état `UP` avec le nombre de matchs si au moins un match existe.
- Intégré avec Spring Boot Actuator pour monitorer facilement l’état de l’application.

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
