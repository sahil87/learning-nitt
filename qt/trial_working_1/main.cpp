#include <qapplication.h>
#include <form.h>
int main( int argc, char **argv )
{
	QApplication a( argc, argv );
	MyWidget *widget=new MyWidget();
	
	a.setMainWidget( widget );
	widget->show();
	return a.exec();
}
