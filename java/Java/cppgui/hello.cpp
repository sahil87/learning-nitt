#include <qapplication.h>
#include <qpushbutton.h>


int main( int argc, char **argv )
{
    QApplication a( argc, argv );
	QVBox vbox1();
    QPushButton hello( "Hello world!", 0 );
    hello.resize( 200, 30 );
    hello.setFont(QFont("Times",18,QFont::Bold));
    a.setMainWidget( &hello );
    hello.show();
    return a.exec();
}
