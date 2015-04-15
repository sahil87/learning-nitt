/****************************************************************************
** metric_conversion meta object code from reading C++ file 'metric_conversion.h'
**
** Created: Tue Oct 3 03:24:37 2006
**      by: The Qt MOC ($Id: qt/moc_yacc.cpp   3.3.5   edited Sep 2 14:41 $)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#undef QT_NO_COMPAT
#include "metric_conversion.h"
#include <qmetaobject.h>
#include <qapplication.h>

#include <private/qucomextra_p.h>
#if !defined(Q_MOC_OUTPUT_REVISION) || (Q_MOC_OUTPUT_REVISION != 26)
#error "This file was generated using the moc from 3.3.5. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

const char *metric_conversion::className() const
{
    return "metric_conversion";
}

QMetaObject *metric_conversion::metaObj = 0;
static QMetaObjectCleanUp cleanUp_metric_conversion( "metric_conversion", &metric_conversion::staticMetaObject );

#ifndef QT_NO_TRANSLATION
QString metric_conversion::tr( const char *s, const char *c )
{
    if ( qApp )
	return qApp->translate( "metric_conversion", s, c, QApplication::DefaultCodec );
    else
	return QString::fromLatin1( s );
}
#ifndef QT_NO_TRANSLATION_UTF8
QString metric_conversion::trUtf8( const char *s, const char *c )
{
    if ( qApp )
	return qApp->translate( "metric_conversion", s, c, QApplication::UnicodeUTF8 );
    else
	return QString::fromUtf8( s );
}
#endif // QT_NO_TRANSLATION_UTF8

#endif // QT_NO_TRANSLATION

QMetaObject* metric_conversion::staticMetaObject()
{
    if ( metaObj )
	return metaObj;
    QMetaObject* parentObject = QDialog::staticMetaObject();
    static const QUMethod slot_0 = {"convert", 0, 0 };
    static const QUMethod slot_1 = {"languageChange", 0, 0 };
    static const QMetaData slot_tbl[] = {
	{ "convert()", &slot_0, QMetaData::Public },
	{ "languageChange()", &slot_1, QMetaData::Protected }
    };
    metaObj = QMetaObject::new_metaobject(
	"metric_conversion", parentObject,
	slot_tbl, 2,
	0, 0,
#ifndef QT_NO_PROPERTIES
	0, 0,
	0, 0,
#endif // QT_NO_PROPERTIES
	0, 0 );
    cleanUp_metric_conversion.setMetaObject( metaObj );
    return metaObj;
}

void* metric_conversion::qt_cast( const char* clname )
{
    if ( !qstrcmp( clname, "metric_conversion" ) )
	return this;
    return QDialog::qt_cast( clname );
}

bool metric_conversion::qt_invoke( int _id, QUObject* _o )
{
    switch ( _id - staticMetaObject()->slotOffset() ) {
    case 0: convert(); break;
    case 1: languageChange(); break;
    default:
	return QDialog::qt_invoke( _id, _o );
    }
    return TRUE;
}

bool metric_conversion::qt_emit( int _id, QUObject* _o )
{
    return QDialog::qt_emit(_id,_o);
}
#ifndef QT_NO_PROPERTIES

bool metric_conversion::qt_property( int id, int f, QVariant* v)
{
    return QDialog::qt_property( id, f, v);
}

bool metric_conversion::qt_static_property( QObject* , int , int , QVariant* ){ return FALSE; }
#endif // QT_NO_PROPERTIES
