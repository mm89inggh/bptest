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

Inicio de servicio
-------------------

Iniciar los servicios por primera vez
  * docker-machine scp servicios.yml manager:
  * docker-machine ssh manager
  * docker stack deploy -c servicios.yml servicios

Probar la integración continua
-----------------------------
Genere un cambio en código y suba a al repositorio, jenkins desplegará su nuevo cambio, si no posee un webhook para cambios genere el pipeline manualmente.
