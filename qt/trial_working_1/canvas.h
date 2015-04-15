#ifndef CANVAS_H_
#define CANVAS_H_

#include <qcanvas.h>
#include <qpainter.h>
#include <qpixmap.h>
#include <qcolor.h>

class Canvas : public QCanvas
{
	Q_OBJECT
public:
	Canvas( int w=200,int h=200 );
	int f,s,t;
	int x,y;
	void makeDrawing(int first,int second, int third);
	QPainter *paint;
	QPixmap *pixmap;
//	void mousePressEvent( QMouseEvent * e);
public slots:
protected:
	void mousePressEvent( QMouseEvent *e );
private:
	void redraw();
	QColor getColor(int a);
	
};



#endif /*CANVAS_H_*/
