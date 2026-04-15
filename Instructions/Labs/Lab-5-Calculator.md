<!-- markdownlint-disable MD024 -->
<!-- markdownlint-disable MD045 -->

# Exercice final

## 🗒️ Guide

### Prérequis

- Copilot
- Copilot chat

---

## Contexte

Vous rejoignez une équipe qui maintient un petit calculateur de panier.
Le projet est situé dans le dossier lab-5.
Le code fonctionne "à peu près", mais plusieurs règles métier sont mal gérées :

- certains calculs monétaires sont fragiles
- certains cas limites ne sont pas traités
- la logique de remise et de frais de port n'est pas fiable
- le comportement attendu est donné dans un fichier de self-check

Votre mission est de transformer Copilot en assistant spécialisé sur ce mini projet, puis de lui faire corriger le code.
Note: Vous pouvez jouer avec l'application (sans modifier le code pour l'instant) en ouvrant un terminal sur le dossier ```.\lab-5\src``` et en lançant la commande ```javac .\lab5\*.java | java .\lab5\App.java```.

## Étape 1 : Analyser le projet

Lancez le fichier de test de l'application en ouvrant un terminal sur le dossier ```.\lab-5\src``` et  lancez la commande ```javac .\lab5\*.java | java .\lab5\SelfCheck.java```.
Vous devriez voir les résultats des tests avec pas suffisamment de cas de réussite.

Tentez de corriger ce projet en n'écrivant aucune ligne de code par vous-même mais en utilisant uniquement Copilot pour vous générer du code que vous pourrez copier-coller (ou éditer directement) dans le projet.

<details>
<summary>Solution</summary>
Une solution possible est de demander à Copilot de réaliser les tâches suivantes :
- Analyser le projet présent dans le package lab5
- Déduire les règles métier à partir de lab5.SelfCheck
- Corriger le code

```Analyse le projet Java dans #file:lab5 , déduis les règles métier à partir de SelfCheck.java, puis corrige le code avec le minimum de changement```

</details>

## Étape 2 : Instruction file
Générez fichier d'instruction (.instruction.md) uniquement par Github Copilot afin de customiser votre projet et lister les features de votre projet.
Mettez le résultat dans le fichier .github/lab5.instruction.md

<details>
<summary>Solution</summary>
VSCode :

```/create-instruction Je veux une instruction file pour un projet Java #file:lab5 et utilise #file:OrderCalculator comme source de vérité.```

IntelliJ :
```Génère un fichier d'instruction pour mon projet Java dans #file:lab5 en utilisant #file:OrderCalculator comme source de vérité.```

Un exemple de résultat
```
Présentation
La classe OrderCalculator permet de calculer le montant total d’une commande en fonction d’une liste d’articles, du type de client et d’un éventuel code promotionnel.
Structure
Classe interne Item : représente un article de la commande.
Attributs :
name (String) : nom de l’article
quantity (int) : quantité commandée (doit être ≥ 0)
unitPrice (double) : prix unitaire
Méthodes d’accès : getName(), getQuantity(), getUnitPrice()
Méthode statique calculateTotal
Signature :
public static double calculateTotal(List<Item> items, String customerType, String promoCode)
Paramètres :
items : liste d’objets Item
customerType : type de client ("PREMIUM" ou autre)
promoCode : code promo ("WELCOME10" ou autre)
Retourne : le montant total à payer (double)
Règles de calcul
Sous-total : somme des quantités × prix unitaire pour chaque article.
Si une quantité < 0, une exception est levée.
Application du code promo :
"WELCOME10" : -10€ sur le sous-total.
Les autres codes sont ignorés.
Remise client :
Si customerType = "PREMIUM" : -10% sur le sous-total (après promo).
Frais de port :
Si le sous-total initial > 50€ : port gratuit.
Sinon : 4,99€ ajoutés.
Total : sous-total final + frais de port.
```

</details>

## Étape 3 : Vérification du fichier d'instruction
Vérifiez que le fichier d'instruction généré correspond bien à la réalité du projet
Lancez le prompt suivant
```Est-ce que mon projet #file:lab5 correspond à l'instruction file #file:.github/lab5.instruction.md ?```
Note: pensez à bien modifier dans votre prompt les "#file:*" en les écrivant par vous-même afin que Github Copilot comprenne bien qu'il doit ajouter ces fichiers dans son contexte

## Étape 4 : Nouvelle feature
Dans le fichier SelfCheck.java (l.59), remplacez les commentaires mis à disposition par ce nouveau test pour une nouvelle règle métier:
```
passed += checkEquals(
                "New special promotion handled",
                33.99,
                OrderCalculator.calculateTotal(
                        Arrays.asList(new OrderCalculator.Item("Keyboard", 1, 50.00)),
                        "STANDARD",
                        "SPECIAL"
                )
        );
        total += 1;
```
Lancez le fichier de test de l'application en ouvrant un terminal sur le dossier ```.\lab-5\src``` et  lancez la commande ```javac .\lab5\*.java | java .\lab5\SelfCheck.java```.
Vous devriez voir que ce nouveau test échouer.

Nous voulons ajouter une nouvelle promotion du nom de "SPECIAL" qui offre une réduction de 42% sur le sous-total.
En modifiant votre fichier d'instruction uniquement, faites modifier votre code pour que cela applique cette nouvelle feature

Testez à nouveau votre code avec la commande ```javac .\lab5\*.java | java .\lab5\SelfCheck.java```. pour vérifier que cela fonctionne.

<details>
<summary>Solution</summary>

Ajoutez dans votre fichier d'instruction la règle métier suivante : "Application du code promo : "SPECIAL" : -42% sur le sous-total."

Puis lancez le prompt suivant pour corriger votre code :
```Corrige le code de #file:lab5 pour appliquer les règles métier de #file:lab5.instruction.md```

</details>