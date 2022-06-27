<h3><strong>Registro de movimientos de cuentas de clientes</strong></h3>

<p>La arquitectura planteada para la soluci&oacute;n del movimiento de cuentas de clientes, se expone en la imagen que sigue.</p>

<p><img alt="" src="https://raw.githubusercontent.com/miriamjaramillo/banco_movimientos/main/arquitectura.png" style="height:460px; width:650px" /></p>

<p>En la imagen siguiente se ilustra el DER utilizado para resolver el ejercicio planteado.</p>

<p><img alt="" src="https://raw.githubusercontent.com/miriamjaramillo/banco_movimientos/main/DER.PNG" style="height:341px; width:250px" /></p>

<p>Esta gu&iacute;a explica el proceso de creaci&oacute;n de contenedores Docker, que permiten ejecutar la aplicaci&oacute;n para los registros de movimientos de cuentas de clientes. Esta es una gu&iacute;a de &quot;inicio&quot;, por lo que el alcance se limita a unas pocas necesidades b&aacute;sicas.</p>

<p><strong>Contiene</strong></p>

<ul>
	<li>Spring Boot</li>
	<li>Spring Data JPA e Hibernate</li>
	<li>API REST</li>
	<li>Spring Cloud Feign</li>
	<li>PostgreSQL</li>
	<li>Documentaci&oacute;n API REST con Swagger</li>
	<li>Postman</li>
</ul>

<p><strong>Requisitos previos GIT en el servidor - Generar la clave p&uacute;blica </strong></p>

<p>Para generar la clave p&uacute;blica se sugiere seguir las instrucciones del siguiente tutorial:<br />
<a href="https://git-scm.com/book/es/v2/Git-en-el-Servidor-Generando-tu-clave-p%C3%BAblica-SSH ">https://git-scm.com/book/es/v2/Git-en-el-Servidor-Generando-tu-clave-p%C3%BAblica-SSH </a></p>

<p>Para m&aacute;s detalles de c&oacute;mo crear claves SSH, consultar la gu&iacute;a correspondiente de GitLab<br />
<a href="https://docs.gitlab.com/ee/ssh/index.html ">https://docs.gitlab.com/ee/ssh/index.html </a></p>

<p>A continuaci&oacute;n, las opciones de instalaci&oacute;n de Docker y Docker Compose, dependiendo del sistema operativo.</p>

<p><strong>Sistemas Operativos basados en WINDOWS</strong></p>

<p>Instalar Docker Desktop<br />
<a href="https://docs.docker.com/desktop/windows/install/ ">https://docs.docker.com/desktop/windows/install/ </a></p>

<p>Instalar el Subsitema de Windows para Linux (WSL) <a href="https://docs.microsoft.com/es-es/windows/wsl/install ">https://docs.microsoft.com/es-es/windows/wsl/install </a><br />
<a href="https://docs.microsoft.com/es-es/windows/wsl/install-manual">https://docs.microsoft.com/es-es/windows/wsl/install-manual</a></p>

<p><strong>Para cualquier otro sistema operativo, se necesita de lo siguiente: </strong></p>

<ul>
	<li>Servidor de Ubuntu 20.04 y un usuario no root con privilegios sudo.</li>
	<li>Docker instalado y C&oacute;mo instalar y usar Docker en Ubuntu 20.04.</li>
</ul>

<p><a href="https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-20-04">https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-20-04</a></p>

<ul>
	<li>Docker Compose instalado y C&oacute;mo instalar Docker Compose en Ubuntu 20.04</li>
</ul>

<p><a href="https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-compose-on-ubuntu-20-04 ">https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-compose-on-ubuntu-20-04</a></p>

<p><strong>Pasos a seguir</strong></p>

<p>Los pasos a seguir son los siguientes:</p>

<p><strong>Paso 1.- Clonar el proyecto</strong></p>

<p>En el proyecto se encuentran los fuentes y los archivos necesarios para levantar el ambiente.</p>

<pre>
git clone <a href="https://github.com/miriamjaramillo/banco_movimientos.git">https://github.com/miriamjaramillo/banco_movimientos.git</a>
</pre>

<p><strong>Paso 2.- Levantar el ambiente. Iniciar los contenedores desde el directorio ra&iacute;z del proyecto.</strong></p>

<pre>
docker-compose up -d
docker-compose ps</pre>

<p>Con el &uacute;ltimo comando de este paso podr&aacute; listar los contenedores creados.</p>

<p><strong>Paso 3.- Acceso mediante la herramienta Swagger.</strong></p>

<p>Las URLs de acceso a los distintos microservicios son:</p>

<pre>
<a href="http://localhost:8001/swagger-ui.html">http://localhost:8761/swagger-ui.html
http://localhost:8081/swagger-ui.html</a>
<a href="http://localhost:8002/swagger-ui.html">http://localhost:8082/swagger-ui.html</a>
<a href="http://localhost:8003/swagger-ui.html">http://localhost:8083/swagger-ui.html</a>

</pre>

<p>Para la validaci&oacute;n de los endpoints se adjunta los archivos json generados con la herramienta Postman.</p>

<p><strong>Agregar/Eliminar/Modificar servicios de docker-compose</strong></p>

<p>Para agregar/eliminar/modificar cualquier servicio m&aacute;s adelante (despu&eacute;s de docker-compose up), recuerde ejecutar los siguientes comandos desde el directorio del proyecto para aplicar sus cambios:</p>

<pre>
docker-compose down
docker-compose up --force-recreate --build -d
docker image prune -f
</pre>
