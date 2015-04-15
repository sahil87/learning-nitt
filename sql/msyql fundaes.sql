select parentid,content,pageid from test where pageid in (select parentid from test where content = 'dalal') and content = 'events';
To find out page id path from pagepath
select t1.pageid,t2.pageid from test as t1,test as t2 where t2.parentid=t1.pageid and t2.content='dalal' and t1.content='events';
explain select t1.pageid,t2.pageid from test as t1,test as t2 use index(PRIMARY,parentid) where t2.parentid=t1.pageid and t2.content='dalal'
 and t1.content='events' ;
