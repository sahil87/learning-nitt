#include "canvas.h"

Canvas::Canvas(int width,int height)	:QCanvas(width,height)
{
	imageLoaded=pixmap.load("image.bmp","BMP");
	image1=pixmap.convertToImage();
	showImage();
}

Canvas::~Canvas()
{
	delete image2;
}

void Canvas::updateImage()
{
	cb = QApplication::clipboard();
	image1 = cb->image(QClipboard::Clipboard);
	showImage();
}

void Canvas::rotateImage()
{
	diagMirrorImage();
	flipXImage();
	showImage();
}

void Canvas::diagMirrorImage()
{
	image2 = new QImage(image1.height(),image1.width(),image1.depth());
	for(int y=0;y<image1.height();y++)
		for(int x=0;x<image1.width();x++)
			image2->setPixel(y,x,image1.pixel(x,y));
	image1=*image2;
	delete image2;
}
void Canvas::flipXImage()
{
	image2 = new QImage(image1.width(),image1.height(),image1.depth());
	int width=image1.width();
	for(int y=0;y<image1.height();y++)
		for(int x=0;x<width;x++)
			image2->setPixel(x,y,image1.pixel(width-x-1,y));
	image1=*image2;
	delete image2;
}

void Canvas::showImage()
{
	pixmap.convertFromImage(image1);
    if ( !pixmap.isNull() )
    {
        qDebug( "Image Shown." );
        imageLoaded=true;
    }
    else
    {
    	qDebug( "Pixmap is empty." );
    }
	
	if(imageLoaded)
		this->setBackgroundPixmap(pixmap);
	else
		qDebug( "Unable to load image" );
}
