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

Spring Validation (JSR-303) sert à :
- garantir la cohérence métier des données
- avant qu’elles soient utilisées (controllers / services)
- automatiquement, via @Valid
👉 Les annotations se mettent sur les entités,
👉 La validation s’active dans les contrôleurs.

---

| Annotation | Catégorie | Rôle | Exemple |
|-----------|----------|------|---------|
| @SpringBootApplication | Configuration | Point d’entrée d’une appli Spring Boot (combine @Configuration, @EnableAutoConfiguration, @ComponentScan) | `@SpringBootApplication public class App {}` |
| @Autowired | Injection de dépendances | Injecte automatiquement un bean Spring | `@Autowired private UserService userService;` |
| @Component | Stéréotype | Déclare une classe comme bean Spring | `@Component public class MyComponent {}` |
| @Service | Stéréotype | Bean de couche métier (service) | `@Service public class UserService {}` |
| @Repository | Stéréotype | Bean de couche accès données (DAO) | `@Repository public class UserRepository {}` |
| @Controller | Web MVC | Contrôleur MVC (retourne des vues) | `@Controller public class UserController {}` |
| @RestController | Web REST | Contrôleur REST (`@Controller + @ResponseBody`) | `@RestController public class UserController {}` |
| @RequestMapping | Web REST | Mappe une requête HTTP (tous verbes) | `@RequestMapping("/users")` |
| @GetMapping | Web REST | Mappe une requête GET | `@GetMapping("/{id}")` |
| @PostMapping | Web REST | Mappe une requête POST | `@PostMapping` |
| @PutMapping | Web REST | Mappe une requête PUT | `@PutMapping` |
| @DeleteMapping | Web REST | Mappe une requête DELETE | `@DeleteMapping` |
| @PathVariable | Web REST | Récupère une variable d’URL | `@PathVariable Long id` |
| @RequestParam | Web REST | Récupère un paramètre de requête | `@RequestParam String name` |
| @RequestBody | Web REST | Mappe le body HTTP vers un objet | `@RequestBody User user` |
| @ResponseStatus | Web REST | Définit le code HTTP de la réponse | `@ResponseStatus(HttpStatus.CREATED)` |
| @Configuration | Configuration | Classe de configuration Spring | `@Configuration public class AppConfig {}` |
| @Bean | Configuration | Déclare un bean manuellement | `@Bean public ModelMapper mapper()` |
| @Value | Configuration | Injecte une propriété | `@Value("${app.name}")` |
| @Profile | Configuration | Active un bean selon l’environnement | `@Profile("dev")` |
| @Entity | JPA | Entité persistée en base | `@Entity class User {}` |
| @Id | JPA | Clé primaire | `@Id private Long id;` |
| @GeneratedValue | JPA | Génération auto de l’ID | `@GeneratedValue` |
| @Table | JPA | Nom de table | `@Table(name="users")` |
| @Column | JPA | Colonne de table | `@Column(nullable=false)` |
| @Transactional | Transaction | Gère les transactions | `@Transactional public void save()` |
| @EnableScheduling | Scheduling | Active les tâches planifiées | `@EnableScheduling` |
| @Scheduled | Scheduling | Tâche planifiée | `@Scheduled(fixedRate=5000)` |
| @EnableCaching | Cache | Active le cache | `@EnableCaching` |
| @Cacheable | Cache | Met en cache une méthode | `@Cacheable("users")` |
| Annotation | Catégorie | Rôle | Exemple |
|-----------|----------|------|---------|
| @SpringBootApplication | Configuration | Point d’entrée d’une appli Spring Boot (combine @Configuration, @EnableAutoConfiguration, @ComponentScan) | `@SpringBootApplication public class App {}` |
| @Autowired | Injection de dépendances | Injecte automatiquement un bean Spring | `@Autowired private UserService userService;` |
| @Component | Stéréotype | Déclare une classe comme bean Spring | `@Component public class MyComponent {}` |
| @Service | Stéréotype | Bean de couche métier (service) | `@Service public class UserService {}` |
| @Repository | Stéréotype | Bean de couche accès données (DAO) | `@Repository public class UserRepository {}` |
| @Controller | Web MVC | Contrôleur MVC (retourne des vues) | `@Controller public class UserController {}` |
| @RestController | Web REST | Contrôleur REST (`@Controller + @ResponseBody`) | `@RestController public class UserController {}` |
| @RequestMapping | Web REST | Mappe une requête HTTP (tous verbes) | `@RequestMapping("/users")` |
| @GetMapping | Web REST | Mappe une requête GET | `@GetMapping("/{id}")` |
| @PostMapping | Web REST | Mappe une requête POST | `@PostMapping` |
| @PutMapping | Web REST | Mappe une requête PUT | `@PutMapping` |
| @DeleteMapping | Web REST | Mappe une requête DELETE | `@DeleteMapping` |
| @PathVariable | Web REST | Récupère une variable d’URL | `@PathVariable Long id` |
| @RequestParam | Web REST | Récupère un paramètre de requête | `@RequestParam String name` |
| @RequestBody | Web REST | Mappe le body HTTP vers un objet | `@RequestBody User user` |
| @ResponseStatus | Web REST | Définit le code HTTP de la réponse | `@ResponseStatus(HttpStatus.CREATED)` |
| @Configuration | Configuration | Classe de configuration Spring | `@Configuration public class AppConfig {}` |
| @Bean | Configuration | Déclare un bean manuellement | `@Bean public ModelMapper mapper()` |
| @Value | Configuration | Injecte une propriété | `@Value("${app.name}")` |
| @Profile | Configuration | Active un bean selon l’environnement | `@Profile("dev")` |
| @Entity | JPA | Entité persistée en base | `@Entity class User {}` |
| @Id | JPA | Clé primaire | `@Id private Long id;` |
| @GeneratedValue | JPA | Génération auto de l’ID | `@GeneratedValue` |
| @Table | JPA | Nom de table | `@Table(name="users")` |
| @Column | JPA | Colonne de table | `@Column(nullable=false)` |
| @Transactional | Transaction | Gère les transactions | `@Transactional public void save()` |
| @EnableScheduling | Scheduling | Active les tâches planifiées | `@EnableScheduling` |
| @Scheduled | Scheduling | Tâche planifiée | `@Scheduled(fixedRate=5000)` |
| @EnableCaching | Cache | Active le cache | `@EnableCaching` |
| @Cacheable | Cache | Met en cache une méthode | `@Cacheable("users")` |


