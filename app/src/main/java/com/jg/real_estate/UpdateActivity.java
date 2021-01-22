package com.jg.real_estate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.material.textfield.TextInputLayout;
import com.jg.real_estate.DB.DatabaseHelper;
import com.jg.real_estate.Model.House;

import java.io.IOException;
import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    // thist class is same as addHome fragment class
    TextView googlePlacePickerText;
    int PLACE_PICKER_REQUEST= 1;
    private static final int PICK_IMAGE_REQUEST= 100;
    private Uri imageFilePath;
    int image = 0;

    TextInputLayout estateName,
            street,
            city,
            province,
            postalCode,
            country,
            phone,
            email,
            estateType,
            floorSpace,
            numberOfBalconies,
            balconiesSpace,
            numberOfBedrooms,
            numberOfBathrooms,
            numberOfGarages,
            numberOfParkingSpace,
            description,
            status,
            price;
    CheckBox petAllow;
    Bitmap bitmap1,bitmap2,bitmap3,bitmap4;
    ImageView imageView1,imageView2,imageView3,imageView4;
    Button update;
    String gLocation;
    DatabaseHelper databaseHelper;
    ArrayList<House> houses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Bundle extras = getIntent().getExtras();
        final int position = (int) extras.get("position");

        googlePlacePickerText = findViewById(R.id.add_google_text);
        estateName = findViewById(R.id.estate_name_text_field);
        street= findViewById(R.id.street_text_field);
        city =findViewById(R.id.city_text_field);
        province =findViewById(R.id.province_text_field);
        postalCode =findViewById(R.id.postal_code_text_field);
        country =findViewById(R.id.country_text_field);
        phone=findViewById(R.id.phone_text_field);
        email=findViewById(R.id.email_text_field);
        estateType =findViewById(R.id.estate_type_text_field);
        floorSpace =findViewById(R.id.floor_space_text_field);
        numberOfBalconies=findViewById(R.id.number_of_balconies_text_field);
        balconiesSpace=findViewById(R.id.balconies_space_text_field);
        numberOfBedrooms=findViewById(R.id.number_of_bedrooms_text_field);
        numberOfBathrooms=findViewById(R.id.number_of_bathrooms_text_field);
        numberOfGarages=findViewById(R.id.number_of_garages_text_field);
        numberOfParkingSpace=findViewById(R.id.number_of_parking_spaces_text_field);
        description=findViewById(R.id.estate_description_text_field);
        status=findViewById(R.id.estate_status_text_field);
        price =findViewById(R.id.estate_price_text_field);
        petAllow =findViewById(R.id.pets_allowed_checkBox);
        update =findViewById(R.id.update_button);
        imageView1=findViewById(R.id.image1_imageView);
        imageView2=findViewById(R.id.image2_imageView);
        imageView3=findViewById(R.id.image3_imageView);
        imageView4=findViewById(R.id.image4_imageView);

        databaseHelper = new DatabaseHelper(this);
        houses = databaseHelper.getAllData();

                estateName.getEditText().setText(houses.get(position).getEstate_name());
                street.getEditText().setText(houses.get(position).getStreet());
                city.getEditText().setText(houses.get(position).getCity());
                province.getEditText().setText(houses.get(position).getProvince());
                postalCode.getEditText().setText(houses.get(position).getPostal_code());
                country.getEditText().setText(houses.get(position).getCountry());
                phone.getEditText().setText(houses.get(position).getPhone());
                email.getEditText().setText(houses.get(position).getEmail());
                estateType.getEditText().setText(houses.get(position).getEstate_type_name());
                floorSpace.getEditText().setText(houses.get(position).getFloor_space());
                numberOfBalconies.getEditText().setText(houses.get(position).getNumber_of_balconies());
                balconiesSpace.getEditText().setText(houses.get(position).getBalconies_space());
                numberOfBedrooms.getEditText().setText(houses.get(position).getNumber_of_bedrooms());
                numberOfBathrooms.getEditText().setText(houses.get(position).getNumber_of_bathrooms());
                numberOfGarages.getEditText().setText(houses.get(position).getNumber_of_garages());
                numberOfParkingSpace.getEditText().setText(houses.get(position).getNumber_of_parking_spaces());
                description.getEditText().setText(houses.get(position).getEstate_description());
                status.getEditText().setText(houses.get(position).getEstate_status());
                price.getEditText().setText(houses.get(position).getEstate_price());

                googlePlacePickerText.setText(houses.get(position).getGoogle_location());
                gLocation =houses.get(position).getGoogle_location();

                imageView1.setImageBitmap(houses.get(position).getEstate_image1());
                imageView2.setImageBitmap(houses.get(position).getEstate_image2());
                imageView3.setImageBitmap(houses.get(position).getEstate_image3());
                imageView4.setImageBitmap(houses.get(position).getEstate_image4());

        googlePlacePickerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(UpdateActivity.this),
                            PLACE_PICKER_REQUEST
                    );
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image=1;
                if(ActivityCompat.checkSelfPermission(UpdateActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000);
                }
                else {
                    startGallery();
                }
            }



        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image=2;
                if(ActivityCompat.checkSelfPermission(UpdateActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000);
                }
                else {
                    startGallery();
                }

            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image=3;
                if(ActivityCompat.checkSelfPermission(UpdateActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000);
                }
                else {
                    startGallery();
                }
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image=4;
                if(ActivityCompat.checkSelfPermission(UpdateActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000);
                }
                else {
                    startGallery();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String estateNameST = estateName.getEditText().getText().toString();;
                String streetST=street.getEditText().getText().toString();
                String cityST=city.getEditText().getText().toString();
                String provinceST =province.getEditText().getText().toString();
                String postalCodeST=postalCode.getEditText().getText().toString();
                String countryST=country.getEditText().getText().toString();
                String phoneST=phone.getEditText().getText().toString();
                String emailST=email.getEditText().getText().toString();
                String estateTypeST=estateType.getEditText().getText().toString();
                String floorSpaceST=floorSpace.getEditText().getText().toString();
                String numberOfBalconiesST=numberOfBalconies.getEditText().getText().toString();
                String balconiesSpaceST=balconiesSpace.getEditText().getText().toString();
                String numberOfBedroomsST=numberOfBedrooms.getEditText().getText().toString();
                String numberOfBathroomsST=numberOfBathrooms.getEditText().getText().toString();

                String numberOfGaragesST=numberOfGarages.getEditText().getText().toString();
                String  numberOfParkingSpaceST=numberOfParkingSpace.getEditText().getText().toString();
                String  descriptionST=description.getEditText().getText().toString();
                String  statusST=status.getEditText().getText().toString();
                String  priceST=price.getEditText().getText().toString();
                boolean petAllowedBool = petAllow.isChecked();



                if(bitmap1 == null){
                    bitmap1 = BitmapFactory.decodeResource(UpdateActivity.this.getResources(),R.drawable.no_image);
                }
                if(bitmap2 == null){
                    bitmap2 =BitmapFactory.decodeResource(UpdateActivity.this.getResources(),R.drawable.no_image);
                }
                if(bitmap3 == null){
                    bitmap3 =BitmapFactory.decodeResource(UpdateActivity.this.getResources(),R.drawable.no_image);
                }
                if(bitmap4 == null){
                    bitmap4 =BitmapFactory.decodeResource(UpdateActivity.this.getResources(),R.drawable.no_image);
                }

                if(estateNameST.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input Name.", Toast.LENGTH_SHORT).show();
                }else if(streetST.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input Street.", Toast.LENGTH_SHORT).show();
                }else if(cityST.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input city.", Toast.LENGTH_SHORT).show();
                }else if(provinceST.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input province.", Toast.LENGTH_SHORT).show();
                }else if(postalCodeST.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input Postal Code.", Toast.LENGTH_SHORT).show();
                }else if(countryST.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input Country.", Toast.LENGTH_SHORT).show();
                }else if(phoneST.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input Phone.", Toast.LENGTH_SHORT).show();
                }else if(emailST.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input Email.", Toast.LENGTH_SHORT).show();
                }else if(estateTypeST.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input Estate Type.", Toast.LENGTH_SHORT).show();
                }else if(floorSpaceST.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input Floor Space.", Toast.LENGTH_SHORT).show();
                }else if(numberOfBalconiesST.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input Number of Balconies.", Toast.LENGTH_SHORT).show();
                }else if(balconiesSpaceST.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input Balconies Space.", Toast.LENGTH_SHORT).show();
                }else if(numberOfBedroomsST.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input Number of Bedrooms.", Toast.LENGTH_SHORT).show();
                }else if(numberOfGaragesST.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input number of Garages.", Toast.LENGTH_SHORT).show();
                }else if(numberOfParkingSpaceST.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input number of Parking Space.", Toast.LENGTH_SHORT).show();
                }else if(descriptionST.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input description.", Toast.LENGTH_SHORT).show();
                }else if(statusST.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input status.", Toast.LENGTH_SHORT).show();
                }else if(priceST.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input price.", Toast.LENGTH_SHORT).show();
                }else if(gLocation.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please Input Google Location.", Toast.LENGTH_SHORT).show();
                }

                else{

                    boolean updateData = databaseHelper.updateData(String.valueOf(houses.get(position).getID()),estateNameST,
                            streetST,
                            cityST,
                            provinceST,
                            postalCodeST,
                            countryST,
                            phoneST,
                            emailST,
                            gLocation,
                            estateTypeST,
                            floorSpaceST,
                            numberOfBalconiesST,
                            balconiesSpaceST,
                            numberOfBedroomsST,
                            numberOfBathroomsST,
                            numberOfGaragesST,
                            numberOfParkingSpaceST,
                            petAllowedBool,  descriptionST,
                            statusST,  priceST,
                            bitmap1,  bitmap2,
                            bitmap3,  bitmap4
                    );
                    if(updateData){
                        Toast.makeText(UpdateActivity.this, "Update Success!", Toast.LENGTH_SHORT).show();
                        Intent intent=  new Intent(UpdateActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(UpdateActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                    }
                }





            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PLACE_PICKER_REQUEST){
            if(resultCode == -1){
                Place place =PlacePicker.getPlace(data,UpdateActivity.this);
                StringBuilder stringBuilder = new StringBuilder();
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);
                stringBuilder.append("LATITUDE :");
                stringBuilder.append(latitude);
                stringBuilder.append("\n");
                stringBuilder.append("LONGITUDE :");
                stringBuilder.append(longitude);
                googlePlacePickerText.setText(stringBuilder.toString());
                gLocation= stringBuilder.toString();



            }
        }
        if(resultCode == -1) {
            if(requestCode == 1000){
                Uri returnUri = data.getData();
                Bitmap bitmapImage = null;
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(UpdateActivity.this.getContentResolver(), returnUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(image==1){
                    bitmap1 = bitmapImage;
                    imageView1.setImageBitmap(bitmapImage);
                }else    if(image==2){
                    bitmap2 = bitmapImage;
                    imageView2.setImageBitmap(bitmapImage);
                }else    if(image==3){
                    bitmap3 = bitmapImage;
                    imageView3.setImageBitmap(bitmapImage);
                }else    if(image==4){
                    bitmap4 = bitmapImage;
                    imageView4.setImageBitmap(bitmapImage);
                }

            }
        }


    }

    private void startGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        if (cameraIntent.resolveActivity(UpdateActivity.this.getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }

}