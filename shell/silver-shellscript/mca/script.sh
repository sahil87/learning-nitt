#! /bin/bash

count=0
#for i in `cat ptest | cut -d" " -f2`
for i in `cat mcapwd`
do
if [ $count -eq 0 ]
then
echo $i
echo $i
filename=s$i
((count=$count+1))
else
echo $i > $filename
echo $i >> $filename
((count=0))
fi
done
