package quetzalsoft.berserkr.qm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

/**
 * Created by lbathen on 7/6/17.
 */

public class Database extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_SALT = "salt";
    private static final String KEY_EMAIL= "email";
    private static final String KEY_BC_SK = "bc_secret_key";
    private static final String KEY_BC_PK = "bc_public_key";
    private static final String KEY_BC_ADDRESS = "bc_address";
    private static final String KEY_RSA_SK = "rsa_secret_key";
    private static final String KEY_RSA_PK = "rsa_public_key";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE "
                + TABLE_CONTACTS + "("
                + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_SALT + " TEXT,"
                + KEY_BC_SK + " TEXT,"
                + KEY_BC_PK + " TEXT,"
                + KEY_BC_ADDRESS + " TEXT,"
                + KEY_RSA_SK + " TEXT,"
                + KEY_RSA_PK + " TEXT" + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addUserProfile(UserProfile profile) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, profile.getId().toString());
        values.put(KEY_NAME, profile.getUsername());
        values.put(KEY_PASSWORD, profile.getPassword());
        values.put(KEY_EMAIL, profile.getEmail());
        values.put(KEY_SALT, profile.getSalt());
        values.put(KEY_BC_SK, profile.getBcSecretKey());
        values.put(KEY_BC_PK, profile.getBcPublicKey());
        values.put(KEY_BC_ADDRESS, profile.getBcAddress());
        values.put(KEY_RSA_SK, profile.getRsaSecretKey());
        values.put(KEY_RSA_PK, profile.getRsaPublicKey());

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    UserProfile getProfileId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PASSWORD, KEY_EMAIL, KEY_SALT, KEY_BC_SK,
                        KEY_BC_PK, KEY_BC_ADDRESS, KEY_RSA_SK, KEY_RSA_PK}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        if(cursor.getCount()==0) {
            return null;
        }

        UserProfile profile = new UserProfile();
        profile.setId(UUID.fromString(cursor.getString(0)));
        profile.setUsername(cursor.getString(1));
        profile.setPassword(cursor.getString(2));
        profile.setEmail(cursor.getString(3));
        profile.setSalt(cursor.getString(4));
        profile.setBcSecretKey(cursor.getString(5));
        profile.setBcPublicKey(cursor.getString(6));
        profile.setBcAddress(cursor.getString(7));
        profile.setRsaSecretKey(cursor.getString(8));
        profile.setRsaPublicKey(cursor.getString(9));

        // return contact
        return profile;
    }

    UserProfile getProfileEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PASSWORD, KEY_EMAIL, KEY_SALT, KEY_BC_SK,
                        KEY_BC_PK, KEY_BC_ADDRESS, KEY_RSA_SK, KEY_RSA_PK}, KEY_EMAIL + "=?",
                new String[] { email }, null, null, null, null);

        if (cursor != null )
            cursor.moveToFirst();

        if(cursor.getCount()==0) {
            return null;
        }

        UserProfile profile = new UserProfile();
        profile.setId(UUID.fromString(cursor.getString(0)));
        profile.setUsername(cursor.getString(1));
        profile.setPassword(cursor.getString(2));
        profile.setEmail(cursor.getString(3));
        profile.setSalt(cursor.getString(4));
        profile.setBcSecretKey(cursor.getString(5));
        profile.setBcPublicKey(cursor.getString(6));
        profile.setBcAddress(cursor.getString(7));
        profile.setRsaSecretKey(cursor.getString(8));
        profile.setRsaPublicKey(cursor.getString(9));

        // return contact
        return profile;
    }

    UserProfile getProfileAddress(String address) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PASSWORD, KEY_EMAIL, KEY_SALT, KEY_BC_SK,
                        KEY_BC_PK, KEY_BC_ADDRESS, KEY_RSA_SK, KEY_RSA_PK}, KEY_BC_ADDRESS + "=?",
                new String[] { address }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        if(cursor.getCount()==0) {
            return null;
        }

        UserProfile profile = new UserProfile();
        profile.setId(UUID.fromString(cursor.getString(0)));
        profile.setUsername(cursor.getString(1));
        profile.setPassword(cursor.getString(2));
        profile.setEmail(cursor.getString(3));
        profile.setSalt(cursor.getString(4));
        profile.setBcSecretKey(cursor.getString(5));
        profile.setBcPublicKey(cursor.getString(6));
        profile.setBcAddress(cursor.getString(7));
        profile.setRsaSecretKey(cursor.getString(8));
        profile.setRsaPublicKey(cursor.getString(9));

        // return contact
        return profile;
    }

    // Getting All Contacts
    public List<UserProfile> getContactList() {

        List<UserProfile> contactList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserProfile profile = new UserProfile();

                profile.setId(UUID.fromString(cursor.getString(0)));
                profile.setUsername(cursor.getString(1));
                profile.setPassword(cursor.getString(2));
                profile.setEmail(cursor.getString(3));
                profile.setSalt(cursor.getString(4));
                profile.setBcSecretKey(cursor.getString(5));
                profile.setBcPublicKey(cursor.getString(6));
                profile.setBcAddress(cursor.getString(7));
                profile.setRsaSecretKey(cursor.getString(8));
                profile.setRsaPublicKey(cursor.getString(9));

                // Adding contact to list
                contactList.add(profile);

            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single contact
    public int updateContactProfile(UserProfile profile) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, profile.getId().toString());
        values.put(KEY_NAME, profile.getUsername());
        values.put(KEY_PASSWORD, profile.getPassword());
        values.put(KEY_EMAIL, profile.getEmail());
        values.put(KEY_SALT, profile.getSalt());
        values.put(KEY_BC_SK, profile.getBcSecretKey());
        values.put(KEY_BC_PK, profile.getBcPublicKey());
        values.put(KEY_BC_ADDRESS, profile.getBcAddress());
        values.put(KEY_RSA_SK, profile.getRsaSecretKey());
        values.put(KEY_RSA_PK, profile.getRsaPublicKey());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(profile.getId().toString()) });
    }

    // Deleting single contact profile
    public void deleteContactProfile(UserProfile profile) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(profile.getId().toString()) });
        db.close();
    }


    // Get the number of contacts
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
