<HTML>
<HEAD><TITLE>Interaction with files in PHP</TITLE></HEAD>
<BODY>
<?php
$fp=fopen("text1.txt","w") or die("Couldn't open text.txt");
if($expression)
  {?>
   <STRONG>This is strong text</STRONG>
   <?php
      }
 else
   {?>
    <P>This is a para.</P>
    <?php
       }
echo ('How are you sweety?');
fclose($fp);
?>
</BODY>
</HTML>