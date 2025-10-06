# ===== Étape 1 : Build Maven =====
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copier pom.xml et télécharger les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier le code source et build
COPY src ./src
RUN mvn clean package -DskipTests

# ===== Étape 2 : Image finale =====
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copier le JAR avec dépendances depuis l'étape build
COPY --from=build /app/target/dictionnaire-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

# Créer le dossier pour SQLite
RUN mkdir -p /root/.local/share/Dictionnaire-extensible/database/

# Commande pour lancer l'application
CMD ["java", "-jar", "app.jar"]
