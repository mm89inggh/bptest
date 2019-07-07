# bptest
Microservicio con balanceador

Esta es una prueba para la generación de un servicio balanceado usando CI y docker swarm 

Creación de swarm (Preparando ambiente)
--------------------

Para este ejercicio usaremos docker-machine en el cual crearemos 3 servidores de los cuales un servidor será el administrador(manager) y los demas servidores serán los host(slaves) con el uso de virtual box(como ejemplo, se pueden usar servidores remotos tambien)

  * docker-machine create --driver virtualbox manager
  * docker-machine create --driver virtualbox nodoA
  * docker-machine create --driver virtualbox nodoB
  * docker-machine ls
  
Una vez creado los host virtuales iniciaremos el SWARM en el nodo manager
  * docker-machine ssh manager
  * docker swarm init --advertise-addr ${ip_generada_para_manager}
  * exit
  
Seguido anclamos los nodos slave a la granja(lo mismo para anbos servidores)
  * docker-machine ssh nodoA
  * Copiar el código generado por en comando anterior en el host manager se parece a docker swarm join --token XXX ....
  * exit

Para un manejo visual instalamos Portainer con archivo portainer.yml en el host manager, para exto copiamos en nuestro host manager el archivo yml.
  * docker-machine scp portainer.yml manager:
  * docker-machine ssh manager
  * docker stack deploy -c portainer.yml portainer
  
Verificamos que portainer este activo en el browser con la ip del manager y el puerto 9000

Integración continua (Preparando ambiente)
---------------------
  
Instalamos a jenkins para en el nodo manager(esto para efectos de prueba ya que para conectarse con github se necesita crear un webhook e integrarlo con una ip publica para que github sea activado y ejecutado automaticamente)
  * docker-machine scp jenkins.yml manager:
  * docker-machine ssh manager
  * docker stack deploy -c jenkins.yml jenkins

Verificamos que portainer este activo en el browser con la ip del manager y el puerto 8080

Al iniciar jenkins se debe copiar el uuid generado al iniciar el mismo
  * docker-machine ssh manager
  * docker ps
  * docker exec -it CONTAINER ID cat var/jenkins_home/secrets/initialAdminPassword
  * Copiamos el uuid generado
  
No olvidar configurar el pipeline en jenkins con el source de git y el archivo Jenkinsfile



1. Define your app's environment with a `Dockerfile` so it can be
reproduced anywhere.
2. Define the services that make up your app in `docker-compose.yml` so
they can be run together in an isolated environment.
3. Lastly, run `docker-compose up` and Compose will start and run your entire app.

A `docker-compose.yml` looks like this:

    version: '2'

    services:
      web:
        build: .
        ports:
         - "5000:5000"
        volumes:
         - .:/code
      redis:
        image: redis

For more information about the Compose file, see the
[Compose file reference](https://github.com/docker/docker.github.io/blob/master/compose/compose-file/compose-versioning.md).

Compose has commands for managing the whole lifecycle of your application:

 * Start, stop and rebuild services
 * View the status of running services
 * Stream the log output of running services
 * Run a one-off command on a service

Installation and documentation
------------------------------

- Full documentation is available on [Docker's website](https://docs.docker.com/compose/).
- Code repository for Compose is on [GitHub](https://github.com/docker/compose).
- If you find any problems please fill out an [issue](https://github.com/docker/compose/issues/new/choose). Thank you!

Contributing
------------

[![Build Status](https://jenkins.dockerproject.org/buildStatus/icon?job=docker/compose/master)](https://jenkins.dockerproject.org/job/docker/job/compose/job/master/)

Want to help build Compose? Check out our [contributing documentation](https://github.com/docker/compose/blob/master/CONTRIBUTING.md).

Releasing
---------

Releases are built by maintainers, following an outline of the [release process](https://github.com/docker/compose/blob/master/project/RELEASE-PROCESS.md).

