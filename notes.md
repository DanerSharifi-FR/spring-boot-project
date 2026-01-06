# Notes
- `Spring` : un framework Java qui aide à structurer une application (gestion des dépendances, sécurité, accès aux données…) sans tout coder à la main.
- ` Spring Boot`  : une surcouche de Spring qui simplifie tout : configuration automatique, serveur intégré, démarrage rapide d’un projet prêt à l’emploi.
- `Spring Boot Actuator` est un module de l’écosystème Spring Boot qui sert à surveiller, gérer et diagnostiquer une application en cours d’exécution, sans avoir à écrire beaucoup de code supplémentaire.

À quoi ça sert concrètement ?

- Actuator expose des endpoints (souvent HTTP, parfois JMX) qui donnent des informations techniques sur ton application, par exemple :
`/actuator/health` : état de santé de l’application (UP / DOWN, base de données, services externes, etc.)
`/actuator/info` : informations personnalisées sur l’application
`/actuator/metrics` : métriques (mémoire, CPU, requêtes HTTP, etc.)
`/actuator/env` : variables d’environnement et propriétés
`/actuator/beans` : liste des beans Spring chargés
`/actuator/loggers` : niveau de logs à chaud