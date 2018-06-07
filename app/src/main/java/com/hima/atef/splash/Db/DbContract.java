package com.hima.atef.splash.Db;

import android.provider.BaseColumns;

/**
 * Created by hima on 3/25/2018.
 */

public class DbContract {

    private DbContract() {}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "MyTry";
        public static final String _ID = "id";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_FAGR = "Fagr";
        public static final String COLUMN_NAME_SHROUQ = "Shrouq";
        public static final String COLUMN_NAME_ZOHR = "Zohr";
        public static final String COLUMN_NAME_ASR = "Asr";
        public static final String COLUMN_NAME_MAGREB = "Magreb";
        public static final String COLUMN_NAME_ISHAA = "Ishaa";
        public static final String COLUMN_NAME_EMSAK = "Emsak";

    }
}
