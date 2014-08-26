package com.lol.lolpro.app.bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteCantOpenDatabaseException;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sergio on 5/06/14.
 */
public class DBManager {
    private AtomicInteger mOpenCounter = new AtomicInteger();
    private AtomicInteger mReadOnlyOpenCounter = new AtomicInteger();

    private static DBManager instance;
    private static BBDDHelper mDatabaseHelper;

    private DBManager(){}

    public static synchronized void initializeInstance(Context context) {
        if (instance == null) {
            instance = new DBManager();
            mDatabaseHelper = new BBDDHelper(context);
        }
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException(DBManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return instance;
    }

    public synchronized void openDatabase(boolean writeMode) throws SQLiteCantOpenDatabaseException {
        if (writeMode) {
            if (mOpenCounter.compareAndSet(0, 1)) {
                // Opening new database
                mDatabaseHelper.openDatabase(true);
                // Say that we are using the connection
                mReadOnlyOpenCounter.incrementAndGet();
                // Opening new database
                mDatabaseHelper.openDatabase(false);
            }
            else
            {
                throw new SQLiteCantOpenDatabaseException();
            }
        }
        else{
            // Say that we are using the connection
            mReadOnlyOpenCounter.incrementAndGet();
            // Opening new database
            mDatabaseHelper.openDatabase(false);
        }
    }

    public synchronized void closeDatabase(boolean writeMode) {
        if (writeMode) {
            if (mOpenCounter.decrementAndGet() == 0) {
                // Closing database
                mDatabaseHelper.closeDatabase(true);
                if (mReadOnlyOpenCounter.decrementAndGet() == 0) {
                    // Closing database
                    mDatabaseHelper.closeDatabase(false);
                }
            }
        }
        else {
            if (mReadOnlyOpenCounter.decrementAndGet() == 0) {
                // Closing database
                mDatabaseHelper.closeDatabase(false);
            }
        }
    }

    public synchronized BBDDHelper getDatabaseHelper(){
        return mDatabaseHelper;
    }
}
