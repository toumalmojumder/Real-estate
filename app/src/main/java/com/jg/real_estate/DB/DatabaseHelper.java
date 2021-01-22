package com.jg.real_estate.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jg.real_estate.Model.House;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Initialize Variable
    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "house";
    private static final String COL1 = "id";
    private static final String COL2 = "estate_name";
    private static final String COL3 = "street";
    private static final String COL4 = "city";
    private static final String COL5 = "province";
    private static final String COL6 = "postal_code";
    private static final String COL7 = "country";
    private static final String COL8 = "phone";
    private static final String COL9 = "email";
    private static final String COL10 = "google_location";
    private static final String COL11 = "estate_type_name";
    private static final String COL12 = "floor_space";

    private static final String COL13 = "number_of_balconies";
    private static final String COL14 = "balconies_space";
    private static final String COL15 = "number_of_bedrooms";
    private static final String COL16 = "number_of_bathrooms";
    private static final String COL17 = "number_of_garages";
    private static final String COL18 = "number_of_parking_spaces";
    private static final String COL19 = "pets_allowed";
    private static final String COL20 = "estate_description";
    private static final String COL21 = "estate_status";
    private static final String COL22 = "estate_price";

    private static final String COL23 = "estate_image1";
    private static final String COL24 = "estate_image2";
    private static final String COL25 = "estate_image3";
    private static final String COL26 = "estate_image4";

private ByteArrayOutputStream outputStream1,outputStream2,outputStream3,outputStream4;
private  byte[] imageInByte1,imageInByte2,imageInByte3,imageInByte4;

//database constractor
    public DatabaseHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME+ " ("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL2 + " TEXT, "+
                COL3 + " TEXT, "+
                COL4 + " TEXT, "+
                COL5 + " TEXT, "+
                COL6 + " TEXT, "+
                COL7 + " TEXT, "+
                COL8 + " TEXT, "+
                COL9 + " TEXT, "+
                COL10 + " TEXT, "+
                COL11 + " TEXT, "+
                COL12 + " TEXT, "+
                COL13 + " TEXT, "+
                COL14 + " TEXT, "+
                COL15 + " TEXT, "+
                COL16 + " TEXT, "+
                COL17 + " TEXT, "+
                COL18 + " TEXT, "+
                COL19 + " Boolean, "+
                COL20 + " TEXT, "+
                COL21 + " TEXT, "+
                COL22 + " TEXT, "+
                COL23 + " BLOB, "+
                COL24 + " BLOB, "+
                COL25 + " BLOB, "+
                COL26 + " BLOB)"
                ;
        sqLiteDatabase.execSQL(createTable);
    }
//drop table
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    //add data request
    public boolean addData(String estate_name, String street, String city, String province, String postal_code,
                           String country, String phone, String email,
                           String google_location, String estate_type_name,
                           String floor_space, String number_of_balconies,
                           String balconies_space, String number_of_bedrooms,
                           String number_of_bathrooms, String number_of_garages,
                           String number_of_parking_spaces,
                           boolean pets_allowed, String estate_description,
                           String estate_status, String estate_price,
                           Bitmap estate_image1, Bitmap estate_image2,
                           Bitmap estate_image3, Bitmap estate_image4){

        SQLiteDatabase db = this.getWritableDatabase();

if(estate_image1!=null){
    outputStream1 = new ByteArrayOutputStream();
    estate_image1.compress(Bitmap.CompressFormat.JPEG,100,outputStream1);
    imageInByte1= outputStream1.toByteArray();
}

        if(estate_image2!=null){
            outputStream2 = new ByteArrayOutputStream();
            estate_image2.compress(Bitmap.CompressFormat.JPEG,100,outputStream2);
            imageInByte2= outputStream1.toByteArray();
        }
        if(estate_image3!=null) {
            outputStream3 = new ByteArrayOutputStream();
            estate_image3.compress(Bitmap.CompressFormat.JPEG, 100, outputStream3);
            imageInByte3 = outputStream1.toByteArray();
        }
        if(estate_image4!=null) {
            outputStream4 = new ByteArrayOutputStream();
            estate_image4.compress(Bitmap.CompressFormat.JPEG, 100, outputStream4);
            imageInByte4 = outputStream1.toByteArray();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,estate_name);
        contentValues.put(COL3,street);
        contentValues.put(COL4,city);
        contentValues.put(COL5,province);
        contentValues.put(COL6,postal_code);
        contentValues.put(COL7,country);
        contentValues.put(COL8,phone);
        contentValues.put(COL9,email);
        contentValues.put(COL10,google_location);
        contentValues.put(COL11,estate_type_name);

        contentValues.put(COL12,floor_space);
        contentValues.put(COL13,number_of_balconies);
        contentValues.put(COL14,balconies_space);
        contentValues.put(COL15,number_of_bedrooms);
        contentValues.put(COL16,number_of_bathrooms);
        contentValues.put(COL17,number_of_garages);
        contentValues.put(COL18,number_of_parking_spaces);
        contentValues.put(COL19,pets_allowed);
        contentValues.put(COL20,estate_description);
        contentValues.put(COL21,estate_status);
        contentValues.put(COL22,estate_price);
        contentValues.put(COL23, imageInByte1);
        contentValues.put(COL24,imageInByte2);
        contentValues.put(COL25,imageInByte3);
        contentValues.put(COL26,imageInByte4);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
//get all data request
    public ArrayList<House> getAllData() {
        ArrayList<House> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String estate_name = cursor.getString(1);
            String street = cursor.getString(2);
            String city = cursor.getString(3);
            String province = cursor.getString(4);
            String postal_code = cursor.getString(5);
            String country = cursor.getString(6);
            String phone = cursor.getString(7);
            String email = cursor.getString(8);
            String google_location = cursor.getString(9);
            String estate_type_name = cursor.getString(10);
            String floor_space = cursor.getString(11);
            String number_of_balconies = cursor.getString(12);
            String balconies_space = cursor.getString(13);
            String number_of_bedrooms = cursor.getString(14);
            String number_of_bathrooms = cursor.getString(15);
            String number_of_garages = cursor.getString(16);
            String number_of_parking_spaces = cursor.getString(17);
            Boolean pets_allowed = cursor.getInt(18)>0;
            String estate_description = cursor.getString(19);
            String estate_status = cursor.getString(20);
            String estate_price = cursor.getString(21);
            byte[] estate_image1 = cursor.getBlob(22);
            byte[] estate_image2 = cursor.getBlob(23);
            byte[] estate_image3 = cursor.getBlob(24);
            byte[] estate_image4 = cursor.getBlob(25);

            Bitmap bitmap1 = BitmapFactory.decodeByteArray(estate_image1,0,estate_image1.length);
            Bitmap bitmap2 = BitmapFactory.decodeByteArray(estate_image2,0,estate_image1.length);
            Bitmap bitmap3 = BitmapFactory.decodeByteArray(estate_image3,0,estate_image1.length);
            Bitmap bitmap4 = BitmapFactory.decodeByteArray(estate_image4,0,estate_image1.length);


            House house = new House(id,estate_name,street,city,province,postal_code,
                   country,phone,email, google_location,estate_type_name,floor_space,number_of_balconies, balconies_space,number_of_bedrooms,
                    number_of_bathrooms,  number_of_garages, number_of_parking_spaces,
                        pets_allowed,  estate_description,
                     estate_status,  estate_price,
                     bitmap1,  bitmap2,
                    bitmap3,  bitmap4);
            arrayList.add(house);

        }
        return arrayList;
    }
