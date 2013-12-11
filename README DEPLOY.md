CalZone
=======
DEPLOYMENT TO WILMA

STEP 1: Make .war file of the project
  > Open terminal 
  > Go to folder containing pom.xml
  > mvn compile
  > mvn package

STEP 2: Send .war to wilma
  > Open terminal
  > Go to /calzone/targets
  > scp calzone.war se2_1314@wilma.vub.ac.be:~/apache-tomcat-7.0.47/webapps
  > enter password
  
STEP 3: SSH into wilma and restart
  > ssh se2_1314@wilma.vub.ac.be
  > goto apache-tomcat-7.0.47/bin
  > ./shutdown.sh
  > ./startup.sh
  
STEP 4: Check runningin browser
  > http://wilma.vub.ac.be:8181/calzone   
  > !! Port 8181
  > !! No https availible 'yet' ''maybe''
