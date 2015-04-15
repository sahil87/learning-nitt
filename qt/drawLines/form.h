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
	QColor color;
	QTable *w;
	Canvas *canvas;
	QSpinBox *spinbox;
	int perimeter;
	QLabel *outputlabel;
	void updatePerimeter();
public slots:
	void setColor();
	void drawLines();
};



#endif /*MYWIDGET_H_*/
