<!--//A script to keep on running PHP queries and displaying their results on the same window-->
<!--//To edit the make_table function to enable it to edit the fields using the edit and delete buttons-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>Running MySQL scripts in PHP</title></head>
<body>

<?php
#connecting to the database
$link=mysql_connect("localhost","sahil","sahil");			//unix shell equivalent-- mysql -h hostname -u username -p password;
if(!$link)								//type this to start mysql-- mysql -p databasename
  die("Could not connect to MySQL");
$db="test";							//>show databases; --shows the databases available
mysql_select_db($db,$link)					//unix shell equivalent >use dbname;
  or die("Couldnt open $db: ".mysql_error());			//>show tables; >describe tablename; --for tables
								//cant use this-- $tablename="students";

//print_r($GLOBALS['_POST']);
if(isset($GLOBALS['_POST']['querystring']))
  {
    $text_in_textarea=$GLOBALS['_POST']['querystring'];
    @ $result=mysql_query($GLOBALS['_POST']['querystring'],$link);//@suppresses error messages
    if(!$result)                                                  // and allows to put cutom error messages like this one - Error: (used here)
      {
	echo "Error: ".mysql_error();
	//exit;
      }
    else
      $temp_result = make_table($result);
  }
 else
   $text_in_textarea="Write your mysql script here";
   

$tablename=get_tablename_from_query($GLOBALS['_POST']['querystring']);

function make_table($result)
{
  $temp_java_result='<SCRIPT type=text/javascript>

	function toggle(imgname)
	{
		if(document.getElementById("chan"+imgname).value=="notchanged")
		{	
			document.getElementById("dat"+imgname).style.display="none";
			document.getElementById("inp"+imgname).style.display="";
			document.getElementById("chan"+imgname).value="changed";
			document.getElementById("checkd"+imgname).checked="checked";
			document.getElementById("checki"+imgname).checked="checked";
		}
		else if(document.getElementById("chan"+imgname).value=="changed")
		{	
			document.getElementById("dat"+imgname).style.display="";
			document.getElementById("inp"+imgname).style.display="none";
			document.getElementById("chan"+imgname).value="notchanged";
			document.getElementById("checkd"+imgname).checked="";
			document.getElementById("checki"+imgname).checked="";
		}
	}
</SCRIPT>';


  $temp_result="\n<form id='f1' metbod='post' action=''>";
  $temp_result.="\n<table cellpadding=2 cellspacing=1>";
  

  for($i=0;$i<mysql_num_rows($result);$i++)
    {
      $a_rows=mysql_fetch_assoc($result);
      $noofcolumns=mysql_num_fields($result);
      #Field name row-
      if($i==0)
	{
	  $temp_result.="\n<tr bgcolor=#d3dce3>\n";
	  	  $temp_result.="<td colspan=3></td>";
	  foreach($a_rows as $key=>$value)
	    {
	      $temp_result.="<td>".$key."</td>";
	    }
	  $temp_result.="\n</tr>";
	}

      #data rows-
      $temp_dat_result="\n<tr id=\"dat".$i."\" bgcolor=".(($i%2==0)?"#e5e5e5":"#d5d5d5").">\n";
      $temp_dat_result.="\n\t<input id=\"chan".$i."\" type=\"hidden\" value=\"notchanged\">";
      $temp_inp_result="\n<tr id=\"inp".$i."\" style=\"display:none\" bgcolor=".(($i%2==0)?"#e5e5e5":"#d5d5d5").">\n";
	#edit columns-
      $temp_dat_result.="\n\t<td><input id=checkd".$i." type=checkbox /></td>";
      $temp_dat_result.="\n\t<td><a href=\"javascript:toggle(".$i.")\"><img src=\"b_edit.png\" border=0 alt=\"edit\"></a></td>";
      $temp_dat_result.="\n\t<td><a href=\"javascript:delete(".$i.")\"><img src=\"b_drop.png\" border=0 alt=\"delete\"/></a></td>";
    
      $temp_inp_result.="\n\t<td><input id=checki".$i." type=checkbox /></td>";
      $temp_inp_result.="\n\t<td><a href=\"javascript:toggle(".$i.")\"><img src=\"b_edit.png\" border=0 alt=\"edit\"></a></td>";
      $temp_inp_result.="\n\t<td><a href=\"javascript:delete(".$i.")\"><img src=\"b_drop.png\" border=0 alt=\"delete\"/></a></td>";

        #data columns- 
      //  print_r($a_rows);
      foreach($a_rows as $key=>$value)
	{
	  $temp_dat_result.="\n\t<td>".$value."</td>";
	  $temp_inp_result.="\n\t<td id='.$key.$value.' display=\"none\">";
	  $temp_inp_result.='<input type="text" value="'.$value.'" size='.get_size_of_field($key).' name='.str_replace(" ","_",$key).$i.' /></td>';
	}
      $temp_dat_result.="\n</tr>";
      $temp_inp_result.="\n</tr>";
      $temp_result.=$temp_dat_result.$temp_inp_result;
    }

  
  $temp_result.="\n</table>";
  $temp_result.="\n</form>";
     
  $temp_result=$temp_java_result.$temp_result;
  return $temp_result;
}


function get_tablename_from_query($query)
{
  //selection of tablename-
  $a=strtok($query,' ');
  while(is_string($a)) 
    { 
      if($a)
	{ 
	  if (strtolower($a)!='from')
	    {
	      $a=strtok(' ');
	      continue;
	    }
	  else
	    {
	      $a=strtok(' ');
	      break;
	    }
	}
    }
}

function get_size_of_field($fieldname)
{
  global $tablename;
  if(!is_string($tablename)) return 15;
  //making query to get info about the column named $key-
  $b=mysql_fetch_assoc(mysql_query("explain ".$tablename." '".$fieldname."'"));
  $a=$b['Type'];//gives datatype of this format - varchar(20)
  $a=substr($a,strpos($a,'(')+1,strpos($a,')')-strpos($a,'(')-1);//extracting 20 out of varchar(20)
  if(!is_string($a)) return 15; 
  return $a;
}
?>




<br/>Enter the query you want to run -
<form id="f2" action=<?php echo "\"".$GLOBALS['_SERVER']['PHP_SELF']."\""?> method="post">
<textarea name="querystring" rows="5" cols="60"><?php echo $text_in_textarea ?></textarea>
<br/><INPUT type="submit" />
</form>

<?php
if(isset($temp_result))
  echo $temp_result;

//print_r($GLOBALS);
mysql_close($link);
?>

</body>
</html>