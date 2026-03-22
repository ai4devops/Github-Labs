<!-- markdownlint-disable MD024 -->
<!-- markdownlint-disable MD045 -->

# Prompt Engineering part 1

## 🗒️ Guide

### Prérequis

- Copilot
- Copilot Chat

---

## System prompt
Le System Prompt est une instruction de haut niveau qui fixe :
- le rôle de l’assistant
- les règles de sécurité et de conformité
- le style de réponse attendu
- les contraintes de comportement (ex. lecture seule, format de sortie, langue).

### Comparaison
```text
Dans plusieurs nouvelles conversations Copilot Chat, envoyez le prompt suivant (Vérifiez bien que votre contexte sois bien vide à chaque nouvelle conversation):
"Comment générer un projet de programmation ?"

Vous constatez qu'à chaque prompt envoyé, le chat va vous envoyer une réponse avec avec des langages de programmation différents


Voici un exemple de system prompt et envoyez de nouveau le prompt dans plusieurs nouvelles conversations Copilot Chat : 
"Langage : Java 11
Framework : Spring Boot
BDD: MySQL
Build: Maven
Test: JUnit 5
Comment générer un projet en programmation"

Vous constatez que cette fois-ci, le chat va vous donner des informations quasi identiques pour vous répondre

Vous pouvez tester le prompt system avec d'autres prompt
Langage : Java 11
Framework : Spring Boot
BDD: MySQL
Build: Maven
Test: JUnit 5

« Comment développer une nouvelle feature ? »
« Comment tester mon projet ? »
```

### Auto-prompt
Si vous ne savez pas comment écrire votre prompt system, il est tout à fait possible de demander au chat de l'écrire pour vous
```text
Ecrit un prompt system avec les caractéristiques suivantes:
Langage : Java 11
Framework : Spring Boot
BDD: MySQL
Build: Maven
Test: JUnit 5
```

## Role prompting
Le role prompting consiste à demander explicitement à l’assistant d’adopter un rôle précis (ex. expert Java senior, formateur pédagogique, reviewer sécurité).

Ce rôle influence :
- le niveau technique
- l’angle d’analyse
- le style de réponse
- les priorités (performance, lisibilité, sécurité, tests, etc.)

### Comparaison
```text
Envoyez ce prompts avec différents roles afin de comparer les résultats
« Agis en tant que <role>, que dois-je priorisé dans la création de mon projet en java »

Roles possible : 
Senior Java
Expert sécurité
Architecte IT
Jury de projet informatique
```

## Few-shot prompting

### Zero-shot
```text
Utilise Github Copilot pour écrire des tests unitaires pour toutes les opérations dans le fichier Lab-2\src\lab2-calculator.java
```
### One-Shot

 ```text
 Utilise Github Copilot pour écrire des tests unitaires pour toutes les opérations dans le fichier Lab-2\src\lab2-calculator.java
Utilise l'exemple suivant : 
@Test
void subtraction_shouldReturnDifference() {
    assertEquals(5, Main.calculator(10, 5, "-"));
};

puis l'exemple suivant :
@Property
void subtraction_inverse_property(@ForAll int a, @ForAll int b) {
    int result = Main.calculator(a, b, "-");
    int back = Main.calculator(result, b, "+");
    assertEquals(a, back);
}

compare les résultats
```
---
### Zero-shot

```text
Utilise Github Copilot pour te générer une liste de personnes pour tester une application bancaire
```

### One-Shot

```text
Utilise Github Copilot pour te générer une liste de personnes pour tester une application bancaire en utilisant l'exemple suivant :
{
  "Nom": "Doe",
  "Prenom": "John,
  "Adresse": "1 rue des Champs-Elysees",
  "Ville": "Paris",
  "Tel": "0123456789"
  "mail": John.Doe@gmail.com"
}

Compare les résultats
```