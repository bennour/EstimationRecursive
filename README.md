# What a speed!

## Utilisation

### Installation des dépendances

#### Pré-requis

Si ce n'est pas encore fait, installez via votre gestionnaire de paquets favori :

* Git (commande `git`)
* SVN (commande `svn`)
* Mercurial (commande `hg`)
* GNUPlot (commande `gnuplot`)

#### Automatiquement

Il est possible de lancer le script pour télécharger les dépendances.

    $ ./lib/libinstall.sh

#### Manuellement

##### Eigen

Bibliothèque C++ pour l'algèbre linéaire (matrices, vecteurs, solveurs numériques...).

    $ cd lib
    $ hg clone https://bitbucket.org/eigen/eigen/

##### GoogleTest

Framework de tests unitaires.

    $ cd lib
    $ svn checkout http://googletest.googlecode.com/svn/trunk/ googletest
    $ cd googletest
    $ g++ -I include -I . -c src/gtest-all.cc
    $ ar -rv libgtest.a gtest-all.o

##### Interface Gnuplot/C++

	$ cd lib
	$ svn checkout http://gnuplot-cpp.googlecode.com/svn/trunk/ gnuplot

### Compilation

Utiliser la commande `make` à la racine du dossier pour compiler le projet.

### Exécution

Une fois le programme compilé, `./bin/radar` à la racine du dossier.

## Contact

[Contacter l'équipe](mailto:bennour@polytech.unice.fr,benslima@polytech.unice.fr)

* Amir Ben Slimane
* Salah Bennour

---

![Polytech Nice Sophia](http://users.polytech.unice.fr/~bennour/logos.png)