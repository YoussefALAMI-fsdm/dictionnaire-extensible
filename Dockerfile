# ===== Étape 1 : Build Maven =====
FROM maven:3.8.5-openjdk-17 AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier pom.xml pour télécharger les dépendances
COPY pom.xml .

# Télécharger les dépendances Maven (cache Docker)
RUN mvn dependency:go-offline -B

# Copier le code source
COPY src ./src

# Build du projet
RUN mvn clean package -DskipTests

# ===== Étape 2 : Création de l'image finale =====
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR généré depuis l'étape build
COPY --from=build /app/target/dictionnaire-1.0-SNAPSHOT.jar .

# Copier le code source pour que le conteneur ait accès au code
COPY src ./src
COPY pom.xml .

# Exposer un port si nécessaire (optionnel)
# EXPOSE 8080

# Commande par défaut pour exécuter l'application
CMD ["java", "-jar", "dictionnaire-1.0-SNAPSHOT.jar"]
