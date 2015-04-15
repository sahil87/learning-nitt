<!-- To recursively compare a given number using PHP -->
<?php
if(!isset($GLOBALS["_POST"]["guess"]))
echo "how are you?";
 else
   { $diff=abs(40-$GLOBALS["_POST"]["guess"]);
    echo "<P>The difference between the secret no and your number is $diff</P>";
    
  }
?>
<HTML>
<HEAD><TITLE>Number Compare</TITLE></HEAD>
<BODY>
<FORM action="<?php print $GLOBALS["_SERVER"]["PHP_SELF"]?>" method="POST">
<INPUT type="TEXT" name="guess">
<BR><INPUT type="submit">
</FORM>
</BODY>
</HTML>