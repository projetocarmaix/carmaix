package br.com.carmaix.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something
    // appropriate for your app
    private static final String DATABASE_NAME = "msdatabase.db";
    // any time you make changes to your database objects, you may have to
    // increase the database version
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the SimpleData table
    //private Dao<ServerInformation, Integer> serverInformationDao = null;
    //private RuntimeExceptionDao<ServerInformation, Integer> serverInformationRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is first created. Usually you should
     * call createTable statements here to create the tables that will store
     * your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {

        /*
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            //TableUtils.createTable(connectionSource, ServerInformation.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
        */

    }

    /**
     * This is called when your application is upgraded and it has a higher
     * version number. This allows you to adjust the various data to match the
     * new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {

            Log.i(DatabaseHelper.class.getName(), "onUpgrade");

            // versão 2 
            if (oldVersion < newVersion && oldVersion == 1) {

                //getSimpleServerInformationDao().executeRaw(
                //        "ALTER TABLE server_information ADD COLUMN settings_cache_time STRING DEFAULT 5");

                oldVersion = oldVersion + 1;

            }

        } catch (Exception e) {

            e.printStackTrace();

            /*

            try {
                //TableUtils.dropTable(connectionSource, ServerInformation.class, true);
                //TableUtils.createTable(connectionSource, ServerInformation.class);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            */

        }
    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It
     * will create it or just give the cached value.
     */
    /*
    public Dao<ServerInformation, Integer> getDao() throws SQLException {

        if (serverInformationDao == null) {
            serverInformationDao = getDao(ServerInformation.class);
        }
        return serverInformationDao;

    }
    */

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao
     * for our SimpleData class. It will create it or just give the cached
     * value. RuntimeExceptionDao only through RuntimeExceptions.
     */

    /*
    public RuntimeExceptionDao<ServerInformation, Integer> getSimpleServerInformationDao() {

        if (serverInformationRuntimeDao == null) {
            serverInformationRuntimeDao = getRuntimeExceptionDao(ServerInformation.class);
        }
        return serverInformationRuntimeDao;

    }
    */

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        //serverInformationRuntimeDao = null;
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

}