#include <qapplication.h>
#include <qpushbutton.h>
#include <qlayout.h>
#include <qcanvas.h>
#include <qvbox.h>
#include <qpixmap.h>
#include <qstring.h>
#include <qpicture.h>
#include <qpainter.h>
#include <qimage.h>

int main( int argc, char **argv )
{
    QApplication app( argc, argv );
	QVBox * widget = new QVBox(0);
	QString * str = new QString("Hello World");
	
	QCanvas * canvas = new QCanvas(3000,3000);
	QCanvasView * canvasview = new QCanvasView(widget);
	canvasview->setCanvas(canvas);

	QPixmap pixmap;
	bool imageLoaded=pixmap.load("image.bmp","BMP");
	
	if(imageLoaded)
		canvas->setBackgroundPixmap(pixmap);
	else
		str=new QString("Unable to load image");
		
	/*QImage image;
    image.load("image.bmp","BMP");
	QPainter paint;
	paint.begin();
	paint.drawImage(50,50, image);
	canvas->drawArea(canvas->rect(),&paint);*/
	
	QPushButton * hello = new QPushButton(widget , "hellobutton");
    hello->setText(*str);
    hello->setFont(QFont("Times",18,QFont::Bold));
    
    widget->connect(hello,SIGNAL(clicked()),canvas,SLOT(update()));
    
    widget->show();
    return app.exec();
}