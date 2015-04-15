<!--//A script to keep on running PHP queries and displaying their results on the same window-->
<!--//To edit the make_table function to enable it to edit the fields using the edit and delete buttons-->
<HTML>
<HEAD><TITLE>Running MySQL scripts in PHP</TITLE></HEAD>
<BODY>

<?php
#connecting to the database
$link=mysql_connect("localhost","sahil","sahil");			//unix shell equivalent-- mysql -h hostname -u username -p password;
if(!$link)								//type this to start mysql-- mysql -p databasename
  die("Could not connect to MySQL");
$db="test";							//>show databases; --shows the databases available
mysql_select_db($db,$link)					//unix shell equivalent >use dbname;
  or die("Couldnt open $db: ".mysql_error());			//>show tables; >describe tablename; --for tables
								//cant use this-- $tablename="students";
function make_table($result)
{
  $temp_result="\n<form metbod='POST' action=''>";
  $temp_result.="\n<table cellpadding=2 cellspacing=1>";
  for($i=0;$i<mysql_num_rows($result);$i++)
    {
      $a_rows=mysql_fetch_assoc($result);
      #Starting rows-
      if($i==0)
	{
	  $temp_result.="\n<tr bgcolor=#d3dce3>\n";
	  	  $temp_result.="<td colspan=3></td>";
	  foreach($a_rows as $key=>$value)
	    {
	      $temp_result.="<td colspan=2>".$key."</td>";
	    }
	  $temp_result.="\n</tr>";
	}

      #data rows-
      $temp_result.="\n<tr bgcolor=".(($i%2==0)?"#e5e5e5":"#d5d5d5").">\n";
	#edit columns-
      $temp_result.="<td><input type=checkbox name=check".$i."></td>";
      $temp_result.="<td><a href=\"\"><img src=\"b_edit.png\" border=0></a></td>";
      $temp_result.="<td><a href=\"\"><img src=\"b_drop.png\" border=0></a></td>";
	#data columns-
       foreach($a_rows as $key=>$value)
	{
	  $temp_result.="<td>".$value."</td>";
	  $temp_result.='<td><input type="text" value="'.$value.'" size='.size_of($key).' disabled</td>';
	}
       $temp_result.="\n</tr>";
    }
  $temp_result.="\n</table>";
  $temp_result.="\n</form>";
 
  return $temp_result;
}

function size_of($key)
{
//selection of tablename-
$e=strtok($GLOBALS['_POST']['querystring'],' ');
while(is_string($e)) 
  {if($e) 
      if (strtolower($e)!='from')
	{$e=strtok(' ');
	  continue;
	}
      else
	{$e=strtok(' ');
	  break;
	}
  }
 if(!is_string($e)) return 15;
//making query to get info about the column named $key-
$b=mysql_fetch_assoc(mysql_query("explain ".$e." '".$key."'"));
 $a=$b['Type'];//gives datatype of this format - varchar(20)
 $a=substr($a,strpos($a,'(')+1,strpos($a,')')-strpos($a,'(')-1);//extracting 20 out of varchar(20)
return $a;
}


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
      {
	$temp_result = make_table($result);
      }
  }
 else
   {
     $text_in_textarea="Write your mysql script here";
   }
?>

<br>Enter the query you want to run -
<form action=<?php echo "\"".$GLOBALS['_SERVER']['PHP_SELF']."\""?> method="POST">
<textarea name="querystring" rows=5 cols=60><?php echo $text_in_textarea ?></textarea>
<br><INPUT type="submit">
</form>

<?php
if(isset($temp_result))
  echo $temp_result;
/*things used to-
#TODO - make the size of the text input in form equal to the size of that field in the table.. to do that query the size the fiels
/*
$b=mysql_fetch_assoc(mysql_query('explain books name'));
$a=$b['Type'];
$c=explode(" ",$GLOBALS['_POST']['querystring']);
echo 'explain '.$c[count($c)-1].' name';
$d="select * from books";
$e=strtok($d,' ');
while(is_string($e)) 
  {if($e) 
      if (strtolower($e)!='from')
	{$e=strtok(' ');
	  continue;
	}
      else
	{$e=strtok(' ');
	  break;
	}
  }
echo $e;
//$a="abcd(1234)";
$a=substr($a,strpos($a,'(')+1,strpos($a,')')-strpos($a,'(')-1);
echo $a;
*/

mysql_close($link);
?>

</BODY>
</HTML>