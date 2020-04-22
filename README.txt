The Source code is in src directory(./sar/src)! To complete the compiling, building and testing this project, the steps below need to be followed!

Step1:
we need to set up the gradle. Here are the command lines:
    $ sudo mkdir /opt/gradle
    $ cd ~/Downloads
    $ wget https://services.gradle.org/distributions/gradle-6.3-bin.zip
    $ sudo unzip -d /opt/gradle gradle-6.3-bin.zip
    $ sudo echo "export GRADLE_HOME=/opt/gradle/gradle-6.3" >> /etc/profile.d/gradle.sh
    $ sudo echo "export PATH=\${GRADLE_HOME}/bin:\${PATH}" >> /etc/profile.d/gradle.sh
    $ source /etc/profile.d/gradle.sh
    $ gradle --version

Step2:
we need to cd to the path of sar directory and then use gradle to build it. Here are the command lines:
    $ cd <path_to_Ning-Zhang-PR>
    $ cd sar
    $ gradle wrapper
    $ ./gradlew build

you will find the war file in ./build/libs.
Also check if the unit tests are running well, you need to go to reports directory. Here are the command lines:
    $ ls -l ./build/reports/tests/test/
you will find index.html and click it to see the unit tests results.
 
Step3:
Deploy the war file (sar.war) to the tomcat server. Firstly you need to get the tomcat code. Here are the commands:
  $ cd ~/Downloads
  $ wget http://apache.mirrors.hoobly.com/tomcat/tomcat-8/v8.5.53/bin/apache-tomcat-8.5.53.tar.gz
  $ gunzip apache-tomcat-8.5.53.tar.gz
  $ tar xvf apache-tomcat-8.5.53.tar
  $ sudo mv apache-tomcat-8.5.53 /opt/tomcat

Step4:
Then copy the sar.war to tomcat web app directory and start the server. Here are the commands:
  $ cd /opt/tomcat/
  $ cp <path_to_Ning-Zhang-PR>/sar/build/libs/sar.war ./webapps
  $ cd ./bin
  $ ./startup.sh
Finally, the sever is started up and you can test the project by postman.