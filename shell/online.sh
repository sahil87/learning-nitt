#!/bin/bash
$i = `/usr/bin/nmap -p 21,80 10.1.0-125.1-2 10.1.0-125.11 10.1.0-125.22`
for($j in $i)