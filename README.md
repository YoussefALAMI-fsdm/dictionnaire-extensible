# Cloner le projet
```bash
git clone https://github.com/YoussefALAMI-fsdm/dictionnaire-extensible <br><br>
```
```bash
cd dictionnaire-extensible
```
# Builder l'image Docker
```bash
docker build -t dictionnaire-app .
```
# Lancer l'application

## 1. Commande avec montage de volume

```bash
docker run -it --rm -v $(pwd)/data:/app/data dictionnaire-app
```

#### Cette commande monte le dossier local ./data dans /app/data du conteneur
#### 👉 La base SQLite (dictionnaire.db) est stockée sur ta machine
####    => elle est persistée et réutilisable même après arrêt du conteneur

## 2. Commande sans volume
```bash
docker run --rm -it dictionnaire-app
```

#### Ici, aucun volume n'est monté
#### 👉 La base SQLite est créée uniquement dans le conteneur
####    => elle sera perdue dès que le conteneur est supprimé (--rm)