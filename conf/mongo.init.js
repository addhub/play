/**
 * Created by sasinda on 10/1/15.
 */

//use addhub

db.category.insert({
                     "name": "Vehicle",
                     "collection":"ad_",
                     "subcats": [
                       "Automotive Items & Parts",
                       "Boats & Watercraft",
                       "Cars",
                       "Classic Cars",
                       "Commercial Trucks & Tractor Trailers",
                       "Off Road Vehicles",
                       "RV & Motorhomes",
                       "SUVs",
                       "Utility & Work Trailers",
                       "Vans"
                     ]
                   });
db.category.insert({
                     "name": "Services",
                     "subcats": [
                       "Automotive Services",
                       "Beauty & Salon Services",
                       "Caregivers & Baby Sitting",
                       "Cleaning Services",
                       "Construction & Remodeling",
                       "Financial Services",
                       "Health & Wellness",
                       "Home Services",
                       "Insurance",
                       "Office Services",
                       "Real Estate Services"
                     ]
                   });
db.category.insert({
                     "name": "Pets",
                     "subcats": [
                       "Birds",
                       "Cats",
                       "Dogs",
                       "Fish & Reptile Pets",
                       "Free Pets to Good Home",
                       "Horses",
                       "Pet Supplies"
                     ]
                   });





db.Vehicle.insert({
    "_id" : ObjectId("560f0841e746b7031b037829"),
    "title" : "Title",
    "category":"Vehicle",
    "subCat" : "Cars",
    "description" : "Gallant",
    "keywords" : ["Gallant","Diesel"],
    "address" : "309 West 30th Street",
    "state" : "NY",
    "country" : "USA",
    "zipcode" : "10001",
    "price" : 100000,
    "agree" : true
});