<HTML>
<?php
##TODO-- add provision for radio buttons, select, expiry date of the form

function unparseString($str) //converts a_b_c to a b c
{
	for($i=0;$i<strlen($str);$i++)
		if($str[$i]=='_')
			$str[$i]=' ';
	//echo "<br>Parsed str=".$str;
	return($str);
}
	if(!(isset($GLOBALS['_GET']['form_id']) OR isset($GLOBALS['_POST']['submit'])))
	{	echo("<B>Please enter the name of the form with the form id in the following format -</B>");
		echo("<BR><code><I>name of the form</I>.lib.php?form_id=<I>form id number</I></code>");
		exit();
	}
/** $form_id(regtable_id) is a gloabal variable which define which table has to be selected
	Add javascript validation**/

	$form_id=$GLOBALS['_GET']['form_id'];
	
	$db=mysql_connect("178.1.3.202","pragyan","weshallrule");
	mysql_select_db("pragyan_cms",$db);
	
###############The code for submission (after the values are POSTed)###################
	if(isset($GLOBALS['_POST']['submit']))
	{
		unset($i);
		$querystring="INSERT INTO `pragyan_reg_".$GLOBALS['_POST']['form_id']."` (";
		foreach($GLOBALS['_POST'] as $key=>$value)
		{	if(strstr($key,"nm_"))
			{	if(isset($i)) $querystring.=",";
				$key_parsed=substr($key,(strlen("nm_")));
				$querystring.="`".$key_parsed."`";
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
				{	$querystring.="MD5('".$value."')";
				}
				else
					$querystring.="'".$value."'";
				$i=0;
			}
		}
		$querystring.=");";
	
	/*print_r($GLOBALS['_POST']);*/
	/*echo $querystring;*/
	$result=mysql_query($querystring,$db);
	if($result)
		echo ("Your query has been submitted");
	else
		echo ("There was an error in the query");
	mysql_close($db);
	exit();
	}
	
	
	
#######################The registration table####################################
	//To find the name of the table--
	$querystring="SELECT * FROM pragyan_reg_tables WHERE `regtable_id`=".$form_id;
	$result=mysql_query($querystring);
		if(!$result)
		{	echo "Couldn't query the table";
			print_r($error);
		}
	else
	{	$a_row=mysql_fetch_assoc($result);
		$reg_form_name=$a_row['regtable_name'];
		$reg_form_desc=$a_row['regtable_desc'];
	}	
	
	//To find the maximum group_mem_id that is the primary key
	$a=mysql_fetch_assoc(mysql_query("SELECT MAX(group_mem_id) from pragyan_reg_".$form_id));
	$max_group_mem_id=$a['MAX(group_mem_id)'];
	$max_group_mem_id++;
		
	//To find the fields and their description--
	$querystring="SELECT * FROM pragyan_reg_".$form_id." WHERE group_mem_id<0 ORDER BY group_mem_id DESC";
	$result=mysql_query($querystring);	
	if(!$result)
		{	echo "Couldn't query the table";
			print_r($error);
		}
	else
	{
		$temp1="<HEAD><TITLE>$reg_form_name</TITLE>";
		$temp="</HEAD>";
		$temp.="\n<BODY><FORM action=".$GLOBALS['_SERVER']['PHP_SELF']." name='frm' method='POST'>";
		$temp.="\n<TABLE align='center'>";
		$temp.="\n\t<TR><INPUT type='hidden' name='nm_group_mem_id' value=".$max_group_mem_id.">";
		$temp.="\n\t<TR><INPUT type='hidden' name='form_id' value=".$form_id.">";
		$temp.="<TH colspan=2 align=center>".$reg_form_name."</TH></TR>";
		$temp.="\n\t<TR><TD colspan=2 align=center>".$reg_form_desc."</TD></TR>";
		    	
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
		{	$temp.="\n\t<TR><TD>".$a_row2[$x].": </TD>";
//			echo "\n\n6:".$a_row6[$x];		
			if($a_row6[$x]=="on")
			{
		//		echo "Inside if";
				$java.="\n if(f.nm_".$a_row1[$x].".value==\"\")
					{
						alert('Please enter the ".unparseString($a_row1[$x])."!');
						f.nm_".$a_row1[$x].".focus();
						return(false);
					}
				";
			}
			if($a_row3[$x]=="textarea")
			{	$type=$a_row3[$x];
				$temp.="\n\t\t<TD><TEXTAREA name='nm_".$a_row1[$x]."'>".$a_row5[$x]."</TEXTAREA>";
			}
			
			elseif(($type=strtok($a_row3[$x],"|"))=="radio")
			{	$temp.="<TD>";
				while($a=strtok("|"))
				{	$temp.="\n\t\t<INPUT TYPE='radio' NAME='nm_".$a_row1[$x]."' ID='".$a."' VALUE='".$a."'>";
					$temp.="<LABEL FOR='".$a."'>".$a."</LABEL><BR>";
				}
				$temp.="</TD>";
			}
			elseif(($type=strtok($a_row3[$x],"|"))=="select")
			{	$temp.="\n\t\t<TD><SELECT NAME='nm_".$a_row1[$x]."'>";
				while($a=strtok("|"))
					$temp.="\n\t\t\t<OPTION>".$a;
				$temp.="</TD>";
			}			
			else
			{	$type=$a_row3[$x];
				$temp.="\n\t\t<TD><INPUT type='".$a_row3[$x]."' name='nm_".$a_row1[$x]."'>";
			}
			
			//to pass the types also on submit - 
			$temp.="\n\t\t<INPUT TYPE='hidden' name='typ_".$a_row1[$x]."' value='".$type."'></TD></TR>";
		}		
		
		$temp.="\n\t<TR><TD colspan=2 align=center>";
		$temp.="<INPUT type='submit' name='submit' onClick='return validate();' value='Submit Form'>";
		$temp.="</TD></TR>";
		$temp.="\n</TABLE></FORM></BODY>";
		$java.="}</script>";
		$temp=($temp1.$java.$temp);
		echo $temp;
	}
?>
</HTML>
