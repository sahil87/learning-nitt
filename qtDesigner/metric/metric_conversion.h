/****************************************************************************
** Form interface generated from reading ui file 'metric/metric_conversion.ui'
**
** Created: Tue Oct 3 03:24:29 2006
**      by: The User Interface Compiler ($Id: qt/main.cpp   3.3.5   edited Aug 31 12:13 $)
**
** WARNING! All changes made in this file will be lost!
****************************************************************************/

#ifndef METRIC_CONVERSION_H
#define METRIC_CONVERSION_H

#include <qvariant.h>
#include <qdialog.h>

class QVBoxLayout;
class QHBoxLayout;
class QGridLayout;
class QSpacerItem;
class QLabel;
class QLineEdit;
class QComboBox;
class QSpinBox;
class QPushButton;

class metric_conversion : public QDialog
{
    Q_OBJECT

public:
    metric_conversion( QWidget* parent = 0, const char* name = 0, bool modal = FALSE, WFlags fl = 0 );
    ~metric_conversion();

    QLabel* textLabel4;
    QLineEdit* numberLineEdit;
    QComboBox* fromComboBox;
    QLabel* textLabel2;
    QLineEdit* resultLineEdit;
    QComboBox* toComboBox;
    QSpinBox* decimalsSpinBox;
    QLabel* textLabel5;
    QLabel* textLabel3;
    QLabel* Enter__number;
    QPushButton* clearPushButton;
    QPushButton* calculatPushButton;
    QPushButton* quitPushButton;

public slots:
    virtual void convert();

protected:
    QVBoxLayout* metric_conversionLayout;
    QSpacerItem* spacer3;
    QGridLayout* layout2;
    QHBoxLayout* layout1;
    QSpacerItem* spacer1;
    QHBoxLayout* layout3;
    QSpacerItem* spacer2;

protected slots:
    virtual void languageChange();

private:
    void init();

};

#endif // METRIC_CONVERSION_H
