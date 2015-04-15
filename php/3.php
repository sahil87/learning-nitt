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
if(isset($expression)) echo ('How are you sweety?');
fclose($fp);
//print "<P>{$PHP_SELF}</P>\n";
foreach($GLOBALS as $key=>$value)
{
  print "\$GLOBALS[\"$key\"]==$value<BR>";
  }
if(isset($GLOBALS[_FILES])) echo "yes";
echo "{$GLOBALS["PHP_SELF"]}";
?>
</BODY>
</HTML>