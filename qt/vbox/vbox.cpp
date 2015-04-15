#include <qapplication.h>
#include <qpushbutton.h>
#include <qlayout.h>
#include <qcanvas.h>
#include <qwidget.h>
#include <qnamespace.h>
#include <qvbox.h>

int main( int argc, char **argv )
{
    QApplication a( argc, argv );
	
	QVBox widget(0);

//	QBoxLayout qbox(& widget,QBoxLayout::Down,0,-1,"Qbox");
	
	
	QCanvasView canvasview(& widget);
//	QCanvas canvas(canvasview,"can");
//	canvas.resize(100,100);
	QCanvas canvas(100,150);
	canvasview.setCanvas(& canvas);
//	
//	
	
//	qbox.addWidget(&canvasview);
	//canvas.resize(100,110);
	
	QPushButton hello(& widget , "hellobutton");
    hello.setText("hello world!!");
    hello.setFont(QFont("Times",18,QFont::Bold));
//    QLabel *splashScreen = new QLabel( 0, "mySplashScreen",
 //                               Qt::WStyle_Customize | Qt::WStyle_Splash );
  //  a.setMainWidget(splashScreen);
    widget.show();
    return a.exec();
}
