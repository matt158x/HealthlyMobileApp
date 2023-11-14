package com.example.healthlyandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class DietActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);





        setStatusBarTransparent();
    }

    private void setStatusBarTransparent() {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(android.graphics.Color.TRANSPARENT);
        }


        Objects.requireNonNull(getSupportActionBar()).hide();




        ImageView imageView1_1 = findViewById(R.id.imageView1_1);
        imageView1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego intentu i przekazanie informacji do RecipeActivity
                Intent intent = new Intent(DietActivity.this, RecipeActivity.class);
                intent.putExtra("imageId", R.drawable.full_grannola); // Przykładowa informacja do przekazania
                intent.putExtra("mealName", "Granola with Fruits");
                intent.putExtra("calories", "412");
                intent.putExtra("proteins", "9");
                intent.putExtra("carbs", "77");
                intent.putExtra("fat", "12");
                intent.putExtra("ingredients", "3 cups old fashioned oats\n" +
                        "1 cup flaked coconut\n" +
                        "1/2 cup chopped almonds\n" +
                        "1/2 cup chopped pecans\n" +
                        "1/3 cup pepitas\n" +
                        "2 tablespoons flaxseed meal\n" +
                        "1 teaspoon cinnamon\n" +
                        "1 teaspoon sea salt\n" +
                        "1/2 cup melted coconut oil\n" +
                        "1/2 cup pure maple syrup\n" +
                        "1 tablespoon pure vanilla extract\n" +
                        "1 cup freeze-dried strawberries\n" +
                        "3/4 cup white chocolate chips");
                intent.putExtra("recipe", "1. Preheat oven to 325 degrees F. Line a large-rimmed baking sheet with parchment paper and set aside.\n\n" +
                        "2. In a large bowl, combine the oats, coconut, almonds, pecans, pepitas, flaxseed, cinnamon, and sea salt.\n\n" +
                        "3. Add the melted coconut oil, maple syrup, and vanilla extract. Stir until mixture is well combined.\n\n" +
                        "4. Transfer the mixture to the prepared baking sheet and spread in an even layer. Use a spatula or the bottom of a measuring cup to press the granola down in an even layer.\n\n" +
                        "5. Bake for 25 to 30 minutes or until granola is slightly golden brown. DO NOT STIR, but make sure you rotate the pan halfway through the baking time.\n\n" +
                        "6. Remove the pan from the oven and let the granola cool completely on the pan without stirring.\n\n" +
                        "7. When the granola is completely cooled, break into clusters and stir in the freeze-dried strawberries and white chocolate chips, if using. Enjoy!\n");
                startActivity(intent);
            }
        });

        ImageView imageView1_2 = findViewById(R.id.imageView1_2);
        imageView1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego intentu i przekazanie informacji do RecipeActivity
                Intent intent = new Intent(DietActivity.this, RecipeActivity.class);
                intent.putExtra("imageId", R.drawable.full_burrito); // Przykładowa informacja do przekazania
                intent.putExtra("mealName", "Breakfast Burrito");
                intent.putExtra("calories", "248");
                intent.putExtra("proteins", "20");
                intent.putExtra("carbs", "18");
                intent.putExtra("fat", "12");
                intent.putExtra("ingredients", "1/2 cup Milk Greek Plain Yogurt\n" +
                        "1/4 cup salsa\n" +
                        "1 tbsp fresh cilantro, chopped\n" +
                        "1/4 tsp garlic powder\n" +
                        "1/4 tsp cumin\n" +
                        "1/8 tsp table salt\n" +
                        "dash hot sauce, optional\n" +
                        "2 whole wheat flour tortillas\n" +
                        "4 large eggs\n" +
                        "1/4 tsp table salt\n" +
                        "1/8 tsp ground black pepper\n" +
                        "1 Tbsp olive oil");
                intent.putExtra("recipe","1. Stir together yogurt, salsa, cilantro, garlic powder, cumin, salt and hot sauce, if using, in a small bowl. Set aside.\n\n" +
                        "2. In another bowl, crack eggs and add salt and pepper. Whisk until frothy and pale yellow in color, about 30 seconds.\n\n" +
                        "3. In a nonstick pan, heat olive oil over medium-low. Add eggs, moving around the pan with a spatula as the edges start to set. Meanwhile, heat tortillas in a dry pan or directly over the flame of your burner. After about 1 minute, transfer eggs from pan to warmed tortillas.\n\n" +
                        "4.  Divide salsa mixture between tortillas and add toppings if you like, such as beans, cheese, avocado or shredded chicken. Turn in sides and roll up your breakfast burrito with creamy salsa.\n");
                startActivity(intent);
            }
        });

        ImageView imageView1_3 = findViewById(R.id.imageView1_3);
        imageView1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego intentu i przekazanie informacji do RecipeActivity
                Intent intent = new Intent(DietActivity.this, RecipeActivity.class);
                intent.putExtra("imageId", R.drawable.full_smoothie); // Przykładowa informacja do przekazania
                intent.putExtra("mealName", "Green Smoothie");
                intent.putExtra("calories", "75");
                intent.putExtra("proteins", "2");
                intent.putExtra("carbs", "17");
                intent.putExtra("fat", "1");
                intent.putExtra("ingredients", "1 cup orange juice\n" +
                        "1/2 cup yogurt, plain \n" +
                        "4 cups fresh baby spinach\n" +
                        "1 cup frozen pineapple chunks \n" +
                        "1 green apple\n" +
                        "2 bananas");
                intent.putExtra("recipe","1. Add orange juice and yogurt to a blender jar. Top with fresh baby spinach and blend until smooth.\n\n" +
                        "2. Add the pineapple, apple, and bananas and blend until smooth. Add more juice (or water) to thin out the smoothie to your preference.\n\n" +
                        "3. Serve immediately or store in an airtight container for up to a day to ensure maximum freshness, nutrition, and flavor.\n");
                startActivity(intent);
            }
        });

        ImageView imageView2_1 = findViewById(R.id.imageView2_1);
        imageView2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego intentu i przekazanie informacji do RecipeActivity
                Intent intent = new Intent(DietActivity.this, RecipeActivity.class);
                intent.putExtra("imageId", R.drawable.full_kurczak); // Przykładowa informacja do przekazania
                intent.putExtra("mealName", "Vietnamese Chicken");
                intent.putExtra("calories", "186");
                intent.putExtra("proteins", "12");
                intent.putExtra("carbs", "21");
                intent.putExtra("fat", "6");
                intent.putExtra("ingredients","1 double chicken fillet\n" +
                        "3 cloves of garlic\n" +
                        "2 medium onions\n" +
                        "1 spoon hot pepper\n" +
                        "1 spoon sweet pepper\n" +
                        "1 spoon curry\n" +
                        "30 ml soy sauce\n" +
                        "3 spoons sugar\n" +
                        "some olive oil\n" +
                        "1 pinch of salt and pepper");
                intent.putExtra("recipe","1. Cut the chicken into thick cubes, mix with spices and chopped garlic.\n\n" +
                        "2. Melt the sugar in a pan with a little olive oil to create caramel.\n\n" +
                        "3. Add the chicken and simmer until the entire chicken is covered with caramel. We can add some water.\n\n" +
                        "4. Add soy sauce, reduce the heat and simmer covered until the chicken slightly changes color to a darker color.\n\n" +
                        "5. Add the chopped onions.\n\n" +
                        "6. Serve with rice, salad or fries.\n");
                startActivity(intent);
            }
        });

        ImageView imageView2_2 = findViewById(R.id.imageView2_2);
        imageView2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego intentu i przekazanie informacji do RecipeActivity
                Intent intent = new Intent(DietActivity.this, RecipeActivity.class);
                intent.putExtra("imageId", R.drawable.full_pasta); // Przykładowa informacja do przekazania
                intent.putExtra("mealName", "Pasta with Vegetables");
                intent.putExtra("calories", "148");
                intent.putExtra("proteins", "4");
                intent.putExtra("carbs", "23");
                intent.putExtra("fat", "3");
                intent.putExtra("ingredients","10 oz. dry Barilla Penne Pasta\n" +
                        "1 pinch of salt\n" +
                        "1/4 cup olive oil\n" +
                        "1/2 medium red onion\n" +
                        "1 large carrot\n" +
                        "2 cups broccoli florets\n" +
                        "1 medium red bell pepper\n" +
                        "1 medium yellow squash\n" +
                        "1 medium zucchini\n" +
                        "3 cloves garlic cloves\n" +
                        "1 cup tomatoes\n" +
                        "2 tsp dried Italian seasoning\n" +
                        "1/2 cup pasta water\n" +
                        "2 Tbsp fresh lemon juice\n" +
                        "1/2 cup shredded parmesan\n" +
                        "2 Tbsp chopped fresh parsley");
                intent.putExtra("recipe","1. Bring a large pot of water to a boil. Cook penne pasta in salted water according to package directions, reserve 1/2 cup pasta water before draining.\n\n" +
                        "2. Meanwhile heat olive oil in a 12-inch skillet over medium-high heat.\n\n" +
                        "3. Add red onion carrot, broccoli + bell pepper and saute 2 minutes.\n\n" +
                        "4. Add squash and zucchini then saute 2 - 3 minutes or until veggies have nearly softened. \n\n" +
                        "5. Add garlic, tomatoes, and Italian seasoning and saute 2 minutes longer.\n\n" +
                        "6. Pour veggies into now empty pasta pot or a serving bowl, add drained pasta, drizzle in lemon juice, season with a little more salt as needed and toss while adding in pasta water to loosen as desired.\n\n" +
                        "7. Toss in 1/4 cup parmesan and parsley then serve with remaining parmesan on top.\n");
                startActivity(intent);
            }
        });

        ImageView imageView2_3 = findViewById(R.id.imageView2_3);
        imageView2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego intentu i przekazanie informacji do RecipeActivity
                Intent intent = new Intent(DietActivity.this, RecipeActivity.class);
                intent.putExtra("imageId", R.drawable.full_pancakes); // Przykładowa informacja do przekazania
                intent.putExtra("mealName", "Pancakes with Fruits");
                intent.putExtra("calories", "210");
                intent.putExtra("proteins", "6");
                intent.putExtra("carbs", "26");
                intent.putExtra("fat", "9");
                intent.putExtra("ingredients","2 eggs,\n" +
                        "1 and 1/2 buttermilk,\n" +
                        "2 cups of wheat flour,\n" +
                        "50 g melted butter,\n" +
                        "4 tablespoons of powdered sugar,\n" +
                        "1 pinch of salt,\n" +
                        "2 spoons of baking powder,\n" +
                        "Serving additions: fruit, sprinkles, maple syrup.");
                intent.putExtra("recipe","1. Combine the dry ingredients in a bowl - sift flour, salt, baking powder and sugar. Mix.\n\n" +
                        "2. Beat the eggs, combine them with melted butter and milk.\n\n" +
                        "3. Combine the dry ingredients with the egg mixture.\n\n" +
                        "4. Mix or blend until combined.\n\n" +
                        "5. Fry in a dry frying pan until golden on both sides.\n\n" +
                        "6. Serve pancakes with your favorite additions: peanut butter, fresh fruit, maple syrup or whipped cream.\n");
                startActivity(intent);
            }
        });

        ImageView imageView3_1 = findViewById(R.id.imageView3_1);
        imageView3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego intentu i przekazanie informacji do RecipeActivity
                Intent intent = new Intent(DietActivity.this, RecipeActivity.class);
                intent.putExtra("imageId", R.drawable.full_airfryer); // Przykładowa informacja do przekazania
                intent.putExtra("mealName", "Air-Fryer Sweet Potato");
                intent.putExtra("calories", "87");
                intent.putExtra("proteins", "2");
                intent.putExtra("carbs", "10");
                intent.putExtra("fat", "5");
                intent.putExtra("ingredients","2 sweet potatoes\n" +
                        "1 tbsp olive oil, plus 2 tsp\n" +
                        "1 small garlic bulb\n" +
                        "150g Greek yogurt\n" +
                        "3 tbsp tahini\n" +
                        "1 lemon, zested and juiced\n" +
                        "1 tbsp maple syrup\n" +
                        "5g fresh curry leaves\n" +
                        "small handful of coriander, finely chopped\n" +
                        "1 pinch of chilli flakes");
                intent.putExtra("recipe","1. Heat air-fryer to 200C. Pierce a few holes in the sweet potatoes using a sharp knife, then rub with 1 tbsp of the olive oil and sprinkle with salt and freshly ground black pepper.\n\n" +
                        "2. Drizzle 1 tsp of the oil over the garlic bulb, sprinkle with salt and pepper, then wrap in foil. Put both the garlic and sweet potatoes in the air-fryer and cook for 35 mins.\n\n" +
                        "3. Remove the garlic and set aside to cool slightly, then put the sweet potatoes back in for 15 mins until a cutlery knife goes through the centre with no resistance.\n\n" +
                        "4. Meanwhile, put the Greek yogurt in a small bowl and mix with the tahini, lemon zest and juice, maple syrup and some salt and pepper. When the garlic has cooled, squeeze the cloves into the tahini and yogurt mixture and stir well.\n\n" +
                        "5. Put the curry leaves, 1 tsp olive oil and a pinch of salt in a small bowl. Massage with your hands so they’re well coated. Put the curry leaves in the air fryer and place a small, ovenproof plate on top. Cook for 5-6 mins at 200C until crispy.\n\n" +
                        "6. Split the sweet potatoes open and fluff up the flesh slightly with a fork. Spoon the yogurt mixture over the sweet potatoes, then top with the crisp curry leaves, chopped coriander and chilli flakes.\n");
                startActivity(intent);
            }
        });

        ImageView imageView3_2 = findViewById(R.id.imageView3_2);
        imageView3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego intentu i przekazanie informacji do RecipeActivity
                Intent intent = new Intent(DietActivity.this, RecipeActivity.class);
                intent.putExtra("imageId", R.drawable.full_steakcajun); // Przykładowa informacja do przekazania
                intent.putExtra("mealName", "Steak with Cajun & Potatoes");
                intent.putExtra("calories", "122");
                intent.putExtra("proteins", "8");
                intent.putExtra("carbs", "6");
                intent.putExtra("fat", "8");
                intent.putExtra("ingredients","430g Maris Piper potatoes\n" +
                        "1 tbsp Cajun seasoning\n" +
                        "2 tbsp olive oil\n" +
                        "400g bavette steak\n" +
                        "1/4 small bunch of coriander\n" +
                        "For the spicy slaw\n" +
                        "1/4 small red cabbage\n" +
                        "1/2 red onion\n" +
                        "2 tbsp mayonnaise\n" +
                        "1 tbsp soured cream\n" +
                        "1/2 tbsp chipotle chilli paste");
                intent.putExtra("recipe","1. Heat the oven to 220C/200C fan/gas 7. Bring a large pan of salted water to the boil, add the potatoes and cook for 5 mins. Drain and leave to steam-dry in the colander for a few minutes.\n\n" +
                        "2. Toss the potatoes with the Cajun seasoning, half the oil and a good pinch of salt. Tip onto a baking tray and roast for 25-30 mins or until golden and crisp, tossing halfway through.\n\n" +
                        "3. To make the slaw, combine the cabbage, onion, mayonnaise, soured cream and chipotle paste in a bowl until the cabbage and onion are well coated. Set aside.\n\n" +
                        "4. Rub the steak with the remaining oil and season both sides. Heat a griddle pan or non-stick frying pan over a high heat until very hot.\n\n" +
                        "5. Add the steak to the pan, cook for 2 mins on one side, then 3 mins on the other. Remove, put on a chopping board, cover with foil and leave to rest for 5 mins. Slice into strips.\n\n" +
                        "6. Serve the steak with the potatoes scattered with the coriander and a pile of the spicy slaw.\n");
                startActivity(intent);
            }
        });

        ImageView imageView3_3 = findViewById(R.id.imageView3_3);
        imageView3_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego intentu i przekazanie informacji do RecipeActivity
                Intent intent = new Intent(DietActivity.this, RecipeActivity.class);
                intent.putExtra("imageId", R.drawable.full_fishpie); // Przykładowa informacja do przekazania
                intent.putExtra("mealName", "Cheesy Fish Pie");
                intent.putExtra("calories", "76");
                intent.putExtra("proteins", "6");
                intent.putExtra("carbs", "4");
                intent.putExtra("fat", "5");
                intent.putExtra("ingredients","200g Maris Piper potatoes\n" +
                        "1 tbsp butter\n" +
                        "1 tbsp flour\n" +
                        "200ml whole milk\n" +
                        "1/2 tsp Dijon mustard\n" +
                        "100g cheddar cheese\n" +
                        "200g fish pie mix \n" +
                        "50g frozen peas\n" +
                        "50g shredded kale\n" +
                        "1 spring onion\n" +
                        "1 tbsp parmesan\n");
                intent.putExtra("recipe","1. Tip the potatoes into a pan, cover with hot water from the kettle and add a pinch of salt. Simmer for 15-20 mins until tender, then drain and leave to steam-dry.\n\n" +
                        "2. Meanwhile, warm the butter in a heavy-based pan over a low heat. Add the flour and stir until you have a paste. Gradually whisk in 200ml milk until you have a thick white sauce. Simmer for 3-4 mins, stirring.\n\n" +
                        "3. Season, then stir in the mustard and half the cheese, and continue to stir until the cheese has melted. Fold in the fish, peas and kale, and cook for 8 mins more, or until the kale wilts, the fish is just cooked and the prawns are starting to turn pink.\n\n" +
                        "4. Heat the grill to medium-high. Mash the potatoes with the spring onion and 1 tbsp milk. Fold in the remaining cheese and season well.\n\n" +
                        "5. Tip the filling into a heatproof dish and spoon the cheesy mash on top, swirling it with a fork until the filling is completely covered. Sprinkle over the parmesan and grill for 10-15 mins until the pie is golden and bubbling.\n");
                startActivity(intent);
            }
        });



        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        // Otwieranie HomeActivity
                        startActivity(new Intent(DietActivity.this, HomeActivity.class));
                        return true;
                    case R.id.diet:
                        // Otwieranie DietActivity
                        startActivity(new Intent(DietActivity.this, DietActivity.class));
                        return true;
                    case R.id.trainingplan:
                        // Otwieranie GymActivity
                        startActivity(new Intent(DietActivity.this, GymActivity.class));
                        return true;
                    case R.id.sleep:
                        // Otwieranie SleepActivity
                        startActivity(new Intent(DietActivity.this, SleepActivity.class));
                        return true;
                    default:
                        return false;
                }
            }
        });



    }

}