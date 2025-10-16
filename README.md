# Dictionnaire Extensible - README

# Cloner le projet
```bash
git clone https://github.com/YoussefALAMI-fsdm/dictionnaire-extensible
```
```bash
cd dictionnaire-extensible
```

# Builder l'image Docker
```bash
sudo docker build -t dictionnaire-app .
```

# Lancer l'application
### Linux / Docker sous WSL2
```bash
sudo docker run -it --rm -v "$HOME/.local/share/Dictionnaire-extensible/database:/app/data" dictionnaire-app
```
 

### macOS
 ```bash
 sudo docker run -it --rm -v "$HOME/Library/Application\ Support/Dictionnaire-extensible/database:/app/data" dictionnaire-app
 ```


### Windows PowerShell
```powershell
docker run -it --rm -v "${env:USERPROFILE}\AppData\Local\Dictionnaire-extensible\database:/app/data" dictionnaire-app
```

## Lancer sans volume (DB temporaire dans le conteneur)
```bash
sudo docker run --rm -it dictionnaire-app
```

---

> La base de données SQLite (dictionnaire.db) est automatiquement créée dans le chemin standard de votre système (Windows, macOS ou Linux) lorsque vous utilisez le montage de volume. Elle est persistante même après l'arrêt ou la suppression du conteneur Docker.

> Si vous lancez l'application sans volume, la DB sera temporaire et supprimée avec le conteneur.


