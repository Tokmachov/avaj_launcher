rm -rf tests_out
mkdir tests_out
cp -r src/test/resources tests_out

javac -d tests_out -cp tests_out --source-path "src/test/java:src/main/java" src/test/java/avaj/launcher/airport_simulation/TestMain.java
java -cp "tests_out:tests_out/resources" avaj.launcher.airport_simulation.TestMain