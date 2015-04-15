#ifndef CANVAS_H_
#define CANVAS_H_

#include <qcanvas.h>
#include <qimage.h>
#include <qclipboard.h>
#include <qpixmap.h>
#include <qapplication.h>
#include <qpainter.h>

class Canvas : public QCanvas
{
	Q_OBJECT
public:
	Canvas(int,int);
	~Canvas();
	QPainter *p;
private:
	QPixmap *pixmap;
	void showImage();
public slots:
	void updateImage(QColor,int thickness,int,int,int,int,int,int,int,int);
};
#endif /*CANVAS_H_*/
