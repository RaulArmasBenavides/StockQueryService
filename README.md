VSCODE

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