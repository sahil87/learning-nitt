<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Registration form to create a registration form</title>
</head>

<script language="javascript">
function pre_change(i)
{
//	document.getElementById("prechange"+i).
}
function instruct(sel)
{
//Gives instruction on how to give options for radios
	if(sel.options[4].selected)
		alert("Please enter the Field Name of the Radio button as Name|option1|option2|option3... ");
	else if(sel.options[5].selected)
		alert("Please enter the Field Name of the Drop down list as Name|option1|option2|option3... ");
}
function unhidenext(cur_row)
{
//This function unhides the next row.
	n=cur_row.name.replace("fldname","");
	n=eval(n+"+1");
	document.getElementById(n).style.display="";
}
function disable(i)
{
	//disables the full row shows a select option 
	if(document.getElementById("pre"+i).checked==false)
	{
		document.getElementById("fldname"+i).style.display="";
		document.getElementById("label"+i).disabled=false;
		document.getElementById("fldtype"+i).disabled=false;
		document.getElementById("comments"+i).disabled=false;
		document.getElementById("default"+i).disabled=false;
		document.getElementById("isessen"+i).disabled=false;
		document.getElementById("preopt"+i).style.display="none";
	}
	else
	{
		document.getElementById("fldname"+i).style.display="none";
		document.getElementById("label"+i).disabled=true;
		document.getElementById("fldtype"+i).disabled=true;
		document.getElementById("comments"+i).disabled=true;
		document.getElementById("default"+i).disabled=true;
		document.getElementById("isessen"+i).disabled=true;
		document.getElementById("preopt"+i).style.display="";
	}
	document.getElementById(i+1).style.display="";
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

<HR>
<BR>Please select the predefined fields you wish to use :


<?php 
$prefields=Array('name'=>'Name:','roll'=>'Roll no:','age'=>'Age:','dob'=>'Date of birth:','address'=>'Address','mail'=>'Email');
$temp="<TABLE width=30%>";
foreach($prefields as $key=>$value)/*checkboxes for predefined fields*/
     $temp.="\n\t<TR>	<TD width=60%></TD>	<TD><LABEL for='".$key."_id'><B>".$value."</B></LABEL></TD>
		        <TD><INPUT type='checkbox' name='pre_".$key."' id='".$key."_id'></TD></TR>";
$temp.="</TABLE>";
echo $temp;
?>

<BR><BR>Enter your user-defined fields here-
<table>


<?php
$fieldinfos=Array('fldname'=>'Field Name','label'=>'Label'      ,'fldtype'=>'Field Type',
		  'comments'=>'Comments' ,'defaults'=>'Defaults','isessen'=>'Is it essential?');

$temp="<tr><th></th>\n";
foreach($fieldinfos as $key => $value)
{
  $temp.="<th>".$value."</th>";
}
$temp.="\n</tr>";
echo $temp;


##################For displaying the form--#########################
/*
	To do:
	Expiry Date & time
	use str_replace in place of parseString
	Length of Fields
        display alert only when he already hasnt enterd a | in the name field while selecting select of drop down list type
	predefined field college which adds to itself new colleges
*/

function parseString($str) //converts a b c to a_b_c
{	
  for($i=0;$i<strlen($str);$i++)
    if($str[$i]==' ')
      $str[$i]='_';
  //echo "<br>Parsed str=".$str;
  return($str);
}

/*The 30(max) user defined fields--*/
for($i=0;$i<30;$i++)
{
	if($i==0)
		$str="\n<tr id='".$i."'>";
	else
		$str="\n<tr style=\" display:none;\" id='".$i."'>";
	
	$str.="\n\t<td><input type='checkbox' name='pre".$i."' id='pre".$i."' onClick='disable(".$i.")'>
	<td><select id='preopt".$i."' name='preopt".$i."' style=\" display:none;\" onChange='prechange(".$i.")' >
	<option value='pre_name'>Name</option>
	<option value='pre_roll'>Roll No</option>
	<option value='pre_age'>Age</option>
	<option value='pre_dob'>Date of Birth</option>
	<option value='pre_address'>Address</option>
	<option value='pre_mail'>E-mail</option>
	</select><input type='text' name='fldname".$i."' id='fldname".$i."' onChange='unhidenext(this);'></td>
	<td><input type='text' name='label".$i."' id='label".$i."'></td>
	<td><select name='fldtype".$i."' id='fldtype".$i."' onChange='instruct(this);'>
		<option value=text>Text</option>
		<option value=textarea>Text Area</option>
		<option value=password>Password</option>
		<option value=checkbox>Check Box</option>
		<option value=radio >Radio button</option>
		<option value=select>Drop down list</option>
		<option value=file>File upload</option>
	</select></td>
	<td><input type='text' name='comments".	$i."' id='comments".	$i."'></td>
	<td><input type='text' name='default".	$i."' id='default".	$i."'></td>
	<td><input type=checkbox name='isessen".$i."' id='isessen".$i."'></td>
</tr>";
	echo $str;
}
?>
</table>
<input type="submit" name="submit" value="submit" >
</form>


<?php
###################Code for submission--########################
if($GLOBALS['_POST']['submit'])
{
	$db=mysql_connect("178.1.3.202","pragyan","weshallrule");
	mysql_select_db("pragyan_cms",$db);
	
/*	if($GLOBALS['_POST']['login']=='on')
		$i=1;
	else
		$i=0;*/	

/*To create an entry in pragyan_reg_tables--*/
	$sql="INSERT INTO `pragyan_reg_tables` ( `regtable_name` , `regtable_desc` , `create_login` , `inherit_from` ) 
	VALUES ('".$GLOBALS['_POST']['name']."', '".$GLOBALS['_POST']['desc']."','".$i."', '".$GLOBALS['_POST']['inherit']."')";
	$res=mysql_query($sql,$db);
	$sql="select MAX(regtable_id) from pragyan_reg_tables";
	$res=mysql_query($sql,$db);
	$result=mysql_fetch_array($res);
	$id=$result[0];
	
/*Table creation--*/
	$sql="CREATE TABLE `pragyan_reg_".$id."` (  `groupmemid` int NOT NULL ";
	$i=0;
	$pre_type=array('pre_name'=>'varchar(3)',
	'pre_roll'=>'varchar(10)','pre_age'=>'tinyint','pre_address'=>'text','pre_dob'=>'date',
	'pre_mail'=>'varchar(30)');
   	while(  ($GLOBALS['_POST']['fldname'.$i] != '')||($GLOBALS['_POST']['pre'.$i] != ''))
	{	
	    if($GLOBALS['_POST']['fldname'.$i] != '')
		{
			$a=explode("|",$GLOBALS['_POST']['fldname'.$i]);
			$sql.=", `".$a[0]."` varchar(30) ";
			$i++;
		}
		else
		{
			$a=$GLOBALS['_POST']['preopt'.$i];
			$sql.=(" , `".$a."` ".$pre_type[$a]);	
			$i++;
			
		}
	}
	/*accounting for predefined fields pre_name, pre_roll, pre_dob, pre_address, pre_sex, pre_email--*/
	/*these predefined fields will be left empty(their data will be in the form parser and not in form/table creator)
	if(isset($GLOBALS['_POST']['pre_name']))	{$sql.=(" , `pre_name` varchar(30)");	}
	if(isset($GLOBALS['_POST']['pre_roll']))	{$sql.=(" , `pre_roll` varchar(10)");	}
	if(isset($GLOBALS['_POST']['pre_age']))		{$sql.=(" , `pre_age` tinyint"); 	}
	if(isset($GLOBALS['_POST']['pre_address']))	{$sql.=(" , `pre_address` text"); 	}
	if(isset($GLOBALS['_POST']['pre_dob'])) 	{$sql.=(" , `pre_dob` date");    	}
	if(isset($GLOBALS['_POST']['pre_mail'])) 	{$sql.=(" , `pre_mail` varchar(30)"); 	}
	*/
	$sql.=", PRIMARY KEY ( `groupmemid` ) )";
//	mysql_error();
//	echo $sql;
	@ mysql_query($sql) or die("Error : ".mysql_error());
//	echo "sdfgdfG".;

/*Insertion of the values--*/
	$j=0;
	$sqlfields="";
	foreach($fieldinfos as $key => $values)
	{	
	  $sql="";
	  $sqlvalues="";
	  $j--;
	  $sql="INSERT INTO `pragyan_reg_".$id."` (";
	  $i=0;
	  while(  $GLOBALS['_POST']['fldname'.$i] != '')
	    {	
	      if($key=="fldname")
		{	      
		  if(strstr($GLOBALS['_POST']['fldname'.$i],"|"))/*to process the name and type fields in case of | */
		    {	
		      $a=explode("|",$GLOBALS['_POST']['fldname'.$i]);
		      $GLOBALS['_POST']['fldtype'.$i].=substr($GLOBALS['_POST']['fldname'.$i],strlen($a[0]));
		      $GLOBALS['_POST']['fldname'.$i]=substr($GLOBALS['_POST']['fldname'.$i],0,strlen($a[0]));
		    }			 
		  $sqlfields.=", `".parseString($GLOBALS['_POST']['fldname'.$i])."`";
		  $sqlvalues.=", '".parseString($GLOBALS['_POST']['fldname'.$i])."'";
		}
	      else
		$sqlvalues.=", '".$GLOBALS['_POST'][$key.$i].	"'";
	      $i++;
	    }
	  $sql=$sql."`groupmemid`".$sqlfields.") VALUES ('".$j."'".$sqlvalues.")";
	//  echo $sql;
	  mysql_query($sql);
	}


	mysql_close($db);
	echo "Your form was created successfully. Your form id is ".$id;
	}
?>

</body>
</html>
