#/bin/bash
echo "Proszę pamiętać o dokonaniu należytych zmian w application.properties !!"
./gradlew build
java -jar ./build/libs/city-parking-spaces-0.0.1-SNAPSHOT.jar