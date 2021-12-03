# DB-final-project

### Setting up JAVA (If not configured already)
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

## Set correct permissions for MariaDB

* In your terminal 

'''
sudo mysql -u root -p
'''

* In the MariaDB mysql shell
'''
use mysql;
update user set plugin='' where User='root';
flush privileges;
exit;
'''

* From now on, to access mysql all you need to type in terminal is (no password required).

'''
mysql -u root
'''

* Now you can go back to the provided sample code and run it. The port, username, and password are all set or default. 

## Running for your DB

* The properties folder will hold you username, password, and port number for your specific database, Change those values and run once db is set up. 

### Contact Us
(If something doesn't function)
- Christopher Casey \ czc0186@auburn.edu 
- Bhargav Joshi \bvj0002@auburn.edu
