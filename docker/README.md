# Docker setup for windows

## Install WSL2

+ Installation instructions can be found here: [Microsoft install wsl2](https://learn.microsoft.com/en-us/windows/wsl/install)
+ Few tips and tricks: [Tips for wsl](https://learn.microsoft.com/en-us/windows/wsl/setup/environment#set-up-your-linux-username-and-password)

## Install Linux Distro

+ [Debian](https://apps.microsoft.com/detail/9MSVKQC78PK6?hl=en-us&gl=US&ocid=pdpshare)

## Install Docker engine inside of wsl2

+ Open linux through windows terminal and follow install guide for [debian](https://docs.docker.com/engine/install/debian/)

If you use a different linux distro, follow the docker install setup for that distro instead

## Start service

`sudo service docker start`

## Run test scripts

Run tests and leave SemossWeb container running.

`./testNRM.sh`

Run tests and remove container

`./tester.sh`

## Results and videos

Test results and output can be found in target/surefire-reports in the e2e directory

Videos can be found in videos/<timestamp>/testclass/testname

** Previous videos get deleted before each run, save outside of videos directory if you wish to retain a video. **
