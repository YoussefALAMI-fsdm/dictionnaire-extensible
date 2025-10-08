# Cloner le projet
```bash
git clone https://github.com/YoussefALAMI-fsdm/dictionnaire-extensible
```
```bash
cd dictionnaire-extensible
```
# Builder l'image Docker
```bash
docker build -t dictionnaire-app .
```
# Lancer l'application

```bash
docker run --rm -it dictionnaire-app
```
<br>
<br>
<br> 

> La base de données SQLite (dictionnaire.db) est automatiquement créée
> dans le chemin standard de votre système (Windows, macOS ou Linux).
> Elle est persistante même après l'arrêt ou la suppression du conteneur Docker.
