#include <qlayout.h>
#include <qapplication.h>
//#include <qstring.h>
//#include "canvas.h"

#include "form.h"

int main( int argc, char **argv )
{
	QApplication a( argc, argv );
	MyWidget widget(0,"Mywidget");
	//connect( colorPushButton, SIGNAL( clicked() ), widget, SLOT(setColor()) );
	a.setMainWidget( &widget );
	widget.show();	
	return a.exec();
}