//delete data request
    public boolean deleteData(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" where id = ?",new String[]{name});

        if(cursor.getCount()>0){
            long result = db.delete(TABLE_NAME,"id=?",new String[]{name});
            if(result == -1){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }
//update data request
    public boolean updateData(String id, String estate_name, String street, String city, String province, String postal_code,
                           String country, String phone, String email,
                           String google_location, String estate_type_name,
                           String floor_space, String number_of_balconies,
                           String balconies_space, String number_of_bedrooms,
                           String number_of_bathrooms, String number_of_garages,
                           String number_of_parking_spaces,
                           boolean pets_allowed, String estate_description,
                           String estate_status, String estate_price,
                           Bitmap estate_image1, Bitmap estate_image2,
                           Bitmap estate_image3, Bitmap estate_image4){

        SQLiteDatabase db = this.getWritableDatabase();

//to avoid null pointer exception
        if(estate_image1!=null){
            outputStream1 = new ByteArrayOutputStream();
            estate_image1.compress(Bitmap.CompressFormat.JPEG,100,outputStream1);
            imageInByte1= outputStream1.toByteArray();
        }
        if(estate_image2!=null){
            outputStream2 = new ByteArrayOutputStream();
            estate_image2.compress(Bitmap.CompressFormat.JPEG,100,outputStream2);
            imageInByte2= outputStream1.toByteArray();
        }
        if(estate_image3!=null) {
            outputStream3 = new ByteArrayOutputStream();
            estate_image3.compress(Bitmap.CompressFormat.JPEG, 100, outputStream3);
            imageInByte3 = outputStream1.toByteArray();
        }
        if(estate_image4!=null) {
            outputStream4 = new ByteArrayOutputStream();
            estate_image4.compress(Bitmap.CompressFormat.JPEG, 100, outputStream4);
            imageInByte4 = outputStream1.toByteArray();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2,estate_name);
        contentValues.put(COL3,street);
        contentValues.put(COL4,city);
        contentValues.put(COL5,province);
        contentValues.put(COL6,postal_code);
        contentValues.put(COL7,country);
        contentValues.put(COL8,phone);
        contentValues.put(COL9,email);
        contentValues.put(COL10,google_location);
        contentValues.put(COL11,estate_type_name);
        contentValues.put(COL12,floor_space);
        contentValues.put(COL13,number_of_balconies);
        contentValues.put(COL14,balconies_space);
        contentValues.put(COL15,number_of_bedrooms);
        contentValues.put(COL16,number_of_bathrooms);
        contentValues.put(COL17,number_of_garages);
        contentValues.put(COL18,number_of_parking_spaces);
        contentValues.put(COL19,pets_allowed);
        contentValues.put(COL20,estate_description);
        contentValues.put(COL21,estate_status);
        contentValues.put(COL22,estate_price);
        contentValues.put(COL23, imageInByte1);
        contentValues.put(COL24,imageInByte2);
        contentValues.put(COL25,imageInByte3);
        contentValues.put(COL26,imageInByte4);
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" where id = ?",new String[]{id});
        if(cursor.getCount()>0){
            long result = db.update(TABLE_NAME, contentValues,"id= ?",new String[]{id});
            if(result == -1){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }
}
