package com.example.fixingmaterial;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fixingmaterial.Adaptor.StockAdaptor;
import com.example.fixingmaterial.Domain.StockDomain;
import com.example.fixingmaterial.Helper.ManagmentCart;

import java.util.ArrayList;

public class AboutfoodActivity extends AppCompatActivity {
    static ArrayList<StockDomain> abFoodDomain = new ArrayList<>();

    private StockDomain pizzaDomain;
    TextView abName, abDescription;
    ImageView abImage;
    Button abAddBut;

    private ManagmentCart managmentCart;

    public static String cartTitle, cartDescription, cartImage;
    public static int cartPrice;
    public static double cartFats, cartProtein, cartCarbon;

    fragment_Cart fragmentCart;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abproduct);

        fragmentCart = new fragment_Cart();
        managmentCart = new ManagmentCart(this);

        getSupportActionBar().hide();

        pizzaDomain = (StockDomain) getIntent().getSerializableExtra("object");

        init();
        setText();

        cartTitle = StockAdaptor.stringName;
        cartImage = StockAdaptor.stringImage;
        cartDescription = StockAdaptor.stringDescription;
        cartPrice = StockAdaptor.stringPrice;
        cartCarbon = StockAdaptor.stringCarbon;
        cartFats = StockAdaptor.stringFats;
        cartProtein = StockAdaptor.stringProtein;

        abName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        abAddBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_Cart.qtyItems += 1;


                pizzaDomain.setCartPosition(1);
                managmentCart.insertFood(pizzaDomain);
            }
        });
    }

    private void init()
    {
        abName = findViewById(R.id.abFoodTitle);
        abDescription = findViewById(R.id.abFoodDescription);
        abImage = findViewById(R.id.abFoodImage);
        abAddBut = findViewById(R.id.abAddButton);
    }

    private void setText()
    {
        abName.setText(pizzaDomain.getTitle());
        abDescription.setText(pizzaDomain.getDescription());
        int drawableResourceId = this.getResources().getIdentifier(pizzaDomain.getImage(), "drawable", this.getPackageName());
        abImage.setImageResource(drawableResourceId);
        abAddBut.setText("Добавить в корзину за " + pizzaDomain.getPrice() + " ₽");
    }
}