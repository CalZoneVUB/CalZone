#
# Shell script for deploying website to wilma server.
# Important: this shell schript should be executed in the directory 
# where the pom.xml file is located.
#
# This shell script requires the installation of 'sshpass'.
# For linux: 'sudo apt-get install sshpass'
# For Mac: 'sudo port install sshpass'
#
# Author: pieter meiresone
echo "Building war file..."
mvn compile
mvn package

echo "Copying war file to wilma..."
cd target
sshpass -p 'Bean59Cabal' scp calzone.war se2_1314@wilma.vub.ac.be:~/apache-tomcat-7.0.47/webapps

echo "Restarting tomcat at wilma..."
sshpass -p 'Bean59Cabal' ssh se2_1314@wilma.vub.ac.be "export JAVA_HOME=/usr/lib64/java && cd apache-tomcat-7.0.47/bin && pwd && ./shutdown.sh && ./startup.sh"

echo "Done."