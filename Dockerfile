FROM eclipse-temurin:17-jdk

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para o container
COPY . .

# Compila o projeto antes de copiar o JAR
RUN ./mvnw clean package -DskipTests

# Copia o JAR gerado para a execução
COPY target/*.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
