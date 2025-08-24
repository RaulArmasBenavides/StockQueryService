Este proyecto corre con jdk 17

Abre Command Palette → Java: Configure Java Runtime.

La conexión de la base de datos está en vercel a modo de pruebas

Use the following commands :

./gradlew clean build --refresh-dependencies
./gradlew bootRun

./gradlew dependencies
./gradlew dependencyInsight --dependency nombre

./gradlew test
./gradlew dependencyInsight --dependency nombre
./gradlew javadoc


docker build -t stockqueryservice .
docker run -d -p 8003:8003 --name stockqueryservice stockqueryservice




spring.datasource.url=jdbc:sqlite:stock.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.datasource.username= # No es necesario para SQLite
spring.datasource.password= # No es necesario para SQLite
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect