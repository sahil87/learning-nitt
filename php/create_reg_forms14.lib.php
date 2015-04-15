<HTML>
<?php
##TODO-- add provision for radio buttons, select, expiry date of the form, change it to absolute path

$file_dir = "//var/www/html/cms/";  //the directory for file upload


if(!(isset($GLOBALS['_GET']['form_id']) OR isset($GLOBALS['_POST']['submit'])))
{	echo("<B>Please enter the name of the form with the form id in the following format -</B>");
	echo("<BR><code><I>name of the form</I>.lib.php?form_id=<I>form id number</I></code>");
	exit();
}
/** $form_id(regtable_id) is a gloabal variable which defines which table has to be selected
       add javascript validation**/
if(isset($GLOBALS['_GET']['form_id']))
	$form_id=$GLOBALS['_GET']['form_id'];
else
	$form_id=$GLOBALS['_POST']['form_id'];


/*connecting to database*/
$db=mysql_connect("178.1.3.202","pragyan","weshallrule");
mysql_select_db("pragyan_cms",$db);


/*To find a few properties of the table--*/
$querystring="SELECT * FROM pragyan_reg_tables WHERE `regtable_id`=".$form_id;
//echo $querystring;
$result=mysql_query($querystring);
if(!$result)
{	
  echo "Couldn't query the table";
  print_r($error);
}
else
{	
  $a_row=mysql_fetch_assoc($result);
  $reg_form_name=$a_row['regtable_name'];
  $reg_form_desc=$a_row['regtable_desc'];
}

/*functions--*/
function unparseString($str) /*converts a_b_c to a b c*/
{
  for($i=0;$i<strlen($str);$i++)
    if($str[$i]=='_')
      $str[$i]=' ';
  //echo "<br>Parsed str=".$str;
  return($str);
}
function parseString($str) //converts a_b_c to a b c
{
	for($i=0;$i<strlen($str);$i++)
		if($str[$i]==' ')
			$str[$i]='_';
	//echo "<br>Parsed str=".$str;
	return($str);
}



