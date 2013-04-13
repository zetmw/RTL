#!/bin/bash

RTL_FOLD=$PWD
USER=$USERNAME

echo -e '*** Please Enter admin password:'
read PASS

chown $USER -Rh $RTL_FOLD
chmod +x -vR $RTL_FOLD

echo -e '*** RTL requires Java 1.7 as default.'
echo -e '* you can change version by typing "update-alternatives --config java"'
