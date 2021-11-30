# DB-final-project

### Navigate to the env_setup folder 
* Download Java SDK 17 Debian here 
https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.deb

* Run this command to download the necessary packages for installing and configuring jdk-17
'''
sudo apt install libc6-i386 libc6-x32 curl -y
'''

* Run this command in the same directory as .deb file (in env_setup)
'''
sudo dpkg -i jdk-17_linux-x64_bin.deb 
'''

* Then run these commands to properly setup compilation and run paths (I think)
'''
sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/jdk-17/bin/java 1
'''

'''
sudo update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/jdk-17/bin/javac 1
'''

''' 
sudo gedit /etc/environment
'''

* Once the environment file is open copy and paste the below to a newline at the end of the file
'''
JAVA_HOME="/usr/lib/jvm/jdk-17/bin/java" save file 
'''

* Then do the following command
'''
source /etc/environment
'''

* Verify with 
'''
echo $JAVA_HOME
'''

** Working on this 
//to run sample code
//  Enter working directory with JDBC driver (should have moved it to working directory) and run 
javac exec.java //get exec.class  name of FILE and name of Main class must be the same  JAVA rule
java -cp ".;mysql-connector-java-8.0.16.jar" YourPackage.YourMainClass