
JBoss (single instance) with Apache
-----------------------------------------------------------------------------------------------------------
This is the default environment used on our integration tests.
No apache instance is started in this environment


* To run the development environment, just execute in this folder:

    docker-compose up

* If you need to recreate JBoss image, run:

    docker-compose up --build

* If your images are in a bad state and you want to re-download everything, run the script docker-clean-all

* The ports 8080 (http), 9990 (console), 9999 (management) and 8787 (debug) from JBoss
 are linked to your localhost

