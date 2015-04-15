/****************************************************************************
** Mainform meta object code from reading C++ file 'mainform.h'
**
** Created: Tue Oct 3 03:24:35 2006
**      by: The Qt MOC ($Id: qt/moc_yacc.cpp   3.3.5   edited Sep 2 14:41 $)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#undef QT_NO_COMPAT
#include "mainform.h"
#include <qmetaobject.h>
#include <qapplication.h>

#include <private/qucomextra_p.h>
#if !defined(Q_MOC_OUTPUT_REVISION) || (Q_MOC_OUTPUT_REVISION != 26)
#error "This file was generated using the moc from 3.3.5. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

const char *Mainform::className() const
{
    return "Mainform";
}

QMetaObject *Mainform::metaObj = 0;
static QMetaObjectCleanUp cleanUp_Mainform( "Mainform", &Mainform::staticMetaObject );

#ifndef QT_NO_TRANSLATION
QString Mainform::tr( const char *s, const char *c )
{
    if ( qApp )
	return qApp->translate( "Mainform", s, c, QApplication::DefaultCodec );
    else
	return QString::fromLatin1( s );
}
#ifndef QT_NO_TRANSLATION_UTF8
QString Mainform::trUtf8( const char *s, const char *c )
{
    if ( qApp )
	return qApp->translate( "Mainform", s, c, QApplication::UnicodeUTF8 );
    else
	return QString::fromUtf8( s );
}
#endif // QT_NO_TRANSLATION_UTF8

#endif // QT_NO_TRANSLATION

QMetaObject* Mainform::staticMetaObject()
{
    if ( metaObj )
	return metaObj;
    QMetaObject* parentObject = QMainWindow::staticMetaObject();
    static const QUMethod slot_0 = {"fileNew", 0, 0 };
    static const QUMethod slot_1 = {"fileOpen", 0, 0 };
    static const QUMethod slot_2 = {"fileSave", 0, 0 };
    static const QUMethod slot_3 = {"fileSaveAs", 0, 0 };
    static const QUMethod slot_4 = {"fileExit", 0, 0 };
    static const QUMethod slot_5 = {"editCut", 0, 0 };
    static const QUMethod slot_6 = {"editCopy", 0, 0 };
    static const QUMethod slot_7 = {"editPaste", 0, 0 };
    static const QUMethod slot_8 = {"editFind", 0, 0 };
    static const QUMethod slot_9 = {"helpIndex", 0, 0 };
    static const QUMethod slot_10 = {"helpContents", 0, 0 };
    static const QUMethod slot_11 = {"helpAbout", 0, 0 };
    static const QUMethod slot_12 = {"languageChange", 0, 0 };
    static const QMetaData slot_tbl[] = {
	{ "fileNew()", &slot_0, QMetaData::Public },
	{ "fileOpen()", &slot_1, QMetaData::Public },
	{ "fileSave()", &slot_2, QMetaData::Public },
	{ "fileSaveAs()", &slot_3, QMetaData::Public },
	{ "fileExit()", &slot_4, QMetaData::Public },
	{ "editCut()", &slot_5, QMetaData::Public },
	{ "editCopy()", &slot_6, QMetaData::Public },
	{ "editPaste()", &slot_7, QMetaData::Public },
	{ "editFind()", &slot_8, QMetaData::Public },
	{ "helpIndex()", &slot_9, QMetaData::Public },
	{ "helpContents()", &slot_10, QMetaData::Public },
	{ "helpAbout()", &slot_11, QMetaData::Public },
	{ "languageChange()", &slot_12, QMetaData::Protected }
    };
    metaObj = QMetaObject::new_metaobject(
	"Mainform", parentObject,
	slot_tbl, 13,
	0, 0,
#ifndef QT_NO_PROPERTIES
	0, 0,
	0, 0,
#endif // QT_NO_PROPERTIES
	0, 0 );
    cleanUp_Mainform.setMetaObject( metaObj );
    return metaObj;
}

void* Mainform::qt_cast( const char* clname )
{
    if ( !qstrcmp( clname, "Mainform" ) )
	return this;
    return QMainWindow::qt_cast( clname );
}

bool Mainform::qt_invoke( int _id, QUObject* _o )
{
    switch ( _id - staticMetaObject()->slotOffset() ) {
    case 0: fileNew(); break;
    case 1: fileOpen(); break;
    case 2: fileSave(); break;
    case 3: fileSaveAs(); break;
    case 4: fileExit(); break;
    case 5: editCut(); break;
    case 6: editCopy(); break;
    case 7: editPaste(); break;
    case 8: editFind(); break;
    case 9: helpIndex(); break;
    case 10: helpContents(); break;
    case 11: helpAbout(); break;
    case 12: languageChange(); break;
    default:
	return QMainWindow::qt_invoke( _id, _o );
    }
    return TRUE;
}

bool Mainform::qt_emit( int _id, QUObject* _o )
{
    return QMainWindow::qt_emit(_id,_o);
}
#ifndef QT_NO_PROPERTIES

bool Mainform::qt_property( int id, int f, QVariant* v)
{
    return QMainWindow::qt_property( id, f, v);
}

bool Mainform::qt_static_property( QObject* , int , int , QVariant* ){ return FALSE; }
#endif // QT_NO_PROPERTIES
