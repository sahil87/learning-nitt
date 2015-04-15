#ifndef CANVAS_H_
#define CANVAS_H_

#include <qcanvas.h>
#include <qimage.h>
#include <qclipboard.h>
#include <qpixmap.h>
#include <qapplication.h>

class Canvas : public QCanvas
{
	Q_OBJECT
public:
	Canvas(int,int);
	~Canvas();
private:
	bool imageLoaded;
	QImage image1;
	QImage * image2;
	QClipboard * cb;
	QPixmap pixmap;
	void showImage();
	void diagMirrorImage();
	void flipXImage();
public slots:
	void updateImage();
	void rotateImage();
};
#endif /*CANVAS_H_*/
