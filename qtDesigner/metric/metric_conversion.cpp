/****************************************************************************
** Form implementation generated from reading ui file 'metric/metric_conversion.ui'
**
** Created: Tue Oct 3 03:24:33 2006
**      by: The User Interface Compiler ($Id: qt/main.cpp   3.3.5   edited Aug 31 12:13 $)
**
** WARNING! All changes made in this file will be lost!
****************************************************************************/

#include "metric_conversion.h"

#include <qvariant.h>
#include <qpushbutton.h>
#include <qlabel.h>
#include <qlineedit.h>
#include <qcombobox.h>
#include <qspinbox.h>
#include <qlayout.h>
#include <qtooltip.h>
#include <qwhatsthis.h>
#include <qimage.h>
#include <qpixmap.h>

#include "metric_conversion.ui.h"
/*
 *  Constructs a metric_conversion as a child of 'parent', with the
 *  name 'name' and widget flags set to 'f'.
 *
 *  The dialog will by default be modeless, unless you set 'modal' to
 *  TRUE to construct a modal dialog.
 */
metric_conversion::metric_conversion( QWidget* parent, const char* name, bool modal, WFlags fl )
    : QDialog( parent, name, modal, fl )
{
    if ( !name )
	setName( "metric_conversion" );
    metric_conversionLayout = new QVBoxLayout( this, 11, 6, "metric_conversionLayout"); 

    layout2 = new QGridLayout( 0, 1, 1, 0, 6, "layout2"); 

    textLabel4 = new QLabel( this, "textLabel4" );

    layout2->addWidget( textLabel4, 3, 0 );

    numberLineEdit = new QLineEdit( this, "numberLineEdit" );
    numberLineEdit->setAlignment( int( QLineEdit::AlignRight ) );

    layout2->addWidget( numberLineEdit, 0, 1 );

    fromComboBox = new QComboBox( FALSE, this, "fromComboBox" );

    layout2->addWidget( fromComboBox, 1, 1 );

    textLabel2 = new QLabel( this, "textLabel2" );

    layout2->addWidget( textLabel2, 1, 0 );

    resultLineEdit = new QLineEdit( this, "resultLineEdit" );
    resultLineEdit->setPaletteBackgroundColor( QColor( 255, 254, 248 ) );
    resultLineEdit->setAlignment( int( QLineEdit::AlignRight ) );

    layout2->addWidget( resultLineEdit, 3, 1 );

    toComboBox = new QComboBox( FALSE, this, "toComboBox" );

    layout2->addWidget( toComboBox, 2, 1 );

    layout1 = new QHBoxLayout( 0, 0, 6, "layout1"); 
    spacer1 = new QSpacerItem( 81, 20, QSizePolicy::Expanding, QSizePolicy::Minimum );
    layout1->addItem( spacer1 );

    decimalsSpinBox = new QSpinBox( this, "decimalsSpinBox" );
    decimalsSpinBox->setMaxValue( 6 );
    decimalsSpinBox->setValue( 3 );
    layout1->addWidget( decimalsSpinBox );

    layout2->addLayout( layout1, 4, 1 );

    textLabel5 = new QLabel( this, "textLabel5" );

    layout2->addWidget( textLabel5, 4, 0 );

    textLabel3 = new QLabel( this, "textLabel3" );

    layout2->addWidget( textLabel3, 2, 0 );

    Enter__number = new QLabel( this, "Enter__number" );

    layout2->addWidget( Enter__number, 0, 0 );
    metric_conversionLayout->addLayout( layout2 );
    spacer3 = new QSpacerItem( 20, 20, QSizePolicy::Minimum, QSizePolicy::Expanding );
    metric_conversionLayout->addItem( spacer3 );

    layout3 = new QHBoxLayout( 0, 0, 6, "layout3"); 

    clearPushButton = new QPushButton( this, "clearPushButton" );
    layout3->addWidget( clearPushButton );

    calculatPushButton = new QPushButton( this, "calculatPushButton" );
    calculatPushButton->setDefault( TRUE );
    layout3->addWidget( calculatPushButton );
    spacer2 = new QSpacerItem( 21, 20, QSizePolicy::Expanding, QSizePolicy::Minimum );
    layout3->addItem( spacer2 );

    quitPushButton = new QPushButton( this, "quitPushButton" );
    layout3->addWidget( quitPushButton );
    metric_conversionLayout->addLayout( layout3 );
    languageChange();
    resize( QSize(326, 222).expandedTo(minimumSizeHint()) );
    clearWState( WState_Polished );

    // signals and slots connections
    connect( clearPushButton, SIGNAL( clicked() ), numberLineEdit, SLOT( clear() ) );
    connect( clearPushButton, SIGNAL( clicked() ), numberLineEdit, SLOT( clear() ) );
    connect( clearPushButton, SIGNAL( clicked() ), numberLineEdit, SLOT( setFocus() ) );
    connect( calculatPushButton, SIGNAL( clicked() ), this, SLOT( convert() ) );
    connect( decimalsSpinBox, SIGNAL( valueChanged(int) ), this, SLOT( convert() ) );
    connect( fromComboBox, SIGNAL( activated(int) ), this, SLOT( convert() ) );
    connect( toComboBox, SIGNAL( activated(int) ), this, SLOT( convert() ) );
    connect( quitPushButton, SIGNAL( clicked() ), this, SLOT( close() ) );

    // tab order
    setTabOrder( numberLineEdit, fromComboBox );
    setTabOrder( fromComboBox, toComboBox );
    setTabOrder( toComboBox, resultLineEdit );
    setTabOrder( resultLineEdit, decimalsSpinBox );
    setTabOrder( decimalsSpinBox, clearPushButton );
    setTabOrder( clearPushButton, calculatPushButton );
    setTabOrder( calculatPushButton, quitPushButton );

    // buddies
    textLabel2->setBuddy( fromComboBox );
    textLabel5->setBuddy( decimalsSpinBox );
    textLabel3->setBuddy( toComboBox );
    Enter__number->setBuddy( numberLineEdit );
    init();
}

/*
 *  Destroys the object and frees any allocated resources
 */
metric_conversion::~metric_conversion()
{
    // no need to delete child widgets, Qt does it all for us
}

/*
 *  Sets the strings of the subwidgets using the current
 *  language.
 */
void metric_conversion::languageChange()
{
    setCaption( tr( "Form2" ) );
    textLabel4->setText( tr( "Result:" ) );
    fromComboBox->clear();
    fromComboBox->insertItem( tr( "Kilometers" ) );
    fromComboBox->insertItem( tr( "Meters" ) );
    fromComboBox->insertItem( tr( "Centimeters" ) );
    fromComboBox->insertItem( tr( "Meters" ) );
    textLabel2->setText( tr( "Convert &From:" ) );
    toComboBox->clear();
    toComboBox->insertItem( tr( "Miles" ) );
    toComboBox->insertItem( tr( "Yards" ) );
    toComboBox->insertItem( tr( "Feets" ) );
    toComboBox->insertItem( tr( "Inches" ) );
    textLabel5->setText( tr( "&Decimals:" ) );
    textLabel3->setText( tr( "Convert &To:" ) );
    Enter__number->setText( tr( "Enter &number:" ) );
    clearPushButton->setText( tr( "&Clear" ) );
    clearPushButton->setAccel( QKeySequence( tr( "Alt+C" ) ) );
    calculatPushButton->setText( tr( "Calculate" ) );
    calculatPushButton->setAccel( QKeySequence( QString::null ) );
    quitPushButton->setText( tr( "&Quit" ) );
    quitPushButton->setAccel( QKeySequence( tr( "Alt+Q" ) ) );
}

