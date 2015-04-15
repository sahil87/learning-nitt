<html>
<head>
<? 

#TODO -- put a button to show all rows AND add javascript to hide the row immedately on uchecking
#the checkbox

$db_link=0;
include "theme1.css";
//echo "<LINK REL=STYLESHEET TYPE='text/css' href='theme1.css'>";
//include "theme1.php";
include "init.php";
include "functions.php";
ds_connect();
$comp_name = get_table_data("COMP_INFO");
?>
<script type="text/javascript" language=javascript>
function update_scroll()
	{
	document.formname.yscroll.value=document.body.scrollTop;
	}
</script>

<?
echo "<body onLoad=document.body.scrollTop='".$_POST['yscroll']."' onUnload=return update_scroll();>";
?>
<form name="formname" method="post" action="<? print $_SERVER['PHP_SELF'];?>">
<input type="hidden" name=yscroll value="0">
</form>

<?php
echo "<table border='0'>";
echo "<TR><TH>Keep Watch</TH><TH>Code</TH><TH>% change</TH>";
echo "<TH>Current Price</TH><TH>Buying Rate</TH><TH>Selling Rate</TH></TR>";

/////have to change this
$user_id=1002;

$result=mysql_query("SELECT COMP_WATCH FROM USER_PREF WHERE `ID`=".$user_id);
$comps_to_watch=mysql_fetch_assoc($result);
//var_dump($comps_to_watch);

while( $a = mysql_fetch_array( $comp_name ))
{

echo "\n\n\n\n<tr id=\"c".$a['COMP_CODE']."\"";
if(!stristr($comps_to_watch['COMP_WATCH'],$a['COMP_CODE']))
	echo ' style="display: none;"';
echo "><td align='center'>";
echo "<INPUT type='checkbox'";
if(stristr($comps_to_watch['COMP_WATCH'],$a['COMP_CODE']))
	echo " checked";
echo "></td>";
echo "\n<td><a href = 'compinfo.php?cmp=".$a['ABBR']."'>".$a['ABBR'];
echo "</a></td>";

$stocks=get_table_data("STOCKS","WHERE COMPANY='".$a['ABBR']."'") or die("Could Not Select");
$stocks_array=mysql_fetch_array($stocks) or die("Could Not Get Array");
$cur=$stocks_array['CUR_RATE'];
$yes=$stocks_array['YES_CLOSE'];
$change=round(($cur-$yes)/$yes * 100,2);
echo "<td>".$change."</td>";
echo "<td>".$cur."</td>";

$bids=get_table_data("BIDS","WHERE COMP_CODE=".$a['COMP_CODE']." AND BUY_SELL=1 ORDER BY RATE DESC") or die("Could Not Select Bids Table");
$bids_array=mysql_fetch_array($bids);
if($bids_array)
echo "<td>".$bids_array['RATE']."</td>";
else
echo "<td align=center>--</td>";

$bids=get_table_data("BIDS","WHERE COMP_CODE=".$a['COMP_CODE']." AND BUY_SELL=0 ORDER BY RATE ASC") or die("Could Not Select Bids Table");
$bids_array=mysql_fetch_array($bids);
if($bids_array)
	echo "<td>".$bids_array['RATE']."</td>";
else
	echo "<td align=center>--</td>";
echo "</tr>";

}

?>
</table></html>