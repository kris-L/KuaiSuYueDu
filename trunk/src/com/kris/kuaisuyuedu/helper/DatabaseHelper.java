package com.kris.kuaisuyuedu.helper;

import java.sql.SQLException;  
import java.util.concurrent.atomic.AtomicInteger;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.kris.kuaisuyuedu.data.Const;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 *
 * @author XiaOt
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = Const.APP_DATABASE_NAME;
	//当数据库表有变动是这个值要增加
	private static final int DATABASE_VERSION = 1;
	private volatile static DatabaseHelper instance = null;
	private static final AtomicInteger usageCounter = new AtomicInteger(0);

	private DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public static DatabaseHelper getInstance(Context context) {
		if (instance == null) {
			synchronized (DatabaseHelper.class) {
				if (instance == null) {
					instance = new DatabaseHelper(context);
				}
			}
		}
		usageCounter.incrementAndGet();
		return instance;
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		Log.i(DatabaseHelper.class.getName(), "onCreate");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		Log.i(DatabaseHelper.class.getName(), "onUpgrade");
		onCreate(db, connectionSource);
	}


	@Override
	public void close() {
		// usageCounter.set(0);
		if (usageCounter.decrementAndGet() == 0) {
			super.close();
			
			instance = null;
		}
	}

}
