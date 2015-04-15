i=0
while [ $i -lt 80 ]
do
	i = `echo "($i + 1)"|bc`
	echo $i
done
