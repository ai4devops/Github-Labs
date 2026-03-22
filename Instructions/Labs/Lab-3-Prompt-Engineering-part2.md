<!-- markdownlint-disable MD024 -->
<!-- markdownlint-disable MD045 -->

# Prompt Engineering part 2

## 🗒️ Guide

### Prérequis

- Copilot
- Copilot Chat

---

## Chain of thought
Le chain-of-thought prompting consiste à demander à l’assistant de structurer son raisonnement en étapes claires pour arriver à une solution (diagnostic, hypothèses, choix du correctif, validation).


### 
```text
Lancez ce prompt sur le fichier Lab-3\src\lab3-apiServer

Tu es un reviewer Java/Spring senior.
Analyse ce bug en 5 parties :
1) Symptôme
2) Hypothèses (classées par probabilité)
3) Diagnostic pas à pas
4) Correctif minimal (code Java)
5) Plan de tests (JUnit + cas limites)
Contraintes : réponse concise, en français, et chaque hypothèse doit être vérifiable.
```

### Le mode Plan
De la même manière que l'auto-prompt, vous avez la possibilité de demander à l'IA de vous fournir un prompt pour vous donner un plan
```text
Demandez à votre chat en mode Plan de vous donner un plan pour faire une review Java/Spring

Récupérez le résultat donné et coller dans le fichier Lab-3\src\lab3-RAG.txt
```

## RAG
Le RAG combine deux mécanismes :
- Retrieval : recherche de contenus pertinents (documentation, procédures, code interne),
- Generation : génération d’une réponse enrichie à partir des éléments retrouvés.

L’assistant ne répond donc pas uniquement sur sa connaissance générale ; il s’appuie aussi sur des sources ciblées.


```text
Dans un nouveau chat Copilot, en utilisant le fichier que vous avez mis dans Lab-3\src\lab3-RAG.txt, refaites une review du fichier Lab-3\src\lab3-apiServer
```
(Note: ce n'est pas un vrai RAG mais dans le fonctionnement c'est un moyen proche de l'illustrer)