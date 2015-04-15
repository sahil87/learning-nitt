#include <qapplication.h>
#include <qpushbutton.h>

int main( int argc, char **argv )
{
    QApplication a( argc, argv );

    QPushButton hello( "Hellogfrld!", 0 );
    hello.resize( 500, 100 );
    hello.setFont(QFont("Times",18,QFont::Bold));
    a.setMainWidget( &hello );
    hello.animateClick();
    hello.show();
    return a.exec();
}
