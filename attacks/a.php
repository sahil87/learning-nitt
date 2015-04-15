<?php
for($i=1;$i<130;$i++)
{
	echo "-A RH-Firewall-1-INPUT -s 10.1.".$i.".1 -j ACCEPT\n";
       echo "-A RH-Firewall-1-INPUT -s 10.1.".$i.".2 -j ACCEPT\n";
       echo "-A RH-Firewall-1-INPUT -s 10.1.".$i.".11 -j ACCEPT\n";
       echo "-A RH-Firewall-1-INPUT -s 10.1.".$i.".22 -j ACCEPT\n";
}
?>
