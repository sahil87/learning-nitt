/****************************************************************************
** MyWidget meta object code from reading C++ file 'form.h'
**
** Created: Tue Nov 7 00:24:13 2006
**      by: The Qt MOC ($Id: qt/moc_yacc.cpp   3.3.6   edited Mar 8 17:43 $)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#undef QT_NO_COMPAT
#include "form.h"
#include <qmetaobject.h>
#include <qapplication.h>

#include <private/qucomextra_p.h>
#if !defined(Q_MOC_OUTPUT_REVISION) || (Q_MOC_OUTPUT_REVISION != 26)
#error "This file was generated using the moc from 3.3.6. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

const char *MyWidget::className() const
{
    return "MyWidget";
}

QMetaObject *MyWidget::metaObj = 0;
static QMetaObjectCleanUp cleanUp_MyWidget( "MyWidget", &MyWidget::staticMetaObject );

#ifndef QT_NO_TRANSLATION
QString MyWidget::tr( const char *s, const char *c )
{
    if ( qApp )
	return qApp->translate( "MyWidget", s, c, QApplication::DefaultCodec );
    else
	return QString::fromLatin1( s );
}
#ifndef QT_NO_TRANSLATION_UTF8
QString MyWidget::trUtf8( const char *s, const char *c )
{
    if ( qApp )
	return qApp->translate( "MyWidget", s, c, QApplication::UnicodeUTF8 );
    else
	return QString::fromUtf8( s );
}
#endif // QT_NO_TRANSLATION_UTF8

#endif // QT_NO_TRANSLATION

QMetaObject* MyWidget::staticMetaObject()
{
    if ( metaObj )
	return metaObj;
    QMetaObject* parentObject = QVBox::staticMetaObject();
    static const QUMethod slot_0 = {"setColor", 0, 0 };
    static const QUMethod slot_1 = {"drawLines", 0, 0 };
    static const QUMethod slot_2 = {"timerDone", 0, 0 };
    static const QMetaData slot_tbl[] = {
	{ "setColor()", &slot_0, QMetaData::Public },
	{ "drawLines()", &slot_1, QMetaData::Public },
	{ "timerDone()", &slot_2, QMetaData::Public }
    };
    metaObj = QMetaObject::new_metaobject(
	"MyWidget", parentObject,
	slot_tbl, 3,
	0, 0,
#ifndef QT_NO_PROPERTIES
	0, 0,
	0, 0,
#endif // QT_NO_PROPERTIES
	0, 0 );
    cleanUp_MyWidget.setMetaObject( metaObj );
    return metaObj;
}

void* MyWidget::qt_cast( const char* clname )
{
    if ( !qstrcmp( clname, "MyWidget" ) )
	return this;
    return QVBox::qt_cast( clname );
}

bool MyWidget::qt_invoke( int _id, QUObject* _o )
{
    switch ( _id - staticMetaObject()->slotOffset() ) {
    case 0: setColor(); break;
    case 1: drawLines(); break;
    case 2: timerDone(); break;
    default:
	return QVBox::qt_invoke( _id, _o );
    }
    return TRUE;
}

bool MyWidget::qt_emit( int _id, QUObject* _o )
{
    return QVBox::qt_emit(_id,_o);
}
#ifndef QT_NO_PROPERTIES

bool MyWidget::qt_property( int id, int f, QVariant* v)
{
    return QVBox::qt_property( id, f, v);
}

bool MyWidget::qt_static_property( QObject* , int , int , QVariant* ){ return FALSE; }
#endif // QT_NO_PROPERTIES
