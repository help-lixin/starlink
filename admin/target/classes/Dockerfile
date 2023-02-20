FROM openjdk:8u302-jdk-oracle

# define env
ENV APPLICATION_NAME      app.jar
ENV APPLICATION_HOME      /opt/application
ENV APPLICATION_BIN_DIR   ${APPLICATION_HOME}/bin
ENV APPLICATION_CONF_DIR  ${APPLICATION_HOME}/conf
ENV APPLICATION_LOG_DIR   ${APPLICATION_HOME}/logs
#ENV APPLICATION_LOG_DIR   /opt/application/logs
ENV APPLICATION_DUMP_DIR  ${APPLICATION_HOME}/dumpfiles
ENV APPLICATION           ${APPLICATION_BIN_DIR}/${APPLICATION_NAME}
ENV LOCAL_SNAPSHOT_PATH   ${APPLICATION_CONF_DIR}

ARG JAR_FILE=target/*.jar

# mkdir application home
RUN groupadd -g 1000 app \
&& adduser -g 1000 -u 1000 app \
&& mkdir ${APPLICATION_HOME} \
&& mkdir ${APPLICATION_BIN_DIR} \
&& mkdir ${APPLICATION_CONF_DIR} \
&& mkdir ${APPLICATION_LOG_DIR} \
&& mkdir ${APPLICATION_DUMP_DIR} \
&& chown -R app:app ${APPLICATION_HOME}

USER app:app

COPY ${JAR_FILE}  ${APPLICATION}

ENV JAVA_OPTS="\
-Xms1024m \
-Xmx1024m "

ENTRYPOINT java ${JAVA_OPTS} -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${APPLICATION_DUMP_DIR} -Dfile.encoding=UTF-8 -Duser.timezone=GMT+8 -jar ${APPLICATION}