######################The code for submission (after the values are POSTed)###################
if(isset($GLOBALS['_POST']['submit']))
{
  /*To find the maximum groupmemid(the primary key)*/
  $a=mysql_fetch_assoc(mysql_query("SELECT MAX(groupmemid) from pragyan_reg_".$form_id));
  $GLOBALS['_POST']['nm_groupmemid']=$a['MAX(groupmemid)'];
  $GLOBALS['_POST']['nm_groupmemid']++;
  
 //print_r($GLOBALS['_FILES']);
  $querystring="INSERT INTO `pragyan_reg_".$GLOBALS['_POST']['form_id']."` (";
  
  
  /*To display the form entries upon submission*/
  /*		$querystring2="SELECT * FROM pragyan_reg_".$form_id." WHERE groupmemid<0 ORDER BY groupmemid DESC";
		$result2=mysql_query($querystring2) or die("dfsgfg77");	
		
	if(!$result2)
		{//	echo "Couldn't query the table";
		//	print_r($error);
		}
		for($i=1;$a=mysql_fetch_row($result2);$i++)
		{	$nameofarray="a_row".$i;
			$$nameofarray=$a;
		}
		
		$x2=0;
		$html.="<table border=0>";
		for($x=1;$x<=mysql_num_fields($result2)-1;$x++)
		{
			if($a_row3[$x]=="file") 
				$z1[$x-1].="Your file has been uploaded.";
			else
				$z1[$x-1].=$a_row2[$x]." :";
		}
		$html.="</table></td><td width=\"20\">&nbsp;</td><td><strong><table>";
		$querystring="INSERT INTO `pragyan_reg_".$form_id."` (";
		foreach($GLOBALS['_POST'] as $key=>$value)
		{	if(strstr($key,"nm_"))
			{	if(isset($abc)) $querystring.=",";
				$key_parsed=substr($key,(strlen("nm_")));
				$querystring.="`".parseString($key_parsed)."`";
				$abc=0;
				if($key_parsed=="groupmemid") continue;
				$z2[$x2++]=$value;
				
			}
		}
		$html="<br>Thank you for your registration with ".str_replace('_' , ' ' , $GLOBALS['_POST']['reg_form_name']).".<br><br>The information you entered is:<br><br> <table width=\"50%\" border=0>";
		for($i=0;$i<$x-1;$i++)
		{
			$html.="<tr><td>".$z1[$i]."</td><td><strong>".$z2[$i]."</strong></td></tr>";
		}
		$html.="</table></strong><br><br>The organisers will get back to you very soon.<br><br>";

		echo $html;
   */
  /*field names--*/
  unset($i); 
  foreach($GLOBALS['_POST'] as $key=>$value)
    {	
      if(substr($key,0,3)=="nm_")/*for userdefined fields*/
	{	
	  if(isset($i)) $querystring.=",";/*for commas to start only after the first field*/
	  $key_parsed=substr($key,(strlen("nm_")));
	  $querystring.="`".unparseString($key_parsed)."`";
	  $i=0;
	}
      if(substr($key,0,4)=="pre_")/*for predefined fields*/
	{	
	  if(isset($i)) $querystring.=",";
	  $querystring.="`".$key."`";
	  $i=0;
	}
    }
  /*for file type- (file types passed in FILES not POST)*/
  foreach($GLOBALS['_FILES'] as $key=>$values)
    {
      if(substr($key,0,3)=="nm_")
	{
	  if(isset($i)) $querystring.=",";
	  $key_parsed=substr($key,(strlen("nm_")));
	  $querystring.="`".unparseString($key_parsed)."`";
	  $i=0;
	}
    }

  /*field values*/
  unset($i);
  $querystring.=") VALUES (";
  foreach($GLOBALS['_POST'] as $key=>$value)
    {	
      if(substr($key,0,3)=="nm_")
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
      /*code for data submission of predifined fields values*/
      if(substr($key,0,4)=="pre_")
	{
	  if(isset($i)) $querystring.=",";
	  $querystring.="'".$value."'";
	  $i=0;
	}
    }
  foreach($GLOBALS['_FILES'] as $key=>$values)
    {	
      if(substr($key,0,3)=="nm_")
	{
	  if(isset($i)) $querystring.=",";
	  $address_parsed=$reg_form_name."/".$GLOBALS['_POST']['nm_groupmemid']."/".$GLOBALS['_FILES'][$key]['name'];
	  $querystring.="'".$address_parsed."'";
	  $i=0;
	}
    }
  
  $querystring.=");";
  /*the files arent passed in $GLOBALS['POST'] they are in $GLOBAL[$FILES]//see the php manual
  so the nm_name doesnt exist for the file field(in 'POST'...  its in 'FILES')*/
  
  /*file uploading procedure-*/
  print_r($GLOBALS['_FILES']);
  foreach($GLOBALS['_FILES'] as $key=>$value)
    {
      if(substr($key,0,3)=="nm_")
	{
	  if(is_uploaded_file($GLOBALS['_FILES'][$key]['tmp_name']))
	    {
	      if($value['size']>$GLOBALS['_POST']['MAX_FILE_SIZE'])
		echo("File size too large");
	      else
		{	
		  if(!file_exists($file_dir."uploads/".$reg_form_name."/"))
		  mkdir("uploads/abc",0777) or die("abcd");
		    mkdir(        $file_dir."uploads/".$reg_form_name, 0777 ) or die("1");
		  if(!file_exists($file_dir."uploads/".$reg_form_name."/".$GLOBALS['_POST']['nm_groupmemid']))
		    mkdir(        $file_dir."uploads/".$reg_form_name."/".$GLOBALS['_POST']['nm_groupmemid'], 0777 ) or die("2");
		  copy ( $value['tmp_name'], $file_dir."uploads/".$reg_form_name."/".$GLOBALS['_POST']['nm_groupmemid']."/".$value['name']) 
		    or die ("Coldnt copy");
		}
	    }
	}
    }
  //phpinfo();
  //print_r($GLOBALS['_POST']);
  //echo $querystring;
  $result=mysql_query($querystring,$db);
  if($result)
    echo ("Your record has been submitted");
  else
    echo ("There was an error while submitting your record");
  mysql_close($db);
  exit();
}


#######################The Registration Table####################################
/*To find the maximum groupmemid that is the primary key*/
$a=mysql_fetch_assoc(mysql_query("SELECT MAX(groupmemid) from pragyan_reg_".$form_id));
$max_groupmemid=$a['MAX(groupmemid)'];
/*we'll increment while submission to solve the problem of two registrants registering at the time but
 we still include it here so that there's a field name groupmemid so while entering the query it's easier*/

