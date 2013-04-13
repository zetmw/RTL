#!/bin/bash

case $2 in 
	1 )	nmap -sS -P0 -O $1
		;;
	0 )	echo $3 | sudo -S nmap -sS -P0 -O $1
		;;
	-1 )	nmap -sT -sV $1
		;;
esac

