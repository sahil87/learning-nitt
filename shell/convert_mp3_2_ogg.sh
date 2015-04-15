echo "Converts all mp3's to ogg's in a directory for N-gage"
echo "Output files - 16000hz sampling rate, Avg bitrate 64kbps with VBR"
echo "Remove original files? (y/n/q)"
read a;
if [ $a = "y" -o $a = "n" ]
then

for i in *.mp3
do
   lame --decode "$i"
   oggenc -b 64 --resample 16000 --downmix "$i".wav -o "`echo $i | cut -f 1 -d .`.ogg"
   if [ $a = "y" ] 
   then
     rm "$i"
   fi
   rm "$i".wav
done

fi
