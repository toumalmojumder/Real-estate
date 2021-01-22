package com.jg.real_estate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jg.real_estate.DB.DatabaseHelper;
import com.jg.real_estate.Model.House;
import com.jg.real_estate.fragment.AllHomeFragment;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
ImageView imageView1,imageView2,imageView3,imageView4;
TextView name,location,phone,
        email,estateType,floor,numberOfBalconies,
        balconiesSpace,numberOfBed,numberOfBathroom,
     numberOfGarage,numberOfParking,petAllowed,desc,status,price;
Button update,delete;

    ArrayList<House> houses;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        final int position = (int) extras.get("position");

        imageView1 = findViewById(R.id.detail_image1);
        imageView2 = findViewById(R.id.detail_image2);
        imageView3 = findViewById(R.id.detail_image3);
        imageView4 = findViewById(R.id.detail_image4);

        update=findViewById(R.id.detail_update);
        delete =findViewById(R.id.detail_delete);

        name=findViewById(R.id.detail_name);
        location=findViewById(R.id.detail_address);
        phone=findViewById(R.id.detail_phone);
        email=findViewById(R.id.detail_Email);
        estateType=findViewById(R.id.detail_estate_type);
        floor =findViewById(R.id.detail_floor_space);
        numberOfBalconies=findViewById(R.id.detail_number_of_balconies);
        balconiesSpace=findViewById(R.id.detail_balconies_space);
        numberOfBed=findViewById(R.id.detail_number_of_bed);
        numberOfBathroom=findViewById(R.id.detail_number_of_bathroom);
        numberOfGarage=findViewById(R.id.detail_number_of_garage);
        numberOfParking=findViewById(R.id.detail_number_of_parking);
        petAllowed=findViewById(R.id.detail_pet);
        desc=findViewById(R.id.detail_desc);
        status=findViewById(R.id.detail_status);
        price=findViewById(R.id.detail_price);
        databaseHelper = new DatabaseHelper(this);
        houses= databaseHelper.getAllData();

        name.setText(houses.get(position).getEstate_name());

        String address= "Address:"
        +houses.get(position).getStreet()+
        ", "
        +houses.get(position).getCity()
        +", "
        +houses.get(position).getProvince()
        +", "+houses.get(position).getPostal_code()
        +", "+houses.get(position).getCountry()+". ";
        location.setText(address);

        String phoneST = "Phone:"+houses.get(position).getPhone();
        String emailST = "Email:"+houses.get(position).getEmail();
        String estateTypeST = "Estate Type:"+houses.get(position).getEmail();
        String floorSpaceST = "Floor Space:"+houses.get(position).getFloor_space();
        String balconiesnumberST = "Number of Balconies:"+houses.get(position).getNumber_of_balconies();
        String balconiesSpaceST = "Balconies Space:"+houses.get(position).getBalconies_space();
        String numberBedST = "Number of Beds:"+houses.get(position).getNumber_of_bedrooms();
        String numberBathST = "Number of Bathrooms:"+houses.get(position).getNumber_of_bathrooms();
        String numberGarageST = "number of Garage:"+houses.get(position).getNumber_of_garages();
        String numberParkingST = "Number of Parking Space:"+houses.get(position).getNumber_of_parking_spaces();
        String descST = "Description:"+houses.get(position).getEstate_description();
        String statusST = "Status:"+houses.get(position).getEstate_status();
        String priceST = "Price: $"+houses.get(position).getEstate_price()+"/-";
        String petST;
        if(houses.get(position).isPets_allowed()){
            petST = "Pet friendly";
        }
        else{
            petST = "Pet not Allowed";
        }

        phone.setText(phoneST);
        email.setText(emailST);
        estateType.setText(estateTypeST);
        floor.setText(floorSpaceST);
        numberOfBalconies.setText(balconiesnumberST);
        balconiesSpace.setText(balconiesSpaceST);
        numberOfBed.setText(numberBedST);
        numberOfBathroom.setText(numberBathST);
        numberOfGarage.setText(numberGarageST);
        numberOfParking.setText(numberParkingST);
        petAllowed.setText(petST);
        desc.setText(descST);
        status.setText(statusST);
        price.setText(priceST);

        imageView1.setImageBitmap(houses.get(position).getEstate_image1());
        imageView2.setImageBitmap(houses.get(position).getEstate_image2());
        imageView3.setImageBitmap(houses.get(position).getEstate_image3());
        imageView4.setImageBitmap(houses.get(position).getEstate_image4());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean delete = databaseHelper.deleteData(String.valueOf( houses.get(position).getID()));
                if(delete){
                    Intent intent = new Intent(DetailActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(DetailActivity.this,UpdateActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }
}