package com.jg.real_estate.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.material.textfield.TextInputLayout;
import com.jg.real_estate.DB.DatabaseHelper;
import com.jg.real_estate.MainActivity;
import com.jg.real_estate.R;

import java.io.IOException;

import static com.jg.real_estate.R.color.colorAccent;

public class AddHomeFragment extends Fragment {
//initializing variable
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
    Button save;
    String gLocation;
    DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_home,container,false);
//assigning view field
                googlePlacePickerText = view.findViewById(R.id.add_google_text);
                estateName = view.findViewById(R.id.estate_name_text_field);
                street= view.findViewById(R.id.street_text_field);
                city =view.findViewById(R.id.city_text_field);
                province =view.findViewById(R.id.province_text_field);
                postalCode =view.findViewById(R.id.postal_code_text_field);
                country =view.findViewById(R.id.country_text_field);
                phone=view.findViewById(R.id.phone_text_field);
                email=view.findViewById(R.id.email_text_field);
                estateType =view.findViewById(R.id.estate_type_text_field);
                floorSpace =view.findViewById(R.id.floor_space_text_field);
                numberOfBalconies=view.findViewById(R.id.number_of_balconies_text_field);
                balconiesSpace=view.findViewById(R.id.balconies_space_text_field);
                numberOfBedrooms=view.findViewById(R.id.number_of_bedrooms_text_field);
                numberOfBathrooms=view.findViewById(R.id.number_of_bathrooms_text_field);
                numberOfGarages=view.findViewById(R.id.number_of_garages_text_field);
                numberOfParkingSpace=view.findViewById(R.id.number_of_parking_spaces_text_field);
                description=view.findViewById(R.id.estate_description_text_field);
                status=view.findViewById(R.id.estate_status_text_field);
                price =view.findViewById(R.id.estate_price_text_field);
                petAllow =view.findViewById(R.id.pets_allowed_checkBox);
                save =view.findViewById(R.id.save_button);
                imageView1=view.findViewById(R.id.image1_imageView);
                imageView2=view.findViewById(R.id.image2_imageView);
                imageView3=view.findViewById(R.id.image3_imageView);
                imageView4=view.findViewById(R.id.image4_imageView);
                databaseHelper = new DatabaseHelper(getContext());

                //google Longitude latitude picker.
                googlePlacePickerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build((Activity) getContext()),
                     PLACE_PICKER_REQUEST
                     );
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

                //picking image from photo gallery
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image=1;
                if(ActivityCompat.checkSelfPermission(getActivity(),
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
//picking image from photo gallery
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image=2;
                if(ActivityCompat.checkSelfPermission(getActivity(),
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
//picking image from photo gallery
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image=3;
                if(ActivityCompat.checkSelfPermission(getActivity(),
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
//picking image from photo gallery
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image=4;
                if(ActivityCompat.checkSelfPermission(getActivity(),
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
//save data in database
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getting text from edit text field and hold in a string variable
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
//avoiding null pointer exception
                if(bitmap1 == null){
                    bitmap1 =BitmapFactory.decodeResource(getContext().getResources(),R.drawable.no_image);
                }
                if(bitmap2 == null){
                    bitmap2 =BitmapFactory.decodeResource(getContext().getResources(),R.drawable.no_image);
                }
                if(bitmap3 == null){
                    bitmap3 =BitmapFactory.decodeResource(getContext().getResources(),R.drawable.no_image);
                }
                if(bitmap4 == null){
                    bitmap4 =BitmapFactory.decodeResource(getContext().getResources(),R.drawable.no_image);
                }
//checking empty field
                if(estateNameST.isEmpty()){
                    Toast.makeText(getContext(), "Please Input Name.", Toast.LENGTH_SHORT).show();
                }else if(streetST.isEmpty()){
                    Toast.makeText(getContext(), "Please Input Street.", Toast.LENGTH_SHORT).show();
                }else if(cityST.isEmpty()){
                    Toast.makeText(getContext(), "Please Input city.", Toast.LENGTH_SHORT).show();
                }else if(provinceST.isEmpty()){
                    Toast.makeText(getContext(), "Please Input province.", Toast.LENGTH_SHORT).show();
                }else if(postalCodeST.isEmpty()){
                    Toast.makeText(getContext(), "Please Input Postal Code.", Toast.LENGTH_SHORT).show();
                }else if(countryST.isEmpty()){
                    Toast.makeText(getContext(), "Please Input Country.", Toast.LENGTH_SHORT).show();
                }else if(phoneST.isEmpty()){
                    Toast.makeText(getContext(), "Please Input Phone.", Toast.LENGTH_SHORT).show();
                }else if(emailST.isEmpty()){
                    Toast.makeText(getContext(), "Please Input Email.", Toast.LENGTH_SHORT).show();
                }else if(estateTypeST.isEmpty()){
                    Toast.makeText(getContext(), "Please Input Estate Type.", Toast.LENGTH_SHORT).show();
                }else if(floorSpaceST.isEmpty()){
                    Toast.makeText(getContext(), "Please Input Floor Space.", Toast.LENGTH_SHORT).show();
                }else if(numberOfBalconiesST.isEmpty()){
                    Toast.makeText(getContext(), "Please Input Number of Balconies.", Toast.LENGTH_SHORT).show();
                }else if(balconiesSpaceST.isEmpty()){
                    Toast.makeText(getContext(), "Please Input Balconies Space.", Toast.LENGTH_SHORT).show();
                }else if(numberOfBedroomsST.isEmpty()){
                    Toast.makeText(getContext(), "Please Input Number of Bedrooms.", Toast.LENGTH_SHORT).show();
                }else if(numberOfGaragesST.isEmpty()){
                    Toast.makeText(getContext(), "Please Input number of Garages.", Toast.LENGTH_SHORT).show();
                }else if(numberOfParkingSpaceST.isEmpty()){
                    Toast.makeText(getContext(), "Please Input number of Parking Space.", Toast.LENGTH_SHORT).show();
                }else if(descriptionST.isEmpty()){
                    Toast.makeText(getContext(), "Please Input description.", Toast.LENGTH_SHORT).show();
                }else if(statusST.isEmpty()){
                    Toast.makeText(getContext(), "Please Input status.", Toast.LENGTH_SHORT).show();
                }else if(priceST.isEmpty()){
                    Toast.makeText(getContext(), "Please Input price.", Toast.LENGTH_SHORT).show();
                }else if(gLocation.isEmpty()){
                    Toast.makeText(getContext(), "Please Input Google Location.", Toast.LENGTH_SHORT).show();
                }

                else{
//save data in data base
                        boolean addData = databaseHelper.addData(estateNameST,
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
                        //checking if successful
                        if(addData){
                            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                            estateName.getEditText().setText("");
                        }
                        else {
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                }





            }
        });

        return view;
    }
//browsing gallery to save
    private void startGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//picking google location and hold in a string variable
       if(requestCode == PLACE_PICKER_REQUEST){
           if(resultCode == -1){
               Place place =PlacePicker.getPlace(data,getContext());
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
        //getting image from gallery and hold in a image field
        if(resultCode == -1) {
            if(requestCode == 1000){
                Uri returnUri = data.getData();
                Bitmap bitmapImage = null;
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), returnUri);
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

}
