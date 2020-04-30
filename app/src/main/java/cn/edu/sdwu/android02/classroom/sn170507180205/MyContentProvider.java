package cn.edu.sdwu.android02.classroom.sn170507180205;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import static android.R.attr.id;

public class MyContentProvider extends ContentProvider {
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase db;
    private static final String MIME_MAINTYPE_DIR="vnd.android.cursor.dir";
    private static final String MIME_MAINTYPE_ITEM="vnd.android.cursor.item";
    private static final String AUTHORITY="com.inspur.android02";
    private static final int SINGLE_STUDENT=1;
    private static final int MULTIPLE_STUDENT=2;
    private static UriMatcher uriMatcher;
    private static final String CONTENT_URI_STRING="content://"+AUTHORITY+"/student";
    public static final Uri CONTENT_URI=Uri.parse(CONTENT_URI_STRING);
static {
    uriMatcher=new UriMatcher (UriMatcher. NO_MATCH) ; // äft# 4 #B ÆitDLEI#tE@TI
    uriMatcher. addURI (AUTHORITY, "student" , MULTIPLE_STUDENT);// content://com. inspur. android02/s tudent
    uriMatcher. addURI (AUTHORITY, "student/#",SINGLE_STUDENT);/* content://com. inspur. android02, 'student/I */

}

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        //返回值代表删除记录的数目
        int count=0;
        switch (uriMatcher. match(uri)) {
        case MULTIPLE_STUDENT:
        count=db. delete(" student" , selection, selectionArgs) ;
        break;
        case SINGLE_STUDENT:
        String id=uri.getLastPathSegment();
            if (selection!=null&&selection.length()>0){
                selection += "and id=" +id;
            }else{
                selection = " id=" + id;
            }
            count = db.delete(" student", selection, selectionArgs);

            break;
        }
        return count;

    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        //返回共享数据的MIME类型
        //通过Uri数据，利用UriMatcher判断请求单条还是多条数据数据
        int code = uriMatcher.match(uri);
        switch (code) {
            case MULTIPLE_STUDENT:
                return MIME_MAINTYPE_DIR + "/student"; //vnd. android. cursor. dir/studentcase SINGLE_ STUDENT:
            case SINGLE_STUDENT:
                return MIME_MAINTYPE_ITEM + "/student";
        }
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id=db. insert("student" , null , values) ;
                Uri newUri=null;
        if(id>0) {
            newUri = ContentUris.withAppendedId(CONTENT_URI, id);
        }
            return newUri;

    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        myOpenHelper=new MyOpenHelper (this.getContext());
        db=myOpenHelper. getWritableDatabase();
        return true;// 初始的结果
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        if (uriMatcher. match(uri)== SINGLE_STUDENT) {
        String id=uri. getLastPathSegment() ;
        if (selection!=null&&selection.length()>0){
                selection += "and id=" +id;
            }else{
                selection = " id=" + id;
            }
        }
        return db. query(" student" , projection, selection, selectionArgs, null, null, sortOrder) ;
    }

//更新
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        //返回值代表更新记录数目
        //返回值代表更新记录的数目
        int count=0;
        switch (uriMatcher. match (uri)) {
            case MULTIPLE_STUDENT:
        count=db.update(" student" , values, selection, selectionArgs) ;
                break;

        case SINGLE_STUDENT:
        String id=uri.getLastPathSegment() ;
            if (selection!=null&&selection.length()>0){
                selection += "and id=" +id;
            }else{
                selection = " id=" + id;
            }
        count=db.update (" student", values, selection, selectionArgs) ;
    }
        return count;
    }

}
