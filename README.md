# Cloner le projet
git clone https://github.com/YoussefALAMI-fsdm/dictionnaire-extensible <br><br>
cd dictionnaire-extensible

# Builder l'image Docker
docker build -t dictionnaire-app .

# Lancer l'application
docker run --rm -it dictionnaire-app