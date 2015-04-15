

#include <qpushbutton.h>
#include <qlayout.h>

#include <qvbox.h>
#include <qstring.h>

#include "canvas.h"

int main( int argc, char **argv )
{
    QApplication app( argc, argv );
	QVBox * widget = new QVBox(0);
	
	Canvas * canvas = new Canvas(300,300);
	QCanvasView * canvasview = new QCanvasView(widget);
	canvasview->setCanvas(canvas);

	QPushButton * loadClipboard = new QPushButton(widget , "loadClipboard");
    loadClipboard->setText("Load Clipboard");
    loadClipboard->setFont(QFont("Times",18,QFont::Bold));
    widget->connect(loadClipboard,SIGNAL(clicked()),canvas,SLOT(updateImage()));
    
    QPushButton * rotateImage = new QPushButton(widget , "rotateImage");
    rotateImage->setText("Rotate Image");
    rotateImage->setFont(QFont("Times",18,QFont::Bold));
    widget->connect(rotateImage,SIGNAL(clicked()),canvas,SLOT(rotateImage()));
    
    QObject::connect(&app,SIGNAL(lastWindowClosed()),&app,SLOT(quit()));
        
    widget->show();
    return app.exec();
}
