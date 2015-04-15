/****************************************************************************
** Canvas meta object code from reading C++ file 'canvas.h'
**
** Created: Mon Nov 6 16:37:15 2006
**      by: The Qt MOC ($Id: qt/moc_yacc.cpp   3.3.6   edited Mar 8 17:43 $)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#undef QT_NO_COMPAT
#include "canvas.h"
#include <qmetaobject.h>
#include <qapplication.h>

#include <private/qucomextra_p.h>
#if !defined(Q_MOC_OUTPUT_REVISION) || (Q_MOC_OUTPUT_REVISION != 26)
#error "This file was generated using the moc from 3.3.6. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

const char *Canvas::className() const
{
    return "Canvas";
}

QMetaObject *Canvas::metaObj = 0;
static QMetaObjectCleanUp cleanUp_Canvas( "Canvas", &Canvas::staticMetaObject );

#ifndef QT_NO_TRANSLATION
QString Canvas::tr( const char *s, const char *c )
{
    if ( qApp )
	return qApp->translate( "Canvas", s, c, QApplication::DefaultCodec );
    else
	return QString::fromLatin1( s );
}
#ifndef QT_NO_TRANSLATION_UTF8
QString Canvas::trUtf8( const char *s, const char *c )
{
    if ( qApp )
	return qApp->translate( "Canvas", s, c, QApplication::UnicodeUTF8 );
    else
	return QString::fromUtf8( s );
}
#endif // QT_NO_TRANSLATION_UTF8

#endif // QT_NO_TRANSLATION

QMetaObject* Canvas::staticMetaObject()
{
    if ( metaObj )
	return metaObj;
    QMetaObject* parentObject = QCanvas::staticMetaObject();
    static const QUParameter param_slot_0[] = {
	{ 0, &static_QUType_varptr, "\x0a", QUParameter::In },
	{ "thickness", &static_QUType_int, 0, QUParameter::In },
	{ 0, &static_QUType_int, 0, QUParameter::In },
	{ 0, &static_QUType_int, 0, QUParameter::In },
	{ 0, &static_QUType_int, 0, QUParameter::In },
	{ 0, &static_QUType_int, 0, QUParameter::In },
	{ 0, &static_QUType_int, 0, QUParameter::In },
	{ 0, &static_QUType_int, 0, QUParameter::In },
	{ 0, &static_QUType_int, 0, QUParameter::In },
	{ 0, &static_QUType_int, 0, QUParameter::In }
    };
    static const QUMethod slot_0 = {"updateImage", 10, param_slot_0 };
    static const QMetaData slot_tbl[] = {
	{ "updateImage(QColor,int,int,int,int,int,int,int,int,int)", &slot_0, QMetaData::Public }
    };
    metaObj = QMetaObject::new_metaobject(
	"Canvas", parentObject,
	slot_tbl, 1,
	0, 0,
#ifndef QT_NO_PROPERTIES
	0, 0,
	0, 0,
#endif // QT_NO_PROPERTIES
	0, 0 );
    cleanUp_Canvas.setMetaObject( metaObj );
    return metaObj;
}

void* Canvas::qt_cast( const char* clname )
{
    if ( !qstrcmp( clname, "Canvas" ) )
	return this;
    return QCanvas::qt_cast( clname );
}

bool Canvas::qt_invoke( int _id, QUObject* _o )
{
    switch ( _id - staticMetaObject()->slotOffset() ) {
    case 0: updateImage((QColor)(*((QColor*)static_QUType_ptr.get(_o+1))),(int)static_QUType_int.get(_o+2),(int)static_QUType_int.get(_o+3),(int)static_QUType_int.get(_o+4),(int)static_QUType_int.get(_o+5),(int)static_QUType_int.get(_o+6),(int)static_QUType_int.get(_o+7),(int)static_QUType_int.get(_o+8),(int)static_QUType_int.get(_o+9),(int)static_QUType_int.get(_o+10)); break;
    default:
	return QCanvas::qt_invoke( _id, _o );
    }
    return TRUE;
}

bool Canvas::qt_emit( int _id, QUObject* _o )
{
    return QCanvas::qt_emit(_id,_o);
}
#ifndef QT_NO_PROPERTIES

bool Canvas::qt_property( int id, int f, QVariant* v)
{
    return QCanvas::qt_property( id, f, v);
}

bool Canvas::qt_static_property( QObject* , int , int , QVariant* ){ return FALSE; }
#endif // QT_NO_PROPERTIES
