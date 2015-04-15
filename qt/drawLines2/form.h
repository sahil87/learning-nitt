#ifndef MYWIDGET_H_
#define MYWIDGET_H_

#include <qvbox.h>
#include <qtable.h>
#include <canvas.h>
#include <qspinbox.h>
#include <qhbox.h>
#include <qlabel.h>
#include <qcolordialog.h>
#include <qcolor.h>
#include <qpushbutton.h>

class MyWidget : public QVBox
{
	Q_OBJECT
public:
	MyWidget(QWidget *parent, const char *name);
	int x0,x1,x2,x3,y0,y1,y2,y3;
	QColor color;
	QTable *w;
	Canvas *canvas;
	QSpinBox *spinbox;
	int perimeter;
	QLabel *outputlabel;
	QTimer *timer;
	void updatePerimeter();
public slots:
	void setColor();
	void drawLines();
	void timerDone();
};



#endif /*MYWIDGET_H_*/
