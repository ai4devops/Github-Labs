<!-- markdownlint-disable MD024 -->
<!-- markdownlint-disable MD045 -->

# Les modes de Github Copilot

## 🗒️ Guide

### Prérequis

- Copilot Chat
- Java 11+ (Optionnel)
---

## Ask mode

### Chatbot

```text
 il est possible d’utiliser GitHub Copilot Chat comme assistant technique interactif. Cet outil permet de poser des questions liées au développement logiciel, à la compréhension du code, au diagnostic d’erreurs, à l’architecture applicative ainsi qu’aux bonnes pratiques de programmation.
```
A vous de tester
```text
 Utilisez certains de ces exemples de prompt à envoyer à votre Copilot chat en mode "Ask" pour tester les interactions:
« Peux-tu m’expliquer cette erreur NullPointerException et comment la corriger ? »
« Comment refactoriser cette fonction pour qu’elle soit plus lisible ? »
« Quelle est la meilleure structure pour organiser mes fichiers dans ce projet ? »
« Peux-tu me proposer des tests unitaires pour cette classe ? »
« Comment optimiser cette requête SQL qui est trop lente ? »
« Peux-tu m’expliquer ce que fait cette regex ligne par ligne ? »
Testez d'autres prompt à votre guise
```
### Les raccourcis

 ```text
 Il existe 3 type de raccourcis invocables grâce aux symboles clé : / @ #
 / : Pour des prompts prédéfinis dans Github Copilot
 @ : Pour ajouter des extensions lié à Github Copilot
 # : Pour ajouter des éléments dans le contexte du prompt
```
A vous de tester
 ```text
 Observer le fichier Lab-1\src\lab1-explain.java et essayer de comprendre ce que fait ce code (Vous pouvez lancer la commande "java .\lab1-explain.java" dans un terminal placé dans le dossier pour avoir un résultat)
 
 Puis, dans une nouvelle conversation Copilot chat, mettez dans un meme prompt:
 - Avec le raccourci #, ajoutez le fichier Lab-1\src\lab1-explain.java 
 - Avec le raccourci /, ajoutez le prompt /explain
Validez le prompt

```
---

## Plan mode

```text
Le mode Plan est un mode d'interaction avec GitHub Copilot dans lequel l'assistant analyse une tâche complexe, élabore un plan d'action structuré et le soumet à l'utilisateur pour validation avant toute exécution.
Contrairement aux modes plus directs (comme le mode Ask ou Edit), le mode Plan introduit une étape intermédiaire de réflexion et de planification, ce qui le rend particulièrement adapté aux tâches multi-étapes ou à fort impact sur le code.
```
A vous de tester
 ```text
 Utisez ces exemples de prompt à envoyer à votre Copilot chat en mode "Plan" pour tester les résultats:
 « Crée un service REST complet pour gérer des utilisateurs avec Spring Boot : entité, repository, service et contrôleur. »
 « Ajoute également une étape pour les tests unitaires du service. »
 Testez d'autres prompt à votre guise
 Note: Si vous avez une version antérieur de Github Copilot et n'avait pas le mode Plan, vous pouvez utiliser le mode Ask en ajoutant la phrase "Rédige moi un plan pour ..."
```

---

### Edit mode

#### Edit mode

```text
Le mode Edit est un mode d'interaction avec GitHub Copilot dans lequel l'assistant modifie directement un ou plusieurs fichiers du projet en réponse à une instruction précise de l'utilisateur.
Contrairement au mode Ask qui se limite à répondre à des questions, le mode Edit intervient concrètement dans le code source, en ciblant des fichiers spécifiques pour y apporter des modifications immédiates.
```
A vous de tester
```text
En utilisant les raccourcis # et /, essayez de fix le code présent dans Lab-1\src\lab1-fix
```
---

Note : Nous verrons plus tard dans les exercices pour le Agent Mode