/*To find the fields and their description and putting them in form--*/
$querystring="SELECT * FROM pragyan_reg_".$form_id." WHERE groupmemid<0 ORDER BY groupmemid DESC";
//echo $querystring;
$result=mysql_query($querystring);	
if(!$result)
{	
  echo "Couldn't query the table";
  print_r($error);
}
else
{
  $tempt="<HEAD><TITLE>$reg_form_name</TITLE>";
  $temp="</HEAD>";
  $temp.="\n<BODY><FORM enctype=\"multipart/form-data\" action=".$GLOBALS['_SERVER']['PHP_SELF']." name='frm' method='POST'>";
  $temp.="\n\t<INPUT type='hidden' name='nm_groupmemid' value=".$max_groupmemid.">";
  $temp.="\n\t<INPUT type='hidden' name='form_id' value=".$form_id.">";
  $temp.="\n<STRONG><U><CENTER>".$reg_form_name."</CENTER></U></STRONG>";
  $temp.="\n<br><TABLE width='70%' align='center'><TR><TD align=left>".$reg_form_desc."</TD></TR>";
  $temp.="\n\n<TR><TD><TABLE width=100% cellpadding=1 cellspacing=0>";
  for($i=1;$a=mysql_fetch_array($result);$i++)
    {
      $nameofarray="a_row".$i;
      $$nameofarray=$a;
      //print_r($a);
    }
  for($i=0;$a_row0[$i]=mysql_field_name($result,$i);$i++);//to get field names also for the predefined fields
  //because there is no other way of identifying them and also now their order will be the same as stored in the table ;)

  $java="<script language=\"javascript\">
		function validate()
		{ 
			var f=document.frm;
			";
  for($x=1;($x<=mysql_num_fields($result)-1)&&($a_row0[$x]!=NULL);$x++)
    {
      $temp.="\n\t<TR><TD width=40%>";
	  if($a_row6[$x]=="on")
	  	  $temp.="*";
	  $temp.=$a_row2[$x]."</TD>";
      if($a_row6[$x]=="on")
	{	
	  $java.="\n if(f.nm_".$a_row1[$x].".value==\"\")
					{
						alert('Please enter the ".unparseString($a_row1[$x])."!');
						f.nm_".$a_row1[$x].".focus();
						return(false);
					}
				";
	}
      
      //textarea
      if($a_row3[$x]=="textarea")
	{	
	  $type=$a_row3[$x];
	  $temp.="\n\t\t<TD><TEXTAREA name='nm_".$a_row1[$x]."'>".$a_row5[$x]."</TEXTAREA>";
	}
      //radio button
      elseif(($type=strtok($a_row3[$x],"|"))=="radio")
	{	
	  $temp.="<TD>";
	  while($a=strtok("|"))
	    {	
	      $temp.="\n\t\t<INPUT TYPE='radio' NAME='nm_".$a_row1[$x]."' ID='".$a."' VALUE='".$a."'>";
	      $temp.="<LABEL FOR='".$a."'>".$a."</LABEL><BR>";
	    }
	}
      //select options
      elseif(($type=strtok($a_row3[$x],"|"))=="select")
	{	
	  $temp.="\n\t\t<TD><SELECT NAME='nm_".$a_row1[$x]."'>";
	  while($a=strtok("|"))
	    $temp.="\n\t\t\t<OPTION>".$a;
	}
      //file upload
      elseif($a_row3[$x]=="file")
	{
	  $type=$a_row3[$x];
	  $temp.='<input type="hidden" name="MAX_FILE_SIZE" value="20971520">';
	  $temp.="\n\t\t<TD><INPUT type='".$a_row3[$x]."' name='nm_".$a_row1[$x]."'>";
	}
      //text field
      elseif($a_row3[$x]=="text")
       	{
	  $type=$a_row3[$x];
	  $temp.="\n\t\t<TD><INPUT type='".$a_row3[$x]."' name='nm_".$a_row1[$x]."'>";
	}
	 //checkbox
      elseif($a_row3[$x]=="checkbox")
       	{
	  $type=$a_row3[$x];
	  $temp.="\n\t\t<TD><INPUT type='".$a_row3[$x]."' name='nm_".$a_row1[$x]."'>";
	}
    
      //to pass the types also on submit - 
      $temp.="\n\t\t<INPUT type='hidden' name='typ_".$a_row1[$x]."' value='".$type."'></TD></TR>";

  //predefined fields 
	  if($fieldname=substr($a_row0[$x],0,4)=="pre_")
	    {
		$fieldname=$a_row0[$x];
	      //predefined field pre_name 
	      if($fieldname=="pre_name")
		{
		  $java.="\n if(f.pre_name.value==\"\")
					{
						alert('Please enter your name!');
						f.pre_name.focus();
						return(false);
					}
				";	      
		  $temp.="\n\t<TR><TD width=40%>*Name</TD>";	
		  $temp.="\n\t\t<TD><INPUT type='text' name=".$fieldname."></TD></TR>";
		}
		//predefined field pre_roll
		  if($fieldname=="pre_roll")
	    {
	      $java.="\n if(f.pre_roll.value==\"\")
					{
						alert('Please enter Roll Number!');
						f.pre_roll.focus();
						return(false);
					}
				";	      
	      $temp.="\n\t<TR><TD width=40%>*Roll Number</TD>";	
	      $temp.="\n\t\t<TD><INPUT type='text' name=".$fieldname."></TD></TR>";
	    }
		//pre_age
		  if($fieldname=="pre_age")
	    {
	      $java.="\n if(f.pre_age.value==\"\")
					{
						alert('Please enter Age!');
						f.pre_age.focus();
						return(false);
					}
					if(isNaN(f.pre_age.value))
					{
						alert('Please enter the Age as number!');
						f.pre_age.value=\"\";
						f.pre_age.focus();
						return(false);
					}
				";	      
	      $temp.="\n\t<TR><TD width=40%>*Age</TD>";	
	      $temp.="\n\t\t<TD><INPUT type='text' name=".$fieldname."></TD></TR>";
	    }
	  if($fieldname=="pre_dob")
	    {
	      $java.="\n f.pre_dob.value=f.year.value+'-'+f.month.value+'-'+f.day.value;
		  			 if(f.pre_name.value==\"\")
					{
						alert('Please enter your name!');
						f.pre_name.focus();
						return(false);
					}
					
				";	      
	      $temp.="\n\t<TR><TD width=40%>*Date of birth</td><td><input type=hidden name=pre_dob><select name=day>";
		   for($i=1;$i<=31;$i++)	
		  {
		  	$temp.="<option value=".$i.">".$i."</option>";
		  }
		   $temp.="</select><select name=month>";
		  $month=Array( "January", "February", "March","April","May","June","July","August","September","October","November","December");
		  for($i=0;$i<=11;$i++)	
		  {
		  	$temp.="<option value=".($i+1).">".$month[$i]."</option>";
		  }
		  $temp.="</select><select name=year>";
		  for($i=2000;$i>1900;$i--)
		  {
		  	$temp.="<option value=".$i.">".$i."</option>";
		  }
		  $temp.="</select>";
//	      $temp.="\n\t\t<TD><INPUT type='text' name=".$fieldname."></TD></TR>";
	    }
		//Address
		  if($fieldname=="pre_address")
	    {
	      $java.="\n if(f.pre_address.value==\"\")
					{
						alert('Please enter your name!');
						f.pre_address.focus();
						return(false);
					}
				";	      
	      $temp.="\n\t<TR><TD width=40%>*Address</TD>";	
	      $temp.="\n\t\t<TD><textarea name=".$fieldname."></textarea></TD></TR>";
	    }
		//Email
	  if($fieldname=="pre_mail")
	    {
	      $java.="\n if(f.pre_mail.value==\"\")
					{
						alert('Please enter email!');
						f.pre_mail.focus();
						return(false);
					}
					if(f.pre_mail.value!=\"\")
				{
		var at=f.pre_mail.value.search(\"@\");
		var dot=f.pre_mail.value.lastIndexOf(\".\");
		var len=f.pre_mail.value.length;
		if(at==-1 || dot==-1 || dot>=(len-2) )
		{
			alert(\"Enter Correct Email Address\");
			f.pre_mail.focus();
			return(false);
		}
	}
				";	      
	      $temp.="\n\t<TR><TD width=40%>*Email</TD>";	
	      $temp.="\n\t\t<TD><INPUT type='text' name=".$fieldname."></TD></TR>";
	    }


	    }
}
 
  $java.="}</script>";
    

  $temp.="\n</TABLE></TD><tr><td align=\"left\">* indicate Mandatory fields</td></tr></TR></TABLE>";
  $temp.="\n<BR><CENTER><INPUT type='submit' name='submit' onClick='return validate();' value='Submit Form'></CENTER>";
  $temp.="\n</FORM></BODY>";
  
  $temp=($tempt.$java.$temp);
  echo $temp;
}
?>
</HTML>
