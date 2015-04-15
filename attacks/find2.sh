if [ `ping -c 1 -w .2 $1 | grep -c from` -eq 1 ]
then
echo $1
if [ `nmap -sT -p 21 -P0 -n $1 | grep -c open` -eq 1 ]
then
echo "FTP"
fi
if [ `nmap -sT -p 80 -P0 -n $1 | grep -c open` -eq 1 ]
then
echo "HTTP"
fi
fi
