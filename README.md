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
docker run -d -p 8080:8080 --name stockqueryservice stockqueryservice