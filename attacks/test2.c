
#define __USE_BSD	/* use bsd'ish ip header */
#include <sys/socket.h>	/* these headers are for a Linux system, but */
#include <netinet/in.h>	/* the names on other systems are easy to guess.. */
#include <netinet/ip.h>
#define __FAVOR_BSD	/* use bsd'ish tcp header */
#include <netinet/tcp.h>
#include <unistd.h>
#include <sys/types.h>
#include <netdb.h>
#define BUF_SIZE 4096
#define P 80		/* lets flood the sendmail port */


int 
main (void)
{
int bytes;
  char buf[BUF_SIZE];  
 int c;
  struct hostent *h;  
  char str[50];
  int s = socket (PF_INET, SOCK_RAW, IPPROTO_TCP);	/* open raw socket */
  char datagram[4096];	/* this buffer will contain ip header, tcp header,
			   and payload. we'll point an ip header structure
			   at its beginning, and a tcp header structure after
			   that to write the header values into it */
  struct ip *iph = (struct ip *) datagram;
  struct tcphdr *tcph = (struct tcphdr *) datagram + sizeof (struct ip);
  struct sockaddr_in sin;
			/* the sockaddr_in containing the dest. address is used
			   in sendto() to determine the datagrams path */

	h = gethostbyname("localhost"); 
while(1)
{	
	memset(&sin, 0, sizeof(sin));
   	sin.sin_family= AF_INET;
   	memcpy(&sin.sin_addr.s_addr, h->h_addr, h->h_length);
   	sin.sin_port= htons(P);
		
	
	c = connect(s, (struct sockaddr *) &sin, sizeof(sin));
  	if (c < 0) printf("connect failed");
	else printf("connect succeded");	
	
	sprintf(str,"GET http://10.1.60.1/forum");
	write(s,str, strlen(str));
	
	#if 0
	 while (1) 
	 {
      		bytes = read(s, buf, BUF_SIZE);              /* read from socket */
      		if (bytes <= 0) break;                     /* check for end of file */
      		write(1, buf, strlen(buf));                        /* write to standard output */
         } 	
	#endif
         


}
 
 return 0;
}
