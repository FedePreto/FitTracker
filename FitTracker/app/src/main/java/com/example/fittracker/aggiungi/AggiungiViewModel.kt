package com.example.fittracker.aggiungi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fittracker.model.Json_Parsing.Json_FoodList
import com.example.fittracker.model.Json_Parsing.Json_Hint
import com.example.fittracker.model.Prodotto
import com.example.fittracker.retrofit.ApiInterface
import com.example.fittracker.retrofit.RetrofitInstance
import com.example.fittracker.utils.APICredentials
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class AggiungiViewModel : ViewModel() {

    private var _foodLiveData = MutableLiveData<List<Json_Hint>>()

    val foodLiveData : LiveData<List<Json_Hint>>
        get() = _foodLiveData
    fun getFoodFromNameorUPC(ingr : String, upc: String) {
        RetrofitInstance.api.getFoodFromNameOrUPC(APICredentials.API_ID,APICredentials.API_KEY,ingr,upc)
            .enqueue(object : Callback<Json_FoodList> {
                override fun onResponse(call: Call<Json_FoodList>, response: Response<Json_FoodList>) {
                    if (response.body() != null) {
                        _foodLiveData.value = response.body()!!.hints!!
                    } else {
                        return
                    }
                }


                override fun onFailure(call: Call<Json_FoodList>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }
            })
    }


    fun jsonToClass(){
        val foodJson = """{
  "text": "banana",
  "parsed": [
    {
      "food": {
        "foodId": "food_bjsfxtcaidvmhaa3afrbna43q3hu",
        "label": "Banana",
        "knownAs": "bananas",
        "nutrients": {
          "ENERC_KCAL": 89,
          "PROCNT": 1.09,
          "FAT": 0.33,
          "CHOCDF": 22.84,
          "FIBTG": 2.6
        },
        "category": "Generic foods",
        "categoryLabel": "food",
        "image": "https://www.edamam.com/food-img/9f6/9f6181163a25c96022ee3fc66d9ebb11.jpg"
      }
    }
  ],
  "hints": [
    {
      "food": {
        "foodId": "food_bjsfxtcaidvmhaa3afrbna43q3hu",
        "label": "Banana",
        "knownAs": "bananas",
        "nutrients": {
          "ENERC_KCAL": 89,
          "PROCNT": 1.09,
          "FAT": 0.33,
          "CHOCDF": 22.84,
          "FIBTG": 2.6
        },
        "category": "Generic foods",
        "categoryLabel": "food",
        "image": "https://www.edamam.com/food-img/9f6/9f6181163a25c96022ee3fc66d9ebb11.jpg"
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_unit",
          "label": "Whole",
          "weight": 115.7,
          "qualified": [
            {
              "qualifiers": [
                {
                  "uri": "http://www.edamam.com/ontologies/edamam.owl#Qualifier_medium",
                  "label": "medium"
                }
              ],
              "weight": 118
            },
            {
              "qualifiers": [
                {
                  "uri": "http://www.edamam.com/ontologies/edamam.owl#Qualifier_large",
                  "label": "large"
                }
              ],
              "weight": 136
            },
            {
              "qualifiers": [
                {
                  "uri": "http://www.edamam.com/ontologies/edamam.owl#Qualifier_small",
                  "label": "small"
                }
              ],
              "weight": 101
            },
            {
              "qualifiers": [
                {
                  "uri": "http://www.edamam.com/ontologies/edamam.owl#Qualifier_extra_large",
                  "label": "extra large"
                }
              ],
              "weight": 152
            }
          ]
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
          "label": "Serving",
          "weight": 126
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_cup",
          "label": "Cup",
          "weight": 225,
          "qualified": [
            {
              "qualifiers": [
                {
                  "uri": "http://www.edamam.com/ontologies/edamam.owl#Qualifier_sliced",
                  "label": "sliced"
                }
              ],
              "weight": 150
            },
            {
              "qualifiers": [
                {
                  "uri": "http://www.edamam.com/ontologies/edamam.owl#Qualifier_mashed",
                  "label": "mashed"
                }
              ],
              "weight": 225
            }
          ]
        }
      ]
    },
    {
      "food": {
        "foodId": "food_apcxoi8abwi00ka8h3sbaa6woutg",
        "label": "Ella's Kitchen Bananas, Bananas, Bananas, Super Smooth Banana Puree Bananas",
        "knownAs": "Ella's Kitchen Bananas, Bananas, Bananas, Super Smooth Banana Puree Bananas",
        "nutrients": {
          "ENERC_KCAL": 91.71230106890907,
          "PROCNT": 1.4109584779832165,
          "FAT": 0,
          "CHOCDF": 22.575335647731464,
          "FIBTG": 2.821916955966433
        },
        "brand": "Ella's Kitchen",
        "category": "Packaged foods",
        "categoryLabel": "food",
        "foodContentsLabel": "ORGANIC BANANAS; <1% ORGANIC LEMON JUICE CONCENTRATE.",
        "image": "https://www.edamam.com/food-img/36a/36a899bb7eb11fa5b00bfd7a66f70ea4.png",
        "servingSizes": [
          {
            "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
            "label": "Gram",
            "quantity": 70
          },
          {
            "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
            "label": "Ounce",
            "quantity": 2.5
          }
        ],
        "servingsPerContainer": 1
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
          "label": "Serving",
          "weight": 70.8738078125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_package",
          "label": "Package",
          "weight": 70.8738078125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        }
      ]
    },
    {
      "food": {
        "foodId": "food_a3t5d1ubn0wxuxaddsz4oasllcdd",
        "label": "Bananas, Dehydrated, or Banana Powder",
        "knownAs": "Bananas, dehydrated, or banana powder",
        "nutrients": {
          "ENERC_KCAL": 346,
          "PROCNT": 3.89,
          "FAT": 1.81,
          "CHOCDF": 88.28,
          "FIBTG": 9.9
        },
        "category": "Generic foods",
        "categoryLabel": "food",
        "image": "https://www.edamam.com/food-img/6bc/6bcf87ba7f4f162b0d257d041d69af34.jpg"
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_tablespoon",
          "label": "Tablespoon",
          "weight": 6.2
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_cup",
          "label": "Cup",
          "weight": 100
        }
      ]
    },
    {
      "food": {
        "foodId": "food_b0f04q6aeh66mga59vdbybcjl22r",
        "label": "Banana",
        "knownAs": "Banana",
        "nutrients": {
          "ENERC_KCAL": 244.34832887665286,
          "PROCNT": 4.559631624176315,
          "FAT": 10.086427424552642,
          "CHOCDF": 35.99461668642338,
          "FIBTG": 2.8502231320038685
        },
        "category": "Generic meals",
        "categoryLabel": "meal",
        "foodContentsLabel": "Nilla Wafer; butter; bananas; cake flour; baking soda; baking powder; salt; sugar; eggs; sour cream; whole milk; egg yolks; vanilla bean; flour; Chantilly Cream; heavy cream; raw sugar; coconut"
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_unit",
          "label": "Whole",
          "weight": 113.57773222579912
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
          "label": "Serving",
          "weight": 113.57773222579912
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_milliliter",
          "label": "Milliliter",
          "weight": 1
        }
      ]
    },
    {
      "food": {
        "foodId": "food_a9qgwwdanptdeuao8lhj0b7okmiq",
        "label": "Banana Split Banana Bread",
        "knownAs": "Banana Split Banana Bread",
        "nutrients": {
          "ENERC_KCAL": 277.13990536454895,
          "PROCNT": 4.085695403864344,
          "FAT": 11.18901642901284,
          "CHOCDF": 42.20881726822129,
          "FIBTG": 2.220554744213344
        },
        "category": "Generic meals",
        "categoryLabel": "meal",
        "foodContentsLabel": "butter; sugar; brown sugar; eggs; bananas; pineapple; maraschino cherries; flour; baking powder; salt; almonds; chocolate chips"
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_unit",
          "label": "Whole",
          "weight": 101.98125065242746
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
          "label": "Serving",
          "weight": 101.98125065242746
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_milliliter",
          "label": "Milliliter",
          "weight": 1
        }
      ]
    },
    {
      "food": {
        "foodId": "food_bttzfrabvyh8cmablexptamzphza",
        "label": "Banana Bread (Banana Cake)",
        "knownAs": "Banana Bread (banana Cake)",
        "nutrients": {
          "ENERC_KCAL": 255.03496206540842,
          "PROCNT": 4.764133617718374,
          "FAT": 8.083024179266562,
          "CHOCDF": 42.749296687803835,
          "FIBTG": 2.2450758391246914
        },
        "category": "Generic meals",
        "categoryLabel": "meal",
        "foodContentsLabel": "flour; baking powder; baking soda; salt; ground cinnamon; sugar; butter, (salted; egg white; egg; bananas; sour cream; vanilla extract; cocoa nibs"
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_unit",
          "label": "Whole",
          "weight": 289.93009053274363
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
          "label": "Serving",
          "weight": 289.93009053274363
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        }
      ]
    },
    {
      "food": {
        "foodId": "food_b0bn6w4ab49t55b1o8jsnbq6nm2g",
        "label": "Bananas, Raw",
        "knownAs": "Bananas, raw",
        "nutrients": {
          "ENERC_KCAL": 89,
          "PROCNT": 1.09,
          "FAT": 0.33,
          "CHOCDF": 22.84,
          "FIBTG": 2.6
        },
        "category": "Generic foods",
        "categoryLabel": "food",
        "image": "https://www.edamam.com/food-img/9f6/9f6181163a25c96022ee3fc66d9ebb11.jpg"
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_unit",
          "label": "Whole",
          "weight": 115.7,
          "qualified": [
            {
              "qualifiers": [
                {
                  "uri": "http://www.edamam.com/ontologies/edamam.owl#Qualifier_medium",
                  "label": "medium"
                }
              ],
              "weight": 118
            },
            {
              "qualifiers": [
                {
                  "uri": "http://www.edamam.com/ontologies/edamam.owl#Qualifier_large",
                  "label": "large"
                }
              ],
              "weight": 136
            },
            {
              "qualifiers": [
                {
                  "uri": "http://www.edamam.com/ontologies/edamam.owl#Qualifier_small",
                  "label": "small"
                }
              ],
              "weight": 101
            },
            {
              "qualifiers": [
                {
                  "uri": "http://www.edamam.com/ontologies/edamam.owl#Qualifier_extra_large",
                  "label": "extra large"
                }
              ],
              "weight": 152
            }
          ]
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_cup",
          "label": "Cup",
          "weight": 225,
          "qualified": [
            {
              "qualifiers": [
                {
                  "uri": "http://www.edamam.com/ontologies/edamam.owl#Qualifier_sliced",
                  "label": "sliced"
                }
              ],
              "weight": 150
            },
            {
              "qualifiers": [
                {
                  "uri": "http://www.edamam.com/ontologies/edamam.owl#Qualifier_mashed",
                  "label": "mashed"
                }
              ],
              "weight": 225
            }
          ]
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
          "label": "Serving",
          "weight": 126
        }
      ]
    },
    {
      "food": {
        "foodId": "food_bu5d976agc6pb0ac8b12bbd6m01l",
        "label": "Banana Candy, Banana",
        "knownAs": "BANANA CANDY, BANANA",
        "nutrients": {
          "ENERC_KCAL": 419,
          "PROCNT": 0,
          "FAT": 8.140000343322754,
          "CHOCDF": 83.72000122070312
        },
        "brand": "DULCE",
        "category": "Packaged foods",
        "categoryLabel": "food",
        "foodContentsLabel": "CORN SYRUP; HYDROGENATED COCONUT OIL; 2% OR LESS OF EGG WHITES; SODIUM ALGINATE; NATURAL FLAVORS; CALCIUM ACETATE; MONOGLYCERIDES; YELLOW 5.",
        "image": "https://www.edamam.com/food-img/47a/47ab151c9680f3990f920793acdfb7dd.jpg",
        "servingSizes": [
          {
            "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
            "label": "Gram",
            "quantity": 43
          }
        ],
        "servingsPerContainer": 1
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
          "label": "Serving",
          "weight": 43
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_bar",
          "label": "Bar",
          "weight": 43
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_package",
          "label": "Package",
          "weight": 43
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        }
      ]
    },
    {
      "food": {
        "foodId": "food_ate5r4tbsw6lhfarjwslfaeuoh95",
        "label": "Banana",
        "knownAs": "Banana",
        "nutrients": {
          "ENERC_KCAL": 89,
          "PROCNT": 1.100000023841858,
          "FAT": 0.30000001192092896,
          "CHOCDF": 23,
          "FIBTG": 2.5999999046325684
        },
        "brand": "Dole Packaged Foods, LLC",
        "category": "Packaged foods",
        "categoryLabel": "food",
        "foodContentsLabel": "Bananas",
        "image": "https://www.edamam.com/food-img/2d8/2d8413480692de9716bff261a2fe0ad7.jpg",
        "servingSizes": [
          {
            "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_fruit",
            "label": "Fruit",
            "quantity": 1
          },
          {
            "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
            "label": "Gram",
            "quantity": 100
          }
        ],
        "servingsPerContainer": 1
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
          "label": "Serving",
          "weight": 100
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_fruit",
          "label": "Fruit",
          "weight": 100
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_package",
          "label": "Package",
          "weight": 100
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        }
      ]
    },
    {
      "food": {
        "foodId": "food_aah9x3ka3e3n5ca0785s1ae7b70m",
        "label": "Organic Banana Chips, Banana",
        "knownAs": "ORGANIC BANANA CHIPS, BANANA",
        "nutrients": {
          "ENERC_KCAL": 525,
          "PROCNT": 2.5,
          "FAT": 27.5,
          "CHOCDF": 65,
          "FIBTG": 5
        },
        "brand": "ORGANIC",
        "category": "Packaged foods",
        "categoryLabel": "food",
        "foodContentsLabel": "ORGANIC BANANAS; ORGANIC COCONUT OIL; ORGANIC CANE SUGAR.",
        "servingSizes": [
          {
            "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_piece",
            "label": "Piece",
            "quantity": 18
          },
          {
            "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
            "label": "Gram",
            "quantity": 40
          }
        ]
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
          "label": "Serving",
          "weight": 40
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_piece",
          "label": "Piece",
          "weight": 2.2222222222222223
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        }
      ]
    },
    {
      "food": {
        "foodId": "food_b84qhlzbsrp5hwboxk9gjag8w92b",
        "label": "Margie's, Banana Syrup, Banana",
        "knownAs": "MARGIE'S, BANANA SYRUP, BANANA",
        "nutrients": {
          "ENERC_KCAL": 203,
          "PROCNT": 1.350000023841858,
          "FAT": 0,
          "CHOCDF": 47.29999923706055,
          "FIBTG": 1.399999976158142
        },
        "brand": "Margie's",
        "category": "Packaged foods",
        "categoryLabel": "food",
        "foodContentsLabel": "WHITE GRAPE JUICE CONCENTRATE; BANANA PUREE; XANTHAN GUM (THICKENER).",
        "image": "https://www.edamam.com/food-img/31a/31a9e703aafa5f40855d642fb15eaa82.jpg",
        "servingSizes": [
          {
            "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_cup",
            "label": "Cup",
            "quantity": 0.25
          },
          {
            "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
            "label": "Gram",
            "quantity": 74
          }
        ]
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
          "label": "Serving",
          "weight": 74
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_cup",
          "label": "Cup",
          "weight": 296
        }
      ]
    },
    {
      "food": {
        "foodId": "food_bcqoaitbngftxsazs8y1sbfa7ppf",
        "label": "Banana Mega Muffin, Banana",
        "knownAs": "BANANA MEGA MUFFIN, BANANA",
        "nutrients": {
          "ENERC_KCAL": 340,
          "PROCNT": 4.489999771118164,
          "FAT": 16.030000686645508,
          "CHOCDF": 44.22999954223633
        },
        "brand": "MEGA",
        "category": "Packaged foods",
        "categoryLabel": "food",
        "foodContentsLabel": "ENRICHED FLOUR (BLEACHED WHOLE WHEAT FLOUR; WHEAT FLOUR; NIACIN; FERROUS SULFATE; REDUCED IRON; THIAMINE MONONITRATE; RIBOFLAVIN; FOLIC ACID); EGG; SUGAR; VEGETABLE OIL (SOYBEAN; CANOLA); BANANA PUREE; WATER; CORN SYRUP; GLYCERIN; MODIFIED CORNSTARCH; WHEY; SODIUM ALUMINUM PHOSPHATE; SALT; BAKING SODA; PROPYLENE GLYCOL ESTERS OF FATTY ACIDS; XANTHAN GUM; MONO AND DIGLYCERIDES; SODIUM STEAROYL LACTYLATE; CELLULOSE GUM; DATEM; NATURAL AND ARTIFICIAL FLAVOR; SORBIC ACID AND POTASSIUM SORBATE (TO RETAIN FRESHNESS); ENZYMES.",
        "servingSizes": [
          {
            "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
            "label": "Gram",
            "quantity": 156
          },
          {
            "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_muffin",
            "label": "Muffin",
            "quantity": 1
          }
        ]
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
          "label": "Serving",
          "weight": 156
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_muffin",
          "label": "Muffin",
          "weight": 156
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        }
      ]
    },
    {
      "food": {
        "foodId": "food_azds4vwbq8u4boave9i3baksdmpd",
        "label": "Banana Pepper",
        "knownAs": "banana pepper",
        "nutrients": {
          "ENERC_KCAL": 27,
          "PROCNT": 1.66,
          "FAT": 0.45,
          "CHOCDF": 5.35,
          "FIBTG": 3.4
        },
        "category": "Generic foods",
        "categoryLabel": "food",
        "image": "https://www.edamam.com/food-img/9be/9bee191c9c786375955831608ca8dbe1.jpg"
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_unit",
          "label": "Whole",
          "weight": 46,
          "qualified": [
            {
              "qualifiers": [
                {
                  "uri": "http://www.edamam.com/ontologies/edamam.owl#Qualifier_medium",
                  "label": "medium"
                }
              ],
              "weight": 46
            },
            {
              "qualifiers": [
                {
                  "uri": "http://www.edamam.com/ontologies/edamam.owl#Qualifier_large",
                  "label": "large"
                }
              ],
              "weight": 75
            },
            {
              "qualifiers": [
                {
                  "uri": "http://www.edamam.com/ontologies/edamam.owl#Qualifier_small",
                  "label": "small"
                }
              ],
              "weight": 33
            }
          ]
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pepper",
          "label": "Pepper",
          "weight": 46
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_cup",
          "label": "Cup",
          "weight": 124
        }
      ]
    },
    {
      "food": {
        "foodId": "food_bfn2tbfbzut6x6bqtbxykblga3s5",
        "label": "Banana Caramel",
        "knownAs": "Banana Caramel",
        "nutrients": {
          "ENERC_KCAL": 168.84213477637914,
          "PROCNT": 0.9837796459511343,
          "FAT": 4.150378931614931,
          "CHOCDF": 34.058626638667405,
          "FIBTG": 2.403007700785947
        },
        "category": "Generic meals",
        "categoryLabel": "meal",
        "foodContentsLabel": "bananas; butter; sugar"
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_unit",
          "label": "Whole",
          "weight": 115.38724094839158
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
          "label": "Serving",
          "weight": 115.38724094839158
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_milliliter",
          "label": "Milliliter",
          "weight": 1
        }
      ]
    },
    {
      "food": {
        "foodId": "food_ahjo4saautqcfdaqamiaxbkjw8sb",
        "label": "Banana Toast",
        "knownAs": "Banana Toast",
        "nutrients": {
          "ENERC_KCAL": 175.79710175901508,
          "PROCNT": 4.697322499422595,
          "FAT": 5.091825074407549,
          "CHOCDF": 29.692327546662487,
          "FIBTG": 3.0156693945837234
        },
        "category": "Generic meals",
        "categoryLabel": "meal",
        "foodContentsLabel": "Bread; Butter; Banana"
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_unit",
          "label": "Whole",
          "weight": 108.6031680358999
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
          "label": "Serving",
          "weight": 108.6031680358999
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_milliliter",
          "label": "Milliliter",
          "weight": 1
        }
      ]
    },
    {
      "food": {
        "foodId": "food_a9mhph0akn8yk5ayay1a8b4jf8b5",
        "label": "Banana Bites",
        "knownAs": "Banana Bites",
        "nutrients": {
          "ENERC_KCAL": 219.14000015396502,
          "PROCNT": 5.632786779254334,
          "FAT": 8.533866853060061,
          "CHOCDF": 33.779708617299654,
          "FIBTG": 4.388929535155798
        },
        "category": "Generic meals",
        "categoryLabel": "meal",
        "foodContentsLabel": "bananas; yogurt; corn; granola"
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_unit",
          "label": "Whole",
          "weight": 89.99706907451649
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
          "label": "Serving",
          "weight": 89.99706907451649
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_milliliter",
          "label": "Milliliter",
          "weight": 1
        }
      ]
    },
    {
      "food": {
        "foodId": "food_bhh5cqrbu64uh5b4vzugvbbnle2s",
        "label": "Banana Halwa",
        "knownAs": "Banana Halwa",
        "nutrients": {
          "ENERC_KCAL": 195.3473274503074,
          "PROCNT": 1.3234351799525959,
          "FAT": 8.056491133915285,
          "CHOCDF": 31.9873512592438,
          "FIBTG": 1.9083805095432305
        },
        "category": "Generic meals",
        "categoryLabel": "meal",
        "foodContentsLabel": "bananas; sugar; ghee; cashew nuts"
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_unit",
          "label": "Whole",
          "weight": 173.31511574074275
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
          "label": "Serving",
          "weight": 173.31511574074275
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_milliliter",
          "label": "Milliliter",
          "weight": 1
        }
      ]
    },
    {
      "food": {
        "foodId": "food_bxs5wk6ajfjyrqafu5t3kafzidk7",
        "label": "Banana Milkshake",
        "knownAs": "Banana Milkshake",
        "nutrients": {
          "ENERC_KCAL": 84.94922268494328,
          "PROCNT": 2.025611567355028,
          "FAT": 2.9918445359357415,
          "CHOCDF": 13.321756311888485,
          "FIBTG": 1.1295938347957895
        },
        "category": "Generic meals",
        "categoryLabel": "meal",
        "foodContentsLabel": "Bananas; Milk; Vanilla bean; Honey"
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_unit",
          "label": "Whole",
          "weight": 278.03127843990103
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
          "label": "Serving",
          "weight": 278.03127843990103
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_cup",
          "label": "Cup",
          "weight": 240
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_milliliter",
          "label": "Milliliter",
          "weight": 1
        }
      ]
    },
    {
      "food": {
        "foodId": "food_anq0rwta25sg5zbh16ot4bcdzlcw",
        "label": "Banana Popsicles",
        "knownAs": "Banana Popsicles",
        "nutrients": {
          "ENERC_KCAL": 140.78959352247662,
          "PROCNT": 2.784545309561987,
          "FAT": 4.046604093744239,
          "CHOCDF": 21.92639080722267,
          "FIBTG": 1.3846097022610009
        },
        "category": "Generic meals",
        "categoryLabel": "meal",
        "foodContentsLabel": "pudding mix; skim milk; banana"
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_unit",
          "label": "Whole",
          "weight": 196.3917584147633
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
          "label": "Serving",
          "weight": 196.3917584147633
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_milliliter",
          "label": "Milliliter",
          "weight": 1
        }
      ]
    },
    {
      "food": {
        "foodId": "food_b6ijfu4afjb6fsad6ui0ybcehtqq",
        "label": "Banana Shake",
        "knownAs": "Banana Shake",
        "nutrients": {
          "ENERC_KCAL": 85.5779035829934,
          "PROCNT": 2.348410177162223,
          "FAT": 3.0032924905644123,
          "CHOCDF": 12.280002770502248,
          "FIBTG": 0.8501172817723754
        },
        "category": "Generic meals",
        "categoryLabel": "meal",
        "foodContentsLabel": "Banana; Sugar; Elaichi; Milk; Dalchini"
      },
      "measures": [
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_unit",
          "label": "Whole",
          "weight": 296.4202176278554
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
          "label": "Serving",
          "weight": 296.4202176278554
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
          "label": "Gram",
          "weight": 1
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
          "label": "Ounce",
          "weight": 28.349523125
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
          "label": "Pound",
          "weight": 453.59237
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
          "label": "Kilogram",
          "weight": 1000
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_cup",
          "label": "Cup",
          "weight": 240
        },
        {
          "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_milliliter",
          "label": "Milliliter",
          "weight": 1
        }
      ]
    }
  ],
  "_links": {
    "next": {
      "title": "Next page",
      "href": "https://api.edamam.com/api/food-database/v2/parser?session=40&app_id=a4e62224&app_key=2b0adbda47d1293d35d373d9f2ffcee1&ingr=banana&nutrition-type=cooking"
    }
  }
}"""
        val moshi = Moshi.Builder().build()
        val foodAdapter : JsonAdapter<Json_FoodList> = moshi.adapter(Json_FoodList::class.java)
        val food = foodAdapter.fromJson(foodJson)
        Log.e("Food", food!!.toString())
    }



}