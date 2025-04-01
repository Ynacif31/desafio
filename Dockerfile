# Usa a imagem oficial do Eclipse Temurin (OpenJDK) com versão LTS
FROM eclipse-temurin:17-jdk-jammy

# Define o diretório de trabalho (melhor prática: usar caminhos absolutos)
WORKDIR /app

# Instala dependências do sistema primeiro (camada otimizada para cache)
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    curl && \
    rm -rf /var/lib/apt/lists/*

# Copia o JAR específico (evita problemas com wildcards)
COPY target/bolota-0.0.1-SNAPSHOT.jar app.jar

# Configura variáveis de ambiente (ajuste conforme sua aplicação)
ENV SPRING_PROFILES_ACTIVE=prod
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Expõe a porta (Spring Boot usa 8080 por padrão)
EXPOSE 8080

# Health check (opcional mas recomendado)
HEALTHCHECK --interval=30s --timeout=3s \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Entrada otimizada para containers
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar /app/app.jar"]
