<HTML>
<?php
##TODO-- add provision for radio buttons, select, expiry date of the form, change it to absolute path

$file_dir = "//var/www/html/cms/";

if(!(isset($GLOBALS['_GET']['form_id']) OR isset($GLOBALS['_POST']['submit'])))
{	echo("<B>Please enter the name of the form with the form id in the following format -</B>");
	echo("<BR><code><I>name of the form</I>.lib.php?form_id=<I>form id number</I></code>");
	exit();
}
/** $form_id(regtable_id) is a gloabal variable which define which table has to be selected
	Add javascript validation**/
if(isset($GLOBALS['_GET']['form_id']))
	$form_id=$GLOBALS['_GET']['form_id'];
else
	$form_id=$GLOBALS['_POST']['form_id'];

$db=mysql_connect("178.1.3.202","pragyan","weshallrule");
mysql_select_db("pragyan_cms",$db);

//To find the name of the table--
$querystring="SELECT * FROM pragyan_reg_tables WHERE `regtable_id`=".$form_id;
//echo $querystring;
$result=mysql_query($querystring);
	if(!$result)
	{	
		echo "Couldn't query the table";
		print_r($error);
	}
else
{	$a_row=mysql_fetch_assoc($result);
	$reg_form_name=$a_row['regtable_name'];
	$reg_form_desc=$a_row['regtable_desc'];
}

//functions--
function unparseString($str) //converts a_b_c to a b c
{
	for($i=0;$i<strlen($str);$i++)
		if($str[$i]=='_')
			$str[$i]=' ';
	//echo "<br>Parsed str=".$str;
	return($str);
}



###############The code for submission (after the values are POSTed)###################
	if(isset($GLOBALS['_POST']['submit']))
	{
		//To find the maximum groupmemid that is the primary key
		$a=mysql_fetch_assoc(mysql_query("SELECT MAX(groupmemid) from pragyan_reg_".$form_id));
		$GLOBALS['_POST']['nm_groupmemid']=$a['MAX(groupmemid)'];
		$GLOBALS['_POST']['nm_groupmemid']++;
		
		unset($i);
		$querystring="INSERT INTO `pragyan_reg_".$GLOBALS['_POST']['form_id']."` (";
		foreach($GLOBALS['_POST'] as $key=>$value)
		{	if(strstr($key,"nm_"))
			{	if(isset($i)) $querystring.=",";
				$key_parsed=substr($key,(strlen("nm_")));
				$querystring.="`".unparseString($key_parsed)."`";
				$i=0;
			}
		}
//for file type- (file types passed in FILES not POST)		
		foreach($GLOBALS['_FILES'] as $key=>$values)
		{	if(strstr($key,"nm_"))
			{	if(isset($i)) $querystring.=",";
				$key_parsed=substr($key,(strlen("nm_")));
				$querystring.="`".unparseString($key_parsed)."`";
				$i=0;
			}
		}
		
		unset($i);
		$querystring.=") VALUES (";
		foreach($GLOBALS['_POST'] as $key=>$value)
		{	if(strstr($key,"nm_"))
			{
				if(isset($i)) $querystring.=",";
				$key_parsed=substr($key,(strlen("nm_")));
				$type_var_name="typ_".$key_parsed;
				if($GLOBALS['_POST'][$type_var_name]=="password")
					$querystring.="MD5('".$value."')";
				else
					$querystring.="'".$value."'";
				$i=0;
			}
		}
		foreach($GLOBALS['_FILES'] as $key=>$values)
		{	if(strstr($key,"nm_"))
			{	if(isset($i)) $querystring.=",";
				$address_parsed=$reg_form_name."/".$GLOBALS['_POST']['nm_groupmemid']."/".$GLOBALS['_FILES'][$key]['name'];
				$querystring.="'".$address_parsed."'";
				$i=0;
			}
		}
		
		$querystring.=");";
		//the files arent passed in $GLOBALS['POST'] they are in $GLOBAL[$FILES]//see the php manual
		//so the nm_name doesnt exist for the file field(in 'POST'...  its in 'FILES')
		
//file uploading procedure-
		foreach($GLOBALS['_FILES'] as $key=>$value)
		{	if(strstr($key,"nm_"))
			{	if(is_uploaded_file($GLOBALS['_FILES'][$key]['tmp_name']))
				{	if($value['size']>$GLOBALS['_POST']['MAX_FILE_SIZE'])
						echo("File size too large");
					else
					{	if(!file_exists($file_dir."uploads/".$reg_form_name."/"))
							mkdir( 	$file_dir."uploads/".$reg_form_name, 0777 );
						if(!file_exists($file_dir."uploads/".$reg_form_name."/".$GLOBALS['_POST']['nm_groupmemid']))
							mkdir( 	$file_dir."uploads/".$reg_form_name."/".$GLOBALS['_POST']['nm_groupmemid'], 0777 );
						copy ( $value['tmp_name'], $file_dir."uploads/".$reg_form_name."/".$GLOBALS['_POST']['nm_groupmemid']."/".$value['name']) or die ("Couldn't copy");
					}
				}
			}
		}
	//phpinfo();
	//print_r($GLOBALS['_POST']);
	//echo $querystring;
	$result=mysql_query($querystring,$db);
	if($result)
		echo ("Your query has been submitted");
	else
		echo ("There was an error in the query");
	mysql_close($db);
	exit();
	}
	
	
