<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Registration form to create a registration form</title>
</head>

<script language="javascript">
function instruct(sel)
{
//Gives instruction on how to give options for radios
	if(sel.options[4].selected)
		alert("Please enter the Field Name of the Radio button as name|option1|option2|option3... ");
	else if(sel.options[5].selected)
		alert("Please enter the Field Name of the Drop down list as name|option1|option2|option3... ");
}
function unhidenext(cur_row)
{
//This function unhides the next row.
	n=cur_row.name.replace("fldname","");
	n=eval(n+"+1");
	document.getElementById(n).style.display="";
}
</script>

<body>
<form name="frm" action="<?php echo $GLOBALS['_SERVER']['PHP_SELF'] ?>" method="post">
<table>
<tr><td>Enter Registration Form Name:</td><td><input id="nameid" type="text" name="name"></td></tr>
<tr><td>Description:</td><td><input type="text" name="desc"></td></tr>
<tr><td>Do you want login:</td><td><input type="checkbox" name="login"></td></tr>
<tr><td>Inherit From:</td><td><input type="text" name="inherit"></td></tr>
</table>
<table>

<tr>
<th>Field Name</th><th>Label</th><th>Field Type</th><th>Comments</th><th>Default</th><th>Is it essential?</th>
</tr>
<?php

##############For displaying the form--#########################
/*
	To do:
	Predefined Fields
	Radio & Select options
	Expiry Date & time
	Length of Fields
*/

function parseString($str) //converts a b c to a_b_c
{
	for($i=0;$i<strlen($str);$i++)
		if($str[$i]==' ')
			$str[$i]='_';
	//echo "<br>Parsed str=".$str;
	return($str);
}

for($i=0;$i<30;$i++)
{//style=\" display:none;\"
	if($i==0)
		$str="<tr id='".$i."'>";
	else
		$str="\n<tr style=\" display:none;\" id='".$i."'>";
	
	$str.="\n\t<td><input type='text' name='fldname".$i."' onChange='unhidenext(this);'></td>
	<td><input type='text' name='label".$i."'></td>
	<td><select name='fldtype".$i."' onChange='instruct(this);'>
		<option value=text>Text</option>
		<option value=textarea>Text Area</option>
		<option value=password>Password</option>
		<option value=checkbox>Check Box</option>
		<option value=radio >Radio button</option>
		<option value=select>Drop down list</option>
	</select></td>
	<td><input type='text' name='comments".$i."'></td>
	<td><input type='text' name='default".$i."'></td>
	<td><input type=checkbox name='isessen".$i."'></td>
</tr>";
	echo $str;
}
?>
</table>
<input type="submit" name="submit" value="submit" >
</form>
<?php

################Code for submission--###################
if($GLOBALS['_POST']['submit'])
{
	$db=mysql_connect("178.1.3.202","pragyan","weshallrule");
	mysql_select_db("pragyan_cms",$db);
	
/*	if($GLOBALS['_POST']['login']=='on')
		$i=1;
	else
		$i=0;*/	

//creates entry in pragyan_reg_tables
	$sql="INSERT INTO `pragyan_reg_tables` ( `regtable_name` , `regtable_desc` , `create_login` , `inherit_from` ) 
	VALUES ('".$GLOBALS['_POST']['name']."', '".$GLOBALS['_POST']['desc']."','".$i."', '".$GLOBALS['_POST']['inherit']."')";// or die("could not");
	$res=mysql_query($sql,$db);
	$sql="select MAX(regtable_id) from pragyan_reg_tables";
	$res=mysql_query($sql,$db);
	$result=mysql_fetch_array($res);
	$id=$result[0];
	
//Table creation
  	$sql="CREATE TABLE `pragyan_reg_".$id."` (  `group_mem_id` int NOT NULL ";
	$i=0;
   	while(  $GLOBALS['_POST']['fldname'.$i] != '')
	{	$a=explode("|",$GLOBALS['_POST']['fldname'.$i]);
		$sql.=", `".$a[0]."` varchar(30) ";
		$i++;
	}
	$sql.=", PRIMARY KEY ( `group_mem_id` ) )";
	//echo $sql;
	mysql_query($sql) or die("Could not create table");


//Insertion of the values
  $sql1=" INSERT INTO `pragyan_reg_".$id."` VALUES (-1";
  $sql2=" INSERT INTO `pragyan_reg_".$id."` VALUES (-2";
  $sql3=" INSERT INTO `pragyan_reg_".$id."` VALUES (-3";
  $sql4=" INSERT INTO `pragyan_reg_".$id."` VALUES (-4";
  $sql5=" INSERT INTO `pragyan_reg_".$id."` VALUES (-5";
  $sql6=" INSERT INTO `pragyan_reg_".$id."` VALUES (-6";
  	$i=0;
  	while(  $GLOBALS['_POST']['fldname'.$i] != '')
	{	if(strstr($GLOBALS['_POST']['fldname'.$i],"|"))
		{	$a=explode("|",$GLOBALS['_POST']['fldname'.$i]);
			strtok($GLOBALS['_POST']['fldname'.$i],"|");
			$GLOBALS['_POST']['fldtype'.$i].=substr($GLOBALS['_POST']['fldname'.$i],strlen($a[0]));
			$GLOBALS['_POST']['fldname'.$i]=substr($GLOBALS['_POST']['fldname'.$i],0,strlen($a[0]));
		}			
		
		$sql1.=", '".parseString($GLOBALS['_POST']['fldname'.$i])."'";
		$sql2.=", '".$GLOBALS['_POST']['label'.$i].	"'";
		$sql3.=", '".$GLOBALS['_POST']['fldtype'.$i].	"'";
		$sql4.=", '".$GLOBALS['_POST']['comments'.$i].	"'";
		$sql5.=", '".$GLOBALS['_POST']['default'.$i].	"'";
		$sql6.=", '".$GLOBALS['_POST']['isessen'.$i].	"'";
		$i++;
	}
  $sql1.=")";
  $sql2.=")";
  $sql3.=")";
  $sql4.=")";
  $sql5.=")";
  $sql6.=")";
     mysql_query($sql1);
     mysql_query($sql2);
     mysql_query($sql3);
     mysql_query($sql4);
     mysql_query($sql5);
     mysql_query($sql6);
  mysql_close($db);
	echo "Your form was created successfully. Your form id is ".$id;
	}
?>

</body>
</html>
