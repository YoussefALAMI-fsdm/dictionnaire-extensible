# Cloner le projet
git clone https://github.com/ton-compte/mon-projet-dictionnaire.git
cd mon-projet-dictionnaire

# Builder l'image Docker
docker build -t dictionnaire-app .

# Lancer l'application
docker run --rm -it dictionnaire-app