---
A faire

## Fonctionnalités CRUD
- Création d'un match (requête POST)
- Mise à jour du statut d'un match (requête PUT)
- Suppression d'un match (requête DELETE)

## Gestion des matchs
- Création en masse de matchs avec :
    - Ajout du rapport (OK/KO), date, nombre d’événements, équipe gagnante, MVP, etc.
    - Vérification pour éviter l'enregistrement de doublons
    - Gestion des erreurs si la création du rapport échoue
- Recherche de matchs selon différents critères (requête GET)

## Gestion des utilisateurs
- Stockage des utilisateurs en base de données via `JdbcUserDetailsManager` (Spring Security)
- Enregistrement des utilisateurs via API ou formulaire (Spring Security)

## Améliorations et monitoring
- Ajout d’un champ date à `Match` (Data)
- Ajout d’un `HealthIndicator` pour vérifier si aucun match n’a été joué depuis 1 mois (Actuator)
- Ajout de métriques sur les matchs finaux lors de leur enregistrement ou au passage au statut TERMINÉ
- Ajout de contraintes sur le format des données (numéro de joueur, nombre de joueurs par équipe, nombre de rounds par match…) via Spring Validation (JSR 303)
- Logging des entrées et sorties de l’API sans surcharger les contrôleurs (AOP)