#######################The registration table####################################
	//To find the maximum groupmemid that is the primary key
	$a=mysql_fetch_assoc(mysql_query("SELECT MAX(groupmemid) from pragyan_reg_".$form_id));
	$max_groupmemid=$a['MAX(groupmemid)'];
	//we ll increment while submission to solve the problem of two registrants registering at the time
		
	//To find the fields and their description--
	$querystring="SELECT * FROM pragyan_reg_".$form_id." WHERE groupmemid<0 ORDER BY groupmemid DESC";
	$result=mysql_query($querystring);	
	if(!$result)
		{	echo "Couldn't query the table";
			echo "hi";
			print_r($error);
		}
	else
	{
		$temp1="<HEAD><TITLE>$reg_form_name</TITLE>";
		$temp="</HEAD>";
		$temp.="\n<BODY><FORM enctype=\"multipart/form-data\" action=".$GLOBALS['_SERVER']['PHP_SELF']." name='frm' method='POST'>";
		$temp.="\n\t<INPUT type='hidden' name='nm_groupmemid' value=".$max_groupmemid.">";
		$temp.="\n\t<INPUT type='hidden' name='form_id' value=".$form_id.">";
		$temp.="\n<STRONG><U><CENTER>".$reg_form_name."</CENTER></U></STRONG>";
		$temp.="\n<TABLE width='70%' align='center'><TR><TD align=left>".$reg_form_desc."</TD></TR>";
		$temp.="\n\n<TR><TD><TABLE width=100% border>";
		for($i=1;$a=mysql_fetch_row($result);$i++)
		{	$nameofarray="a_row".$i;
			$$nameofarray=$a;
		}
		$java="<script language=\"javascript\">
		function validate()
		{ 
			var f=document.frm;
			";
		for($x=1;$x<=mysql_num_fields($result)-1;$x++)
		{	$temp.="\n\t<TR><TD width=40%>".$a_row2[$x].": </TD>";
			if($a_row6[$x]=="on")
			{	$java.="\n if(f.nm_".$a_row1[$x].".value==\"\")
					{
						alert('Please enter the ".unparseString($a_row1[$x])."!');
						f.nm_".$a_row1[$x].".focus();
						return(false);
					}
				";
			}
			
			//textarea
			if($a_row3[$x]=="textarea")
			{	$type=$a_row3[$x];
				$temp.="\n\t\t<TD><TEXTAREA name='nm_".$a_row1[$x]."'>".$a_row5[$x]."</TEXTAREA>";
			}
			//radio button
			elseif(($type=strtok($a_row3[$x],"|"))=="radio")
			{	$temp.="<TD>";
				while($a=strtok("|"))
				{	$temp.="\n\t\t<INPUT TYPE='radio' NAME='nm_".$a_row1[$x]."' ID='".$a."' VALUE='".$a."'>";
					$temp.="<LABEL FOR='".$a."'>".$a."</LABEL><BR>";
				}
			}
			//select options
			elseif(($type=strtok($a_row3[$x],"|"))=="select")
			{	$temp.="\n\t\t<TD><SELECT NAME='nm_".$a_row1[$x]."'>";
				while($a=strtok("|"))
					$temp.="\n\t\t\t<OPTION>".$a;
			}
			//file upload
			elseif($a_row3[$x]=="file")
			{	$type=$a_row3[$x];
				$temp.='<input type="hidden" name="MAX_FILE_SIZE" value="20971520">';
				$temp.="\n\t\t<TD><INPUT type='".$a_row3[$x]."' name='nm_".$a_row1[$x]."'>";
			}
			else
			//text field -- default
			{	$type=$a_row3[$x];
				$temp.="\n\t\t<TD><INPUT type='".$a_row3[$x]."' name='nm_".$a_row1[$x]."'>";
			}
			
			//to pass the types also on submit - 
			$temp.="\n\t\t<INPUT type='hidden' name='typ_".$a_row1[$x]."' value='".$type."'></TD></TR>";
		}		
		$java.="}</script>";
		$temp.="\n</TABLE></TD></TR></TABLE>";
		$temp.="\n<BR><CENTER><INPUT type='submit' name='submit' onClick='return validate();' value='Submit Form'></CENTER>";
		$temp.="\n</FORM></BODY>";
		
		$temp=($temp1.$java.$temp);
		echo $temp;
	}
?>
</HTML>
