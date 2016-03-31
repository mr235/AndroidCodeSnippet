package com.mr235.androidcodesnippet.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mr235.androidcodesnippet.db.table.InfoTable;

/**
 * Created by Administrator on 2015/4/20.
 * 数据库
 */
public class BaseDBHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db.db";

    public BaseDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createUserInfoTable(db);
        onUpgrade(db, 1, DATABASE_VERSION);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 使用for实现跨版本升级数据库
        for (int i = oldVersion; i <= newVersion; i++) {
            switch (i) {
                case 1:
                    break;
                case 2:
                    if (!checkColumnExist(db, InfoTable.TABLE_NAME, InfoTable.INFO)) {
                        db.execSQL("ALTER TABLE " + InfoTable.TABLE_NAME + " ADD " +InfoTable.INFO + " INT ");
                    }
                    break;
//                case 3:
//                    if (!checkColumnExist(db, UserInfoTable.TABLE_NAME, UserInfoTable.AFTER_PAY_CREDIT)) {
//                        db.execSQL("ALTER TABLE " + UserInfoTable.TABLE_NAME + " ADD " +UserInfoTable.AFTER_PAY_CREDIT + " FLOAT ");
//                    }
//                    if (!checkColumnExist(db, UserInfoTable.TABLE_NAME, UserInfoTable.NEED_PAY_CREDIT)) {
//                        db.execSQL("ALTER TABLE " + UserInfoTable.TABLE_NAME + " ADD " +UserInfoTable.NEED_PAY_CREDIT + " INT ");
//                    }
//                    if (!checkColumnExist(db, UserInfoTable.TABLE_NAME, UserInfoTable.NEED_PAY_CREDIT_DATE)) {
//                        db.execSQL("ALTER TABLE " + UserInfoTable.TABLE_NAME + " ADD " +UserInfoTable.NEED_PAY_CREDIT_DATE + " VARCHAR ");
//                    }
//                    break;
//                case 4:
//                    if (!checkColumnExist(db, UserInfoTable.TABLE_NAME, UserInfoTable.UNREAD_COUPON_NUM)) {
//                        db.execSQL("ALTER TABLE " + UserInfoTable.TABLE_NAME + " ADD " +UserInfoTable.UNREAD_COUPON_NUM + " INT ");
//                    }
//                    if (!checkColumnExist(db, UserInfoTable.TABLE_NAME, UserInfoTable.USER_LEVEL)) {
//                        db.execSQL("ALTER TABLE " + UserInfoTable.TABLE_NAME + " ADD " +UserInfoTable.USER_LEVEL + " VARCHAR ");
//                    }
//                    if (!checkColumnExist(db, UserInfoTable.TABLE_NAME, UserInfoTable.USER_VALUE)) {
//                        db.execSQL("ALTER TABLE " + UserInfoTable.TABLE_NAME + " ADD " +UserInfoTable.USER_VALUE + " INT ");
//                    }
//                    if (!checkColumnExist(db, UserInfoTable.TABLE_NAME, UserInfoTable.HAVE_PASSWORD)) {
//                        db.execSQL("ALTER TABLE " + UserInfoTable.TABLE_NAME + " ADD " +UserInfoTable.HAVE_PASSWORD + " INT ");
//                    }
//                    if (!checkColumnExist(db, UserInfoTable.TABLE_NAME, UserInfoTable.INVITE_INFO)) {
//                        db.execSQL("ALTER TABLE " + UserInfoTable.TABLE_NAME + " ADD " +UserInfoTable.INVITE_INFO + " VARCHAR ");
//                    }
//                    break;
//                case 5:
//                    createPayStatusTable(db);
//                    break;
                default:
                    break;
            }
        }
    }

    /** 创建用户表 */
    private void createUserInfoTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + InfoTable.TABLE_NAME + "(" +
                InfoTable.INFO + " INTEGER " +
                ")";
        db.execSQL(sql);
    }

    private boolean checkColumnExist(SQLiteDatabase db, String tableName, String column) {
        String sql = "select " + column + " from " + tableName;

        try {
            db.rawQuery(sql, null);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
