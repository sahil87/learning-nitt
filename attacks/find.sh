i=1
while [ $i -le 125 ]
do
ip=10.1.$i.1
./find2.sh $ip &
ip=10.1.$i.2
./find2.sh $ip &
ip=10.1.$i.11
./find2.sh $ip &
ip=10.1.$i.22
./find2.sh $ip &
i=`expr $i + 1`
done
