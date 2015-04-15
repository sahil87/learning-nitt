<HTML>
<HEAD><TITLE>Global Variables in PHP</TITLE></HEAD>
<BODY>
<FONT SIZE=-1>
<PRE>
<?php

//print "<P>{$PHP_SELF}</P>\n";

 //print_r($GLOBALS);

foreach($GLOBALS as $key1=>$value1)
{

  print "\n\$GLOBALS['$key1']==".$value1;
  //  echo gettype($value1);
  if (gettype($value1)=='array'&&isset($notfirst))
    
    foreach($value1 as $key2=>$value2)
      
      print "\n\$GLOBALS['$key1']['$key2']==".$value1[$key2];
  $notfirst=1;  #if this condition not put the loop stops in $GLOBALS['GLOBALS] array itself - doesnt go forward
#TODO - How to test whether an array is recurssive or not? 
#Here if we go in $GLOBALS['GLOBALS'] the foreach of the $GLOBALS starts acting up
}

/*foreach($GLOBALS["_SERVER"] as $key=>$value)
 {
  print "\$GLOBALS[\"_SERVER\"][\"$key\"]==$value<BR>";
 }*/
//if(isset($GLOBALS['_FILES'])) echo "yes";
echo "\nName of this file={$GLOBALS['_SERVER']['PHP_SELF']}";
?>
</PRE>
</FONT>
</BODY>
</HTML>