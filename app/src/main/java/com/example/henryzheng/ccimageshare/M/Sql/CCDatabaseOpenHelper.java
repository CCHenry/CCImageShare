package com.example.henryzheng.ccimageshare.M.Sql;

import com.example.henryzheng.ccimageshare.M.Contants.MyContonts;
import com.example.henryzheng.ccimageshare.M.common.CCFileUtil;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

/**
 * Created by henryzheng on 2016/12/20.
 */
public class CCDatabaseOpenHelper {
    private DbManager.DaoConfig daoConfig;
    private static DbManager db;
    private final String DB_NAME = "mydb.db";
    private final int VERSION = 1;
    private CCDatabaseOpenHelper() {
        CCFileUtil.getHandleFilePath(MyContonts.cacheDir);
        daoConfig = new DbManager.DaoConfig()
                .setDbName(DB_NAME)

                .setDbVersion(VERSION)
                .setDbDir(new File(MyContonts.dbDir))
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        db.getDatabase().enableWriteAheadLogging();
                        //开启WAL, 对写入加速提升巨大(作者原话)
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        //数据库升级操作
                    }
                });
        db = x.getDb(daoConfig);
    }
    public static DbManager getInstance(){
        if (db == null){
            CCDatabaseOpenHelper databaseOpenHelper = new CCDatabaseOpenHelper();
        }
        return db;
    }

}
